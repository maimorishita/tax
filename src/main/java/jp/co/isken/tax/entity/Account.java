package jp.co.isken.tax.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Account {

	private boolean isSave = false;
	private String name;
	private Item item;
	private static Vector<Account> $accountList = new Vector<Account>();

	public Account(String name) throws Exception {
		this.name = name;
		this.item = Item.getItemByName(name);
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

	public Item getItem() {
		return item;
	}

	//TODO ƒeƒXƒg
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

}
