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
				new BigDecimal("90.00"), date, date, "�̔�", "����", "�ЊO");
		assertThat(target, is(new BigDecimal("4.5000")));
	}

	@Test(expected = Exception.class)
	public void �d������̏ꍇ�͏���ł��v�Z���Ȃ�() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("�i�b�N�o�[�K�[",
				new BigDecimal("90.00"), date, date, "�d��", "����", "�ЊO");
	}
	
	@Test(expected = Exception.class)
	public void ���O����̏ꍇ�͏���ł��v�Z���Ȃ�() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("�i�b�N�o�[�K�[",
				new BigDecimal("90.00"), date, date, "�̔�", "���O", "�ЊO");
	}
	
	@Test(expected = Exception.class)
	public void �Г�����̏ꍇ�͏���ł��v�Z���Ȃ�() throws Exception {
		Date date = Util.stringToDate("20130707000000");
		BigDecimal target = CalculationTaxAmount.getExciese("�i�b�N�o�[�K�[",
				new BigDecimal("90.00"), date, date, "�̔�", "����", "�Г�");
	}
}
