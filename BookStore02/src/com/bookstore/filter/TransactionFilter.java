package com.bookstore.filter;

import com.bookstore.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        Connection connection = JDBCUtils.getConnection();
        try {
            //开启事务
            connection.setAutoCommit(false);
            //放行
            chain.doFilter(request,response);
            //无异常，提交事务
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //有异常，回滚事务
            try {
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath()+"/pages/error/transaction_error.jsp");
            }
        } finally {
            JDBCUtils.close();
        }
    }
}
