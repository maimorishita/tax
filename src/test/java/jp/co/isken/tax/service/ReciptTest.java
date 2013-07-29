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
import jp.co.isken.tax.util.TestUtil;
import jp.co.isken.tax.util.Util;

import org.junit.Before;
import org.junit.Test;

public class ReciptTest {

	@Before
	public void before(){
		Initializer.test();
	}
	
	@Test
	public void ������o�^����() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date,0);
		receipt.addLineItem(0, 1);
		receipt.save();
		String expected = "[0 : 20130707000000, 20130707000000, [�i�b�N�o�[�K�[,1]]";
		List<Transaction> trans = Transaction.getTByContract(Contract.getContract(0));
		assertThat(trans.size(), is(1));
		assertThat(trans.toString(), is(expected));
	}
	
	@Test
	public void ������o�^���đ���Ə���ł��v�Z����() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date,0);
		receipt.addLineItem(0, 1);
		receipt.addLineItem(1, 2);
		receipt.save();
		receipt.total();
		String line1 = "0 : 20130707000000, 20130707000000, [�i�b�N�o�[�K�[,1]["+HardCode.DAIKIN+",90.00]["+HardCode.EXCISE+",4.50]";
		String line2 = "1 : 20130707000000, 20130707000000, [�i�b�N�`�[�Y�o�[�K�[,2]["+HardCode.DAIKIN+",240.00]["+HardCode.EXCISE+",12.00]";
		List<Transaction> trans = Transaction.getTByContract(Contract.getContract(0));
		List<CFTransaction> cfTrans = trans.get(0).getCFTransactions();
		assertThat(trans.size(), is(1));
		assertThat(cfTrans.size(), is(2));
		assertThat(cfTrans.get(0).toString(), is(line1));
		assertThat(cfTrans.get(1).toString(), is(line2));
	}
}
