package jp.co.isken.tax.exciseLibrary.service;

import java.math.BigDecimal;

public enum CalTaxOption {

	ROOUND_UP(1, "êÿè„Ç∞") {
		@Override
		BigDecimal round(BigDecimal target) {
			return target.setScale(0, BigDecimal.ROUND_UP);
		}
	},
	ROUND_DOWN(2, "êÿéÃÇƒ") {
		@Override
		BigDecimal round(BigDecimal target) {
			return target.setScale(0, BigDecimal.ROUND_DOWN);
		}
	},
	ROUND_HALF_UP(3, "éléÃå‹ì¸") {
		@Override
		BigDecimal round(BigDecimal target) {
			return target.setScale(0, BigDecimal.ROUND_HALF_UP);
		}
	};

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

	abstract BigDecimal round(BigDecimal target);

}
