package fr.unice.polytech.soa1.teamforce.entities;

import java.util.ArrayList;
import java.util.List;

public class Catalogue {

	private List<String> goodsS;

	public Catalogue(String id,List<Good> goods) {
		super();
		this.id = id;
		this.goods = goods;
	}

	public Catalogue(String id,ArrayList<String> goods) {
		super();
		this.id = id;
		this.goodsS = goods;
	}
	
	private List<Good> goods;
	private String id;

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}
	
	public void addGood(Good good){
		goods.add(good);
	}

	public void addGoods(String goods){
		goodsS.add(goods);
	}
	
	public String getId() {
		return id;
	}
	
	public boolean containsGood(String goodId){
		for(int i=0; i<goods.size();i++){
			if(goods.get(i).getId()==goodId)
				return true;
		}
		return false;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getGoodsS() {
		return goodsS;
	}

	public void setGoodsS(List<String> goodsS) {
		this.goodsS = goodsS;
	}
}
