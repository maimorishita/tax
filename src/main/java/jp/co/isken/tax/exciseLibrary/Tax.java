package jp.co.isken.tax.exciseLibrary;

import java.util.ArrayList;
import java.util.List;

public class Tax {
	
	private static class SingletonHolder {
		private static final List<Tax> instances = new ArrayList<Tax>();
	}

	public Tax(String n) {
		name = n;
	}

	public static List<Tax> getInstances() {
		return SingletonHolder.instances;
	}

	private boolean isSave = false;
	private String name;

	public void save() {
		if (isSave == false) {
			isSave = true;
			SingletonHolder.instances.add(this);
		}
	}
}
