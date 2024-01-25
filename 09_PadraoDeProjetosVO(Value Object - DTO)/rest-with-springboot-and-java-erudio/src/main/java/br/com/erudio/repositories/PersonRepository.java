package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.models.PersonModel;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long>{}
