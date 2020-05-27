package com.bookstore.dao;

import com.bookstore.bean.Book;
import com.bookstore.bean.Page;

import javax.swing.*;
import java.util.List;

public interface BookDao {

    public List<Book> getAllBooks();
    /*
    添加book信息
     */
    public void addBooks(Book book);

    public void delBookById(String id);

    /*
    修改book
    通过id获取book信息
    updatebook
     */
    public Book getBookById(String id);

    public void updateBook(Book book);

    public void updateBook(Object[][] params);


    /*
    实现分页查询
     */
    public Page<Book> getBooksByPage(Page<Book> page);
    public Page<Book> getBooksByPageAndPrice(Page<Book> page,Double min,Double max);

}
