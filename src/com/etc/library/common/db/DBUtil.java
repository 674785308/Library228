package com.etc.library.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    /**
     * 执行增删改方法
     * executeUpdate()
     *
     * @param vo
     * @return
     */
    public int executeUpdate(DB_VO vo) {
        int index = -1;
        try {
            index = vo.psmt.executeUpdate();
        } catch (SQLException e) {
        }
        return index;
    }

    /**
     * 执行查询方法
     * executeQuery()
     *
     * @param vo
     */
    public void executeQuery(DB_VO vo) {

        try {
            vo.set = vo.psmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     *
     * @param vo
     */
    public void releaseSource(DB_VO vo) {
        this.releaseSource(vo.conn, vo.psmt, vo.set);
    }

    /**
     * 释放资源
     *
     * @param conn
     * @param psmt
     * @param set
     */
    public void releaseSource(Connection conn, PreparedStatement psmt, ResultSet set) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (psmt != null) {
                psmt.close();
            }
            if (set != null) {
                set.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
