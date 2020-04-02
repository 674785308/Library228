package com.etc.library.service.impl;

import com.etc.library.dao.TypeDAO;
import com.etc.library.dao.impl.TypeDAOImpl;
import com.etc.library.domain.Type;
import com.etc.library.service.TypeService;

import java.util.List;

public class TypeServiceImpl implements TypeService {
    TypeDAO typeDAO = new TypeDAOImpl();

    @Override
    public List<Type> queryAllType() {
        return typeDAO.queryAllType();
    }

    @Override
    public int addType(Type type) {
        return typeDAO.addType(type);
    }
}
