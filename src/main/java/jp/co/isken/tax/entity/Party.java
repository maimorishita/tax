package jp.co.isken.tax.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

public class Party {

	private static Vector<Party> instances = new Vector<Party>();
	private static int count = 0;
	private int id;
	private String name;

	public Party(String name) {
		setId(count++);
		this.name = name;
	}

	public void save() {
		Party.instances.add(this);
	}

	public static Party getParty(String name) {
		for (Party p : Party.instances) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		Party t = new Party(name);
		t.save();
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

}
