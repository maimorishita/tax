package jp.co.isken.tax.taxLibrary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Date;

import jp.co.isken.tax.exciseLibrary.service.CalculationTaxAmount;
import jp.co.isken.tax.run.Initializer;
import jp.co.isken.tax.util.Util;

import org.junit.Before;
import org.junit.Test;

public class CalExciseServiceTest {

	@Before
	public void setUp() {
		Initializer.test();
	}

	@Test
	public void 消費税を計算する() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("ナックバーガー",
				new BigDecimal("90.00"), date, date, "販売", "国内", "社外");
		assertThat(target, is(new BigDecimal("4.5000")));
	}

	@Test(expected = Exception.class)
	public void 仕入取引の場合は消費税を計算しない() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("ナックバーガー",
				new BigDecimal("90.00"), date, date, "仕入", "国内", "社外");
	}
	
	@Test(expected = Exception.class)
	public void 国外取引の場合は消費税を計算しない() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("ナックバーガー",
				new BigDecimal("90.00"), date, date, "販売", "国外", "社外");
	}
	
	@Test(expected = Exception.class)
	public void 社内取引の場合は消費税を計算しない() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("ナックバーガー",
				new BigDecimal("90.00"), date, date, "販売", "国内", "社内");
	}
}
