package com.etc.library.controller;

import com.etc.library.domain.Book;
import com.etc.library.domain.PageBean;
import com.etc.library.service.BookService;
import com.etc.library.service.impl.BookServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet({"/queryAllBook.do", "/delBook.do", "/queryBookById.do", "/updateBook.do"})
public class BookServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        resultMap.clear();
        if ("queryAllBook.do".equals(action)) {
            int currPage = 1;
            try {
                currPage = Integer.parseInt(request.getParameter("currPage"));
            } catch (NumberFormatException e) {
            }
            String title = request.getParameter("title");
            int count = bookService.getCount(title);
            PageBean page = new PageBean(count, 5, currPage);
            List<Map<String, Object>> bookList = bookService.queryAllBook(page, title);
            if (bookList.size() > 0) {
                resultMap.put("code", 200);
                resultMap.put("msg", "查询成功");
                resultMap.put("result", bookList);
                resultMap.put("page", page);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "没有图书信息");
            }
        } else if ("delBook.do".equals(action)) {
            int bid = Integer.parseInt(request.getParameter("bid"));
            int result = bookService.delBook(bid);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "删除成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "删除失败");
            }
        } else if ("queryBookById.do".equals(action)) {
            int bid = Integer.parseInt(request.getParameter("bid"));
            Book book = bookService.queryBookById(bid);
            if (book != null) {
                resultMap.put("code", 200);
                resultMap.put("msg", "查询成功");
                resultMap.put("result", book);
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "没有找到指定编号的图书信息");
            }
        } else if ("updateBook.do".equals(action)) {
            int bid = Integer.parseInt(request.getParameter("bid"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String pubdate = request.getParameter("pubdate");
            double price = Double.parseDouble(request.getParameter("price"));
            String summary = request.getParameter("summary");
            int tid = Integer.parseInt(request.getParameter("tid"));
            Book book = new Book(bid, title, author, price, publisher, pubdate, summary, tid);
            int result = bookService.updateBook(book);
            if (result != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "编辑信息成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "编辑信息失败");
            }
        }
        out.print(gson.toJson(resultMap));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
