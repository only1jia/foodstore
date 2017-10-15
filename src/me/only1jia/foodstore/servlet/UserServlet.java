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
//		��ȡ username ���������ֵ
		
		
		String method = request.getParameter("method");  
	    if("login".equals(method)) {//��¼  
	    	String username = request.getParameter("username");
	    	String password = request.getParameter("password"); 
	        if(username == null || "".equals(username.trim()) || password == null
	        		|| "".equals(password.trim())){
	        	System.out.println("�û��������벻��Ϊ�գ�");  
	            response.sendRedirect("login.jsp");  
	            return;  
	        }
	        boolean isValid = false;
	        User user = userService.getUserByUserName(username);
	        if (user != null && user.getPassword().equals(password)) isValid = true;
	        if(isValid) {
	        	System.out.println("��¼�ɹ���");  
	            request.getSession().setAttribute("username", username);  
	            response.sendRedirect("index.jsp");  
	            return;  
	        } else {
	            System.out.println("�û�����������󣬵�¼ʧ�ܣ�");  
	            response.sendRedirect("login.jsp");  
	            return;  
	        }
	     } else if("logout".equals(method)){//�˳���¼  
	         System.out.println("�˳���¼��");  
	         request.getSession().removeAttribute("username");  
	         response.sendRedirect("login.jsp");  
	         return;  
	     } else if("register".equals(method)){//ע��  
	    	 String username = request.getParameter("username");  
	         String password1 = request.getParameter("password1");  
	         String password2 = request.getParameter("password2");  
	         String telephone = request.getParameter("telephone");  
	         if(username == null || "".equals(username.trim())
	        		 || password1 == null || "".equals(password1.trim())
	        	     || password2 == null || "".equals(password2.trim())
	            	 || !password1.equals(password2)){
	        	 System.out.println("�û��������벻��Ϊ�գ�ǰ���������벻ͬ��");  
	             response.sendRedirect("register.jsp");  
	             return;  
	         }  
	         boolean isExit = false;
	         User user = userService.getUserByUserName(username);
	         if (user != null) isExit = true;
	         if (!isExit){
	        	 userService.addUser(new User(username, password1, telephone, 1));
	             System.out.println("ע��ɹ������¼��");  
	             response.sendRedirect("login.jsp");  
	             return;  
	         } else {  
	             System.out.println("�û����Ѵ��ڣ�");  
	             response.sendRedirect("register.jsp");  
	             return;  
	         }  
	    }        
		String username = request.getParameter("username");
		
//		���� UserService �� getUser(username) ��ȡUser ����Ҫ�� trades �Ǳ�װ��õģ�����ÿһ�� Trade ����� items Ҳ��װ���
		User user = userService.getUserWithTrades(username);
		
//		�� User ������뵽 request ��
		if(user == null){
			response.sendRedirect(request.getServletPath() + "/error-1.jsp");
			return;
		}
		
		request.setAttribute("user", user);
		
//		ת��ҳ�浽 /WEB-INF/pages/trades.jsp
		request.getRequestDispatcher("/WEB-INF/pages/trades.jsp").forward(request, response);

	}

}
