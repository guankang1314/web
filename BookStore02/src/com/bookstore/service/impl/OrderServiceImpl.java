package com.bookstore.service.impl;

import com.bookstore.bean.*;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.OrderItemDao;
import com.bookstore.dao.impl.BookDaoImpl;
import com.bookstore.dao.impl.OrderDaoImpl;
import com.bookstore.dao.impl.OrderItemDaoImpl;
import com.bookstore.service.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, User user) {
        //生成订单

        String orderId =  System.currentTimeMillis()+""+user.getId();
        orderDao.insertOrder(new Order(orderId,new Date(),cart.getTotalCount(),
                cart.getTotalAmount(),0,user.getId()));
        //生成订单详情
        //获取所有购物项
        List<CartItem> cartItems = cart.getCartItems();

        //二维参数
        Object[][] orderItemParams = new Object[cartItems.size()][];
        Object[][] bookParams = new Object[cartItems.size()][];
        //遍历购物项，添加到订单详情
        for (int i=0;i<cartItems.size();i++) {


            CartItem cartItem = cartItems.get(i);
            Book book = cartItem.getBook();
            int count = cartItem.getCount();

//            orderItemDao.insertOrderItem(new OrderItem(null,cartItem.getCount(),cartItem.getAmount()
//            ,book.getTitle(),book.getAuthor(),book.getPrice(),book.getImgPath(),orderId));
            orderItemParams[i] = new Object[]{cartItem.getCount(),cartItem.getAmount()
            ,book.getTitle(),book.getAuthor(),book.getPrice(),book.getImgPath(),orderId};

            //更改相应book的库存和销量
            int stock = book.getStock()-count;
            int sales = book.getSales()+count;
            bookParams[i] = new Object[]{sales,stock,book.getId()};
//            bookDao.updateBook(stock,sales,book.getId());
        }

        orderItemDao.insertOrderItem(orderItemParams);
        bookDao.updateBook(bookParams);
        //清空购物车
        cart.emptyCart();
        return orderId;
    }
}
