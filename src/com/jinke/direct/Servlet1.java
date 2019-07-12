package com.jinke.direct;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet1 extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //没有响应 告知客户端去重定向到servlet2
        //1、设置状态码302:重定向
        //response.setStatus(302);
        //2、设置响应头Location
        //response.setHeader("Location", "/10.Response/servlet2");
        //以上2行可以合并一行：功能相同,封装成一个重定向的方法sendRedirect(url)
        response.sendRedirect("/10.Response/servlet2");//经常使用重定向的方法!
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        }
}