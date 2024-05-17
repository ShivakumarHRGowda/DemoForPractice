package practice.datadriventesting;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcel {
	public static void main(String[] args) throws Throwable {
       FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\ExcellDataForSelenium\\textScriptData.xlsx");
	    Workbook wb=WorkbookFactory.create(fis);
	    Sheet sheet=wb.getSheet("Sheet1");
//	    Row row=sheet.getRow(1);
//	    String column1Data=row.getCell(0).getStringCellValue();
//	    String column2Data=row.getCell(1).getStringCellValue();
//	    System.out.println(column1Data+"\t"+column2Data);
	    
	    int rowCount=sheet.getLastRowNum();
	    
	    for(int i=1;i<=rowCount;i++) {
	    	Row row=sheet.getRow(i);
		    String column1Data=row.getCell(0).getStringCellValue();
		    String column2Data=row.getCell(1).getStringCellValue();
		    System.out.println(column1Data+"\t"+column2Data);
	    }
	    wb.close();
	}
}
