package ca.programmerlife.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@SuppressWarnings("unchecked")
@Component
public class ReportUtil implements Serializable{

	private static final long serialVersionUID = 3844822707374736636L;

	private static final String UNDERLINE = "_";
	private static final String REPORT_FOLDER = "/reports";
	private static final String SUBREPORT_FOLDER = "SUBREPORT_FOLDER";
	private static final String EXTENSION_ODS = "ods";
	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_HTML = "html";
	private static final String EXTENSION_PDF = "pdf";
	
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
	private String subReportPath = "";
	
	
	public StreamedContent createReport(List<?> listDataBeanCollectionResport, HashMap reportParameters, String ReportJasperName, 
			String outPutReportName, int ReportType) throws Exception{
		//Create the list of datasource beans to insert into report
		JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(listDataBeanCollectionResport);
		
		//Provide the physical path to the folder which contain the compiled .jasper files 
		FacesContext context = FacesContext.getCurrentInstance();
		context.responseComplete();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		
		String reportPath = servletContext.getRealPath(REPORT_FOLDER);
		
		//EX: -> [application_folder]/reports/my-report.jasper
		File file = new File(reportPath + SEPARATOR + ReportJasperName + DOT + "jasper");
		
		if(reportPath == null || (reportPath != null && reportPath.isEmpty()) 
				|| !file.exists()) {
			reportPath = this.getClass().getResource(REPORT_FOLDER).getPath();
			SEPARATOR = "";
		}
		
		//Image path
		reportParameters.put("REPORT_PARAMETERS_IMG", reportPath);
		
		//Full path to compiled report
		String filePathJasper = reportPath + SEPARATOR + ReportJasperName + DOT + "jasper";
		
		//Responsible for loading the report
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(filePathJasper);
		
		subReportPath = reportPath + SEPARATOR;
		reportParameters.put(SUBREPORT_FOLDER, subReportPath);
		
		//Loading compiled file to the memory
		
		JasperPrint printer = JasperFillManager.fillReport(jasperReport, reportParameters, jrDataSource);
		
		switch (ReportType) {
		case REPORT_PDF:
			typeOfExportedFile = new JRPdfExporter();
			extensionOfExportedFile = EXTENSION_PDF;
			
			break;
		case REPORT_EXCEL:
			typeOfExportedFile = new JRXlsExporter();
			extensionOfExportedFile = EXTENSION_XLS;
			break;
		case REPORT_OPEN_OFFICE:
			typeOfExportedFile = new JROdsExporter();
			extensionOfExportedFile = EXTENSION_ODS;
			break;
		
		default:
			typeOfExportedFile = new JRHtmlExporter();
			extensionOfExportedFile = EXTENSION_HTML;
			break;
			
		}
		
		outPutReportName += UNDERLINE + DateUtil.getActualDateReportName();
		// Export report path
		pathReportFile = reportPath + SEPARATOR + outPutReportName + DOT + extensionOfExportedFile;
		
		//Create new exported file
		createdFile = new File(pathReportFile);
		
		//Prepare to print
		typeOfExportedFile.setParameter(JRExporterParameter.JASPER_PRINT, printer);
		
		//
		typeOfExportedFile.setParameter(JRExporterParameter.OUTPUT_FILE, createdFile);
		
		typeOfExportedFile.exportReport();
		
		//Remove the file from the server after downloaded by the user
		createdFile.deleteOnExit();
		
		//Create the inputstream for PrimeFaces
		InputStream reportContent = new FileInputStream(createdFile);
		returnFile = new DefaultStreamedContent(reportContent, 
				"application/" + extensionOfExportedFile, 
				outPutReportName + DOT + extensionOfExportedFile);
		
		return returnFile;
	}
	
}
