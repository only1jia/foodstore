package me.only1jia.foodstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	
	private Map<Integer, ShoppingCartItem> foods = new HashMap<>();
	
	/**
	 * �޸�ָ�������������
	 */
	public void updateItemQuantity(Integer id, int quantity){
		ShoppingCartItem sci =foods.get(id);
		if(sci != null){
			sci.setQuantity(quantity);
		}
	}
	
	/**
	 * �Ƴ�ָ���Ĺ�����
	 * @param id
	 */
	public void removeItem(Integer id){
		foods.remove(id);
	}
	
	/**
	 * ��չ��ﳵ
	 */
	public void clear(){
		foods.clear();
	}
	
	/**
	 * ���ع��ﳵ�Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty(){
		return foods.isEmpty();
	}
	
	/**
	 * ��ȡ���ﳵ�����е���Ʒ���ܵ�Ǯ��
	 * @return
	 */
	public float getTotalMoney(){
		float total = 0;
		
		for(ShoppingCartItem sci: getItems()){
			total += sci.getItemMoney();
		}
		
		return total;
	}
	
	/**
	 * ��ȡ���ﳵ�е����е� ShoppingCartItem ��ɵļ���
	 * @return
	 */
	public Collection<ShoppingCartItem> getItems(){
		return foods.values();
	}
	
	/**
	 * ���ع��ﳵ����Ʒ��������
	 * @return
	 */
	public int getFoodNumber(){
		int total = 0;
		
		for(ShoppingCartItem sci: foods.values()){
			total += sci.getQuantity();
		}
		
		return total;
	}
	
	public Map<Integer, ShoppingCartItem> getFoods() {
		return foods;
	}
	
	/**
	 * ���鹺�ﳵ���Ƿ��� id ָ������Ʒ		
	 * @param id
	 * @return
	 */
	public boolean hasFood(Integer id){
		return foods.containsKey(id);
	}		
			
	/**
	 * ���ﳵ�����һ����Ʒ		
	 * @param book
	 */
	public void addFood(Food food){
		//1. ��鹺�ﳵ����û�и���Ʒ, ����, ��ʹ������ +1, ��û��, 
		//�´������Ӧ�� ShoppingCartItem, ��������뵽 books ��
		ShoppingCartItem sci = foods.get(food.getId());
		
		if(sci == null){
			sci = new ShoppingCartItem(food);
			foods.put(food.getId(), sci);
		}else{
			sci.increment();
		}
	}
}
