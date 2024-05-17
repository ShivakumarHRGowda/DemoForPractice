package practice.datadriventesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBackToExcelTest {
	public static void main(String[] args) throws Exception {
		FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\ExcellDataForSelenium\\textScriptData.xlsx");
	    Workbook wb=WorkbookFactory.create(fis);
	    Sheet sheet=wb.getSheet("org");
	    Row row=sheet.getRow(1);
        Cell cell=row.createCell(4);
        
         cell.setCellType(CellType.STRING);
         cell.setCellValue("FAIL");
         
         //to save the excel sheet once its edited
         FileOutputStream fos=new FileOutputStream("C:\\Users\\User\\Desktop\\ExcellDataForSelenium\\textScriptData.xlsx");
	     wb.write(fos);
	     
	     wb.close();
	    System.out.println("*****Excecuted*****"); 
	}
}
