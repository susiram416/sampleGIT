package org.sampledemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	
	public void getDriver(String browserType) {
		switch(browserType)	{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
			
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
			
		default	:
			System.out.println("invalid user");
	    }
	}
	
	public void getUrl(String url){
		driver.get(url);
		driver.manage().window().maximize();
	}
		
	public String readExcel(int rowNo,int cellNo) throws IOException {
		File file=new File("C:\\Users\\susit\\Downloads\\document (1).xlsx");
					
		FileInputStream stream=new FileInputStream(file);
		Workbook book=new XSSFWorkbook(stream);
		Sheet sheet = book.getSheet("Sheet1");
		Row row = sheet.getRow(rowNo);
		Cell cell = row.getCell(cellNo);
		return cell.getStringCellValue();
		
	}
	
	public void typeText(WebElement element, String value) {
		element.sendKeys(value);
	}
	
	public void addAllItemsToCart() {
		List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item']"));
		
		for (WebElement item: items) {
			item.findElement(By.cssSelector("[id^=add-to-cart]")).click();
		}
	}
	
	public void removeCartItem(double num) {
		
		List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		for (int i=0;i<items.size();i++) {
			double itemPrice = Double.parseDouble(items.get(i).getText().replace("$", ""));
			if(itemPrice < num) {
				WebElement remove = driver.findElement(By.cssSelector("[id^=remove-]"));
				remove.click();
			}
		}
	}
		
	public void chechOutInfo(String firstName, String lastName, String postalCode) {
		WebElement firstNamEle = driver.findElement(By.id("first-name"));
		typeText(firstNamEle, firstName);
		WebElement lastNameEle = driver.findElement(By.id("last-name"));
		typeText(lastNameEle, lastName);
		WebElement zipCodeEle = driver.findElement(By.id("postal-code"));
		typeText(zipCodeEle, postalCode);
	}
		
	public void logOut() throws InterruptedException {
		WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
		menuButton.click();
		Thread.sleep(2000);
		WebElement logOutButton = driver.findElement(By.id("logout_sidebar_link"));
		logOutButton.click();
	}
}
