package jp.co.isken.tax.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class MainServiceTest extends StanderdIOTest {

	@Test
	public void testMain() {
		String inputString = joinStrings("����ɂ���", "���悤�Ȃ�");
		System.setIn(new ByteArrayInputStream(inputString.getBytes()));
		MainService.main(null);
		System.out.flush();
		String expected = joinStrings("Hello!", "���j���[��I�����Ă��������B",
				"INPUT: ", "OUTPUT: ����ɂ���", "INPUT: ", "OUTPUT: ���悤�Ȃ�",
				"PROGRAM END");
		String actual = _baos.toString();
		assertThat(actual, is(expected));
	}
}
