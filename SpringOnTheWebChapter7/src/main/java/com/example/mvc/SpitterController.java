package com.example.mvc;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.example.spitters.Spitter;
import com.example.spitters.SpitterRepository;


@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	@Autowired
	private SpitterRepository spitterRepository;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new Spitter());
		return "spitter/form";
	}
	
	//Next we need to modify the @RequestMapping method that handles the POST message of the registration to accept images
	//We do this with annotation @RequestPart and name it "profilePicture". The object annotated with this will contain the image data that's marked with name="profilePicture"
	//The object that we use is either MultipartFile or Part. If we use Part, we don't need the MultipartResolver bean
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture, @Valid Spitter spitter, BindingResult errors) {
		
		if(errors.hasErrors()) {
			return "spitter/form";
		}
		
		spitterRepository.save(spitter);
		//This saves the file permanently in a location, but this method throws an exception that we need to handle
		
		try{
		profilePicture.transferTo(new File("C:/temp/"+profilePicture.getOriginalFilename()));
		}
		catch(IOException e) {
			//In case the file is not stored, the user will be redirected again to the form
			return "spitter/form";
		}
		
		//Next, go to SpittleRepository to see how a custom exception is created, thrown, catched and handled
		
		
		return "redirect:/spitter/repository/"+spitter.getUsername();
		
	}
	
	@RequestMapping(value="/repository/{username}", method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		
		Spitter spitter = spitterRepository.findByUsername(username);
		model.addAttribute(spitter);
		return "spitter/repository/profile";
		
	}
	
	
	

}
