package com.example.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.spitters.Spitter;
import com.example.spitters.SpitterRepository;


@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	@Autowired
	private SpitterRepository spitterRepository;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		//Continued from form.jsp
		//Here we added a Model as an input parameter. Remember, this is a GET method, so this model is actually 
		//passed to the .jsp page that the view resolves (in this case, /spitter/form.jsp
		//Basically, with this and the spring form-tags, the input the user provides will be directly filled in this Spitter object
		model.addAttribute(new Spitter());
		//Back to form.jsp
		return "spitter/form";
	}
	
	//When the user fills in the registration form, this handler method will recieve the response. It will validate the Spitter input
	//In case of errors, it will result with the "spitter/form", which will resolve the form.jsp
	//Note that even if we don't have the Model attribute here, the Spitter will be automatically bound to the Model from which the form.jsp will be filled
	//In other words, if the used enters an invalid input, the form will display again, but it won't be empty, it will have his inputs
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegistration(@Valid Spitter spitter, BindingResult errors) {
		
		if(errors.hasErrors()) {
			return "spitter/form";
		}
		
		spitterRepository.save(spitter);
		
		return "redirect:/spitter/repository/"+spitter.getUsername();
		
	}
	
	@RequestMapping(value="/repository/{username}", method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		
		Spitter spitter = spitterRepository.findByUsername(username);
		model.addAttribute(spitter);
		return "spitter/repository/profile";
		
	}
	
	
	

}
