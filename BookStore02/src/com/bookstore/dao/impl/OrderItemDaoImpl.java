package com.bookstore.dao.impl;

import com.bookstore.bean.OrderItem;
import com.bookstore.dao.BaseDao;
import com.bookstore.dao.OrderItemDao;
import com.sun.javafx.geom.transform.SingularMatrixException;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public void insertOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO order_item(count,amount,title,author,price,img_path,order_id) VALUES(?,?,?,?,?,?,?)";
        this.update(sql,orderItem.getCount(),orderItem.getAmount(),orderItem.getTitle(),orderItem.getAuthor(),orderItem.getPrice(),orderItem.getImgPath(),orderItem.getOrderId());
    }

    @Override
    public void insertOrderItem(Object[][] params) {
        String sql = "INSERT INTO order_item(count,amount,title,author,price,img_path,order_id) VALUES(?,?,?,?,?,?,?)";
        this.batchUpdate(sql,params);
    }
}
