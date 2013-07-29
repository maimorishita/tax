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
	public void 代金計算ができる() throws Exception {
		String inputString = TestUtil.joinStrings("0", "1", "0", "1", "1", "1",
				"2", "2");
		System.setIn(new ByteArrayInputStream(inputString.getBytes()));
		Date _date = Util.stringToDate("20130105000000");
		TAX.sell(_date);
		System.out.flush();
		String menuAndInput = TestUtil
				.joinStrings(
						"0 : 20130707000000, 20130707000000, SATO, 切捨て, 計上日",
						"\n一覧かから選んでください(契約ID)",
						"\n販売メニュー:(1.注文　2.会計) )",
						"0 : ナックバーガー(90.00)",
						"1 : ナックチーズバーガー(120.00)",
						"2 : ナックポテトS(150.00)",
						"\nメニューの中から選んでください(商品ID)",
						"\n個数を入力してください",
						"\n販売メニュー:(1.注文　2.会計) )",
						"0 : ナックバーガー(90.00)",
						"1 : ナックチーズバーガー(120.00)",
						"2 : ナックポテトS(150.00)",
						"\nメニューの中から選んでください(商品ID)",
						"\n個数を入力してください",
						"\n販売メニュー:(1.注文　2.会計) )",
						"注文内容",
						"0 : 20130105000000, 20130105000000, [ナックバーガー,1][ナックチーズバーガー,2]",
						"代金",
						"0 : 20130105000000, 20130105000000, [ナックバーガー,1][商品代金,90.00][消費税,4.50]",
						"1 : 20130105000000, 20130105000000, [ナックチーズバーガー,2][商品代金,240.00][消費税,12.00]");
		String actual = _baos.toString();
		assertThat(actual, is(menuAndInput));
	}
}
