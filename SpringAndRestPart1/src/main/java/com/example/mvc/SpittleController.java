package com.example.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spittles.Spittle;
import com.example.spittles.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	
	@Autowired
	private SpittleRepository spittleRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Spittle> spittles(@RequestParam(value="cound",defaultValue="5") int count) {
		
		return spittleRepository.getSpittles(count);
		
	}

}
