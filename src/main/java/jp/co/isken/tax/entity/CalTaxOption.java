package jp.co.isken.tax.entity;


public enum CalTaxOption {

	ROOUND_UP(1,"切上げ"), ROUND_DOWN(2,"切捨て"), ROUND_HALF_UP(3,"四捨五入");
	
	private String name;
	private int id;

	CalTaxOption(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public static CalTaxOption getEnum(String str) {
		CalTaxOption[] enumArray = CalTaxOption.values();
		for (CalTaxOption enumStr : enumArray) {
			if (str.equals(enumStr.name.toString())) {
				return enumStr;
			}
		}
		return null;
	}

	public static CalTaxOption getEnumById(int id) {
		for (CalTaxOption enumStr : CalTaxOption.values()) {
			if (id == enumStr.id) {
				return enumStr;
			}
		}
		return null;
	}
	
}
