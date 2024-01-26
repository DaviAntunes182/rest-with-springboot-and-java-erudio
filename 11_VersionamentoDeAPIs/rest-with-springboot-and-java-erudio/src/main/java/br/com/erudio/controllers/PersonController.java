package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	//private PersonServices service = new PersonServices();
	
	@GetMapping(value="/find",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findAll() {
		return service.findAll();
	}
	@GetMapping(value="/find/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO findById(
			@PathVariable(value = "id") Long id ){
		return service.findById(id);
	}

	@PostMapping(value="/create",			
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO create(
			@RequestBody PersonVO person){
		return service.create(person);
	}

	@PostMapping(value="/create/v2",			
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVOV2 createv2(
			@RequestBody PersonVOV2 person){
		return service.createV2(person);
	}

	@PutMapping(value="/update",			
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO update(
			@RequestBody PersonVO person){
		return service.update(person);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(
			@PathVariable(value = "id") Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
