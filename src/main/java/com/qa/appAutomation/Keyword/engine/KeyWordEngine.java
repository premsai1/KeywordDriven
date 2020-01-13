package com.qa.AppAutomation.Keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.AppAutomation.Base.*;
/**
 * @author PremSai Manthani
 * @purpose this class is used to read data from the excel sheet and provide it to test scenario 
 * @date 10/01/2020
 */
public class KeyWordEngine {

	public WebDriver driver;
	public Properties prop;

	public static Workbook book;
	public static Sheet sheet;

	public TestBase testBase;

	public final String SCENARIO_SHEET_PATH = "/home/user/home/Prem/KeyWord_AppAutomation/src/main/java/com/qa/AppAutomation/Scenaoris/KeyWordDriven.xlsx";

	public void startExecution(String sheetName) {
		String locatorName = null;
		String locatorValue = null;

		FileInputStream file = null;

		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {
			String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim(); // id=username
			if (!locatorColValue.equalsIgnoreCase("NA")) {
				locatorName = locatorColValue.split("=")[0].trim();
				locatorValue = locatorColValue.split("=")[1].trim();

			}
			String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
			String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();

			switch (action) {
			case "open browser":
				testBase = new TestBase();
				prop = testBase.init_properties();
				if (value.isEmpty() || value.contentEquals("NA")) {
					testBase.init_driver(prop.getProperty("browser"));
				} else {
					driver = testBase.init_driver(value);
				}
				break;

			case "enter url":
				if (value.isEmpty() || value.equals("NA")) {
					driver.get(prop.getProperty("url"));
				} else {
					driver.get(value);
				}
				break;

			case "quit":
				driver.quit();
				break;

			default:
				break;
			}
			switch (locatorName) {
			case "id":
				WebElement element = driver.findElement(By.id(locatorValue));
				if (action.equalsIgnoreCase("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				} else if (action.equalsIgnoreCase("click")) {
					element.click();
				}
				break;

			default:
				break;
			}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}

	}

}
