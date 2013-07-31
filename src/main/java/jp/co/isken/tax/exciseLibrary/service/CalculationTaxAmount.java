package jp.co.isken.tax.exciseLibrary.service;

import java.math.BigDecimal;
import java.util.Date;

import jp.co.isken.tax.exciseLibrary.entity.Item;
import jp.co.isken.tax.exciseLibrary.transaction.CanTax;

public class CalculationTaxAmount {

	public static BigDecimal getExciese(String itemName, BigDecimal itemAmount,
			Date whenOccered, String canTaxType, String calTaxOption)
			throws Exception {

		if (isInternationalTransaction(canTaxType)) {
			return new BigDecimal("0");
		}
		BigDecimal rs = calcuration(itemName, itemAmount, whenOccered,
				calTaxOption);
		return rs;
	}

	public static BigDecimal calcuration(String itemName,
			BigDecimal itemAmount, Date whenOccered, String calTaxOption)
			throws Exception {
		Item i = Item.getItemByName(itemName);
		BigDecimal rate = getRate(whenOccered, i);
		BigDecimal taxAmount = itemAmount.multiply(rate);
		BigDecimal rs = roundTaxAmount(taxAmount, calTaxOption);
		return rs;
	}

	public static boolean isInternationalTransaction(String canTax) {
		return CanTax.INTERNATIONAL.getName().equals(canTax);
	}

	public static BigDecimal getRate(Date whenOccered, Item i) throws Exception {
		return i.getTaxRate(whenOccered).getRate();
	}

	private static BigDecimal roundTaxAmount(BigDecimal target,
			String calTaxOption) throws Exception {
		return CalTaxOption.getEnum(calTaxOption).round(target);
	}
}
