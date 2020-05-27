package com.bookstore.service.impl;

import com.bookstore.bean.User;
import com.bookstore.dao.UserDao;
import com.bookstore.dao.impl.UserDaoimpl;
import com.bookstore.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoimpl();

    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }

    @Override
    public boolean checkUserName(String username) {
        return userDao.checkUserName(username);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
}
