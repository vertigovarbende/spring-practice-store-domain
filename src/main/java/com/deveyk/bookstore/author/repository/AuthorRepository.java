package com.deveyk.bookstore.author.repository;

import com.deveyk.bookstore.author.repository.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, String> {


}
