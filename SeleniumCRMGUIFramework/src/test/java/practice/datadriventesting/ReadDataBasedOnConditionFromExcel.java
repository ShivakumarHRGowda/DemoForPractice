package practice.datadriventesting;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBasedOnConditionFromExcel {
	public static void main(String[] args) throws Exception {
		String expectedData="tc_02";
		String data1="",data2="",data3="";
		 FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\ExcellDataForSelenium\\textScriptData.xlsx");
		    Workbook wb=WorkbookFactory.create(fis);
		    Sheet sheet=wb.getSheet("org");
		    int count=sheet.getLastRowNum();
		    for(int i=0;i<=count;i++) {
		    	String data="";
		    	try {
		    		 data=sheet.getRow(i).getCell(0).toString();
		    		 if(data.equals(expectedData)) {
		    			   data1=sheet.getRow(i).getCell(1).toString();
		    			   data2=sheet.getRow(i).getCell(2).toString();
		    			   data3=sheet.getRow(i).getCell(3).toString();
		    		 }
				} catch (Exception e) {}
		    }
		    System.out.println(data1+"\t"+data2+"\t"+data3);
		    wb.close();
	}
}
