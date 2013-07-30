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
	public void ����ł��v�Z����() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("�i�b�N�o�[�K�[",
				new BigDecimal("90.00"), date, "����", "�؎̂�");
		assertThat(target, is(new BigDecimal("4")));
	}

	@Test
	public void ����ł��v�Z����_�؏グ() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("�i�b�N�o�[�K�[",
				new BigDecimal("90.00"), date, "����", "�؏グ");
		assertThat(target, is(new BigDecimal("5")));
	}

	@Test
	public void ����ł��v�Z����_�l�̌ܓ�() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("�i�b�N�o�[�K�[",
				new BigDecimal("90.00"), date, "����", "�l�̌ܓ�");
		assertThat(target, is(new BigDecimal("5")));
	}

	public void ���O����̏ꍇ�͏���ł��v�Z���Ȃ�() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("�i�b�N�o�[�K�[",
				new BigDecimal("90.00"), date, "���O", "�؎̂�");
		assertThat(target, is(new BigDecimal("0")));
	}
}
