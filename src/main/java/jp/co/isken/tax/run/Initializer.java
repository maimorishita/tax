package jp.co.isken.tax.run;

import java.math.BigDecimal;
import java.util.Date;

import jp.co.isken.tax.entity.Account;
import jp.co.isken.tax.entity.CalTaxOption;
import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Entry;
import jp.co.isken.tax.entity.Party;
import jp.co.isken.tax.entity.Product;
import jp.co.isken.tax.entity.cashFlow.CFAccount;
import jp.co.isken.tax.entity.cashFlow.CFEntry;
import jp.co.isken.tax.entity.cashFlow.CFTransaction;
import jp.co.isken.tax.entity.transaction.Transaction;
import jp.co.isken.tax.exciseLibrary.entity.Item;
import jp.co.isken.tax.exciseLibrary.entity.Tax;
import jp.co.isken.tax.exciseLibrary.entity.TaxRate;
import jp.co.isken.tax.exciseLibrary.entity.Term;
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

		Product nacburger = new Product("�i�b�N�o�[�K�[", new BigDecimal("90.00"));
		Product naccheeseburger = new Product("�i�b�N�`�[�Y�o�[�K�[", new BigDecimal(
				"120.00"));
		Product nacpotato_s = new Product("�i�b�N�|�e�gS", new BigDecimal("150.00"));
		Product nacpotato_m = new Product("�i�b�N�|�e�gM", new BigDecimal("220.00"));
		Product naccola = new Product("�i�b�N�R�[��", new BigDecimal("150.00"));
		Product nacshake = new Product("�i�b�N�V�F�[�N", new BigDecimal("200.00"));

		Party p1 = new Party("SATO");
		Party p2 = new Party("GOTO");
		Party p3 = new Party("MUTO");

		Date contractedDate = Util.stringToDate("20130707000000");
		Date effectiveDate = Util.stringToDate("20130708000000");
		Contract c = new Contract(contractedDate, effectiveDate, p1,
				CalTaxOption.ROUND_DOWN);
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

		Product nacburger = new Product("�i�b�N�o�[�K�[", new BigDecimal("90.00"));
		Product naccheeseburger = new Product("�i�b�N�`�[�Y�o�[�K�[", new BigDecimal(
				"120.00"));
		Product nacpotato_s = new Product("�i�b�N�|�e�gS", new BigDecimal("150.00"));

		Party p1 = new Party("SATO");
		Party p2 = new Party("GOTO");
		Party p3 = new Party("MUTO");

		Date contractedDate = Util.stringToDate("20130707000000");
		Date effectiveDate = Util.stringToDate("20130708000000");
		Contract c = new Contract(contractedDate, effectiveDate, p1,
				CalTaxOption.ROUND_DOWN);

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
