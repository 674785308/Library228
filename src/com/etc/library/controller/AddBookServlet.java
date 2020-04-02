package com.etc.library.controller;

import com.etc.library.domain.Book;
import com.etc.library.service.BookService;
import com.etc.library.service.impl.BookServiceImpl;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/addBook.do")
public class AddBookServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        //创建一个解析工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置编码
        upload.setHeaderEncoding("UTF-8");

        Book book = new Book();

        try {
            //解析请求对象
            List<FileItem> items = upload.parseRequest(request);
            System.out.println(items);
            Map<String, Object> tempMap = new HashMap<>();
            for (FileItem item : items) {
                if (item.isFormField()) {
                    tempMap.put(item.getFieldName(), new String(item.getString().getBytes("ISO-8859-1"), "UTF-8"));
                } else {
                    //http://localhost:8080/Library/image/a.jpg
                    String oldName = item.getName();
                    String newName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(System.currentTimeMillis()) + oldName.substring(oldName.lastIndexOf("."));

                    String path = getServletContext().getRealPath("upload");
                    if (!new File(path).exists()) {
                        new File(path).mkdirs();
                    }
                    File file = new File(path, newName);
                    //获取文件输入流
                    InputStream is = item.getInputStream();
                    //创建一个输出流对象
                    FileOutputStream fos = new FileOutputStream(file);

                    byte[] b = new byte[1024 * 8];
                    int len;
                    while ((len = is.read(b)) != -1) {
                        fos.write(b, 0, len);
                        fos.flush();
                    }
                    fos.close();
                    is.close();
                    tempMap.put("cover", "/upload/" + newName);
                }

            }
            System.out.println(tempMap);
            book.setTitle(tempMap.get("title").toString());
            book.setSummary(tempMap.get("summary").toString());
            book.setPubdate(tempMap.get("pubdate").toString());
            book.setPublisher(tempMap.get("publisher").toString());
            book.setPrice(Double.parseDouble(tempMap.get("price").toString()));
            book.setCover(tempMap.get("cover").toString());
            book.setAuthor(tempMap.get("author").toString());
            book.setTid(Integer.parseInt(tempMap.get("tid").toString()));

            int index = bookService.addBook(book);
            resultMap.clear();
            if (index != -1) {
                resultMap.put("code", 200);
                resultMap.put("msg", "添加图书成功");
            } else {
                resultMap.put("code", 201);
                resultMap.put("msg", "添加图书失败");
            }
            out.print(gson.toJson(resultMap));

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
