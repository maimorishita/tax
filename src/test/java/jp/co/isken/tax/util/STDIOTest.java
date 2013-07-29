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
}