package br.com.erudio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.models.PersonModel;

@Mapper(componentModel = "spring")
public interface PersonMapper {
	
	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
		
	// Entidade -> DTO
	PersonVO personModelToPersonVO(PersonModel personModel);
	
	// Entidade <- DTO
	PersonModel personVOToPersonModel(PersonVO personVO);
	
	List<PersonVO> listPersonModelToListPersonVO(List<PersonModel> persons);
	
	List<PersonModel> listPersonVOToListPersonModel(List<PersonVO> persons);
	
	// Tentar fazer mappers mais genéricos que sirvam para qualquer classe
}
