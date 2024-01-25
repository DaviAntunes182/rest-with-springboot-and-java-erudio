package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.erudio.models.PersonModel;


@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	


	public List<PersonModel> findAll() {
		//Mock
		logger.info("Finding all people");
		List<PersonModel> persons = new ArrayList<>();
		for(int i = 0; i < 8; i++) {
			PersonModel person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
	}
	

	public PersonModel findById(String id) {
		// Mock -> Estrutura temporária que sustenta o código até
		// outros componentes sejam desenvolvidos para entrar em seu lugar
		logger.info("Finding one person");
		
		PersonModel person = new PersonModel();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Davi");
		person.setLastName("Antunes");
		person.setAdress("Rio de Janeiro");
		person.setGender("Masculino");
		return person;
	}
	

	private PersonModel mockPerson(int i) {	
		PersonModel person = new PersonModel();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setAdress("Some adress " + i);
		if(i % 2 == 0) {
			person.setGender("Masculino");
		}else {
			person.setGender("Feminino");
		}
		return person;
	}
	
	public PersonModel create(PersonModel person) {
		logger.info("Creating person");
		
		
		
		return person;
	}

	public PersonModel update(PersonModel person) {
		logger.info("Updating person");
		
		
		
		return person;
	}
	
	public void delete(String id) {
		logger.info("Deleting person");
		
		//System.out.println();
	}
}
