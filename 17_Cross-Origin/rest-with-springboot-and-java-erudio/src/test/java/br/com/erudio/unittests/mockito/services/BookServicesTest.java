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

import br.com.erudio.converter.mocks.MockBook;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.mapper.BookMapper;
import br.com.erudio.models.BookModel;
import br.com.erudio.repositories.BookRepository;
import br.com.erudio.services.BookServices;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BookServicesTest {

	//Mockar book -> Indivíduo de teste
	MockBook input;
	
	@InjectMocks
	private BookServices service;
	
	@Mock
	BookRepository repository;
	
	@SpyBean
	BookMapper mapperConverter;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		// acessa o banco de dados(repositório) e retorna um elemento
		// Vamos verificar se o find está adicionando o link hateoas
		BookModel book = input.mockEntity(1);
		book.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(book));

		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [<http://localhost/api/book/v1/find/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(25D, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}
	
	@Test
	void testCreate() {
		BookModel book = input.mockEntity(1);
		
		BookModel persisted = book;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setId(1L);
		
		when(repository.save(book)).thenReturn(persisted);
		
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [<http://localhost/api/book/v1/find/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(25D, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}
	
	@Test
	void testCreateNullBook() {
		Exception e = assertThrows(RequiredObjectIsNullException.class, () ->{
			service.create(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = e.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testUpdate() {
		BookModel book = input.mockEntity(1);
		book.setId(1L);
		
		BookModel persisted = book;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(book));
		when(repository.save(book)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [<http://localhost/api/book/v1/find/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(25D, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}
	
	@Test
	void testUpdateNullBook() {
		Exception e = assertThrows(RequiredObjectIsNullException.class, () ->{
			service.update(null);
		});
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = e.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		BookModel book = input.mockEntity(1);
		book.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(book));

		service.delete(1L);
	}
	
	@Test
	void testFindAll() {
		List<BookModel> books = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(books);

		var bookList = service.findAll();
		assertNotNull(bookList);
		assertEquals(14, bookList.size());
		
		var resultOne = bookList.get(1);
		assertNotNull(resultOne);
		assertNotNull(resultOne.getId());
		assertNotNull(resultOne.getLinks());
		
		assertTrue(resultOne.toString().contains("links: [<http://localhost/api/book/v1/find/1>;rel=\"self\"]"));
		assertEquals("Author Test1", resultOne.getAuthor());
		assertNotNull(resultOne.getLaunchDate());
		assertEquals(25D, resultOne.getPrice());
		assertEquals("Title Test1", resultOne.getTitle());
		
		var resoltFor = bookList.get(4);
		assertNotNull(resoltFor);
		assertNotNull(resoltFor.getId());
		assertNotNull(resoltFor.getLinks());
		
		assertTrue(resoltFor.toString().contains("links: [<http://localhost/api/book/v1/find/4>;rel=\"self\"]"));
		assertEquals("Author Test4", resoltFor.getAuthor());
		assertNotNull(resoltFor.getLaunchDate());
		assertEquals(25D, resoltFor.getPrice());
		assertEquals("Title Test4", resoltFor.getTitle());
		
		var resultSeven = bookList.get(7);
		assertNotNull(resultSeven);
		assertNotNull(resultSeven.getId());
		assertNotNull(resultSeven.getLinks());
		
		assertTrue(resultSeven.toString().contains("links: [<http://localhost/api/book/v1/find/7>;rel=\"self\"]"));
		assertEquals("Author Test7", resultSeven.getAuthor());
		assertNotNull(resultSeven.getLaunchDate());
		assertEquals(25D, resultSeven.getPrice());
		assertEquals("Title Test7", resultSeven.getTitle());
	}
}
