package ca.programmerlife.report.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil implements Serializable {

	private static final long serialVersionUID = -1468659188744655501L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public static String getActualDateReportName() {
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return hoje.format(format);
	}
	
	public static String formatDateSql(Date date) {
		StringBuffer stringBuilder = new StringBuffer();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		stringBuilder.append("'");
		stringBuilder.append(df.format(date));
		stringBuilder.append("'");
		return stringBuilder.toString();
	}

}
