package jp.co.isken.tax.exciseLibrary.service;

import java.math.BigDecimal;
import java.util.Date;

import jp.co.isken.tax.exciseLibrary.entity.Item;
import jp.co.isken.tax.taxLibrary.transaction.CanTax;
import jp.co.isken.tax.taxLibrary.transaction.TaxableType;
import jp.co.isken.tax.taxLibrary.transaction.TransactionType;

public class CalculationTaxAmount {

	public static BigDecimal getExciese(String itemName, BigDecimal itemAmount,
			Date whenOccered, Date whenNoticed, String transactionType,
			String canTax, String taxableType) throws Exception {
		canTaxByTransactionType(transactionType);
		canTaxByCanTaxType(canTax);
		CanTaxByTaxableType(taxableType);
		Item i = Item.getItemByName(itemName);
		BigDecimal rate = i.getTaxRate(whenOccered).getRate();
		return itemAmount.multiply(rate);
	}

	public static BigDecimal CanTaxByTaxableType(String taxableType) throws Exception {
		if (taxableType.equals(TaxableType.INSIDE.getName())) {
			throw new Exception("社内取引は課税されません。");
		}
		return new BigDecimal("0");
	}

	public static BigDecimal canTaxByCanTaxType(String canTax) throws Exception {
		if (canTax.equals(CanTax.INTERNATIONAL.getName())) {
			throw new Exception("国外取引は課税されません。");
		}
		return new BigDecimal("0");
	}

	public static BigDecimal canTaxByTransactionType(String transactionType) throws Exception {
		if (transactionType.equals(TransactionType.PURCHASE.getName())) {
			throw new Exception("仕入取引は課税されません。");
		}
		return new BigDecimal("0");
	}

}
