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
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture, @Valid Spitter spitter, BindingResult errors) {
		
		if(errors.hasErrors()) {
			return "spitter/form";
		}
		
		spitterRepository.save(spitter);
		
		try{
		profilePicture.transferTo(new File("C:/temp/"+profilePicture.getOriginalFilename()));
		}
		catch(IOException e) {
			return "spitter/form";
		}
		
		return "redirect:/spitter/repository/"+spitter.getUsername();
		
	}
	
	@RequestMapping(value="/repository/{username}", method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model) {
		
		Spitter spitter = spitterRepository.findByUsername(username);
		model.addAttribute(spitter);
		return "spitter/repository/profile";
		
	}
	
	
	

}
