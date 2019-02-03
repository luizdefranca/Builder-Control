package ca.programmerlife.report.util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtil implements Serializable{

	private static final long serialVersionUID = 3844822707374736636L;

	private static final String UNDERLINE = "_";
	private static final String REPORT_FOLDER = "/reports";
	private static final String SUBREPORT_FOLDER = "SUBREPORT_FOLDER";
	private static final String EXTENSION_ODS = "ods";
	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_HTML = "html";
	private static final String EXTENSION_pdf = "pdf";
	
	private String SEPARATOR = File.separator;
	private static final int REPORT_PDF = 1;
	private static final int REPORT_EXCEL = 2;
	private static final int REPORT_HTML = 3;
	private static final int REPORT_OPEN_OFFICE = 4;
	
	private static final String DOT = ".";
	private StreamedContent returnFile = null;
	private String pathReportFile = null;
	private JRExporter typeOfExportedFile = null;
	private String extensionOfExportedFile = "";
	private File createdFile = null;
	
	public StreamedContent createReport(List<?> listDataBeanCollectionResport, HashMap reportParameters, String ReportJasperName, 
			String outPutReportName, int ReportType) throws Exception{
		//Create the list of datasource beans to insert into report
		JRBeanCollectionDataSource jrbcds
	}
	
}
