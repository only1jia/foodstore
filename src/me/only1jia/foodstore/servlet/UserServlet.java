package me.only1jia.foodstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.only1jia.foodstore.domain.User;
import me.only1jia.foodstore.service.UserService;


/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		获取 username 请求参数的值
		
		
		String method = request.getParameter("method");  
	    if("login".equals(method)) {//登录  
	    	String username = request.getParameter("username");
	    	String password = request.getParameter("password"); 
	        if(username == null || "".equals(username.trim()) || password == null
	        		|| "".equals(password.trim())){
	        	System.out.println("用户名或密码不能为空！");  
	            response.sendRedirect("login.jsp");  
	            return;  
	        }
	        boolean isValid = false;
	        User user = userService.getUserByUserName(username);
	        if (user != null && user.getPassword().equals(password)) isValid = true;
	        if(isValid) {
	        	System.out.println("登录成功！");  
	            request.getSession().setAttribute("username", username);  
	            response.sendRedirect("index.jsp");  
	            return;  
	        } else {
	            System.out.println("用户名或密码错误，登录失败！");  
	            response.sendRedirect("login.jsp");  
	            return;  
	        }
	     } else if("logout".equals(method)){//退出登录  
	         System.out.println("退出登录！");  
	         request.getSession().removeAttribute("username");  
	         response.sendRedirect("login.jsp");  
	         return;  
	     } else if("register".equals(method)){//注册  
	    	 String username = request.getParameter("username");  
	         String password1 = request.getParameter("password1");  
	         String password2 = request.getParameter("password2");  
	         String telephone = request.getParameter("telephone");  
	         if(username == null || "".equals(username.trim())
	        		 || password1 == null || "".equals(password1.trim())
	        	     || password2 == null || "".equals(password2.trim())
	            	 || !password1.equals(password2)){
	        	 System.out.println("用户名或密码不能为空，前后两次密码不同！");  
	             response.sendRedirect("register.jsp");  
	             return;  
	         }  
	         boolean isExit = false;
	         User user = userService.getUserByUserName(username);
	         if (user != null) isExit = true;
	         if (!isExit){
	        	 userService.addUser(new User(username, password1, telephone, 1));
	             System.out.println("注册成功，请登录！");  
	             response.sendRedirect("login.jsp");  
	             return;  
	         } else {  
	             System.out.println("用户名已存在！");  
	             response.sendRedirect("register.jsp");  
	             return;  
	         }  
	    }        
		String username = request.getParameter("username");
		
//		调用 UserService 的 getUser(username) 获取User 对象：要求 trades 是被装配好的，而且每一个 Trade 对象的 items 也被装配好
		User user = userService.getUserWithTrades(username);
		
//		把 User 对象放入到 request 中
		if(user == null){
			response.sendRedirect(request.getServletPath() + "/error-1.jsp");
			return;
		}
		
		request.setAttribute("user", user);
		
//		转发页面到 /WEB-INF/pages/trades.jsp
		request.getRequestDispatcher("/WEB-INF/pages/trades.jsp").forward(request, response);

	}

}
