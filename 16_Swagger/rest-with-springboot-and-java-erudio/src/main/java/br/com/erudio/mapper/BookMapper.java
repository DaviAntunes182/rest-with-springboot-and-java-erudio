package br.com.erudio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.models.BookModel;

@Mapper(componentModel = "spring")
public interface BookMapper {
	
	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
		
	// Entidade -> DTO
	BookVO bookModelToBookVO(BookModel bookModel);
	
	// Entidade <- DTO
	BookModel bookVOToBookModel(BookVO bookVO);
	
	List<BookVO> listBookModelToListBookVO(List<BookModel> books);
	
	List<BookModel> listBookVOToListBookModel(List<BookVO> books);
}
