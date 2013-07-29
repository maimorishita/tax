package jp.co.isken.tax.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Account {

	private boolean isSave = false;
	private int id;
	private String name;
	private Product product;
	private Party party;
	private static List<Account> $accountList = new ArrayList<Account>();
	private static int $count = 0;

	private Account(String name, Party party) throws Exception {
		this.name = name;
		this.product = Product.getProductByName(name);
		this.party = party;
		id = $count++;
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
	public static Account getAccount(String itemName, Party party) throws Exception {
		for (Account a : $accountList) {
			if (a.getName().equals(itemName)) {
				return a;
			}
		}
		return new Account(itemName, party);
	}

	public static Account getAccount(Product product, Party party) throws Exception {
		return Account.getAccount(product.getName(), party);
	}

	public static void init() {
		$accountList = new ArrayList<Account>();
		$count = 0;
	}

	public static Iterator<Account> iterator() {
		return $accountList.iterator();
	}

	public Party getParty() {
		return party;
	}

}
