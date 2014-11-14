package fr.unice.polytech.soa1.teamforce.entities;

import java.util.List;
import java.util.Map;

public class Order {

	private String id;
	private List<GoodOrder> goods;
	private String price;
	private OrderStatus status;
	private String Date = "12/11/14";
	private String customerId;
	
	public Order(String id, List<GoodOrder> goods, OrderStatus status, String customerId) {
		super();
		this.id = id;
		this.goods = goods;
		this.status = status;
		this.customerId = customerId;
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
