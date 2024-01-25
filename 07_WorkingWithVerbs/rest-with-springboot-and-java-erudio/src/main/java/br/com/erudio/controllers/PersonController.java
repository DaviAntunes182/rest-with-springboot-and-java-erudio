package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.models.PersonModel;
import br.com.erudio.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	//private PersonServices service = new PersonServices();
	
	@RequestMapping(value="/find",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonModel> findAll() {
		return service.findAll();
	}
	@RequestMapping(value="/find/{id}",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonModel findById(
			@PathVariable(value = "id") String id ){
		return service.findById(id);
	}

	@RequestMapping(value="/create",
			method=RequestMethod.POST,			
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonModel create(
			@RequestBody PersonModel person){
		return service.create(person);
	}

	@RequestMapping(value="/update",
			method=RequestMethod.PUT,			
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonModel update(
			@RequestBody PersonModel person){
		return service.update(person);
	}

	@RequestMapping(value="/delete/{id}",
			method=RequestMethod.DELETE)
	public void delete(
			@PathVariable(value = "id") String id){
		service.delete(id);
	}
	
	
	
}
