package com.bookstore.dao.impl;

import com.bookstore.bean.User;
import com.bookstore.dao.UserDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoimplTest {

    private UserDao userDao = new UserDaoimpl();
    @Test
    public void getUser() {
        User user = userDao.getUser(new User(null,"test01","test01",null));
        System.out.println(user);
    }

    @Test
    public void checkUserName() {
        boolean yOn = userDao.checkUserName("test01");
        System.out.println(yOn);
    }
}