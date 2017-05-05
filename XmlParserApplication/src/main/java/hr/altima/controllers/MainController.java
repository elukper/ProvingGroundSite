package hr.altima.controllers;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

import hr.altima.calculation.RelationshipCalculator;
import hr.altima.calculation.exceptions.DuplicateEntryException;
import hr.altima.calculation.exceptions.LoopedRelationException;
import hr.altima.dao.DbEntry;
import hr.altima.dao.service.DbEntryService;
import hr.altima.utils.EntityServiceUtils;
import hr.altima.xmlparser.XMLParsingUnit;
import hr.altima.xmlparser.parseddata.Entries;

@Controller
@PropertySource("classpath:xmlparsing.properties")
public class MainController {

	@Autowired
	DbEntryService dbEntryService;

	@Autowired
	Environment env;

	@RequestMapping(value="/resolvefromdisk", method = RequestMethod.GET)
	public ResponseEntity<List<String>> parseXmlFromDisk() {

		XMLParsingUnit<Entries> xmlParsingUnit;
		try {
			xmlParsingUnit = new XMLParsingUnit<>(Entries.class);
		} catch (final JAXBException e1) {
			return new ResponseEntity<List<String>>(Arrays.asList("Unable to resolve xml parser, contact support"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		final File file = new File(env.getProperty("input.folderandfile"));

		List<String> errors;
		try {
			errors = parseSingleFile(Files.asByteSource(file).openStream(), xmlParsingUnit);
		} catch (final IOException e) {
			return new ResponseEntity<List<String>>(Arrays.asList("Error resolving file"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(errors.isEmpty()) {
			return new ResponseEntity<List<String>>(Arrays.asList("Parsing sucessfully resolved and stored","All good"), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value="/resolvefromfile", method = RequestMethod.POST)
	public ResponseEntity<List<String>> parseXmlFromFile(@RequestParam("file") final MultipartFile[] multiPart){

		final Map<String,List<String>> errorsPerFile = new HashMap<>();

		XMLParsingUnit<Entries> xmlParsingUnit;
		try {
			xmlParsingUnit = new XMLParsingUnit<>(Entries.class);
		} catch (final JAXBException e1) {
			return new ResponseEntity<List<String>>(Arrays.asList("Unable to resolve xml parser, contact support"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		for(int i = 0; i<multiPart.length; i++) {

			final List<String> errors = new ArrayList<>();
			errorsPerFile.put(multiPart[i].getOriginalFilename(), errors);
			try {
				errors.addAll(parseSingleFile(multiPart[i].getInputStream(), xmlParsingUnit));
			} catch (final IOException e) {
				errors.add("Error resolving file");
			}
		}

		final List<String> errorMessage = resolveErrorMessage(errorsPerFile);

		if(errorMessage.isEmpty()) {
			return new ResponseEntity<>(Arrays.asList("Parsing sucessfully resolved and stored","All good"),HttpStatus.OK);
		}

		return new ResponseEntity<List<String>>(errorMessage, HttpStatus.BAD_REQUEST);



	}

	private List<String> parseSingleFile(final InputStream inputStream, final XMLParsingUnit<Entries> xmlParsingUnit) {
		final List<String> errors = new ArrayList<>();

		try {
			final Entries entries = xmlParsingUnit.parseXmlTData(inputStream);

			entries.getEntries();

			final RelationshipCalculator relationshipCalculator = new RelationshipCalculator();

			final Map<String,String> validatedParsedData = relationshipCalculator.validateInput(entries.getEntries());

			final Map<String,DbEntry> currentEntries = EntityServiceUtils.mapDbEntryToName(dbEntryService.findAll());

			final Set<DbEntry> dataToStore = relationshipCalculator.createDatabaseInput(currentEntries, validatedParsedData);

			dbEntryService.save(new ArrayList<>(dataToStore));


		} catch (final JAXBException e) {
			errors.add("Error parsing XML file: "+e.getMessage());
		}  catch (final DuplicateEntryException e) {
			errors.addAll(e.getErrors());
		} catch (final LoopedRelationException e) {
			errors.addAll(e.getErrors());
		}

		return errors;
	}



	private List<String> resolveErrorMessage(final Map<String,List<String>> errorsPerFile) {

		final List<String> result = new ArrayList<>();

		for(final Entry<String,List<String>> fileErrors : errorsPerFile.entrySet()) {
			if(!fileErrors.getValue().isEmpty()){
				result.add("Errors regarding file: "+fileErrors.getKey());
				result.addAll(fileErrors.getValue());
			}
		}
		return result;
	}


}