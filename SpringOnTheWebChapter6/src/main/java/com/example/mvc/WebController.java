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

@Controller
public class WebController {
	
	
	@Autowired
	SpittleRepository spittleRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "home";
	}
	
	@RequestMapping(value = "/spittleshome", method = RequestMethod.GET)
	public String getSpittles(Model model) {
		model.addAttribute(spittleRepository.getSpittles());
		return "spittles";
	}
	
	@RequestMapping(value = "/spittles", method = RequestMethod.GET)
	public List<Spittle> getSpittles(@RequestParam("count") int count) {
		if(count == 0) {
			return spittleRepository.getSpittles();
		}
		return spittleRepository.getSpittles(count);
	}
	
	
//	@RequestMapping(value = "/spittles", method = RequestMethod.GET)
//	public List<Spittle> getSpittles(@RequestParam(value="count", defaultValue="0") int count) {
//		if(count == 0) {
//			return spittleRepository.getSpittles();
//		}
//		return spittleRepository.getSpittles(count);
//	}
	
	@RequestMapping(value = "/spittles/{spittleId}", method=RequestMethod.GET)
	public String getSpittleById(@PathVariable("spittleId") int spittleId, Model model) {
		
		if(spittleRepository.getSpittleById(spittleId) != null) {
			List<Spittle> listSpittle = new ArrayList<Spittle>();
			listSpittle.add(spittleRepository.getSpittleById(spittleId));
			model.addAttribute(listSpittle);
		}
		
		return "spittles";
	}
	
	

}
