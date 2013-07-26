package jp.co.isken.tax.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.isken.tax.util.Util;

public class CFTransaction {
	private int id ;
	private boolean isSave = false;
	private Date whenOccered;
	private Date whenNoticed;
	private Transaction transaction;
	private static List<CFTransaction> $TransactionList = new ArrayList<CFTransaction>();
	private static int count;

	public CFTransaction(Transaction transaction) {
		id = count++;
		setWhenOccered(transaction.getWhenOccered());
		setWhenNoticed(transaction.getWhenNoticed());
		this.transaction = transaction;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
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

	public static List<CFTransaction> getCashFlowTByTransaction(
			Transaction transaction) {
		List<CFTransaction> target = new ArrayList<CFTransaction>();
		for (CFTransaction t : $TransactionList) {
			if (t.getTransaction().equals(transaction)) {
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
		for (CFEntry entry : getEntries()) {
			rs += "[" + entry.getAccount().getName() + "," + entry.getAmmount()
					+ "]";
		}
		return rs;
	}

	public static void init() {
		$TransactionList = new ArrayList<CFTransaction>();
		count = 0;
	}
}
