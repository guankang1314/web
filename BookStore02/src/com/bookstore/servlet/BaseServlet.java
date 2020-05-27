package com.bookstore.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String method = request.getParameter("method");
        //使用反射通过方法名动态获取方法对象，从而执行该方法
        try {
            Method method2 = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            try {
                method2.invoke(this,request,response);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        if ("login".equals(method)) {
//
        //          //登录
        //        login(request,response);
//        }else if ("regist".equals(method)) {
//            //注册
//            regist(request,response);
//        }
    }
}
