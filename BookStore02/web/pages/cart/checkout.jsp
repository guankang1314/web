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
<title>结算页面</title>
	<%@ include file="/WEB-INF/include/base.jsp"%>

	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">结算</span>
		<%@ include file="/WEB-INF/include/welcome.jsp" %>

	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为<span style="color:red">${sessionScope.orderId}</span></h1>
		
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>