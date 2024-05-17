package practice.datadriventesting;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelTest {
	public static void main(String[] args) throws Throwable {
      FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\ExcellDataForSelenium\\textScriptData.xlsx");
	  Workbook wb=WorkbookFactory.create(fis);
	  Sheet sheet=wb.getSheet("org");
	  Row row=sheet.getRow(1);
	  String value=row.getCell(2).getStringCellValue();
	  System.out.println(value);
	  wb.close();
	}
}
