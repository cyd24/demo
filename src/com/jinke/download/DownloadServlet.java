package com.jinke.download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获得要下载的文件的名称
        String filename = request.getParameter("filename");
        //System.out.println(filename);

        //要下载的这个文件的类型-----客户端通过文件的MIME类型去区分类型
        //System.out.println(this.getServletContext().getMimeType(filename));
        response.setContentType(this.getServletContext().getMimeType(filename));

        //告诉客户端该文件不是直接解析 而是以附件形式打开(下载)
        response.setHeader("Content-Disposition", "attachment;filename="+filename);

        //获得服务器上的图片
        String path = this.getServletContext().getRealPath(filename);

        //通过文件的路径将文件打散变成文件输入流
        FileInputStream in = new FileInputStream(path);

        //使用response获得字节输出流--- 用于向客户端写内容
        ServletOutputStream out = response.getOutputStream();

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