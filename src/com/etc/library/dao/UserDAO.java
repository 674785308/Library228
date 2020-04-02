package com.etc.library.dao;

import com.etc.library.domain.User;

public interface UserDAO {
    /**
     * 登录
     *
     * @param account
     * @return
     */
    User login(String account);
}
