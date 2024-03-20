package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.models.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long>{}
