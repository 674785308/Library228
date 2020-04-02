package com.etc.library.controller;

import com.etc.library.domain.Type;
import com.etc.library.service.TypeService;
import com.etc.library.service.impl.TypeServiceImpl;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.GOTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/queryAllType.do"})
public class TypeServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TypeService typeService = new TypeServiceImpl();
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        resultMap.clear();
        if ("queryAllType.do".equals(action)) {
            List<Type> typeList = typeService.queryAllType();
            resultMap.put("code", 200);
            resultMap.put("msg", "查询类别成功");
            resultMap.put("result", typeList);
        }
        out.print(gson.toJson(resultMap));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
