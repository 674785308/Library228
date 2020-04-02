package com.etc.library.controller;

import com.etc.library.domain.User;
import com.etc.library.service.UserService;
import com.etc.library.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/login.do"})
public class UserServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/") + 1);
        UserService userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        resultMap.clear();
        if ("login.do".equals(action)) {
            String account = request.getParameter("account");
            String password = request.getParameter("password");

            User user = userService.login(account);
            if (user == null) {
                resultMap.put("code", 301);
                resultMap.put("msg", "用户名不存在");
            } else if (!password.equals(user.getPassword())) {
                resultMap.put("code", 302);
                resultMap.put("msg", "密码错误");
            } else {
                session.setAttribute("user", user);
                resultMap.put("code", 200);
                resultMap.put("msg", "登录成功");
                resultMap.put("result", user);
            }

        }
        out.print(gson.toJson(resultMap));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
