package jp.co.isken.tax.util;

import static org.junit.Assert.assertEquals;
import junit.framework.JUnit4TestAdapter;

import org.junit.*;

import java.io.*;

public class StanderdIOTest {
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
	public void tearDown() {
		System.setOut(_out);
		System.setIn(_in);
	}

	@Test
	public void testHello() {
		String inputString = joinStrings("‚±‚ñ‚É‚¿‚Í", "‚³‚æ‚¤‚È‚ç");
		System.setIn(new ByteArrayInputStream(inputString.getBytes()));
		StdIOSample.main(new String[0]);
		System.out.flush();
		String expected = joinStrings("Hello!", "http://www.hyuki.com/",
				"INPUT: ", "OUTPUT: ‚±‚ñ‚É‚¿‚Í", "INPUT: ", "OUTPUT: ‚³‚æ‚¤‚È‚ç",
				"PROGRAM END");
		String actual = _baos.toString();
		assertEquals(expected, actual);
	}

	protected String joinStrings(String... strs) {
		String newLine = System.getProperty("line.separator");
		String result = "";
		for (String s : strs) {
			result += s + newLine;
		}
		return result;
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(StanderdIOTest.class);
	}
}