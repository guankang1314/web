package com.bookstore.filter;

import com.bookstore.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckLoginFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //判断是否登录
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            //未登录
            request.setAttribute("Msg","请先登录!");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            chain.doFilter(request,response);
        }
    }
}
