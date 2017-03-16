package com.example.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spittles.DefaultSpittleRepository;
import com.example.spittles.Spittle;
import com.example.spittles.SpittleRepository;

//@Controller component, decides what occurs when a HTTP request with a certain value arrives (returns Model and String for view resolvement
//@Controller is basically equivalent to @Component
@Controller
public class WebController {
	
	
	@Autowired
	SpittleRepository spittleRepository;
	
	//Decides what happens when a GET method is recieved with a value of "/"
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "home";
	}
	
	//Decides what happens when a GET method is received with a value of "/spittles"
	@RequestMapping(value = "/spittleshome", method = RequestMethod.GET)
	//The Model, while passed as an argument here is actually forwarded to the view (.jsp) file that is resolved with this method ("WEB-INF/views/spittles.jsp")
	public String getSpittles(Model model) {
		model.addAttribute(spittleRepository.getSpittles());
		return "spittles";
	}
	
	//Example 2: Input parameter by Querry
	@RequestMapping(value = "/spittles", method = RequestMethod.GET)
	//@RequestParam will bind an input parameter to the int count (e.g. localhost:8080/WebTest/spittles?count=10
	//Note that the class returns a List. This list will be mapped to the Model, while the String for view resolvement will be derived from the value (will be spittles)
	public List<Spittle> getSpittles(@RequestParam("count") int count) {
		if(count == 0) {
			return spittleRepository.getSpittles();
		}
		return spittleRepository.getSpittles(count);
	}
	
	//Alternatively, instead of having two methods, we can specify a default value for the @RequestParam
	//This code is commented out, since we can only have one method that responds to @RequestMapping(value = "/spittles", method = RequestMethod.GET)
	
//	@RequestMapping(value = "/spittles", method = RequestMethod.GET)
//	public List<Spittle> getSpittles(@RequestParam(value="count", defaultValue="0") int count) {
//		if(count == 0) {
//			return spittleRepository.getSpittles();
//		}
//		return spittleRepository.getSpittles(count);
//	}
	
	//Example 3: Input via Path parameters
	//Instead of passing parameters via the "?param=...", we can pass it as part of the path
	//In the request mapping, we only need to identify which part of the URL path will be treated as an input variable. We do this by using { } (e.g. /"spittles/{spittleId}"
	//This means that whichever value is set after "spittles/" will be treated as a Path parameter
	@RequestMapping(value = "/spittles/{spittleId}", method=RequestMethod.GET)
	//Next, we just need to map the Path parameter to an input parameter, by using the @PathVariable
	public String getSpittleById(@PathVariable("spittleId") int spittleId, Model model) {
		
		//Note: this resulting Spittle could be passed to the Model, but the .jsp currently knows only how to work with Lists, so....we just add the result to a new list
		if(spittleRepository.getSpittleById(spittleId) != null) {
			List<Spittle> listSpittle = new ArrayList<Spittle>();
			listSpittle.add(spittleRepository.getSpittleById(spittleId));
			model.addAttribute(listSpittle);
		}
		
		//The result again resolves the spittle.jsp
		return "spittles";
	}
	
	//Example 4: Input via Forms - go to SpitterController to start
	

}
