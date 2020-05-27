package com.bookstore.servlet;

import com.bookstore.bean.Cart;
import com.bookstore.bean.User;
import com.bookstore.service.OrderService;
import com.bookstore.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //取值
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        User user = (User)session.getAttribute("user");
        //调用Service
        String orderId = orderService.createOrder(cart,user);
        session.setAttribute("orderId",orderId);
        //跳转
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");

    }

}
