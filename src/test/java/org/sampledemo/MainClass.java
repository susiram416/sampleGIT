package org.sampledemo;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainClass {
	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		BaseClass bc=new BaseClass();
		bc.getDriver("chrome");
		bc.getUrl("https://www.saucedemo.com");
		String username = bc.readExcel(1, 0);
		String password = bc.readExcel(1, 1);
		WebElement userNameEle = bc.driver.findElement(By.id("user-name"));
		bc.typeText(userNameEle, username);
		WebElement passwordEle = bc.driver.findElement(By.id("password"));
		bc.typeText(passwordEle, password);
		bc.driver.findElement(By.id("login-button")).click();
		bc.addAllItemsToCart();
	
		bc.driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		bc.removeCartItem(10.00);
		bc.driver.findElement(By.id("checkout")).click();
		bc.chechOutInfo("Susithra", "Ramamoorthy", "626102");
		bc.driver.findElement(By.id("continue")).click();
		bc.driver.findElement(By.id("finish")).click();
		bc.driver.findElement(By.id("back-to-products")).click();
		bc.logOut();
		
	}

}
