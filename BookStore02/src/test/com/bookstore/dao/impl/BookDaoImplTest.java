package com.bookstore.dao.impl;

import com.bookstore.bean.Book;
import com.bookstore.bean.Page;
import com.bookstore.dao.BookDao;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void testGetAllBooks() {
        List<Book> allBooks = bookDao.getAllBooks();
        for (Book book : allBooks) {
            System.out.println(book);
        }
    }
    @Test
    public void testaddBooks(){
        bookDao.addBooks(new Book(null,"testTitle","testAuthor",100,200,250,null));

    }
    @Test
    public void testGetBooksByPage(){
        Page<Book> page = new Page<>();
        bookDao.getBooksByPage(page);
        page.setPageNo(1);
        Page<Book> page2 = bookDao.getBooksByPage(page);

        System.out.println(page.getPageNo()+"/"+page.getTotalPageNo());
        System.out.println("总记录数："+page.getTotalRecord());//   5/19
        for (Book book : page2.getList()) {
            System.out.println(book);
        }
    }


    }