package jp.co.isken.tax.transaction;

public enum TaxableType {

	INSIDE(1, "ŽÐ“à"), OUTSIDE(2, "ŽÐŠO");

	private String name;
	private int id;

	TaxableType(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public static TaxableType getEnum(String str) {
		TaxableType[] enumArray = TaxableType.values();
		for (TaxableType enumStr : enumArray) {
			if (str.equals(enumStr.name.toString())) {
				return enumStr;
			}
		}
		return null;
	}

	public static TaxableType getEnumById(int id) {
		for (TaxableType enumStr : TaxableType.values()) {
			if (id == enumStr.id) {
				return enumStr;
			}
		}
		return null;
	}

}
