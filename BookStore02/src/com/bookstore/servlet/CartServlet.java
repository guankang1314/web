package com.bookstore.servlet;

import com.bookstore.bean.Book;
import com.bookstore.bean.Cart;
import com.bookstore.service.BookService;
import com.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();
    protected void addBookToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //取bookId值
        String bookId = request.getParameter("bookId");
        //通过bookId获取Book(BookService)

        Book book = bookService.getBookById(bookId);
        //调用Cart中的addBookToCart()
        //Cart存放Session域中
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
            //存放到Session域中
            session.setAttribute("cart", cart);
        }
        cart.addBookToCart(book);
        //验证库存问题
        Integer stock = book.getStock();
        int count = cart.getMap().get(book.getId()+"").getCount();
        if (count>stock) {
            //库存不足
            session.setAttribute("msg","库存不足,只剩"+stock+"件商品！");
            //将购买商品的数量,设置为最大库存
            cart.getMap().get(book.getId()+"").setCount(stock);
        }else {
            session.setAttribute("title",book.getTitle());
        }

        //跳转
        //获取Referer:跳转
        String url = request.getHeader("Referer");
        response.sendRedirect(url);
    }
    protected void delCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        //取bookid值
        String bookId = request.getParameter("bookId");
        //调用Cart
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart!=null) {
            cart.delCartItem(bookId);
        }
        //跳转
        response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
    }
    protected void emptyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart!=null){
            cart.emptyCart();
        }
        //response.sendRedirect(request.getContextPath()+"/index.jsp");
        response.sendRedirect(request.getContextPath()+"pages/cart/cart.jsp");
    }
    protected void updateCartItemCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取bookId,count值
        HttpSession session = request.getSession();
        String bookId = request.getParameter("bookId");
        String count = request.getParameter("count");
        //调用cart
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart!=null) {
            cart.updateCartItemCount(bookId,count);
        }
        //response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
        int totalCount = cart.getTotalCount();
        double totalAmount = cart.getTotalAmount();
        double amount = cart.getMap().get(bookId).getAmount();
        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalAmount",totalAmount);
        map.put("amount",amount);
        //将数据封装为jsonString(Gson)
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        //响应
        response.getWriter().write(jsonStr);
    }
}
