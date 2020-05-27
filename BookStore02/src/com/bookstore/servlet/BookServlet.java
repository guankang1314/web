package com.bookstore.servlet;

import com.bookstore.bean.Book;
import com.bookstore.bean.Page;
import com.bookstore.service.BookService;
import com.bookstore.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /*protected void getAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用service中的相应方法
        List<Book> books = bookService.getAllBooks();
        request.setAttribute("books",books);
        //跳转，boo_manager.jsp
        request.getRequestDispatcher("pages/manager/book_manager.jsp").forward(request,response);
    }*/
    /*protected void addBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String author = request.getParameter("author");
        String sales = request.getParameter("sales");
        String stock = request.getParameter("stock");

        bookService.addBook(new Book(null,title,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock),null));

        //跳转重新查询
        getAllBooks(request,response);
    }*/
    protected void getBooksByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取值
        String pageNo = request.getParameter("pageNo");
        //调用service
        Page<Book> page = bookService.getBookByPage(pageNo);
        //将page存放到域中
        request.setAttribute("page", page);
        //跳转
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }
    protected void delBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //取值
        String bookId = request.getParameter("bookId");
        //调用service
        bookService.delBookById(bookId);
        //跳转
        //request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath()+"/BookServlet?method=getBooksByPage");
    }
    protected void getBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //取值
        String id = request.getParameter("bookId");
        //调用service
        Book book = bookService.getBookById(id);
        //将book存放在域中
        request.setAttribute("book",book);

        //跳转
        request.getRequestDispatcher("/pages/manager/book_update.jsp").forward(request,response);

    }
    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //取值

        String id = request.getParameter("bookId");
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String author = request.getParameter("author");
        String sales = request.getParameter("sales");
        String stock = request.getParameter("stock");
        //通过id是否为空判断执行方法
        if (id == null||"".equals(id)){
            //调用addBooks
            bookService.addBook(new Book(null,title,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock),null));
        }else {

            //调用update
            //调用service
            bookService.updateBook(new Book(Integer.parseInt(id),title,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock),null));

        }

        //request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath()+"/BookServlet?method=getBooksByPage");

    }
}
