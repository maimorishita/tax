package jp.co.isken.tax.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.Iterator;

import jp.co.isken.tax.entity.cashFlow.CFAccount;
import jp.co.isken.tax.run.Initializer;

import org.junit.Before;
import org.junit.Test;

public class CFAccountTest {

	@Before
	public void setUp() {
		Initializer.test();
	}

	@Test
	public void �A�J�E���g�̍쐬���ł���() {
		try {
			CFAccount target = CFAccount.getAccount("����",
					Party.getParty("SATO"));
		} catch (Exception e) {
			fail("");
		}
		Iterator<CFAccount> iter = CFAccount.iterator();
		iter.hasNext();
		assertThat(iter.next().getName(), is("����"));
		assertThat(iter.hasNext(), is(false));
	}
}
