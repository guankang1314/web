package com.bookstore.servlet;

import com.bookstore.bean.User;
import com.bookstore.service.UserService;
import com.bookstore.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserServlet<Menthod> extends BaseServlet {

    UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

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

            session.setAttribute("user",user);
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        //获取验证码
        String code = request.getParameter("code");
        //获取session中的验证码文本
        Object code2 = session.getAttribute("KAPTCHA_SESSION_KEY");
        //判断验证输入是否正确
        boolean yOn = userService.checkUserName(username);

        if (code2!=null && code2.toString().equals(code)) {
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
        }else {
            //验证码错误
            request.setAttribute("Msg","验证码错误！");
            request.getRequestDispatcher("pages/user/regist.jsp").forward(request,response);
        }



    }
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将user从session域中移除
        request.getSession().removeAttribute("user");
        //跳转首页
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
    protected void checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        String username = request.getParameter("uname");

        //调用service
        boolean yOn = userService.checkUserName(username);
        //响应数据
//        if (yOn) {
//            writer.write("用户名已存在,请重新输入!");
//        }else {
//            //用户名可用
//            writer.write("用户名可用！");
//        }
        writer.print(yOn);
    }

}
