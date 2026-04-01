package com.deveyk.bookstore.book.repository;

import com.deveyk.bookstore.book.repository.entity.BookEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    @EntityGraph(attributePaths = {"authors"})
    List<BookEntity> findAll();


}
