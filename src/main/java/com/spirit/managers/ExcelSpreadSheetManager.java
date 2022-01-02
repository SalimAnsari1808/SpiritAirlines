package com.spirit.managers;

import org.openqa.selenium.WebDriver;

import com.spirit.utility.ExcelUtil;




public class ExcelSpreadSheetManager {
	
	WebDriver driver;
	ExcelUtil excelUtil;
	
	
	public ExcelSpreadSheetManager(){

	}
	
	public ExcelUtil getExcelUtil(String excelFileName) {
		if(excelUtil == null) {
			return excelUtil = new ExcelUtil(excelFileName);
		}else {
			return excelUtil;
		}
	}

}
