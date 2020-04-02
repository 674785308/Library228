package com.etc.library.dao.impl;

import com.etc.library.common.db.DBUtil;
import com.etc.library.common.db.DB_VO;
import com.etc.library.common.db.DateSource;
import com.etc.library.dao.BookDAO;
import com.etc.library.domain.Book;
import com.etc.library.domain.PageBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDAOImpl implements BookDAO {


    @Override
    public List<Map<String, Object>> queryAllBook(PageBean page, String title) {
        List<Map<String, Object>> books = new ArrayList<>();
        String sql = "SELECT b.*,t.tname FROM tb_book b LEFT JOIN tb_type t ON b.tid = t.tid WHERE 1=1 ";
        if (title != null && !"".equals(title)) {
            sql += " and title like ?";
        }
        sql += " limit ?,?";
        DBUtil util = new DBUtil();
        DB_VO vo = new DB_VO();
        vo.conn = DateSource.getConnection();
        try {
            vo.psmt = vo.conn.prepareStatement(sql);
            if (title != null && !"".equals(title)) {
                vo.psmt.setString(1, "%" + title + "%");
                vo.psmt.setInt(2, page.getStartNum());
                vo.psmt.setInt(3, page.getNum());
            } else {
                vo.psmt.setInt(1, page.getStartNum());
                vo.psmt.setInt(2, page.getNum());
            }
            util.executeQuery(vo);
            while (vo.set.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("bid", vo.set.getInt(1));
                map.put("title", vo.set.getString(2));
                map.put("author", vo.set.getString(3));
                map.put("cover", vo.set.getString(4));
                map.put("price", vo.set.getDouble(5));
                map.put("publisher", vo.set.getString(6));
                map.put("pubdate", vo.set.getString(7));
                map.put("bnum", vo.set.getInt(8));
                map.put("summary", vo.set.getString(9));
                map.put("tid", vo.set.getInt(10));
                map.put("tname", vo.set.getString(11));
                books.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.releaseSource(vo);
        }
        return books;
    }

    @Override
    public int addBook(Book book) {
        int index = -1;
        String sql = "INSERT INTO tb_book(title,author,cover,price,publisher,pubdate,bnum,summary,tid) VALUES(?,?,?,?,?,?,5,?,?)";
        DBUtil util = new DBUtil();
        DB_VO vo = new DB_VO();
        vo.conn = DateSource.getConnection();
        try {
            vo.psmt = vo.conn.prepareStatement(sql);
            vo.psmt.setString(1, book.getTitle());
            vo.psmt.setString(2, book.getAuthor());
            vo.psmt.setString(3, book.getCover());
            vo.psmt.setDouble(4, book.getPrice());
            vo.psmt.setString(5, book.getPublisher());
            vo.psmt.setString(6, book.getPubdate());
            vo.psmt.setString(7, book.getSummary());
            vo.psmt.setInt(8, book.getTid());
            index = util.executeUpdate(vo);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.releaseSource(vo);
        }
        return index;
    }

    @Override
    public int updateBook(Book book) {
        int index = -1;
        String sql = "UPDATE tb_book SET title=?,author=?,price=?,publisher=?,pubdate=?,summary=?,tid=? WHERE bid = ?";
        DBUtil util = new DBUtil();
        DB_VO vo = new DB_VO();
        vo.conn = DateSource.getConnection();
        try {
            vo.psmt = vo.conn.prepareStatement(sql);
            vo.psmt.setString(1, book.getTitle());
            vo.psmt.setString(2, book.getAuthor());
            vo.psmt.setDouble(3, book.getPrice());
            vo.psmt.setString(4, book.getPublisher());
            vo.psmt.setString(5, book.getPubdate());
            vo.psmt.setString(6, book.getSummary());
            vo.psmt.setInt(7, book.getTid());
            vo.psmt.setInt(8, book.getBid());
            index = util.executeUpdate(vo);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.releaseSource(vo);
        }
        return index;
    }

    @Override
    public Book queryBookById(int bid) {
        Book book = null;
        String sql = "SELECT * FROM tb_book WHERE bid = ? ";

        DBUtil util = new DBUtil();
        DB_VO vo = new DB_VO();
        vo.conn = DateSource.getConnection();
        try {
            vo.psmt = vo.conn.prepareStatement(sql);
            vo.psmt.setInt(1, bid);
            util.executeQuery(vo);
            if (vo.set.next()) {
                book = new Book();
                book.setBid(vo.set.getInt(1));
                book.setTitle(vo.set.getString(2));
                book.setAuthor(vo.set.getString(3));
                book.setCover(vo.set.getString(4));
                book.setPrice(vo.set.getDouble(5));
                book.setPublisher(vo.set.getString(6));
                book.setPubdate(vo.set.getString(7));
                book.setBnum(vo.set.getInt(8));
                book.setSummary(vo.set.getString(9));
                book.setTid(vo.set.getInt(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.releaseSource(vo);
        }
        return book;
    }

    @Override
    public int delBook(int bid) {
        int index = -1;
        String sql = "DELETE FROM tb_book WHERE bid = ?";
        DBUtil util = new DBUtil();
        DB_VO vo = new DB_VO();
        vo.conn = DateSource.getConnection();
        try {
            vo.psmt = vo.conn.prepareStatement(sql);
            vo.psmt.setInt(1, bid);
            index = util.executeUpdate(vo);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.releaseSource(vo);
        }
        return index;
    }

    @Override
    public int getCount(String title) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM tb_book where 1=1 ";
        if (title != null && !"".equals(title)) {
            sql += " and title like ?";
        }
        DBUtil util = new DBUtil();
        DB_VO vo = new DB_VO();
        vo.conn = DateSource.getConnection();
        try {
            vo.psmt = vo.conn.prepareStatement(sql);
            if (title != null && !"".equals(title)) {
                vo.psmt.setString(1, "%"+title+"%");
            }
            util.executeQuery(vo);
            if (vo.set.next()) {
                count = vo.set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.releaseSource(vo);
        }
        return count;
    }
}
