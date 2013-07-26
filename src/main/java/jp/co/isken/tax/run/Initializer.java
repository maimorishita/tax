package jp.co.isken.tax.run;

import java.math.BigDecimal;

import jp.co.isken.tax.entity.Account;
import jp.co.isken.tax.entity.CFAccount;
import jp.co.isken.tax.entity.CFEntry;
import jp.co.isken.tax.entity.CFTransaction;
import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Entry;
import jp.co.isken.tax.entity.Party;
import jp.co.isken.tax.entity.Product;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.exciseLibrary.Item;
import jp.co.isken.tax.exciseLibrary.Tax;
import jp.co.isken.tax.exciseLibrary.TaxRate;
import jp.co.isken.tax.exciseLibrary.Term;
import jp.co.isken.tax.util.HardCode;
import jp.co.isken.tax.util.Util;

public class Initializer {

	public static void init() {
		datainit();
		
		Term term = new Term(Util.stringToDate("20120401000000"),
				Util.stringToDate("21000401000000"));

		Tax tax = new Tax(HardCode.EXCISE);
		BigDecimal bd = new BigDecimal("0.05");
		
		Item ei1 = new Item("�i�b�N�o�[�K�[");
		Item ei2 = new Item("�i�b�N�`�[�Y�o�[�K�[");
		Item ei3 = new Item("�i�b�N�|�e�gS");
		Item ei4 = new Item("�i�b�N�|�e�gM");
		Item ei5 = new Item("�i�b�N�R�[��");
		Item ei6 = new Item("�i�b�N�V�F�[�N");
		TaxRate rate1 = new TaxRate(tax, bd, ei1, term);
		TaxRate rate2 = new TaxRate(tax, bd, ei2, term);
		TaxRate rate3 = new TaxRate(tax, bd, ei3, term);
		TaxRate rate4 = new TaxRate(tax, bd, ei4, term);
		TaxRate rate5 = new TaxRate(tax, bd, ei5, term);
		TaxRate rate6 = new TaxRate(tax, bd, ei6, term);

		Product nacburger = new Product("�i�b�N�o�[�K�[", 90);
		Product naccheeseburger = new Product("�i�b�N�`�[�Y�o�[�K�[", 120);
		Product nacpotato_s = new Product("�i�b�N�|�e�gS", 150);
		Product nacpotato_m = new Product("�i�b�N�|�e�gM", 220);
		Product naccola = new Product("�i�b�N�R�[��", 150);
		Product nacshake = new Product("�i�b�N�V�F�[�N", 200);

		Party p1 = new Party("SATO");
		Party p2 = new Party("GOTO");
		Party p3 = new Party("MUTO");
		
		Contract c = new Contract(Util.stringToDate("20130707000000"), p1,
				"�؎̂�", "�v���");
	}

	public static void test() {
		datainit();

		Item ei1 = new Item("�i�b�N�o�[�K�[");
		Item ei2 = new Item("�i�b�N�`�[�Y�o�[�K�[");
		Item ei3 = new Item("�i�b�N�|�e�gS");

		Term term = new Term(Util.stringToDate("20120401000000"),
				Util.stringToDate("21000401000000"));

		Tax tax = new Tax(HardCode.EXCISE);
		BigDecimal bd = new BigDecimal("0.05");
		TaxRate rate1 = new TaxRate(tax, bd, ei1, term);
		TaxRate rate2 = new TaxRate(tax, bd, ei2, term);
		TaxRate rate3 = new TaxRate(tax, bd, ei3, term);

		Product nacburger = new Product("�i�b�N�o�[�K�[", 90);
		Product naccheeseburger = new Product("�i�b�N�`�[�Y�o�[�K�[", 120);
		Product nacpotato_s = new Product("�i�b�N�|�e�gS", 150);

		Party p1 = new Party("SATO");
		Party p2 = new Party("GOTO");
		Party p3 = new Party("MUTO");

		Contract c = new Contract(Util.stringToDate("20130707000000"), p1,
				"�؎̂�", "�v���");

	}

	public static void datainit() {
		Contract.init();
		Product.init();
		CFTransaction.init();
		CFEntry.init();
		CFAccount.init();
		Transaction.init();
		Entry.init();		
		Account.init();
		Item.init();
	}

}
