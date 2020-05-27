package com.bookstore.dao.impl;

import com.bookstore.bean.Book;
import com.bookstore.bean.Page;
import com.bookstore.dao.BaseDao;
import com.bookstore.dao.BookDao;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> getAllBooks() {
        String sql = "select id,title,author,price,sales,stock,img_path from books";

        return this.getBeanList(sql);
    }

    @Override
    public void addBooks(Book book) {
        String sql = "insert into books(title,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        this.update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public void delBookById(String id) {
        String sql = "delete from books where id = ?";
        this.update(sql,id);
    }

    @Override
    public Book getBookById(String id) {
        String sql = "select id,title,author,price,sales,stock,img_path from books where id = ?";

        return this.getBean(sql,id);
    }

    @Override
    public void updateBook(Book book) {
        String sql = "update books set title=?,author=?,price=?,sales=?,stock=? where id=?";
        this.update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getId());
    }

    @Override
    public void updateBook(Object[][] params) {
        String sql = "update books set sales=?,stock=? where id=?";
        this.batchUpdate(sql,params);
    }

    @Override
    public Page<Book> getBooksByPage(Page<Book> page) {
        String sql = "select count(*) from books";
        int count = Integer.parseInt(this.getSingeValue(sql)+"");//long
        page.setTotalRecord(count);
        System.out.println("record:"+page.getTotalRecord());
        System.out.println("pageNo:"+page.getTotalPageNo());
        //dao:list

        String sql2 = "SELECT id,title,author,price,sales,stock,img_path "
                + "FROM books "
                + "WHERE 1=1 "
                + "LIMIT ?,?";
        List<Book> list = this.getBeanList(sql2, (page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
        page.setList(list);
        return page;
    }

    @Override
    public Page<Book> getBooksByPageAndPrice(Page<Book> page, Double min,Double max) {
        String sql = "SELECT count(*) "
                + "FROM books "
                + "WHERE 1=1 "
                + "AND price BETWEEN ? AND ?";
        int count = Integer.parseInt(this.getSingeValue(sql, min,max)+"");  //long（int） Integer.parse
        //将totalRecord赋值
        page.setTotalRecord(count);
        //dao:list
        String sql2 = "SELECT id,title,author,price,sales,stock,img_path "
                + "FROM books "
                + "WHERE 1=1 "
                + "AND price BETWEEN ? AND ? "
                + "LIMIT ?,?";
        List<Book> list = this.getBeanList(sql2, min,max,(page.getPageNo()-1)*Page.PAGE_SIZE,Page.PAGE_SIZE);
        //将list存放到page中
        page.setList(list);
        return page;
    }


}
