package jp.co.isken.tax.exciseLibrary.transaction;

public enum CanTax {

	DOMESTIC(1, "çëì‡"), INTERNATIONAL(2, "çëäO");

	private String name;
	private int id;

	CanTax(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public static CanTax getEnum(String str) {
		CanTax[] enumArray = CanTax.values();
		for (CanTax enumStr : enumArray) {
			if (str.equals(enumStr.name.toString())) {
				return enumStr;
			}
		}
		return null;
	}
	
	public static CanTax getEnumById(int id) {
		for (CanTax enumStr : CanTax.values()) {
			if (id == enumStr.id) {
				return enumStr;
			}
		}
		return null;
	}

}
