package jp.co.isken.tax.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Account {

	private boolean isSave = false;
	private int id;
	private String name;
	private Product product;
	private static List<Account> $accountList = new ArrayList<Account>();
	private static int count = 0;

	private Account(String name) throws Exception {
		this.name = name;
		this.product = Product.getProductByName(name);
		id = count++;
		save();
	}

	public void save() {
		if (isSave == false) {
			isSave = true;
			$accountList.add(this);
		}
	}

	public String getName() {
		return name;
	}

	public Product getProduct() {
		return product;
	}

	// TODO ƒeƒXƒg
	public static Account getAccount(String itemName) throws Exception {
		for (Account a : $accountList) {
			if (a.getName().equals(itemName)) {
				return a;
			}
		}
		return new Account(itemName);
	}

	public static Account getAccount(Product product) throws Exception {
		return Account.getAccount(product.getName());
	}

	public static void init() {
		$accountList = new ArrayList<Account>();
		count = 0;
	}

	public static Iterator<Account> iterator() {
		return $accountList.iterator();
	}

}
