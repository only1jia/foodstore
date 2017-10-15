package me.only1jia.foodstore.domain;

public class TradeItem {

	private Integer tradeItemId;
	
	//和 TradeItem 关联的 Food
	private Food food;
	
	private int quantity;
	
	//和 TradeItem 关联的 Food 的 FoodId
	private Integer foodId;

	private Integer tradeId;

	public void setFood(Food food) {
		this.food = food;
	}
	
	public Food getFood() {
		return food;
	}
	
	public Integer getTradeItemId() {
		return tradeItemId;
	}

	public void setTradeItemId(Integer tradeItemId) {
		this.tradeItemId = tradeItemId;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public TradeItem(Integer tradeItemId, Integer foodId, int quantity,
			Integer tradeId) {
		super();
		this.tradeItemId = tradeItemId;
		this.foodId = foodId;
		this.quantity = quantity;
		this.tradeId = tradeId;
	}
	
	public TradeItem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TradeItem [tradeItemId=" + tradeItemId + ", quantity="
				+ quantity + ", foodId=" + foodId + "]";
	}
	
	

}
