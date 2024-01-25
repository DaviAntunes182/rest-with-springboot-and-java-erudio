package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.erudio.models.PersonModel;
import br.com.erudio.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	//private PersonServices service = new PersonServices();
	
	@GetMapping(value="/find",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonModel> findAll() {
		return service.findAll();
	}
	@GetMapping(value="/find/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonModel findById(
			@PathVariable(value = "id") Long id ){
		return service.findById(id);
	}

	@PostMapping(value="/create",			
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonModel create(
			@RequestBody PersonModel person){
		return service.create(person);
	}

	@PutMapping(value="/update",			
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonModel update(
			@RequestBody PersonModel person){
		return service.update(person);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(
			@PathVariable(value = "id") Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
