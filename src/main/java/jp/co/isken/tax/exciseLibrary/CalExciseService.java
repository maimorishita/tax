package jp.co.isken.tax.exciseLibrary;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jp.co.isken.tax.entity.CFAccount;
import jp.co.isken.tax.entity.CFEntry;
import jp.co.isken.tax.entity.CFTransaction;
import jp.co.isken.tax.entity.Entry;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.util.HardCode;
import jp.co.isken.tax.util.Util;

public class CalExciseService {

	public static CFTransaction createExciseTransaction(Transaction transaction) throws Exception {
		CFTransaction cft = transaction.getCFTransaction();
		BigDecimal sum = new BigDecimal("0.00");
		for(CFEntry entry : cft.getEntries()){
			sum = sum.add(getExciese(entry));
		}
		CFEntry entry = new CFEntry(cft, CFAccount.getAccount(HardCode.EXCISE), sum);
		entry.save();
		return cft;
	}

	private static BigDecimal getExciese(CFEntry entry) throws Exception {
		//TODO 消費税は品目ごとに出すの？　総額で出すのか？ 品目ごとに出すならば金流取引は商品ごとに作る？アカウントも商品ごとにできる？
		return entry.getAmmount().multiply(new BigDecimal("0.05"));
	}

	private static TaxRate getTaxRate(Item item, Date date) {
		List<TaxRate> rates = TaxRate.getTaxRates();
		for(TaxRate rate : rates){
			if(rate.getItem().equals(item) && rate.getTerm().getFrom().before(date)  &&  rate.getTerm().getTo().after(date)){
				return rate;
			}
		}
		//TODO nullを返さない
		return null;
	}

}
