package com.deveyk.bookstore.category.service.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Category {

    private UUID id;
    private String name;

}
