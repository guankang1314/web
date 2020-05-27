package com.bookstore.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CharSetFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String code = this.getFilterConfig().getInitParameter("code");

        request.setCharacterEncoding(code);
        response.setContentType("text/html;charset=UTF-8");
        //放行
        chain.doFilter(request,response);
    }
}
