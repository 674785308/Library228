package com.etc.library.service.impl;

import com.etc.library.dao.UserDAO;
import com.etc.library.dao.impl.UserDAOImpl;
import com.etc.library.domain.User;
import com.etc.library.service.UserService;

public class UserServiceImpl implements UserService {
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public User login(String account) {
        return userDAO.login(account);
    }
}
