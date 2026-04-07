package com.deveyk.bookstore.book.repository;

import com.deveyk.bookstore.book.repository.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    @Query("""
        SELECT DISTINCT b
        FROM BookEntity b
        JOIN b.authors a
        WHERE
            LOWER(COALESCE(a.name.firstName, '')) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
            OR LOWER(COALESCE(a.name.middleName, '')) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
            OR LOWER(COALESCE(a.name.lastName, '')) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
            OR LOWER(COALESCE(a.name.penName, '')) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
    """)
    Page<BookEntity> searchByAuthorName(@Param("searchTerm") String searchTerm, Pageable pageable);


}
