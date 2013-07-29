package jp.co.isken.tax.exciseLibrary;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jp.co.isken.tax.entity.CFAccount;
import jp.co.isken.tax.entity.CFEntry;
import jp.co.isken.tax.entity.CFTransaction;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.util.HardCode;

public class CalExciseService {

//	public static CFTransaction createExciseTransaction(Transaction transaction)
//			throws Exception {
//		CFTransaction cft = transaction.getCFTransaction();
//		BigDecimal sum = new BigDecimal("0.00");
//		for (CFEntry entry : cft.getEntries()) {
//			new CFEntry(cft, CFAccount.getAccount(HardCode.EXCISE),
//					getExciese(entry));
//		}
//		return cft;
//	}

	private static BigDecimal getExciese(CFEntry entry) throws Exception {
		Item i = Item.getItemByName(entry.getAccount().getName());
		BigDecimal rate = i.getTaxRate(entry.getTransaction().getWhenOccered()).getRate();
		return entry.getAmmount().multiply(rate);
	}
}
