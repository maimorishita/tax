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
import jp.co.isken.tax.service.Order;
import jp.co.isken.tax.util.HardCode;

public class TAX {

	public static void main(String[] args) throws Exception {
		init();
		Date _date = setDate();
		while (true) {
			System.out.println("メニュー:(0.契約  1.販売　2.売上表示　3.日付の変更　q.メニュー終了) )");
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
		System.out.println("取引先名を入力してください");
		String party = Input.$getLine();
		System.out.println("締結日を入力してください(yy/MM/dd)");
		Date contracatedDate = Input.$getDate();
		System.out.println("発効日を入力してください(yy/MM/dd)");
		Date effectiveDate = Input.$getDate();
		showCalTaxOption();
		System.out.println("丸め方法を入力してください");
		int calType = Input.$getNumber();
		System.out.println("下記の通りに記録しました。");
		ContractFacade.saveContract(contracatedDate, effectiveDate, party,
				calType);
		Contract contract = ContractFacade.getContracatByContractedDate(party, contracatedDate);
		System.out.println(contract.toString());
	}

	
	public static void sell(Date _date) throws Exception {
		Order order = selectContract(_date);
		boolean isfirst = true;
		while (true) {
			System.out.println(HardCode.SELLMENU);
			char op = Input.$getOp();
			if (op == '1') {
				if (isfirst == true) {
					showTransactionTypeEnum();
					System.out.println("\n取引種別を選んでください。");
					int tt = Input.$getNumber();
					showCanTaxEnum();
					System.out.println("\n不課税対象区分を選んでください。");
					int cantax = Input.$getNumber();
					showTaxableTypeEnum();
					System.out.println("\n課税対象区分を選んでください。");
					int taxable = Input.$getNumber();
					order.set(TransactionType.getEnumById(tt),
							CanTax.getEnumById(cantax),
							TaxableType.getEnumById(taxable));
					isfirst = false;
				}
				order(order);
			} else if (op == '2') {
				saveorder(order);
				total(order);
				showTotal(_date, order);
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

	public static void order(Order order) throws Exception {
		displayList(Product.iterator());
		System.out.println("\nメニューの中から選んでください(商品ID)");
		int id = Input.$getNumber();
		System.out.println("\n個数を入力してください");
		int quantity = Input.$getNumber();
		order.addLineItem(id, quantity);
	}

	public static void saveorder(Order order) {
		order.save();
	}

	public static void total(Order order) throws Exception {
		order.total();
	}

	public static Order selectContract(Date _date) {
		displayList(Contract.iterator());
		System.out.println("\n一覧かから選んでください(契約ID)");
		int contractId = Input.$getNumber();
		Order order = new Order(_date, contractId);
		return order;
	}

	public static void showTotal(Date _date, Order order) {
		System.out.println("注文内容");
		Contract c = order.getTransaction().getContract();
		Iterator<Transaction> targets = Transaction.getTransactions(c, _date);
		displayList(targets);
		System.out.println("代金");
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
		System.out.println("\n今日の日付を入力してください(yy/MM/dd)");
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
			System.out.println("日付入力エラー");
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
