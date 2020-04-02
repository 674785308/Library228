package com.etc.library.controller;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

public class BaseServlet extends HttpServlet {
    protected Map<String, Object> resultMap = new HashMap<>();
}
