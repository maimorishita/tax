package jp.co.isken.tax.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class MainServiceTest extends StanderdIOTest {

	@Test
	public void testMain() {
		String inputString = joinStrings("こんにちは", "さようなら");
		System.setIn(new ByteArrayInputStream(inputString.getBytes()));
		MainService.main(null);
		System.out.flush();
		String expected = joinStrings("Hello!", "メニューを選択してください。",
				"INPUT: ", "OUTPUT: こんにちは", "INPUT: ", "OUTPUT: さようなら",
				"PROGRAM END");
		String actual = _baos.toString();
		assertThat(actual, is(expected));
	}
}
