package jp.co.isken.tax.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CFAccount {

	private int id;
	private boolean isSave = false;
	private String name;
	private static List<CFAccount> $cfaccountList = new ArrayList<CFAccount>();
	private static int count;

	private CFAccount(Account account) {
		new CFAccount(account.getName());
	}

	private CFAccount(String name) {
		this.name = name;
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
	public static CFAccount getAccount(String name) {
		for (CFAccount a : $cfaccountList) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		return new CFAccount(name);
	}

	public static void init() {
		$cfaccountList = new ArrayList<CFAccount>();
		count = 0;
	}

	public static Iterator<CFAccount> iterator() {
		return $cfaccountList.iterator();
	}
}
