package com.etc.test;

import com.etc.library.domain.Book;
import com.etc.library.service.BookService;
import com.etc.library.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class BookTest {
    BookService service = null;

    @Before
    public void testBefore() {
        service = new BookServiceImpl();
    }

    @Test
    public void testQueryAllBook() {

        /*List<Map<String, Object>> books = service.queryAllBook(null);
        System.out.println(books);*/
    }

    @Test
    public void testAddBook() {
        Book book = new Book("Java从入门到精通", "张老三", "", 89, "清华大学出版社", "2020-01-05", " “软件开发视频大讲堂”丛书系清华社“视频大讲堂”重点大系之一。该大系包括多个子系列，每个子系列的图书在其同品种的图书中销售名列前茅，其中：\n" +
                "\n" +
                "      4个品种荣获“全行业优秀畅销品种”\n" +
                "\n" +
                "      1个品种荣获2012年清华大学出版社“专业畅销书”一等奖\n" +
                "\n" +
                "      绝大多数品种在“全国计算机零售图书排行榜”同品种排行中名列前茅\n" +
                "\n" +
                "      截至目前该大系累计销售超过130万册\n" +
                "\n" +
                "      该大系已成为近年来清华社计算机专业基础类零售图书*畅销的品牌之一\n" +
                "\n" +
                "\n" +
                "\n" +
                "       “软件开发视频大讲堂”系列作为清华社“视频大讲堂”大系的子系列之一，继承和创新了清华社“视频大讲堂”大系的编写模式、写作风格和优良品质。\n" +
                "\n" +
                "      \n" +
                "\n" +
                "      “软件开发视频大讲堂”系列作为清华社“视频大讲堂”大系的子系列之一，执着于专业，精细于品质。\n" +
                "\n" +
                "      集基础知识、核心技能、高级应用、项目案例于一体\n" +
                "\n" +
                "      好学、好用、高效 ",7);
        int index = service.addBook(book);
        if (index != -1) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }
}
