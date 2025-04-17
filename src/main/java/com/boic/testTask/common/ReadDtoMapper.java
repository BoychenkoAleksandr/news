package com.boic.testTask.common;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ReadDtoMapper<OUT, T> {

    OUT toOut(T t);

    List<OUT> toOut(List<? extends T> tList);

    default Page<OUT> toOut(Page<T> page) {
        return page.map(this::toOut);
    }

}
