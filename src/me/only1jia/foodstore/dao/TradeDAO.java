package me.only1jia.foodstore.dao;

import java.util.Set;

import me.only1jia.foodstore.domain.Trade;


public interface TradeDAO {

	/**
	 * �����ݱ��в��� Trade ����
	 * @param trade
	 */
	public abstract void insert(Trade trade);

	/**
	 * ���� userId ��ȡ��������� Trade �ļ���
	 * @param userId
	 * @return
	 */
	public abstract Set<Trade> getTradesWithUserId(Integer userId);

}