package com.example.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spitters.Spitter;
import com.example.spitters.SpitterRepository;

//This starts Example 4: input via Forms

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	@Autowired
	private SpitterRepository spitterRepository;
	
	//We created a form.jsp file under views/spitter
	//This is a simple @RequestMapping method that maps the "http://URL/WebTest/spitter/register" GET request to the registration form view .jsp file
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm() {
		return "spitter/form";
	}
	
	//Before proceeding, check out the form.jsp file
	//For the purpose of this Example, classes in the com.default.spitters have been added
	//The idea is to submit input data via a form aka a HTTP POST mehtod
	
	//The form.jsp will take input from the user and send it. This Controller method will catch it. Notice that an input parameter is a Spitter. Unlike the GET method, when dealing with the POST method, values recieved
	//from the HTTP POST will be mapped onto the input parameter as best as possible
	//In this case, the input values from the form.jsp are "firstName", "lastName", "username" and "password"
	//These will be mapped to the Spitter, since Spitter has private parameters WITH THE EXACT SAME NAME, CASE SENSITIVE. This is how mapping is performed
	@RequestMapping(value="/register", method=RequestMethod.POST)
	//Aside from providing the proper @RequestMapper for the POST method, we're also using Java Validation API to make sure that the user input is valid
	//This is done with the @Valid annotation in the input and the Errors interface. If the input for the Spitter is not valid, errors will be logged in the Errors object
	//Check the Spitter class to see which validations must be met
	//Note: This shit wasn't working utill we added the hybernate-validator dependency into pom.xml. Of course, this shit wasn't even mentioned in the Spring into Action 4
	public String processRegistration(@Valid Spitter spitter, Errors errors) {
		
		//If any errors have been registered, return the value for the input form to the user
		//Basically, ask him again to input data
		if(errors.hasErrors()) {
			return "spitter/form";
		}
		
		//If no errors, the newly registered Spitter is stored in the repository. Usually this would be a database
		spitterRepository.save(spitter);
		
		//A good practice for a POST method is to reply with a redirect, so to avoid resubmission. Read below how the InternalResourceViewResolver resolves a redirect*
		return "redirect:/spitter/repository/"+spitter.getUsername();
		
	}
	
	//The redirect from the previous method will result in a local HTTP redirect of the type GET, targetet at URL/spitters/repository/{username}
	//This method will catch that redirect, use the {username} to fetch the Spitter and add it to the Model
	//It will then return a String that will be resolved as the spitter/repository/profile.jsp. The profile.jsp knows how to handle Spitter data and show it
	@RequestMapping(value="/repository/{username}", method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		
		Spitter spitter = spitterRepository.findByUsername(username);
		model.addAttribute(spitter);
		return "spitter/repository/profile";
		
	}
	
//*How InternalResourceViewResolver resolves a redirect: When InternalResourceViewResolver sees the redirect: prefix on the view specification, 
	//it knows to interpret it as a redirect specification instead of as a view name. In
	//this case, it will redirect to the path for a user’s profile page. For example, if the Spitter
	//.username property is jbauer, then the view will redirect to /spitter/jbauer
	//It’s worth noting that in addition to redirect:, InternalResourceViewResolver
	//also recognizes the forward: prefix. When it sees a view specification prefixed with
	//forward:, the request is forwarded to the given URL path instead of redirected.
	
	

}
