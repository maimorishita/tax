package jp.co.isken.tax.run;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.service.ContractFacade;

public class TAX {

	public static void main(String[] args) {
		// init();
		Date _date = setDate();
		while (true) {
			System.out.println("メニュー:(0.契約  1.販売　2.売上表示　3.日付の変更　q.メニュー終了) )");
			char _op = Input.$getOp();
			switch (_op) {
			case '0':
				contract(_date);
				break;
			case '1':
//				sell(_date);
				break;
			case '2':
//				showSales();
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

	public static void contract(Date _date) {
		System.out.println("取引先名を入力してください");
		String party = Input.$getLine();
		System.out.println("丸め方法を入力してください");
		String calType = Input.$getLine();
		System.out.println("課税日基準を入力してください");
		String baseDateType = Input.$getLine();
		System.out.println("下記の通りに記録しました。");
		ContractFacade.saveContract(_date,party,calType,baseDateType);
		Contract contract = ContractFacade.getContract(party,_date);
		System.out.println(contract.toString());
	}


	public static Date setDate() {
		System.out.println("\n今日の日付を入力してください(yy/MM/dd)");
		return Input.$getDate();
	}
}

class Input {
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("yy/MM/dd");

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
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			line = br.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return line;
	}
}
