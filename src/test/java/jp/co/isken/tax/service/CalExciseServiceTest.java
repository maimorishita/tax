package jp.co.isken.tax.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;
import java.util.List;

import jp.co.isken.tax.entity.CFTransaction;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.exciseLibrary.CalExciseService;
import jp.co.isken.tax.run.Initializer;
import jp.co.isken.tax.util.HardCode;
import jp.co.isken.tax.util.Util;

import org.junit.Before;
import org.junit.Test;

public class CalExciseServiceTest {

	@Before
	public void setUp() {
		Initializer.test();
	}

//	@Test
//	public void è§ó¨éÊà¯Ç©ÇÁè¡îÔê≈ÇåvéZÇ∑ÇÈ() throws Exception {
//		Date date = Util.stringToDate("20130707000000");
//		Receipt receipt = new Receipt(date,0);
//		receipt.addLineItem(0, 1);
//		receipt.addLineItem(1, 1);
//		receipt.save();
//		Transaction t = receipt.getTransaction();
//		CashFlow cf = new CashFlow(receipt.getTransaction());
//		t.update();
//		
////		CFTransaction target = CalExciseService.createExciseTransaction(receipt.getTransaction());
//		List<CFTransaction> target = t.getCFTransactions();
//		String expected = "0 : 20130707000000, 20130707000000, ["+HardCode.DAIKIN+",90]["+HardCode.EXCISE+",4.50]";
//		assertThat(target.size(), is(1));
//		assertThat(target.get(0).toString(), is(expected));
//	}
}
