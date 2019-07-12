package com.jinke.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.jinke.domain.User;
import com.jinke.utils.DataSourceUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class LoginServlet extends HttpServlet {
    public void init() throws ServletException {
        //在ServletContext域中存一个数据 的count
        int count=0;
        ServletContext context = this.getServletContext();//获取ServletContext域对象,一个web应用只有一个
        context.setAttribute("count", count);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //解决中文乱码问题
        //获取中文乱码问题：
        request.setCharacterEncoding("UTF-8");
        //响应中文乱码问题：
        response.setContentType("text/html;charset=utf-8");

        //1.获取用户提交的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
		/*System.out.println("username="+username+"password="+password);
		response.getWriter().write("username="+username+"password="+password);*/

        // 2.创建核心类QueryRunner
        //创建一个与数据库关联的queryRunner对象，后期再操作数据库的时候，不需要Connection对象，自动管理事务。DataSourceUtils：数据库连接池对象。
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

        // 3.填写SQL语句
        String sql = "select * from user where username=? and password=?";

        // 4.为占位符赋值,参数放在数组中
        Object[] params={username,password};

        //5.执行SQL
        User user=null;
        try {
            user = qr.query(sql, new BeanHandler<User>(User.class), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //6.判断是否登录成功
        if(user!=null)
        {
            //从servletcontext中获取count进行++运算,统计访问计次数
            ServletContext context = this.getServletContext();//获取ServletContext域对象,一个web应用只有一个
            Integer count=(Integer)context.getAttribute("count");
            count++;
            //登录成功
            response.getWriter().write("登录成功"+username+",你是第"+count+"位访问者！");
            context.setAttribute("count",count);//重新给count赋值，让下一次访问在此值基础上加1
			request.setAttribute("username", username);
//			request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        }
        else
        {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
