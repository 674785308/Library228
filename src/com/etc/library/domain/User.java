package com.etc.library.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private int uid;
    private String account;
    private String password;
    private String phone;
    private int roleid;
}
