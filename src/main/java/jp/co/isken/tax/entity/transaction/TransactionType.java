package jp.co.isken.tax.entity.transaction;

public enum TransactionType {
	SALE(1, "�̔�"), PURCHASE(2, "�d����");

	private String name;
	private int id;

	TransactionType(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public static TransactionType getEnum(String str) {
		TransactionType[] enumArray = TransactionType.values();
		for (TransactionType enumStr : TransactionType.values()) {
			if (str.equals(enumStr.name.toString())) {
				return enumStr;
			}
		}
		return null;
	}

	public static TransactionType getEnumById(int id) {
		for (TransactionType enumStr : TransactionType.values()) {
			if (id == enumStr.id) {
				return enumStr;
			}
		}
		return null;
	}

}
