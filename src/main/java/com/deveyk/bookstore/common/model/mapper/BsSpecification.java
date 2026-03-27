package com.deveyk.bookstore.common.model.mapper;

import org.springframework.data.jpa.domain.Specification;

public interface BsSpecification {

    <C> Specification<C> toSpecification(final Class<C> clazz);

}
