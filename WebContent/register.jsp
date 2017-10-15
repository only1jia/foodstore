<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>註冊頁面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
<center>
	<h1>注册界面</h1>
	<form action="userServlet?method=register" method="POST">
	<table>
	<tr>
	<td>用户名:</td>
	<td><input type="text" name="username"></td>
	</tr>
	<tr>
	<td>密码:</td>
	<td><input type="password" name="password1"></td>
	</tr>
	<tr>
	<td>再次输入密码:</td>
	<td><input type="password" name="password2"></td>
	</tr>
	<tr>
	<td>手机号码:</td>
	<td><input type="text" name="telephone"></td>
	</tr>
	<tr>
	<td><input type="submit" name="submit" value="完成"></td>
	<td><a href="<%=basePath%>login.jsp">点击返回登陆页面</a></td>
	</tr>
	</table>

	</form>
</center>
	
</body>

</html>