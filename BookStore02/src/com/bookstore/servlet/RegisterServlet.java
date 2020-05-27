package com.bookstore.servlet;

import com.bookstore.bean.User;
import com.bookstore.service.UserService;
import com.bookstore.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        boolean yOn = userService.checkUserName(username);
        if (yOn) {

            //用户名存在
            request.setAttribute("Msg","用户名已存在，请重新输入！");
            request.getRequestDispatcher("pages/user/regist.jsp").forward(request,response);
        }else {
            //用户名不存在
            System.out.println("saveUser");
            userService.saveUser(new User(null,username,password,email));
            response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
        }

    }
}
