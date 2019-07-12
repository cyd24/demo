package com.jinke.writeContext;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ByteServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //使用response获得字节输出流
        ServletOutputStream out = response.getOutputStream();

        //获得服务器上的图片
        String path = this.getServletContext().getRealPath("/a.jpg");
        //System.out.println(path);

        //通过文件的路径将文件打散变成文件输入流
        FileInputStream in = new FileInputStream(path);

        //定义一个字节类型的数组,容量是1024字节
        int len=0;
        byte[] buffer=new byte[1024];
        while((len=in.read(buffer))>0)
        {
            out.write(buffer);
        }
        in.close();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
