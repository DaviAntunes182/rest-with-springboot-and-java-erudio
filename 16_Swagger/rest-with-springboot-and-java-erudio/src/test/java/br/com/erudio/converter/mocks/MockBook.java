package br.com.erudio.converter.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.models.BookModel;


public class MockBook {


    public BookModel mockEntity() {
    	return mockEntity(0);
    }
    
    public BookVO mockVO() {
    	return mockVO(0);
    }
    
    public List<BookModel> mockEntityList() {
        List<BookModel> books = new ArrayList<BookModel>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public BookModel mockEntity(Integer number) {
    	BookModel book = new BookModel();
    	book.setId(number.longValue());
    	book.setAuthor("Author Test" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setTitle("Title Test" + number);
        return book;
    }

    public BookVO mockVO(Integer number) {
    	BookVO book = new BookVO();
    	book.setId(number.longValue());
    	book.setAuthor("Author Test" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setTitle("Title Test" + number);
        return book;
    }

}