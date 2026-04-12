package com.deveyk.bookstore.book.model.mapper;

import com.deveyk.bookstore.book.repository.entity.BookEntity;
import com.deveyk.bookstore.book.service.domain.Book;
import com.deveyk.bookstore.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookDomainToEntityMapper extends BaseMapper<Book, BookEntity> {

    BookDomainToEntityMapper INSTANCE = Mappers.getMapper(BookDomainToEntityMapper.class);

}
