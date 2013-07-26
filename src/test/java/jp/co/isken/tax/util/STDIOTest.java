package jp.co.isken.tax.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;


public class STDIOTest {
	protected ByteArrayOutputStream _baos;
	protected PrintStream _out;
	protected ByteArrayInputStream _bais;
	protected InputStream _in;

	@Before
	public void setUp() {
		_baos = new ByteArrayOutputStream();
		_out = System.out;
		System.setOut(new PrintStream(new BufferedOutputStream(_baos)));
		_bais = new ByteArrayInputStream("".getBytes());
		_in = System.in;
		System.setIn(_bais);
	}

	@After
	public void tearDown() throws IOException {
		System.setOut(_out);
		System.setIn(_in);
		_bais.close();
		_baos.close();
	}

//	@Test
//	public void testHello() {
//		String inputString = joinStrings("����ɂ���", "���悤�Ȃ�");
//		System.setIn(new ByteArrayInputStream(inputString.getBytes()));
//		StdIOSample.main(new String[0]);
//		System.out.flush();
//		String expected = joinStrings("Hello!", "http://www.hyuki.com/",
//				"INPUT: ", "OUTPUT: ����ɂ���", "INPUT: ", "OUTPUT: ���悤�Ȃ�",
//				"PROGRAM END");
//		String actual = _baos.toString();
//		assertEquals(expected, actual);
//	}

	protected String joinStrings(String... strs) {
		String newLine = System.getProperty("line.separator");
		String result = "";
		for (String s : strs) {
			result += s + newLine;
		}
		return result;
	}

//	public static junit.framework.Test suite() {
//		return new JUnit4TestAdapter(STDIOTest.class);
//	}
}