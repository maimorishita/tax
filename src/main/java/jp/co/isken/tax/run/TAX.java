package jp.co.isken.tax.run;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Transaction;
import jp.co.isken.tax.service.ContractFacade;
import jp.co.isken.tax.service.PartyFacade;
import jp.co.isken.tax.service.ProductFacade;
import jp.co.isken.tax.service.Receipt;
import jp.co.isken.tax.service.TransactionFacade;

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

	public static void contract(Date _date) {
		System.out.println("取引先名を入力してください");
		String party = Input.$getLine();
		System.out.println("丸め方法を入力してください");
		String calType = Input.$getLine();
		System.out.println("課税日基準を入力してください");
		String baseDateType = Input.$getLine();
		System.out.println("下記の通りに記録しました。");
		ContractFacade.saveContract(_date, party, calType, baseDateType);
		Contract contract = ContractFacade.getContract(party, _date);
		System.out.println(contract.toString());
	}

	public static void sell(Date _date) throws Exception {
		displayList(ContractFacade.iterator());
		System.out.println("\n一覧かから選んでください(契約ID)");
		int contractId = Input.$getNumber();
		Receipt receipt = new Receipt(_date,contractId);
		while (true) {
			System.out.println("\n販売メニュー:(1.注文　2.会計) )");
			char op = Input.$getOp();
			if (op == '1') {
				displayList(ProductFacade.iterator());
				System.out.println("\nメニューの中から選んでください(商品ID)");
				int id = Input.$getNumber();
				System.out.println("\n個数を入力してください");
				int quantity = Input.$getNumber();
				receipt.addLineItem(id, quantity);
			}
			if (op == '2') {
				receipt.save();
				System.out.println("注文内容");
				displayList(Transaction.getTransactions(contractId, _date));
				break;
			}
		}
	}

	private static void displayList(Iterator iter) {
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
