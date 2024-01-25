package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.models.PersonModel;
import br.com.erudio.repositories.PersonRepository;


@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	// Na pratica Service e Repository é a mesma coisa: Component
	@Autowired
	PersonRepository repository;
	
	
	
	public List<PersonModel> findAll() {
		//Mock
		
		return repository.findAll();
	}
	

	public PersonModel findById(Long id) {
		logger.info("Finding one person");
		
		return repository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}
	
	public PersonModel create(PersonModel person) {
		logger.info("Creating person");
		
		
		
		return repository.save(person);
	}

	public PersonModel update(PersonModel person) {
		logger.info("Updating person");
		
		var entity = repository.findById(person.getId())
							.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		logger.info("Deleting person");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}
