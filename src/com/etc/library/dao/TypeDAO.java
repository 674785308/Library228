package com.etc.library.dao;

import com.etc.library.domain.Type;

import java.util.List;

public interface TypeDAO {
    List<Type> queryAllType();

    int addType(Type type);
}
