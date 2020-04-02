package com.etc.library.dao.impl;

import com.etc.library.common.db.DBUtil;
import com.etc.library.common.db.DB_VO;
import com.etc.library.common.db.DateSource;
import com.etc.library.dao.UserDAO;
import com.etc.library.domain.User;

import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public User login(String account) {
        User user = null;
        String sql = "SELECT * FROM tb_user WHERE account = ?";
        DBUtil util = new DBUtil();
        DB_VO vo = new DB_VO();
        vo.conn = DateSource.getConnection();
        try {
            vo.psmt = vo.conn.prepareStatement(sql);
            vo.psmt.setString(1, account);
            util.executeQuery(vo);
            if (vo.set.next()) {
                user = new User();
                user.setUid(vo.set.getInt(1));
                user.setAccount(vo.set.getString(2));
                user.setPassword(vo.set.getString(3));
                user.setPhone(vo.set.getString(4));
                user.setRoleid(vo.set.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.releaseSource(vo);
        }
        return user;
    }
}
