package me.only1jia.foodstore.dao;

import java.util.Collection;
import java.util.Set;

import me.only1jia.foodstore.domain.TradeItem;


public interface TradeItemDAO {

	/**
	 * �������� TradeItem ����
	 * @param items
	 */
	public abstract void batchSave(Collection<TradeItem> items);

	/**
	 * ���� tradeId ��ȡ��������� TradeItem �ļ���
	 * @param tradeId
	 * @return
	 */
	public abstract Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId);

}

