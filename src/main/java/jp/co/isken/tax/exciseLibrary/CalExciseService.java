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
		//TODO ����ł͕i�ڂ��Ƃɏo���́H�@���z�ŏo���̂��H �i�ڂ��Ƃɏo���Ȃ�΋�������͏��i���Ƃɍ��H�A�J�E���g�����i���Ƃɂł���H
		return entry.getAmmount().multiply(new BigDecimal("0.05"));
	}

	private static TaxRate getTaxRate(Item item, Date date) {
		List<TaxRate> rates = TaxRate.getTaxRates();
		for(TaxRate rate : rates){
			if(rate.getItem().equals(item) && rate.getTerm().getFrom().before(date)  &&  rate.getTerm().getTo().after(date)){
				return rate;
			}
		}
		//TODO null��Ԃ��Ȃ�
		return null;
	}

}
