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
<title>图书管理</title>
	<%@ include file="/WEB-INF/include/base.jsp"%>
	<script type="text/javascript">

		$(function () {
			$(".dela").click(function () {
				var title = $(this).parents("tr").children().html();
				if (confirm("确定删除【"+title+"】吗?")==false) {
					return false;
				}
			});
			//实现确定按钮分页查询
			$("#sub_page").click(function () {
				//取pageNo值
				var pageNo1 = $("#pn_input").val();
				//请求BookServlet
				location = "BookServlet?method=getBooksByPage&pageNo="+pageNo1;
			});
		});


	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@ include file="/WEB-INF/include/header.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.list}" var="book">
				<tr>
					<td>${book.title}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="BookServlet?method=getBookById&bookId=${book.id}">修改</a></td>
					<td><a class="dela" href="BookServlet?method=delBookById&bookId=${book.id}">删除</a></td>
				</tr>
			</c:forEach>



			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_update.jsp">添加图书</a></td>
			</tr>	
		</table>

		<br>
		<br>
		<div id="page_nav">
			<a href="BookServlet?method=getBooksByPage&pageNo=1">首页</a>
			<c:if test="${page.pageNo>1}">
				<a href="BookServlet?method=getBooksByPage&pageNo=${page.pageNo-1}">上一页</a>

			</c:if>
			<c:if test="${page.pageNo<page.totalPageNo}">
				<a href="BookServlet?method=getBooksByPage&pageNo=${page.pageNo+1}">下一页</a>

			</c:if>
			<a href="BookServlet?method=getBooksByPage&pageNo=${page.totalPageNo}">末页</a>
			共${requestScope.page.pageNo }/${requestScope.page.totalPageNo }页，${requestScope.page.totalRecord}条记录
			到第<input value="${requestScope.page.pageNo }" name="pn" id="pn_input"/>页
			<input id="sub_page" type="button" value="确定">
		</div>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>