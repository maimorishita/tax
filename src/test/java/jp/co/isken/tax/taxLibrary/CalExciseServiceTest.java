package jp.co.isken.tax.taxLibrary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Date;

import jp.co.isken.tax.exciseLibrary.service.CalculationTaxAmount;
import jp.co.isken.tax.util.Initializer;
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
				new BigDecimal("90.00"), date, "国内", "切捨て");
		assertThat(target, is(new BigDecimal("4")));
	}

	@Test
	public void 消費税を計算する_切上げ() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("ナックバーガー",
				new BigDecimal("90.00"), date, "国内", "切上げ");
		assertThat(target, is(new BigDecimal("5")));
	}

	@Test
	public void 消費税を計算する_四捨五入() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("ナックバーガー",
				new BigDecimal("90.00"), date, "国内", "四捨五入");
		assertThat(target, is(new BigDecimal("5")));
	}

	public void 国外取引の場合は消費税を計算しない() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("ナックバーガー",
				new BigDecimal("90.00"), date, "国外", "切捨て");
		assertThat(target, is(new BigDecimal("0")));
	}
}
