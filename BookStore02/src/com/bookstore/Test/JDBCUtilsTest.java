package com.bookstore.Test;

import com.bookstore.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

public class JDBCUtilsTest {
    @Test
    public void testJDBCUtils(){



        System.out.println(JDBCUtils.getConnection());
    }

}
