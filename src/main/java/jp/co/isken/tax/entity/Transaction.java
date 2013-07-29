package jp.co.isken.tax.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.co.isken.tax.util.Util;

public class Transaction {

	private boolean isSave = false;
	private int id;
	private Contract contract;
	private Date whenOccered;
	private Date whenNoticed;
	private static List<Transaction> $transactionList = new ArrayList<Transaction>();
	private static int count = 0;

	public Transaction(Contract c, Date occerd, Date noticed) {
		id = count++;
		contract = c;
		setWhenOccered(occerd);
		setWhenNoticed(noticed);
	}

	public static List<Transaction> getTByContract(Contract c) {
		List<Transaction> tlist = new ArrayList<Transaction>();
		for (Transaction t : $transactionList) {
			if (t.getContract().equals(c)) {
				tlist.add(t);
			}
		}
		return tlist;
	}

	public Contract getContract() {
		return contract;
	}

	// TODO　isSave未テスト
	public void save() {
		if (isSave == false) {
			isSave = true;
			$transactionList.add(this);
			count++;
		}
	}

	public List<Entry> getEntries() {
		return Entry.getEByTransaction(this);
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

	public static Transaction getTransaction(int id) {
		for (Transaction t : $transactionList) {
			if (t.getId() == id) {
				return t;
			}
		}
		// TODO nullを返さない
		return null;
	}

	private int getId() {
		return id;
	}

	public static Iterator<Transaction> getTransactions(Contract c, Date _date) {
		List<Transaction> retval = new ArrayList<Transaction>();
		Iterator<Transaction> iter = Transaction.iterator();
		while (iter.hasNext()) {
			Transaction target = iter.next();
			if (target.getContract().equals(c)) {
				retval.add(target);
			}
		}
		return retval.iterator();
	}

	private static Iterator<Transaction> iterator() {
		return $transactionList.iterator();
	}

	public String toString() {
		String rs = "";
		String occered = Util.dateToString(whenOccered);
		String noticed = Util.dateToString(whenNoticed);

		rs = id + " : " + occered + ", " + noticed + ", ";
		for (Entry entry : getEntries()) {
			rs += "[" + entry.getAccount().getName() + "," + entry.getAmmount()
					+ "]";
		}
		return rs;
	}

	public static void init() {
		count =0;
		$transactionList = new ArrayList<Transaction>();
	}

	public List<CFTransaction> getCFTransactions() {
		return CFTransaction.getCFTransactionByTransaction(this);
	}


	public void update() {
		$transactionList.remove(this);
		$transactionList.add(this);
	}

	// public List<CFTransaction> getCashFlowT() {
	// return CFTransaction.getCashFlowTByTransaction(this);
	// }
}
