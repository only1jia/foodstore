package me.only1jia.foodstore.dao;

import java.util.Collection;
import java.util.List;

import me.only1jia.foodstore.domain.Food;
import me.only1jia.foodstore.domain.ShoppingCartItem;
import me.only1jia.foodstore.web.CriteriaFood;
import me.only1jia.foodstore.web.Page;


public interface FoodDAO {

	/**
	 * ���� id ��ȡָ�� Food ����
	 * @param id
	 * @return
	 */
	public abstract Food getFood(int id);

	/**
	 * ���ݴ���� CriteriaFood ���󷵻ض�Ӧ�� Page ����
	 * @param cb
	 * @return
	 */
	public abstract Page<Food> getPage(CriteriaFood cb);

	/**
	 * ���ݴ���� CriteriaFood ���󷵻����Ӧ�ļ�¼��
	 * @param cb
	 * @return
	 */
	public abstract long getTotalFoodNumber(CriteriaFood cb);

	/**
	 * ���ݴ���� CriteriaFood �� pageSize ���ص�ǰҳ��Ӧ�� List 
	 * @param cb
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract List<Food> getPageList(CriteriaFood cb,int pageSize);

	/**
	 * ����ָ�� id �� Food �� storeNumber �ֶε�ֵ
	 * @param id
	 * @return
	 */
	public abstract int getStoreNumber(Integer id);

	/**
	 * ���ݴ���� ShoppingCartItem �ļ���, 
	 * �������� Foods ���ݱ�� storenumber �� salesnumber �ֶε�ֵ
	 * @param items
	 */
	public abstract void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items);

}