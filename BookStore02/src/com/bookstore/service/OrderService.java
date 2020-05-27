package com.bookstore.service;

import com.bookstore.bean.Cart;
import com.bookstore.bean.User;

public interface OrderService {
    public String createOrder(Cart cart, User user);
}
