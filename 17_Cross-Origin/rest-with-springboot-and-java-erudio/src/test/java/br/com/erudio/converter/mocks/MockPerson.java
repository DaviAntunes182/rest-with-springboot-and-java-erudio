package br.com.erudio.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.models.PersonModel;


public class MockPerson {


    public PersonModel mockEntity() {
    	return mockEntity(0);
    }
    
    public PersonVO mockVO() {
    	return mockVO(0);
    }
    
    public List<PersonModel> mockEntityList() {
        List<PersonModel> persons = new ArrayList<PersonModel>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonVO> mockVOList() {
        List<PersonVO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }
    
    public PersonModel mockEntity(Integer number) {
    	PersonModel person = new PersonModel();
    	person.setAdress("Addres Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    public PersonVO mockVO(Integer number) {
    	PersonVO person = new PersonVO();
    	person.setAdress("Addres Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

}