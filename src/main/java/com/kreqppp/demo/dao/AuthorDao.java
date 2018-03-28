package com.kreqppp.demo.dao;

import com.kreqppp.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface AuthorDao extends CrudRepository<Author, Long>{
}
