package com.bookstore.dao;

import com.bookstore.bean.User;

public interface UserDao {
    User getUser(User user);

    boolean checkUserName(String username);
    void saveUser(User user);

}
