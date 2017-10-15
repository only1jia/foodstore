<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		
		$("#pageNo").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			
			//1. 校验 val 是否为数字 1, 2, 而不是 a12, b
			var flag = false;
			var reg = /^\d+$/g;
			var pageNo = 0;
			
			if(reg.test(val)){
				//2. 校验 val 在一个合法的范围内： 1-totalPageNumber
				pageNo = parseInt(val);
				if(pageNo >= 1 && pageNo <= parseInt("${foodpage.totalPageNumber }")){
					flag = true;
				}
			}
			
			
			if(!flag){
				alert("输入的不是合法的页码.");
				$(this).val("");
				return;
			}
			
			//3. 页面跳转
			var href = "foodServlet?method=getfoods&pageNo=" + pageNo + "&" + $(":hidden").serialize();
			window.location.href = href;
		});
	})
	
</script>

</head>
<body>
	<%
	 if(session.getAttribute("username") == null) { %>
	 	<a href="login.jsp">登陆</a>
	<%} else {
		out.print("用户：" + session.getAttribute("username")); %>
		<form action="userServlet?method=logout" method="post">
			<input type="submit" value="登出" />
		</form>
	<%}
	%>
	
	<center>
		<c:if test="${param.title != null}">
			您已经将 <% String obj = (String)request.getParameter("title");
		byte[] b = obj.getBytes("UTF-8");
		String title = new String(b, "UTF-8");
		out.print(title);
		%>  放入到购物车中. 
			<br><br>
		</c:if>
		
		<c:if test="${!empty sessionScope.ShoppingCart.foods }">
			您的购物车中有 ${sessionScope.ShoppingCart.foodNumber } 份订单, <a href="foodServlet?method=forwardPage&page=cart&pageNo=${foodpage.pageNo }">查看购物车</a>
		</c:if>
		
		<br><br>
		<!-- <form action="foodServlet?method=getFoods" method="post">
			Price: 
			<input type="text" size="1" name="minPrice"/> - 
			<input type="text" size="1" name="maxPrice"/>
			
			<input type="submit" value="Submit"/>
		</form>
		-->
		<h2>菜单</h2>
 		<table cellpadding="10">
			
			<c:forEach items="${foodpage.list }" var="food">
				<tr>
					<td>
						<a href="foodServlet?method=getFood&pageNo=${foodpage.pageNo }&id=${food.id}">${food.title }</a>
						<br>
					</td>
					<td>${food.price } /份</td>
					<td><a href="foodServlet?method=addToCart&pageNo=${foodpage.pageNo }&id=${food.id}&title=${food.title}">加入购物车</a></td>
				</tr>
			</c:forEach>
			
		</table>
		
		<br><br>
		共 ${foodpage.totalPageNumber } 页
		&nbsp;&nbsp;
		当前第 ${foodpage.pageNo } 页		
		&nbsp;&nbsp;
		
		<c:if test="${foodpage.hasPrev }">
			<a href="foodServlet?method=getFoods&pageNo=1">首页</a>
			&nbsp;&nbsp;
			<a href="foodServlet?method=getFoods&pageNo=${foodpage.prevPage }">上一页</a>
		</c:if>

		&nbsp;&nbsp;
		
		<c:if test="${foodpage.hasNext }">
			<a href="foodServlet?method=getFoods&pageNo=${foodpage.nextPage }">下一页</a>
			&nbsp;&nbsp;
			<a href="foodServlet?method=getFoods&pageNo=${foodpage.totalPageNumber }">末页</a>
		</c:if>
		
		&nbsp;&nbsp;
		
		转到 <input type="text" size="1" id="pageNo"/> 页		
			
	</center>
	
</body>
</html>