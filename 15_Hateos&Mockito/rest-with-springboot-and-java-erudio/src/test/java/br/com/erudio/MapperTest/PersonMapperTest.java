package br.com.erudio.MapperTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.mapper.PersonMapper;
import br.com.erudio.models.PersonModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PersonMapperTest {
	
	@Autowired(required = true)
	PersonMapper mapper;

	@Test
	public void personModelToPersonVO() {
		PersonModel personModel = new PersonModel();
		personModel.setId(1L);
		personModel.setFirstName("Davi");
		personModel.setLastName("Antunes");
		personModel.setAdress("Rio de Janeiro - RJ");
		personModel.setGender("Male");
		
		System.out.println("Passou1");
		PersonVO personVO = mapper.INSTANCE.personModelToPersonVO(personModel);
		System.out.println("Passou2");
		
		assertEquals(personModel.getId(), personVO.getId());
		assertEquals(personModel.getFirstName(), personVO.getFirstName());
		assertEquals(personModel.getLastName(), personVO.getLastName());
		assertEquals(personModel.getAdress(), personVO.getAdress());
		assertEquals(personModel.getGender(), personVO.getGender());		
		assertNotEquals(personModel.getGender(), personVO.getGender());
		
	}
	
	@Test
	public void personVOToPersonModel() {
		PersonVO personVO = new PersonVO();
		personVO.setId(1L);
		personVO.setFirstName("Davi");
		personVO.setLastName("Antunes");
		personVO.setAdress("Rio de Janeiro - RJ");
		personVO.setGender("Male");
		
		System.out.println("Passou1");
		PersonModel personModel = mapper.INSTANCE.personVOToPersonModel(personVO);
		System.out.println("Passou2");
		
		assertEquals(personVO.getId(), personModel.getId());
		assertEquals(personVO.getFirstName(), personModel.getFirstName());
		assertEquals(personVO.getLastName(), personModel.getLastName());
		assertEquals(personVO.getAdress(), personModel.getAdress());
		assertEquals(personVO.getGender(), personModel.getGender());
		
		}
}
