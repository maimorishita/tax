package jp.co.isken.tax.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

public class Product {

	private int id;
	private String name;
	private int price;
	private Item item;
	private static Vector<Product> $productList = new Vector<Product>();
	private static int $count = 0;

	public Product(String name, int price) {
		this.id = $count++;
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

	public int getPrice() {
		return this.price;
	}

	// public int getTotalQuantity(Date date){
	// return LineItem.$getTotalQuantity(this, date);
	// }

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
		$count = 0;
		$productList = new  Vector<Product>();	
	}

	
}