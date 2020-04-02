package com.etc.library.common.db;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DateSource {
    private static DruidDataSource dataSource = null;

    static {
        dataSource = new DruidDataSource();
        //设置驱动信息
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //设置连接地址
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_library?useUnicode=true&characterEncoding=UTF-8");
        //设置用户名
        dataSource.setUsername("root");
        //设置密码
        dataSource.setPassword("root");

        //设置初始化连接数
        dataSource.setInitialSize(10);
        //设置最大连接数
        dataSource.setMaxActive(50);
        //设置最小活动数
        dataSource.setMinIdle(5);
        //设置最大等待时间
        dataSource.setMaxWait(300000);
    }

    /**
     * 返回连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            //e.printStackTrace();
            return null;
        }
    }
}
