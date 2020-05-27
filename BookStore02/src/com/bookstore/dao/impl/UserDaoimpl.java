package com.bookstore.dao.impl;

import com.bookstore.bean.User;
import com.bookstore.dao.BaseDao;
import com.bookstore.dao.UserDao;

public class UserDaoimpl  extends BaseDao<User> implements UserDao {


    @Override
    public User getUser(User user) {
        String sql = "select id,username,password,email from users where username =? and password = ?";
        return this.getBean(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public boolean checkUserName(String username) {
        String sql = "select id,username,password,email from users where username =?";
        User user = this.getBean(sql,username);
        return user!=null;
    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into users(username,password,email) values(?,?,?)";
        this.update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
