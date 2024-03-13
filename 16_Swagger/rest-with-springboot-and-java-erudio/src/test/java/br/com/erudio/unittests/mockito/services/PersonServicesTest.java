package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import br.com.erudio.converter.mocks.MockPerson;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.models.PersonModel;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonServices;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PersonServicesTest {

	//Mockar person -> Indivíduo de teste
	MockPerson input;
	
	@InjectMocks
	private PersonServices service;
	
	@Mock
	PersonRepository repository;
	
	@SpyBean
	PersonMapper mapperConverter;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		// acessa o banco de dados(repositório) e retorna um elemento
		// Vamos verificar se o find está adicionando o link hateoas
		PersonModel person = input.mockEntity(1);
		person.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(person));

		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [<http://localhost/api/person/v1/find/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAdress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreate() {
		PersonModel person = input.mockEntity(1);
		
		PersonModel persisted = person;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setId(1L);
		
		when(repository.save(person)).thenReturn(persisted);
		
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [<http://localhost/api/person/v1/find/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAdress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreateNullPerson() {
		Exception e = assertThrows(RequiredObjectIsNullException.class, () ->{
			service.create(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = e.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testUpdate() {
		PersonModel person = input.mockEntity(1);
		person.setId(1L);
		
		PersonModel persisted = person;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(person));
		when(repository.save(person)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [<http://localhost/api/person/v1/find/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAdress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testUpdateNullPerson() {
		Exception e = assertThrows(RequiredObjectIsNullException.class, () ->{
			service.update(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = e.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		PersonModel person = input.mockEntity(1);
		person.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(person));

		service.delete(1L);
	}
	
	@Test
	void testFindAll() {
		List<PersonModel> persons = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(persons);

		var personList = service.findAll();
		assertNotNull(personList);
		assertEquals(14, personList.size());
		
		var resultOne = personList.get(1);
		assertNotNull(resultOne);
		assertNotNull(resultOne.getId());
		assertNotNull(resultOne.getLinks());
		
		assertTrue(resultOne.toString().contains("links: [<http://localhost/api/person/v1/find/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", resultOne.getAdress());
		assertEquals("First Name Test1", resultOne.getFirstName());
		assertEquals("Last Name Test1", resultOne.getLastName());
		assertEquals("Female", resultOne.getGender());
		
		var resoltFor = personList.get(4);
		assertNotNull(resoltFor);
		assertNotNull(resoltFor.getId());
		assertNotNull(resoltFor.getLinks());
		
		assertTrue(resoltFor.toString().contains("links: [<http://localhost/api/person/v1/find/4>;rel=\"self\"]"));
		assertEquals("Addres Test4", resoltFor.getAdress());
		assertEquals("First Name Test4", resoltFor.getFirstName());
		assertEquals("Last Name Test4", resoltFor.getLastName());
		assertEquals("Male", resoltFor.getGender());
		
		var resultSeven = personList.get(7);
		assertNotNull(resultSeven);
		assertNotNull(resultSeven.getId());
		assertNotNull(resultSeven.getLinks());
		
		assertTrue(resultSeven.toString().contains("links: [<http://localhost/api/person/v1/find/7>;rel=\"self\"]"));
		assertEquals("Addres Test7", resultSeven.getAdress());
		assertEquals("First Name Test7", resultSeven.getFirstName());
		assertEquals("Last Name Test7", resultSeven.getLastName());
		assertEquals("Female", resultSeven.getGender());
	}
}
