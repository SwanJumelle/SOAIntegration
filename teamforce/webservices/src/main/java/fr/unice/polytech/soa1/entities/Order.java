package fr.unice.polytech.soa1.entities;

import java.util.List;
import java.util.Map;

public class Order {

	private String id;
	private List<GoodOrder> goods;
	private String price;
	private Status status;
	private String Date = "12/11/14";
	
	public Order(String id, List<GoodOrder> goods, Status status) {
		super();
		this.id = id;
		this.goods = goods;
		this.status = status;
	}
	
	public List<GoodOrder> getGoods() {
		return goods;
	}
	public void setGoods(List<GoodOrder> goods) {
		this.goods = goods;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
