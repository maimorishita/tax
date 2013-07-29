package jp.co.isken.tax.exciseLibrary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Term {

	private int id;
	private Date from;
	private Date to;
	private static List<Term> $termList = new ArrayList<Term>();
	private static int count = 0;

	public Term(Date from, Date to) {
		this.setFrom(from);
		this.setTo(to);
		id = count++;
	}

	public int getId() {
		return id;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public boolean isEffectiveDate(Date date) {
		if (from.before(date)
				&& to.after(date)) {
			return true;
		}
		return false;
	}

}
