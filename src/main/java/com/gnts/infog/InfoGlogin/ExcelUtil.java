package com.gnts.infog.InfoGlogin;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.text.DateFormatter;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	  private XSSFSheet ExcelWSheet;
      private  XSSFWorkbook ExcelWBook;
      private  XSSFCell Cell;
      private  XSSFRow Row;
      private  String path;

  //This method is to set the File path and to open the Excel file
  //Pass Excel Path and SheetName as Arguments to this method
  
  ExcelUtil(String Path,String SheetName) throws Exception{
	  path = Path;    
	  FileInputStream ExcelFile = new FileInputStream(Path);
      ExcelWBook = new XSSFWorkbook(ExcelFile);
      ExcelWSheet = ExcelWBook.getSheet(SheetName);
  }
  
  public int RowCount()
  {
	  
	return ExcelWSheet.getPhysicalNumberOfRows();
  }
  
  
  public XSSFSheet getExcelWSheetObj(){
	  return ExcelWSheet;
  }
  
  public void readCheck() throws Exception{
	  int lastColumn = ExcelWSheet.getRow(2).getLastCellNum();
	  
	  boolean execute = true;
	  int cellNumber = 0;
	  int cellIncrementor = 1;
	  
	  // Loop all Test Cases List
	  for(int j=0;j<=lastColumn;j++)
	  {
		  int i=2;
		  // Executes single Test case from list
		  while(execute)
		  {
			  if(ExcelWSheet.getRow(i) == null)
			  {
				  cellNumber = cellNumber==0 ?2:cellNumber+cellIncrementor;
				  cellIncrementor++;
				  execute  = false;
				  break;
			  }
			  
			  String function = getCellData(i, cellNumber);
			  if(function == null)
			  {
				  break;
			  }
			  
			  String location = getCellData(i, cellNumber+1);
			  
			  System.out.println("function" +function);
			  System.out.println("location" +location);
			  i++;
		  }		 
		  execute = true;		 
	  }
	  
	  
	  
  }

  //This method is to read the test data from the Excel cell
  //In this we are passing parameters/arguments as Row Num and Col Num
  public String getCellData(int RowNum, int ColNum) throws Exception{
  	  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
  	  if(Cell == null)
  		  return null;
  	DataFormatter df = new DataFormatter();
        String CellData =df.formatCellValue(Cell);
        return CellData;
  	}
  public void setCellData(String Result,  int RowNum, int ColNum) throws Exception	
	{
		try
		{
 			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
				} else {
					Cell.setCellValue(Result);
				}
 // Constant variables Test Data path and Test Data file name
 				FileOutputStream fileOut = new FileOutputStream(path);
 				ExcelWBook.write(fileOut);
 				fileOut.flush();
				fileOut.close();
				}catch(Exception e){
					e.printStackTrace();
					Row  = ExcelWSheet.createRow(RowNum);
					Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
					if (Cell == null) {
						Cell = Row.createCell(ColNum);
						Cell.setCellValue(Result);
						} else {
							Cell.setCellValue(Result);
						}
          // Constant variables Test Data path and Test Data file name
          				FileOutputStream fileOut = new FileOutputStream(path);
          				ExcelWBook.write(fileOut);
          				fileOut.flush();
 						fileOut.close();
 						
 						
			}
		
	}
  
    public void writeSubTests(ArrayList<Map<String, Object>> allTestCaseResults) {
    	
    	
    	int cellNumber = 0;
		
    	for (Map<String, Object> parentTask : allTestCaseResults) {
			
    		int row = 0;
    		
    		
    		// For Title, Function, status, message    		
    		
    		XSSFRow titleRow= ExcelWSheet.getRow(row);
    		if(titleRow == null)
    		{
    			 titleRow =  ExcelWSheet.createRow(row);
    		}
    		
    		XSSFCell cell = titleRow.createCell(cellNumber);
    		
    		cell.setCellValue(parentTask.get("title").toString());    		
    		
    		row = 1;
    		titleRow  = ExcelWSheet.getRow(row);
    		if(titleRow == null)
    		{
    			 titleRow =  ExcelWSheet.createRow(row);
    		}
    		
    		titleRow.createCell(cellNumber).setCellValue("FUNCTION");
    		titleRow.createCell(cellNumber+1).setCellValue("ELEMENT_NAME");
    		titleRow.createCell(cellNumber+2).setCellValue("STATUS");
    		titleRow.createCell(cellNumber+3).setCellValue("MESSAGE");
    		
    		row = 2;    		
    		 
	    	System.out.println(parentTask.get("title")+" , "+parentTask.get("wholeStatus"));
	    	
	    	ArrayList<Map<String, Object>> subTestCases = (ArrayList<Map<String, Object>>) parentTask.get("subTestCaseResult");
	    	XSSFRow testcase_row;
	    	 for (Map<String, Object> subTest : subTestCases) {
	    		 
	    		 testcase_row  = ExcelWSheet.getRow(row);
	    		 if(testcase_row == null)
	    		 {
	    			 testcase_row = ExcelWSheet.createRow(row);
	    		 }
	    		 
	    		 testcase_row.createCell(cellNumber).setCellValue(subTest.get("function").toString());
	    		 if(subTest.containsKey("element") && subTest.get("element") != null )
	    		 { 
	    			 testcase_row.createCell(cellNumber+1).setCellValue(subTest.get("element").toString());
	    		 }
	    		 testcase_row.createCell(cellNumber+2).setCellValue(subTest.get("status").toString());
	    		 
	    		 if(subTest.containsKey("message") && subTest.get("message") != null )
	    		 { 
	    			 testcase_row.createCell(cellNumber+3).setCellValue(subTest.get("message").toString());
	    		 }
	    		 
	    		 row++;	    		 
	    		 System.out.println(subTest.get("function")+" , "+subTest.get("status"));
	    	 }
	    	 
	    	 
	    	 cellNumber = cellNumber+4;
		}
    	
    	FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(path);
			ExcelWBook.write(fileOut);
			fileOut.flush();
		    fileOut.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    
     public void writeOverallTests(ArrayList<Map<String, Object>> allTestCaseResults) {
    	
    	int row = 1;
    	int cellNumber = 0;
		
    	for (Map<String, Object> parentTask : allTestCaseResults) {
			
    		
    		// For Title, Function, status, message    		
    		
    		XSSFRow titleRow= ExcelWSheet.getRow(row);
    		if(titleRow == null)
    		{
    			 titleRow =  ExcelWSheet.createRow(row);
    		}
    		
    		XSSFCell cell = titleRow.createCell(cellNumber);
    		
    		cell.setCellValue(parentTask.get("title").toString());    	
    		titleRow.createCell(cellNumber+1).setCellValue(parentTask.get("wholeStatus").toString());
    	
	    	
	    	row ++;
	    	 
	    	
		}
    	
    	FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(path);
			ExcelWBook.write(fileOut);
			fileOut.flush();
		    fileOut.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
