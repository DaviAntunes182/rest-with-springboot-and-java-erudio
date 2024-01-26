package br.com.erudio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
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
	
	// Versão 2.0
//    @Mapping(target = "birthDay", ignore = true)
	PersonVOV2 personModelToPersonVOV2(PersonModel person);
	
	PersonModel personVOV2ToPersonModel(PersonVOV2 person);
	
	List<PersonVOV2> listPersonModelToListPersonVOV2(List<PersonModel> persons);
	
	List<PersonModel> listPersonVOV2ToListPersonModel(List<PersonVOV2> persons);
	
	
	// Tentar fazer mappers mais genéricos que sirvam para qualquer classe
}
