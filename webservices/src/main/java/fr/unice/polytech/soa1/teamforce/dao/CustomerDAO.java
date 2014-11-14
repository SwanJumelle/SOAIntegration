package fr.unice.polytech.soa1.teamforce.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.ejb.Singleton;

import fr.unice.polytech.soa1.teamforce.entities.Address;
import fr.unice.polytech.soa1.teamforce.entities.Catalogue;
import fr.unice.polytech.soa1.teamforce.entities.Customer;
import fr.unice.polytech.soa1.teamforce.entities.Good;
import fr.unice.polytech.soa1.teamforce.entities.GoodOrder;
import fr.unice.polytech.soa1.teamforce.entities.Order;
import fr.unice.polytech.soa1.teamforce.entities.OrderStatus;

@Singleton(name = "TeamForce-DB-Mock")
public class CustomerDAO {

	//private static final String fileSeparator = File.separator;
	//private static final String path = ".."+fileSeparator+".."+fileSeparator+"ressources"+fileSeparator ;
	private List<Customer> customers;
	private List<Order> orders;
	private List<Catalogue> catalogues;
	private List<Good> goods;
	
	public List<Customer> getCustomers() { return customers; }
	public void setCustomers(List<Customer> Customers) { this.customers = Customers; }
	public List<Order> getOrders() { return orders; }
	public void setOrders(List<Order> orders) {	this.orders = orders; }

	public CustomerDAO() { init(); }


	public Optional<Customer> findCustomerById(String id) {
		return getCustomers().stream().filter(c -> c.getId().equals(id)).findFirst();
	}
	
	public Optional<Catalogue> findCatalogueById(String id) {
		return getCatalogues().stream().filter(c -> c.getId().equals(id)).findFirst();
	}
	
	public Optional<Order> findOrderById(String id) {
		return getOrders().stream().filter(o -> o.getId().equals(id)).findFirst();
	}

	public Optional<Good> findGoodById(String id) {
		return getGoods().stream().filter(g -> g.getId().equals(id)).findFirst();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("Database: \n");
		for(Customer c: customers) {
			builder.append(String.format("[%s] %s - %s \n%s\n", c.getId(),c.getName(), c.getDeliveryAddresses().toString(), c.getInvoiceAddresses().toString()));
			/*
			for(Order o: c.getOrders()) {
				builder.append(o.toString());
				builder.append("\n");
			}*/
			builder.append("\n");
		}
		return builder.toString();
	}

	private void init() {
		String str="";
		String customersFile = "customers.csv";
		String AddressesFile = "addresses.csv";
		String OrderFile = "orders.csv";
		String CatalogueFile = "catalogues.csv";
		customers = new ArrayList<Customer>();
		orders = new ArrayList<Order>();
		catalogues = new ArrayList<Catalogue>();
		goods = new ArrayList<Good>();
		
		try{
			//InputStream ips=new FileInputStream(customersFile); 
			//InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(File.separator + customersFile)));
			String line;
			//InputStream ipsO=new FileInputStream(OrderFile); 
			//InputStreamReader ipsrO=new InputStreamReader(ipsO);
			BufferedReader brO=new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(File.separator + OrderFile)));
			String lineO;
			//InputStream ipsA=new FileInputStream(AddressesFile); 
			//InputStreamReader ipsrA=new InputStreamReader(ipsA);
			BufferedReader brA=new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(File.separator + AddressesFile)));
			String lineA;
			//InputStream ipsC=new FileInputStream(CatalogueFile); 
			//InputStreamReader ipsrC=new InputStreamReader(ipsC);
			BufferedReader brC=new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(File.separator + CatalogueFile)));
			String lineC;
			br.readLine();
			brA.readLine();
			brO.readLine();
			brC.readLine();
			while ((line=br.readLine())!=null && (lineA=brA.readLine())!=null && (lineO=brO.readLine())!=null && (lineC=brC.readLine())!=null){
				str = line+"|"+lineA;
				String[] tab = str.split("\\|");
				String[] tabOrders = lineO.split("\\|");
				String[] tabCata = lineC.split("\\|");
				
				if(catalogues.isEmpty()){
					ArrayList<String> goodsC = new ArrayList<String>();
					goodsC.add(tabCata[0]);
					catalogues.add(new Catalogue(tabCata[1], goodsC));
				}else{
					boolean b = false;
					for(int i = 0;i< catalogues.size();i++){
						if(catalogues.get(i).getId().equals(tabCata[1])){
							catalogues.get(i).addGoods(tabCata[0]);
							b = true;
						}
					}
					if(!b){
						ArrayList<String> goodsC = new ArrayList<String>();
						goodsC.add(tabCata[0]);
						catalogues.add(new Catalogue(tabCata[1], goodsC));
					}
				}
				
				Customer customer = (new Customer(tab[0],tab[1],tab[2],tab[3],tab[4],new Address(tab[5], tab[6], tab[7],tab[8]),new Address(tab[5], tab[6], tab[7],tab[8]),new ArrayList<Order>()));
				customers.add(customer);
				Optional<Order> exOrder = findOrderById(tabOrders[0]);
				if(exOrder.isPresent()){
					Good good = new Good(tabOrders[1],tabOrders[3], "None");
					goods.add(good);
					exOrder.get().getGoods().add(new GoodOrder(Integer.valueOf(tabOrders[2]), good));
				}else{
					List<GoodOrder> goodsOrder = new ArrayList<GoodOrder>();
					Good goodTemp = new Good(tabOrders[1],tabOrders[3], "None");
					goods.add(goodTemp);
					goodsOrder.add(new GoodOrder(Integer.valueOf(tabOrders[2]), goodTemp));
					int pick = new Random().nextInt(OrderStatus.values().length);
				    OrderStatus status =  OrderStatus.values()[pick];
					Order newOrder = new Order(tabOrders[0],goodsOrder, status, customer.getId());
					orders.add(newOrder);
					if( customer.getOrders() != null){
						customer.getOrders().add(newOrder);
					}
				}
			}
			br.close(); 
			brA.close();
			brO.close();
			brC.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	public List<Catalogue> getCatalogues() {
		return catalogues;
	}
	public void setCatalogues(List<Catalogue> catalogues) {
		this.catalogues = catalogues;
	}
	public List<Good> getGoods() {
		return goods;
	}
	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}
}
