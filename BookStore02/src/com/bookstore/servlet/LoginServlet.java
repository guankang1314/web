package com.bookstore.servlet;

import com.bookstore.bean.User;
import com.bookstore.dao.UserDao;
import com.bookstore.dao.impl.UserDaoimpl;
import com.bookstore.service.UserService;
import com.bookstore.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //UserDao userDao =new UserDaoimpl();

        UserService userService = new UserServiceImpl();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.getUser(new User(null,username,password,null));
        if (user ==null){
            //登陆失败
            //标记，存放数据
            request.setAttribute("Msg","用户名或密码有误，请重新输入!");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            //登陆成功

            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }

    }
}
