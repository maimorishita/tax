package jp.co.isken.tax.entity.cashFlow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isken.tax.entity.Account;
import jp.co.isken.tax.entity.Party;

public class CFAccount {

	private int id;
	private boolean isSave = false;
	private String name;
	private Party party;
	private static List<CFAccount> $cfaccountList = new ArrayList<CFAccount>();
	private static int count;

	private CFAccount(Account account, Party party) {
		new CFAccount(account.getName(), party);
	}

	private CFAccount(String name, Party party) {
		this.name = name;
		this.party = party;
		id = count++;
		save();
	}

	public void save() {
		if (isSave == false) {
			isSave = true;
			$cfaccountList.add(this);
		}
	}

	public String getName() {
		return name;
	}

	// TODO ƒeƒXƒg
	public static CFAccount getAccount(String name, Party party) {
		for (CFAccount a : $cfaccountList) {
			if (a.getName().equals(name)&&a.getParty().equals(party)) {
				return a;
			}
		}
		return new CFAccount(name, party);
	}

	public Party getParty() {
		return this.party;
	}

	public static void init() {
		$cfaccountList = new ArrayList<CFAccount>();
		count = 0;
	}

	public static Iterator<CFAccount> iterator() {
		return $cfaccountList.iterator();
	}
}
