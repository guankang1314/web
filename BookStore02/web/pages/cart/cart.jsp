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
<title>购物车</title>
<!-- <base href="http://localhost:8080/BookStore02/"> -->
	<%@ include file="/WEB-INF/include/base.jsp"%>
	<script type="text/javascript">
		$(function () {
			$(".cartItemCount").change(function () {
				//获取默认值
				var dValue = this.defaultValue;
				var stock = $(this).attr("name");
				var bookId = $(this).attr("id");

				var count = $(this).val();
				//定义正则规则(非零的正整数)
				var countReg = /^\+?[1-9][0-9]*$/
				if (!countReg.test(count)) {
					alert("购买的数量输入有误,请重新输入！(非零的正整数) ");
					$(this).val(dValue);
					return false;
				}
				//验证库存
				if (parseInt(count)>parseInt(stock)) {
					alert("库存不足,库存仅剩"+stock+"件商品!");
					$(this).val(dValue);
					return false;
				}

				//调用CartServlet

				//location = "CartServlet?method=updateCartItemCount&bookId="+bookId+"&count="+count;
				var  $amountTr = $(this).parent().next().next();
				//ajax方式
				$.getJSON("CartServlet?method=updateCartItemCount",{"bookId":bookId,"count":count},function (msg) {

					//alert(msg.totalCount)
					$(".b_count").html(msg.totalCount);
					$(".b_price").html(msg.totalAmount);
					$amountTr.html(msg.amount);
				})
			})
		})
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@ include file="/WEB-INF/include/welcome.jsp" %>

	</div>
	
	<div id="main">
		<c:if test="${empty sessionScope.cart.cartItems}">
			<h1 align="center">购物车中暂无数据，快来<a href="index.jsp" style="color: red">购物</a>呀!</h1>
		</c:if>

		<c:if test="${not empty sessionScope.cart.cartItems}">
			<table>
				<tr>
					<td>商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
				<c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
					<tr>
						<td>${cartItem.book.title}</td>
						<td>
							<input id="${cartItem.book.id}" type="text" class="cartItemCount" name="${cartItem.book.stock}" value="${cartItem.count}" size="2" style="text-align: center" >
						</td>
						<td>${cartItem.book.price}</td>
						<td>${cartItem.amount}</td>
						<td><a href="CartServlet?method=delCartItem&bookId=${cartItem.book.id}">删除</a></td>
					</tr>

				</c:forEach>

			</table>
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalAmount}</span>元</span>
				<span class="cart_span"><a href="CartServlet?method=emptyCart">清空购物车</a></span>
				<span class="cart_span"><a href="OrderServlet?method=checkout">去结账</a></span>
				<span class="cart_span"><a href="index.jsp">继续购物</a></span>

			</div>
		</c:if>

		

	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>