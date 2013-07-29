package jp.co.isken.tax.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayInputStream;
import java.util.Date;

import jp.co.isken.tax.run.Initializer;
import jp.co.isken.tax.run.TAX;

import org.junit.Before;
import org.junit.Test;

public class TAXTest extends STDIOTest {

	@Before
	public void init() {
		Initializer.test();
	}

	@Test
	public void ����v�Z���ł���() throws Exception {
		String inputString = TestUtil.joinStrings("0", "1", "0", "1", "1", "1",
				"2", "2");
		System.setIn(new ByteArrayInputStream(inputString.getBytes()));
		Date _date = Util.stringToDate("20130105000000");
		TAX.sell(_date);
		System.out.flush();
		String menuAndInput = TestUtil
				.joinStrings(
						"0 : 20130707000000, 20130707000000, SATO, �؎̂�, �v���",
						"\n�ꗗ������I��ł�������(�_��ID)",
						"\n�̔����j���[:(1.�����@2.��v) )",
						"0 : �i�b�N�o�[�K�[(90.00)",
						"1 : �i�b�N�`�[�Y�o�[�K�[(120.00)",
						"2 : �i�b�N�|�e�gS(150.00)",
						"\n���j���[�̒�����I��ł�������(���iID)",
						"\n������͂��Ă�������",
						"\n�̔����j���[:(1.�����@2.��v) )",
						"0 : �i�b�N�o�[�K�[(90.00)",
						"1 : �i�b�N�`�[�Y�o�[�K�[(120.00)",
						"2 : �i�b�N�|�e�gS(150.00)",
						"\n���j���[�̒�����I��ł�������(���iID)",
						"\n������͂��Ă�������",
						"\n�̔����j���[:(1.�����@2.��v) )",
						"�������e",
						"0 : 20130105000000, 20130105000000, [�i�b�N�o�[�K�[,1][�i�b�N�`�[�Y�o�[�K�[,2]",
						"���",
						"0 : 20130105000000, 20130105000000, [�i�b�N�o�[�K�[,1][���i���,90.00][�����,4.50]",
						"1 : 20130105000000, 20130105000000, [�i�b�N�`�[�Y�o�[�K�[,2][���i���,240.00][�����,12.00]");
		String actual = _baos.toString();
		assertThat(actual, is(menuAndInput));
	}
}
