package br.com.erudio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.models.PersonModel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {
	
	// Entidade -> DTO
	PersonVO personModelToPersonVO(PersonModel person);
	
	// Entidade <- DTO
	PersonModel personVOToPersonModel(PersonVO person);
	
	List<PersonVO> listPersonModelToListPersonVO(List<PersonModel> persons);
	
	List<PersonModel> listPersonVOToListPersonModel(List<PersonVO> persons);
	
}
