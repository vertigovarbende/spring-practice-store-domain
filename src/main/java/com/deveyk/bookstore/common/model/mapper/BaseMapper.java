package com.deveyk.bookstore.common.model.mapper;

import java.util.List;

public interface BaseMapper<S, T> {

    T map(S source);

    List<T> mapList(List<S> source);


}
