package jp.co.isken.tax.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import jp.co.isken.tax.entity.CFTransaction;
import jp.co.isken.tax.entity.CanTax;
import jp.co.isken.tax.entity.TaxableType;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.entity.TransactionType;
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

	@Test
	public void 商流取引から消費税を計算する() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date, 0);
		receipt.set(TransactionType.getEnum("販売"), CanTax.getEnum("国内"),
				TaxableType.getEnum("社外"));
		receipt.addLineItem(0, 1);
		receipt.addLineItem(1, 1);
		receipt.total();
		receipt.save();
		Transaction t = receipt.getTransaction();
		CashFlow cf = new CashFlow(receipt.getTransaction());
		t.update();

		List<CFTransaction> target = t.getCFTransactions();
		String expected = "0 : 20130707000000, 20130707000000, [ナックバーガー,1]["
				+ HardCode.DAIKIN + ",90.00][" + HardCode.EXCISE + ",4.50]";
		assertThat(target.size(), is(2));
		assertThat(target.get(0).toString(), is(expected));
	}

	@Test
	public void 国外取引の場合は消費税を計算しない() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		Receipt receipt = new Receipt(date, 0);
		receipt.set(TransactionType.getEnum("販売"), CanTax.getEnum("国外"),
				TaxableType.getEnum("社外"));
		receipt.addLineItem(0, 1);
		receipt.addLineItem(1, 1);
		receipt.total();
		receipt.save();
		Transaction t = receipt.getTransaction();
		CashFlow cf = new CashFlow(receipt.getTransaction());
		t.update();

		List<CFTransaction> target = t.getCFTransactions();
		String expected = "0 : 20130707000000, 20130707000000, [ナックバーガー,1]["
				+ HardCode.DAIKIN + ",90.00]";
		String expected2 = "1 : 20130707000000, 20130707000000, [ナックチーズバーガー,1]["
				+ HardCode.DAIKIN + ",120.00]";
		assertThat(target.size(), is(2));
		assertThat(target.get(0).toString(), is(expected));
		assertThat(target.get(1).toString(), is(expected2));
	}
}
