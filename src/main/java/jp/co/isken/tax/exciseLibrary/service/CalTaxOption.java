package jp.co.isken.tax.exciseLibrary.service;


public enum CalTaxOption {

	ROOUND_UP(1,"Øã‚°"), ROUND_DOWN(2,"ØÌ‚Ä"), ROUND_HALF_UP(3,"lÌŒÜ“ü");
	
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
