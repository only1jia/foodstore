package me.only1jia.foodstore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import me.only1jia.foodstore.domain.ShoppingCart;


public class FoodStoreWebUtils {
	
	/**
	 * ��ȡ���ﳵ����: �� session �л�ȡ, �� session ��û�иĶ���.
	 * �򴴽�һ���µĹ��ﳵ����, ���뵽 session ��.
	 * ����, ��ֱ�ӷ���. 
	 * @param request
	 * @return
	 */
	public static ShoppingCart getShoppingCart(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
		if(sc == null){
			sc = new ShoppingCart();
			session.setAttribute("ShoppingCart", sc);
		}
		
		return sc;
	}
	
}
