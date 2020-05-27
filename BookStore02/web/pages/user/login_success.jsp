<%--
Created by IntelliJ IDEA.
User: asus6878
Date: 2020-05-21
Time: 18:50
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%@ include file="/WEB-INF/include/base.jsp"%>

	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
			<%@ include file="/WEB-INF/include/welcome.jsp" %>

		</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="BookClientServlet?method=getBookByPage">转到主页</a></h1>
	
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>