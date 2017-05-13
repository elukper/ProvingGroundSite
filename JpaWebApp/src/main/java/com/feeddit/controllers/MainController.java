package com.feeddit.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.feeddit.model.DbEntry;
import com.feeddit.service.EntityService;

@Controller
public class MainController {

	@Resource
	private EntityService entityService;

	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {

		System.out.println("TADA!");

		final String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);
	}

	@RequestMapping(value="/test", method = RequestMethod.GET)
	public ResponseEntity<String> testDb() {

		final Iterable<DbEntry> entries = entityService.findAll();

		final DbEntry parent = new DbEntry();
		parent.setName("John");

		final DbEntry child = new DbEntry();
		child.setName("Jack");
		child.setParent(parent);

		final List<DbEntry> list = new ArrayList<>();
		list.add(child);
		list.add(parent);

		entityService.saveAll(list);


		for(final DbEntry entry : entries) {
			entry.setParent(parent);
		}

		System.out.println("TADA!");

		return new ResponseEntity<String>("Done", HttpStatus.OK);
	}

}
