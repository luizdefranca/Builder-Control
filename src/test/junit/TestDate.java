package test.junit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import org.jfree.util.Log;
import org.junit.Test;

import ca.programmerlife.report.util.DateUtil;

public class TestDate {

	@Test
	public void testGetActualDateReportName() {
		
		try {
			String expectedValue = "02-03-2019";
			String actualValue = DateUtil.getActualDateReportName();
			assertEquals(expectedValue, actualValue);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testformatDateSql() {
		
		try {
			String expectedValue = "\'2019-02-03\'";
			String actualValue = DateUtil.formatDateSql(Date.valueOf(LocalDate.now()));
			assertEquals(expectedValue, actualValue);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
