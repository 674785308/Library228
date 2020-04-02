package com.etc.library.service;

import com.etc.library.domain.User;

public interface UserService {
    /**
     * 登录
     *
     * @param account
     * @return
     */
    User login(String account);
}
