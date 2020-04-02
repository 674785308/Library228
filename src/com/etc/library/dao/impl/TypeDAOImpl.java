package com.etc.library.dao.impl;

import com.etc.library.common.db.DBUtil;
import com.etc.library.common.db.DB_VO;
import com.etc.library.common.db.DateSource;
import com.etc.library.dao.TypeDAO;
import com.etc.library.domain.Type;
import com.sun.org.apache.bcel.internal.generic.DUP;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDAOImpl implements TypeDAO {
    @Override
    public List<Type> queryAllType() {
        List<Type> types = new ArrayList<>();
        String sql = "select * from tb_type";
        DBUtil util = new DBUtil();
        DB_VO vo = new DB_VO();
        vo.conn = DateSource.getConnection();
        try {
            vo.psmt = vo.conn.prepareStatement(sql);
            util.executeQuery(vo);
            while (vo.set.next()) {
                Type type = new Type();
                type.setTid(vo.set.getInt(1));
                type.setTname(vo.set.getString(2));
                types.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.releaseSource(vo);
        }
        return types;
    }

    @Override
    public int addType(Type type) {
        int index = -1;
        String sql = "insert into tb_type(tname) values(?)";
        DBUtil util = new DBUtil();
        DB_VO vo = new DB_VO();
        vo.conn = DateSource.getConnection();
        try {
            vo.psmt = vo.conn.prepareStatement(sql);
            vo.psmt.setString(1, type.getTname());
            index = util.executeUpdate(vo);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.releaseSource(vo);
        }
        return index;
    }
}
