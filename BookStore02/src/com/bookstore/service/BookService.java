package com.bookstore.service;

import com.bookstore.bean.Book;
import com.bookstore.bean.Page;

import java.util.List;

public interface BookService {

    public List<Book> getAllBooks();
    public void addBook(Book book);
    public void delBookById(String id);

    public Book getBookById(String id);

    public void updateBook(Book book);
    /**
     * 分页查询
     */
    public Page<Book> getBookByPage(String pageNo);
    public Page<Book> getBookByPageANDPrice(String pageNo,String min,String max);

}
