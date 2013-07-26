package jp.co.isken.tax.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CFEntry {

	private boolean isSave = false;
	private CFTransaction transaction;
	private CFAccount account;
	private BigDecimal ammount = new BigDecimal("0.00");
	private static List<CFEntry> $entryList = new ArrayList<CFEntry>();
	private static int count;

	public BigDecimal getAmmount() {
		return ammount;
	}

	public CFEntry(CFTransaction t, CFAccount a, BigDecimal d) {
		transaction = t;
		account = a;
		ammount = d;
	}

	public void setAmmount(BigDecimal a) {
		ammount = a;
	}

	public CFTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(CFTransaction transaction) {
		this.transaction = transaction;
	}

	public CFAccount getAccount() {
		return account;
	}

	public void setAccount(CFAccount account) {
		this.account = account;
	}

	public void save() {
		if (isSave == false) {
			isSave = true;
			$entryList.add(this);
		}
	}

	public static List<CFEntry> getEByTransaction(CFTransaction cashFlowT) {
		List<CFEntry> elist = new ArrayList<CFEntry>();
		for (CFEntry e : $entryList) {
			if (e.getTransaction().equals(cashFlowT)) {
				elist.add(e);
			}
		}
		return elist;
	}

	public static void init() {
		$entryList = new ArrayList<CFEntry>();
		count = 0;
	}
}
