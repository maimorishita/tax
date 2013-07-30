package jp.co.isken.tax.exciseLibrary.service;

import java.math.BigDecimal;
import java.util.Date;

public class CalExciseFacade {

	public static BigDecimal getExciese(String itemName, BigDecimal itemAmount,
			Date whenOccered,String transactionType,
			String canTax) throws Exception {
		return CalculationTaxAmount.getExciese(itemName, itemAmount,
				whenOccered,transactionType, canTax);
	}
}
