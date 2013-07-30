package jp.co.isken.tax.run;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import jp.co.isken.tax.entity.CalTaxOption;
import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Product;
import jp.co.isken.tax.entity.cashFlow.CFTransaction;
import jp.co.isken.tax.entity.transaction.CanTax;
import jp.co.isken.tax.entity.transaction.TaxableType;
import jp.co.isken.tax.entity.transaction.Transaction;
import jp.co.isken.tax.entity.transaction.TransactionType;
import jp.co.isken.tax.service.ContractFacade;
import jp.co.isken.tax.service.Receipt;
import jp.co.isken.tax.util.HardCode;

public class TAX {

	public static void main(String[] args) throws Exception {
		init();
		Date _date = setDate();
		while (true) {
			System.out.println("���j���[:(0.�_��  1.�̔��@2.����\���@3.���t�̕ύX�@q.���j���[�I��) )");
			char _op = Input.$getOp();
			switch (_op) {
			case '0':
				contract(_date);
				break;
			case '1':
				sell(_date);
				break;
			case '2':
				// showSales();
				break;
			case '3':
				_date = setDate();
				break;
			default:
				return;
			}
			System.out.println();
		}
	}

	private static void init() {
		Initializer.init();
	}

	public static void contract(Date _date) throws Exception {
		System.out.println("����於����͂��Ă�������");
		String party = Input.$getLine();
		System.out.println("����������͂��Ă�������(yy/MM/dd)");
		Date contracatedDate = Input.$getDate();
		System.out.println("����������͂��Ă�������(yy/MM/dd)");
		Date effectiveDate = Input.$getDate();
		showCalTaxOption();
		System.out.println("�ۂߕ��@����͂��Ă�������");
		int calType = Input.$getNumber();
		System.out.println("���L�̒ʂ�ɋL�^���܂����B");
		ContractFacade.saveContract(contracatedDate, effectiveDate, party,
				calType);
		Contract contract = ContractFacade.getContracatByContractedDate(party, contracatedDate);
		System.out.println(contract.toString());
	}

	
	public static void sell(Date _date) throws Exception {
		Receipt receipt = selectContract(_date);
		boolean isfirst = true;
		while (true) {
			System.out.println(HardCode.SELLMENU);
			char op = Input.$getOp();
			if (op == '1') {
				if (isfirst == true) {
					showTransactionTypeEnum();
					System.out.println("\n�����ʂ�I��ł��������B");
					int tt = Input.$getNumber();
					showCanTaxEnum();
					System.out.println("\n�s�ېőΏۋ敪��I��ł��������B");
					int cantax = Input.$getNumber();
					showTaxableTypeEnum();
					System.out.println("\n�ېőΏۋ敪��I��ł��������B");
					int taxable = Input.$getNumber();
					receipt.set(TransactionType.getEnumById(tt),
							CanTax.getEnumById(cantax),
							TaxableType.getEnumById(taxable));
					isfirst = false;
				}
				order(receipt);
			} else if (op == '2') {
				saveReceipt(receipt);
				total(receipt);
				showTotal(_date, receipt);
				break;
			} else {
				break;
			}
		}
	}

	
	private static void showCalTaxOption() {
		for (CalTaxOption e : CalTaxOption.values()) {
			System.out.println(e.getId() + ": " + e.getName());
		}
	}

	
	private static void showTransactionTypeEnum() {
		for (TransactionType e : TransactionType.values()) {
			System.out.println(e.getId() + ": " + e.getName());
		}
	}

	private static void showCanTaxEnum() {
		for (CanTax e : CanTax.values()) {
			System.out.println(e.getId() + ": " + e.getName());
		}
	}

	private static void showTaxableTypeEnum() {
		for (TaxableType e : TaxableType.values()) {
			System.out.println(e.getId() + ": " + e.getName());
		}
	}

	public static void order(Receipt receipt) throws Exception {
		displayList(Product.iterator());
		System.out.println("\n���j���[�̒�����I��ł�������(���iID)");
		int id = Input.$getNumber();
		System.out.println("\n������͂��Ă�������");
		int quantity = Input.$getNumber();
		receipt.addLineItem(id, quantity);
	}

	public static void saveReceipt(Receipt receipt) {
		receipt.save();
	}

	public static void total(Receipt receipt) throws Exception {
		receipt.total();
	}

	public static Receipt selectContract(Date _date) {
		displayList(Contract.iterator());
		System.out.println("\n�ꗗ������I��ł�������(�_��ID)");
		int contractId = Input.$getNumber();
		Receipt receipt = new Receipt(_date, contractId);
		return receipt;
	}

	public static void showTotal(Date _date, Receipt receipt) {
		System.out.println("�������e");
		Contract c = receipt.getTransaction().getContract();
		Iterator<Transaction> targets = Transaction.getTransactions(c, _date);
		displayList(targets);
		System.out.println("���");
		targets = Transaction.getTransactions(c, _date);
		while (targets.hasNext()) {
			Transaction t = targets.next();
			for (CFTransaction cft : t.getCFTransactions()) {
				System.out.println(cft.toString());
			}
		}
	}

	private static <T> void displayList(Iterator<T> iter) {
		while (iter.hasNext()) {
			System.out.println(iter.next().toString());
		}
	}

	public static Date setDate() {
		System.out.println("\n�����̓��t����͂��Ă�������(yy/MM/dd)");
		return Input.$getDate();
	}
}

class Input {
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("yy/MM/dd");
	public static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));

	public static char $getOp() {
		String buf = null;
		buf = $getLine();
		if (buf == null)
			buf = "q";
		return buf.charAt(0);
	}

	public static int $getNumber() {
		String buf = null;
		buf = $getLine();
		if (buf == null)
			buf = "0";
		return Integer.parseInt(buf);
	}

	public static Date $getDate() {
		String buf = null;
		Date date = null;
		try {
			buf = $getLine();
			date = DATE_FORMAT.parse(buf);
		} catch (Exception ex) {
			System.out.println("���t���̓G���[");
		}
		return date;
	}

	public static String $getLine() {
		String line = null;
		try {
			line = br.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return line;
	}

}
