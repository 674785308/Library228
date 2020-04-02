package com.etc.library.service;

import com.etc.library.domain.Type;

import java.util.List;

public interface TypeService {
    List<Type> queryAllType();

    int addType(Type type);
}
