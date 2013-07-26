package jp.co.isken.tax.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;
import java.util.List;

import jp.co.isken.tax.entity.CFTransaction;
import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.run.Initializer;
import jp.co.isken.tax.util.HardCode;
import jp.co.isken.tax.util.Util;

import org.junit.Before;
import org.junit.Test;

public class ReciptTest {

	@Before
	public void before(){
		Initializer.test();
	}
	
	@Test
	public void íçï∂Çìoò^Ç∑ÇÈ() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date,0);
		receipt.addLineItem(0, 1);
		receipt.save();
		String expected = "[0 : 20130707000000, 20130707000000, [ÉiÉbÉNÉoÅ[ÉKÅ[,1]]";
		List<Transaction> trans = Transaction.getTByContract(Contract.getContract(0));
		assertThat(trans.size(), is(1));
		assertThat(trans.toString(), is(expected));
	}
	
	@Test
	public void íçï∂Çìoò^ÇµÇƒë„ã‡Ç∆è¡îÔê≈ÇåvéZÇ∑ÇÈ() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date,0);
		receipt.addLineItem(0, 1);
		receipt.save();
		receipt.total();
		String cfexpected = "0 : 20130707000000, 20130707000000, ["+HardCode.DAIKIN+",90]["+HardCode.EXCISE+",4.50]";
		List<Transaction> trans = Transaction.getTByContract(Contract.getContract(0));
		CFTransaction cfTran = trans.get(0).getCFTransaction();
		assertThat(trans.size(), is(1));
		assertThat(cfTran.toString(), is(cfexpected));
	}
}
