package me.only1jia.foodstore.domain;


public class Food {
	
	private Integer id;
	private String title;
	private float price;
	
	private int salesAmount;
	private int storeNumber;
	private String remark;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
	}
	public int getStoreNumber() {
		return storeNumber;
	}
	public void setStoreNumber(int storeNumber) {
		this.storeNumber = storeNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title
				+ ", price=" + price + ", salesAmount=" 
				+ salesAmount + ", storeNumber="+ storeNumber 
				+ ", remark=" + remark + "]\n\n";
	}
	
	
		
}
