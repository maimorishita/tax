package jp.co.isken.tax.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Party {

	private static int count = 0;
	private static List<Party> $partyList = new ArrayList<Party>();
	private int id;
	private String name;

	public Party(String name) {
		setId(count++);
		this.name = name;
		Party.$partyList.add(this);
	}

	public static Party getParty(String name) {
		for (Party p : Party.$partyList) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		Party t = new Party(name);
		return t;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static Iterator<Party> iterator() {
		return $partyList.iterator();
	}

	public String toString() {
		return id + " : " + name;
	}
}
