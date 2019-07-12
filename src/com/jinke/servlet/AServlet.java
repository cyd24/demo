package com.jinke.servlet;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AServlet implements Servlet {
    /**
     * 方法介绍如下：

     1.destroy：被servlet容器调用，用于表示servlet已经被移除

     2.getServletConfig：返回一个ServletConfig对象，包含servlet的初始化信息和启动信息

     3.getServletInfo：获取servlet的作者，版本号，版权等信息。

     4.init：被servlet容器调用，用于表示servlet已经被创建。

     5.service：被servlet容器调用，用于允许servlet响应请求
     */

    public void destroy() {
        System.out.println("关闭服务器 ");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "This is my default servlet created by Eclipse";
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void init(ServletConfig arg0) throws ServletException {
        System.out.println("我在运行时就被执行！只执行一次！");
        System.out.println("被servlet容器调用，用于表示servlet已经被创建,只执行一次");
    }

    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        System.out.println("每次都要执行一次！");
        response.getWriter().write("欢迎学习Servlet!!!");
    }
}