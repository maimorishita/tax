package jp.co.isken.tax.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.isken.tax.util.Util;

public class CFTransaction {
	private int id;
	private boolean isSave = false;
	private Date whenOccered;
	private Date whenNoticed;
	private Entry entry;

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	private static List<CFTransaction> $TransactionList = new ArrayList<CFTransaction>();
	private static int count;

	public CFTransaction(Entry entry) {
		id = count++;
		setWhenOccered(entry.getTransaction().getWhenOccered());
		setWhenNoticed(entry.getTransaction().getWhenNoticed());
		this.entry = entry;
	}

	public void save() {
		if (isSave == false) {
			isSave = true;
			$TransactionList.add(this);
		}
	}

	public Date getWhenOccered() {
		return whenOccered;
	}

	public void setWhenOccered(Date whenOccered) {
		this.whenOccered = whenOccered;
	}

	public Date getWhenNoticed() {
		return whenNoticed;
	}

	public void setWhenNoticed(Date whenNoticed) {
		this.whenNoticed = whenNoticed;
	}

	public static List<CFTransaction> getCFTransactionByTransaction(
			Transaction transaction) {
		List<CFTransaction> target = new ArrayList<CFTransaction>();
		for (CFTransaction t : $TransactionList) {
			if (t.getEntry().getTransaction().equals(transaction)) {
				target.add(t);
			}
		}
		return target;
	}

	public List<CFEntry> getEntries() {
		return CFEntry.getEByTransaction(this);
	}

	public String toString() {
		String rs = "";
		String occered = Util.dateToString(whenOccered);
		String noticed = Util.dateToString(whenNoticed);

		rs = id + " : " + occered + ", " + noticed + ", ";
		rs += "[" + entry.getAccount().getName() + "," + entry.getAmmount() + "]";
		for (CFEntry entry : getEntries()) {
			BigDecimal amount = new BigDecimal("0.00");
			amount = amount.add(entry.getAmmount()).setScale(2);
			rs += "[" + entry.getAccount().getName() + "," + amount + "]";
		}
		return rs;
	}

	public static void init() {
		$TransactionList = new ArrayList<CFTransaction>();
		count = 0;
	}

	public Party getCustomer() {
		return this.getEntry().getTransaction().getContract().getCustomer();
	}
}
