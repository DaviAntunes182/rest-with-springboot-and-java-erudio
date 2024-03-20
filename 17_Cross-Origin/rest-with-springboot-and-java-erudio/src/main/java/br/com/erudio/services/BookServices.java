package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.erudio.controllers.BookController;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.BookMapper;
import br.com.erudio.models.BookModel;
import br.com.erudio.repositories.BookRepository;


@Service
public class BookServices {
	
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	// Na pratica Service e Repository é a mesma coisa: Component
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private BookMapper mapper;
	
	
	public List<BookVO> findAll() {
		logger.info("Finding all books");
		
		List<BookModel> books = repository.findAll();
		long id;
		
		var booksVo = mapper.listBookModelToListBookVO(books);

		for (var bookVO : booksVo) {
			
			id = bookVO.getId();
			bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());			
		}
			
//			.stream()
//			.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
//		
		return booksVo;
	}
	

	public BookVO findById(Long id) {
		logger.info("Finding one book");
		
		var book = repository.findById(id).get();
		
		
		
		var bookVO = mapper.bookModelToBookVO(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		
		return bookVO;
	}
	
	public BookVO create(BookVO book) {
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating book");
		
		var bookEntity  = mapper.bookVOToBookModel(book);
		var bookVO = mapper.bookModelToBookVO(repository.save(bookEntity));
		var id = bookVO.getId();
		
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());		
		return bookVO;
	}

	public BookVO update(BookVO book) {
		if(book == null) throw new RequiredObjectIsNullException();
		logger.info("Updating book");
		
		var bookEntity = repository.findById(book.getId())
							.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		bookEntity.setAuthor(book.getAuthor());
		bookEntity.setLaunchDate(book.getLaunchDate());
		bookEntity.setPrice(book.getPrice());
		bookEntity.setTitle(book.getTitle());
		
		var bookVO = mapper.bookModelToBookVO(repository.save(bookEntity));
		var id = bookVO.getId();

		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return bookVO;
	}
	
	public void delete(Long id) {
		logger.info("Deleting book");
		
		var bookEntity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(bookEntity);
	}
}
