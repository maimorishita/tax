package jp.co.isken.tax.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.cashFlow.CFTransaction;
import jp.co.isken.tax.entity.transaction.CanTax;
import jp.co.isken.tax.entity.transaction.TaxableType;
import jp.co.isken.tax.entity.transaction.Transaction;
import jp.co.isken.tax.entity.transaction.TransactionType;
import jp.co.isken.tax.run.Initializer;
import jp.co.isken.tax.util.HardCode;
import jp.co.isken.tax.util.Util;

import org.junit.Before;
import org.junit.Test;

public class ReciptTest {

	@Before
	public void before() {
		Initializer.test();
	}

	@Test
	public void 注文を登録する() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date, 0);
		receipt.addLineItem(0, 1);
		receipt.save();
		String expected = "[0 : 20130707000000, 20130707000000, [ナックバーガー,1]]";
		List<Transaction> trans = Transaction.getTByContract(Contract
				.getContract(0));
		assertThat(trans.size(), is(1));
		assertThat(trans.toString(), is(expected));
	}

	@Test
	public void 注文を登録して代金と消費税を計算する() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date, 0);
		receipt.set(TransactionType.getEnum("販売"), CanTax.getEnum("国内"),
				TaxableType.getEnum("社外"));
		receipt.addLineItem(0, 1);
		receipt.addLineItem(1, 2);
		receipt.save();
		receipt.total();
		String line1 = "0 : 20130707000000, 20130707000000, [ナックバーガー,1]["
				+ HardCode.DAIKIN + ",90.00][" + HardCode.EXCISE + ",4.00]";
		String line2 = "1 : 20130707000000, 20130707000000, [ナックチーズバーガー,2]["
				+ HardCode.DAIKIN + ",240.00][" + HardCode.EXCISE + ",12.00]";
		List<Transaction> trans = Transaction.getTByContract(Contract
				.getContract(0));
		List<CFTransaction> cfTrans = trans.get(0).getCFTransactions();
		assertThat(trans.size(), is(1));
		assertThat(cfTrans.size(), is(2));
		assertThat(cfTrans.get(0).toString(), is(line1));
		assertThat(cfTrans.get(1).toString(), is(line2));
	}

	
	@Test
	public void 国外取引の場合は消費税は0円になる() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date, 0);
		receipt.set(TransactionType.getEnum("販売"), CanTax.getEnum("国外"),
				TaxableType.getEnum("社外"));
		receipt.addLineItem(0, 1);
		receipt.addLineItem(1, 2);
		receipt.save();
		receipt.total();

		List<CFTransaction> target = receipt.getTransaction().getCFTransactions();
		String expected = "0 : 20130707000000, 20130707000000, [ナックバーガー,1]["
				+ HardCode.DAIKIN + ",90.00][消費税,0.00]";
		String expected2 = "1 : 20130707000000, 20130707000000, [ナックチーズバーガー,2]["
				+ HardCode.DAIKIN + ",240.00][消費税,0.00]";
		assertThat(target.size(), is(2));
		assertThat(target.get(0).toString(), is(expected));
		assertThat(target.get(1).toString(), is(expected2));
	}
	
	@Test
	public void 仕入れ取引の場合は消費税を計算しない() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date, 0);
		receipt.set(TransactionType.getEnum("仕入れ"), CanTax.getEnum("国内"),
				TaxableType.getEnum("社外"));
		receipt.addLineItem(0, 1);
		receipt.addLineItem(1, 2);
		receipt.save();
		receipt.total();

		List<CFTransaction> target = receipt.getTransaction().getCFTransactions();
		String expected = "0 : 20130707000000, 20130707000000, [ナックバーガー,1]["
				+ HardCode.DAIKIN + ",90.00]";
		String expected2 = "1 : 20130707000000, 20130707000000, [ナックチーズバーガー,2]["
				+ HardCode.DAIKIN + ",240.00]";
		assertThat(target.size(), is(2));
		assertThat(target.get(0).toString(), is(expected));
		assertThat(target.get(1).toString(), is(expected2));
	}
	
	@Test
	public void 社内取引の場合は消費税を計算しない() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date, 0);
		receipt.set(TransactionType.getEnum("販売"), CanTax.getEnum("国内"),
				TaxableType.getEnum("社内"));
		receipt.addLineItem(0, 1);
		receipt.addLineItem(1, 2);
		receipt.save();
		receipt.total();

		List<CFTransaction> target = receipt.getTransaction().getCFTransactions();
		String expected = "0 : 20130707000000, 20130707000000, [ナックバーガー,1]["
				+ HardCode.DAIKIN + ",90.00]";
		String expected2 = "1 : 20130707000000, 20130707000000, [ナックチーズバーガー,2]["
				+ HardCode.DAIKIN + ",240.00]";
		assertThat(target.size(), is(2));
		assertThat(target.get(0).toString(), is(expected));
		assertThat(target.get(1).toString(), is(expected2));
	}
}
