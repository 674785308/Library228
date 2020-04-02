package com.etc.library.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB_VO {
    public Connection conn;
    public PreparedStatement psmt;
    public ResultSet set;
}
