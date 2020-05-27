package com.bookstore.dao;

import com.bookstore.bean.OrderItem;

public interface OrderItemDao {

    public void insertOrderItem(OrderItem orderItem);

    public void insertOrderItem(Object[][] params);
}
