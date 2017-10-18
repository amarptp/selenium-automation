package com.pageobjectmodel.ddf.util;

import java.io.IOException;


public class DataUtil {	
		
	static Xls_Reader dataTable = new Xls_Reader(Constants.DATA_XLS_PATH);	
	
	//code for data provider
	public static Object[][] getData(String sheetName) throws IOException{
		
		int totalRows = dataTable.getRowCount(sheetName);
		int totalCols = dataTable.getColumnCount(sheetName);
		
		Object[][] data = new Object[totalRows-1][totalCols];
		 for(int i=2; i<=totalRows; i++)
			 for(int j=0; j<totalCols; j++)
				 data[i-2][j] = dataTable.getCellData(sheetName, j, i);	
		
		return data;		
	}
	
	//code for skipping the test cases
	public static boolean isTestExecutable(String testName){
		
		String sheet="TestCases";
		int rows = dataTable.getRowCount(sheet);
		
		for(int r=2; r<=rows; r++){
			String tName=dataTable.getCellData(sheet, "TCID", r);
			if(tName.equals(testName)){
				String runmode=dataTable.getCellData(sheet, "Runmode", r);
				if(runmode.equals("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}

}
