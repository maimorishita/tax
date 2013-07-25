package jp.co.isken.tax.run;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayInputStream;
import java.util.Date;

import jp.co.isken.tax.util.StanderdIOTest;
import jp.co.isken.tax.util.Util;

import org.junit.Test;

public class TAXTest extends StanderdIOTest {

	@Test
	public void test() throws Exception {
		Initializer.test();
		String inputString = joinStrings("0", "1", "0", "1", "2");
		System.setIn(new ByteArrayInputStream(inputString.getBytes()));
		Date _date = Util.stringToDate("20130105000000");
		TAX.sell(_date);
		System.out.flush();
		String expected = joinStrings(
				"0 : 20130707000000, 20130707000000, SATO, 切捨て, 計上日",
				"\n一覧かから選んでください(契約ID)", "\n販売メニュー:(1.注文　2.会計) )",
				"0 : ナックバーガー(90)", "1 : ナックチーズバーガー(120)", "2 : ナックポテトS(150)",
				"\nメニューの中から選んでください(商品ID)", "\n個数を入力してください",
				"\n販売メニュー:(1.注文　2.会計) )", "注文内容",
				"0 : 20130105000000, 20130105000000, [ナックバーガー,1]");
		String actual = _baos.toString();
		assertThat(actual, is(expected));
	}

}
