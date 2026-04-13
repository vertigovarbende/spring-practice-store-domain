package com.deveyk.bookstore.book.controller.mapper;

import com.deveyk.bookstore.book.controller.request.BookChangeStatusRequest;
import com.deveyk.bookstore.book.service.command.BookChangeStatusCommand;
import com.deveyk.bookstore.common.model.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookChangeStatusRequestToBookChangeStatusCommandMapper extends BaseMapper<BookChangeStatusRequest, BookChangeStatusCommand> {

    BookChangeStatusRequestToBookChangeStatusCommandMapper INSTANCE = Mappers.getMapper(BookChangeStatusRequestToBookChangeStatusCommandMapper.class);

    BookChangeStatusCommand map(BookChangeStatusRequest request, String bookId);

}
