package me.only1jia.foodstore.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import me.only1jia.foodstore.dao.TradeDAO;
import me.only1jia.foodstore.domain.Trade;


public class TradeDAOImpl extends BaseDAO<Trade> implements TradeDAO {

	@Override
	public void insert(Trade trade) {
		String sql = "INSERT INTO trade (userid, tradetime) VALUES " +
				"(?, ?)";
		long tradeId = insert(sql, trade.getUserId(), trade.getTradeTime());
		trade.setTradeId((int)tradeId);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<Trade> getTradesWithUserId(Integer userId) {
		String sql = "SELECT tradeId, userId, tradeTime FROM trade " +
				"WHERE userId = ? ORDER BY tradeTime DESC";
		return new LinkedHashSet(queryForList(sql, userId));
	}

}
