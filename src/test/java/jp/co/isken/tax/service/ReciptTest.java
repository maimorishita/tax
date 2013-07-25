package jp.co.isken.tax.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;
import java.util.List;

import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.run.Initializer;
import jp.co.isken.tax.util.Util;

import org.junit.Before;
import org.junit.Test;

public class ReciptTest {

	@Before
	public void before(){
		Initializer.test();
	}
	
	@Test
	public void saveTransaction() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date,0);
		receipt.addLineItem(0, 1);
		receipt.save();
		String expected = "[0 : 20130707000000, 20130707000000, [ナックバーガー,1]]";
		List<Transaction> target = Transaction.getTByContract(Contract.getContract(0));
		assertThat(target.size(), is(1));
		assertThat(target.toString(), is(expected));
	}

}
