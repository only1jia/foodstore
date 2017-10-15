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
		$(".pay").click(function() {
			var s = <%=session.getAttribute("username")%>;
			if (s === null) {
				alert("尚未登陆,请先登录");
				return false;
			}
			return true;
		});
		
		$(".delete").click(function(){
			
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			var flag = confirm("确定要删除" + title + "的信息吗?");
			if(flag){
				return true;
			}
			
			return false;
		});
		
		//ajax 修改单个商品的数量:
		//1. 获取页面中所有的 text, 并为其添加 onchange 响应函数. 弹出确认对话框: 确定要修改吗? 
		$(":text").change(function(){
			var quantityVal = $.trim(this.value);

			var flag = false;
			
			var reg = /^\d+$/g;
			var quantity = -1;
			if(reg.test(quantityVal)){
				quantity = parseInt(quantityVal);
				if(quantity >= 0){
					flag = true;
				}
			}
			
			if(!flag){
				alert("输入的数量不合法!");
				$(this).val($(this).attr("class"));
				return;
			}
			
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());

			if(quantity == 0){
				var flag2 = confirm("确定要删除" + title + "吗?");
				if(flag2){
					//得到了 a 节点
					var $a = $tr.find("td:last").find("a");
					//执行 a 节点的 onclick 响应函数. 
					$a[0].onclick();
					
					return;
				}
			}
			
			var flag = confirm("确定要修改" + title + "的数量吗?");
			
			if(!flag){
				$(this).val($(this).attr("class"));
				return;
			}
			//2. 请求地址为: foodServlet
			var url = "foodServlet";
			
			//3. 请求参数为: method:updateItemQuantity, id:name属性值, quantity:val, time:new Date()
			var idVal = $.trim(this.name);
			var args = {"method":"updateItemQuantity", "id":idVal, "quantity":quantityVal, "time":new Date()};
			
			//4. 在 updateItemQuantity 方法中, 获取 quanity, id, 再获取购物车对象, 调用 service 的方法做修改
			//5. 传回 JSON 数据: foodNumber:xx, totalMoney
			
			//6. 更新当前页面的 foodNumber 和 totalMoney
			$.post(url, args, function(data){
				var foodNumber = data.foodNumber;
				var totalMoney = data.totalMoney;
				
				$("#totalMoney").text("总金额: ￥" + totalMoney);
				$("#foodNumber").text("您的购物车中共有" + foodNumber + "份订单");
			},"JSON");
			
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
		
		<br><br>
		<div id="foodNumber">您的购物车中共有 ${sessionScope.ShoppingCart.foodNumber } 份订单</div>
		<table cellpadding="10">
			<tr>
				<td>Title</td>
				<td>Quantity</td>
				<td>Price</td>
				<td>&nbsp;</td>
			</tr>
			
			<c:forEach items="${sessionScope.ShoppingCart.items }" var="item">
				<tr>
					<td>${item.food.title }</td>
					<td> 
						<input class="${item.quantity }" type="text" size="1" name="${item.food.id }" value="${item.quantity }"/>
					</td>
					<td>${item.food.price }</td>
					<td><a href="foodServlet?method=remove&pageNo=${param.pageNo }&id=${item.food.id }" class="delete">删除</a></td>
				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="4" id="totalMoney">总金额: ￥ ${ sessionScope.ShoppingCart.totalMoney}</td>
			</tr>
			
			<tr>
				<td colspan="4">
					<a href="foodServlet?method=getFoods&pageNo=${param.pageNo }">继续购物</a>
					&nbsp;&nbsp;
					
					<a href="foodServlet?method=clear">清空购物车</a>
					&nbsp;&nbsp;
					
					<a href="foodServlet?method=forwardPage&page=cash" class="pay">结账</a>
					&nbsp;&nbsp;
				</td>
			</tr>
			
		</table>
		
	</center>
	
	<br><br>
	
	
</body>
</html>