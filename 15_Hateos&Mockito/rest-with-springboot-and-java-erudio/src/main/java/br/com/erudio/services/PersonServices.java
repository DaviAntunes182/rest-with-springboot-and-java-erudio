package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.models.PersonModel;
import br.com.erudio.repositories.PersonRepository;


@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	// Na pratica Service e Repository é a mesma coisa: Component
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonMapper mapper;
	
	
	public List<PersonVO> findAll() {
		logger.info("Finding all persons");
		
		List<PersonModel> persons = repository.findAll();
		long id;
		
		var personsVo = mapper.listPersonModelToListPersonVO(persons);

		for (var personVO : personsVo) {
			
			id = personVO.getId();
			personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());			
		}
			
//			.stream()
//			.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
//		
		return personsVo;
	}
	

	public PersonVO findById(Long id) {
		logger.info("Finding one person");
		
		var person = repository.findById(id).get();
		
		
		
		var personVO = mapper.personModelToPersonVO(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return personVO;
	}
	
	public PersonVO create(PersonVO person) {
		if(person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating person");
		
		var personEntity  = mapper.personVOToPersonModel(person);
		var personVO = mapper.personModelToPersonVO(repository.save(personEntity));
		var id = personVO.getId();
		
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());		
		return personVO;
	}

	public PersonVO update(PersonVO person) {
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("Updating person");
		
		var personEntity = repository.findById(person.getId())
							.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		personEntity.setFirstName(person.getFirstName());
		personEntity.setLastName(person.getLastName());
		personEntity.setAdress(person.getAdress());
		personEntity.setGender(person.getGender());
		
		var personVO = mapper.personModelToPersonVO(repository.save(personEntity));
		var id = personVO.getId();

		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	public void delete(Long id) {
		logger.info("Deleting person");
		
		var personEntity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(personEntity);
	}
}
