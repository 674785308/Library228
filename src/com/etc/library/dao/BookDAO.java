package com.etc.library.dao;

import com.etc.library.domain.Book;
import com.etc.library.domain.PageBean;

import java.util.List;
import java.util.Map;

public interface BookDAO {
    /**
     * 查询所有图书信息
     *
     * @return
     */
    List<Map<String, Object>> queryAllBook(PageBean page, String title);

    /**
     * 添加图书
     *
     * @param book
     * @return
     */
    int addBook(Book book);

    /**
     * 更新图书信息
     *
     * @param book
     * @return
     */
    int updateBook(Book book);

    /**
     * 根据图书编号查询图书信息
     *
     * @param bid
     * @return
     */
    Book queryBookById(int bid);

    /**
     * 删除图书信息
     *
     * @param bid
     * @return
     */
    int delBook(int bid);

    /**
     * 根据条件获取记录数
     *
     * @param title
     * @return
     */
    int getCount(String title);
}
