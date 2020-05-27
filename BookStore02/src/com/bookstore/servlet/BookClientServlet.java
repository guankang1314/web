package com.bookstore.servlet;

import com.bookstore.bean.Book;
import com.bookstore.bean.Page;
import com.bookstore.service.BookService;
import com.bookstore.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookClientServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void getBookByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取值
        String pageNo = request.getParameter("pageNo");
        //调用
        Page<Book> page = bookService.getBookByPage(pageNo);
        //将page存放在域中
        request.setAttribute("page",page);
        //跳转
        request.getRequestDispatcher("/pages/client/book_client.jsp").forward(request,response);

    }
    /*
    带价格区间的分页查询
     */
    protected void getBookByPageANDPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取值
        String pageNo = request.getParameter("pageNo");
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        //调用方法
        Page<Book> page = bookService.getBookByPageANDPrice(pageNo,min,max);
        //将数据放入域中
        request.setAttribute("page",page);
        //跳转
        request.getRequestDispatcher("/pages/client/book_client.jsp").forward(request,response);
    }
}
