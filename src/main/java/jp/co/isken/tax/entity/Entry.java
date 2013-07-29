package jp.co.isken.tax.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Entry {

	private int id ;
	private boolean isSave = false;
	private Transaction transaction;
	private Account account;
	private BigDecimal amount = new BigDecimal("0.00");
	private static List<Entry> $entyList = new ArrayList<Entry>();
	private static int count;

	public Entry(Transaction t, Account a, BigDecimal b) {
		account = a;
		amount = b;
		transaction = t;
		id = count++;
	}

	public void save() {
		if (isSave == false) {
			isSave = true;
			$entyList.add(this);
		}
	}

	public static List<Entry> getEByTransaction(Transaction transaction2) {
		List<Entry> elist = new ArrayList<Entry>();
		for (Entry e : $entyList) {
			if (e.getTransaction().equals(transaction2)) {
				elist.add(e);
			}
		}
		return elist;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public Account getAccount() {
		return account;
	}

	public BigDecimal getAmmount() {
		return amount;
	}

	public void setAmmount(BigDecimal a) {
		amount = a;
	}

	public void setTransaction(Transaction t) {
		transaction = t;
	}

	public void setAccount(Account a) {
		account = a;
	}

	public static void init() {
		$entyList = new ArrayList<Entry>();
		count = 0;
	}
}
