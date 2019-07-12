package com.jinke.servletContext;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 1.获取web应用中任何资源的绝对路径
 * 方法：String path= context.getRealPath(相对于该web应用的相对地址
 * 2.ServletContext是一个域对象
 * 什么是域对象？什么是域？
 * 存储数据的区域就是域对象
 * ServletContext域对象的作用范围？
 * 所有的web资源都可以随意向servletcontext域中存取数据据，数据可以共享
 * */
public class ServletContext1 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.获得ServletContext对象
        ServletContext context = this.getServletContext();
        //2. 获取初化化参数
        String initParameter = context.getInitParameter("driver");
        System.out.println(initParameter);
        //3.域对象 ---向ServletContext中存入数据
        context.setAttribute("name", "张三");
        //4.获取当前项目中的文件路径
        String path = context.getRealPath("/a.txt");
        System.out.println(path);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}





