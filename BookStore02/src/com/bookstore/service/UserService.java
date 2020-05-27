package com.bookstore.service;

import com.bookstore.bean.User;

public interface UserService {

    User getUser(User user);

    boolean checkUserName(String username);

    void saveUser(User user);

}
