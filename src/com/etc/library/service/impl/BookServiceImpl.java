package com.etc.library.service.impl;

import com.etc.library.dao.BookDAO;
import com.etc.library.dao.impl.BookDAOImpl;
import com.etc.library.domain.Book;
import com.etc.library.domain.PageBean;
import com.etc.library.service.BookService;

import java.util.List;
import java.util.Map;

public class BookServiceImpl implements BookService {
    BookDAO bookDAO = new BookDAOImpl();

    @Override
    public List<Map<String, Object>> queryAllBook(PageBean page, String title) {
        return bookDAO.queryAllBook(page, title);
    }

    @Override
    public int addBook(Book book) {
        return bookDAO.addBook(book);
    }

    @Override
    public int updateBook(Book book) {
        return bookDAO.updateBook(book);
    }

    @Override
    public Book queryBookById(int bid) {
        return bookDAO.queryBookById(bid);
    }

    @Override
    public int delBook(int bid) {
        return bookDAO.delBook(bid);
    }

    @Override
    public int getCount(String title) {
        return bookDAO.getCount(title);
    }
}
