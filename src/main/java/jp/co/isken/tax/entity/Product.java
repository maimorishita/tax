package jp.co.isken.tax.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Product {

	private int id;
	private String name;
	private BigDecimal price= new BigDecimal("0.00");
	private Item item;
	private static List<Product> $productList = new ArrayList<Product>();
	private static int count = 0;

	public Product(String name, BigDecimal price) {
		this.id = count++;
		this.name = name;
		this.price = price;
		//TOODÅ@ëΩèdìxíºÇ∑ item:product=1:1;
		this.item = new Item(name);
		$productList.add(this);
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public static Iterator<Product> iterator() {
		return $productList.iterator();
	}

	public static Product getProduct(int id) {
		Iterator<Product> iter = $productList.iterator();
		while (iter.hasNext()) {
			Product product = (Product) iter.next();
			if (product.id == id) {
				return product;
			}
		}
		return null;
	}

	public String toString() {
		return id + " : " + name + "(" + price + ")";
	}

	public static void init() {
		count = 0;
		$productList = new ArrayList<Product>();	
	}

	public static Product getProductByName(String name) throws Exception {
		for (Product i : $productList) {
			if (i.getName().equals(name)) {
				return i;
			}
		}
		throw new Exception();
	}

	public Item getItem() {
		return item;
	}

	
}