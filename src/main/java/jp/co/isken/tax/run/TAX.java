package jp.co.isken.tax.run;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import jp.co.isken.tax.entity.Contract;
import jp.co.isken.tax.entity.Party;
import jp.co.isken.tax.entity.Product;
import jp.co.isken.tax.service.ContractFacade;
import jp.co.isken.tax.service.CustomerFacade;
import jp.co.isken.tax.service.ProductFacade;
import jp.co.isken.tax.service.Receipt;

public class TAX {

	public static void main(String[] args) {
		// init();
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

	public static void contract(Date _date) {
		System.out.println("����於����͂��Ă�������");
		String party = Input.$getLine();
		System.out.println("�ۂߕ��@����͂��Ă�������");
		String calType = Input.$getLine();
		System.out.println("�ېœ������͂��Ă�������");
		String baseDateType = Input.$getLine();
		System.out.println("���L�̒ʂ�ɋL�^���܂����B");
		ContractFacade.saveContract(_date, party, calType, baseDateType);
		Contract contract = ContractFacade.getContract(party, _date);
		System.out.println(contract.toString());
	}

	public static void sell(Date _date) {
		Receipt receipt = new Receipt(_date);
		while (true) {
			System.out.println("\n�̔����j���[:(1.�����@2.��v) )");
			char op = Input.$getOp();
			if (op != '1')
				break;
			displayList(CustomerFacade.iterator());
			System.out.println("\n�ꗗ������I��ł�������(�����ID)");

			displayList(ProductFacade.iterator());
			System.out.println("\n���j���[�̒�����I��ł�������(���iID)");
			int id = Input.$getNumber();

			System.out.println("\n������͂��Ă�������");
			int quantity = Input.$getNumber();

			receipt.addLineItem(id, quantity);
		}

		// System.out.println("����於����͂��Ă�������");
		// String party = Input.$getLine();
		// receipt.setCustomer(Party.getParty(party));
		// receipt.commit();

		// System.out.println("���L�̒ʂ�ɋL�^���܂����B");
		// ContractFacade.saveContract(_date, party, calType, baseDateType);
		// Contract contract = ContractFacade.getContract(party, _date);
		// System.out.println(contract.toString());
	}

	private static void displayList(Iterator iter) {
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
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			line = br.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return line;
	}

}
