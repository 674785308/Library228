package com.etc.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {

    private int tid;
    private String tname;

    public Type(String tname) {
        this.tname = tname;
    }
}
