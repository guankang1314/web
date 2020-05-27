package com.bookstore.dao.impl;

import com.bookstore.bean.Order;
import com.bookstore.bean.OrderItem;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.OrderItemDao;
import org.junit.Test;

import java.util.Date;

public class OrderDaoImplTest {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao oid = new OrderItemDaoImpl();
    @Test
    public void testInsertOrder() {
        orderDao.insertOrder(new Order("test01",new Date(),2,300,0,1));
    }
    @Test
    public void testInsertOrder2() {
        oid.insertOrderItem(new OrderItem(null,1,150,"testt","test0df",150,"testtt","test01"));
    }

}
