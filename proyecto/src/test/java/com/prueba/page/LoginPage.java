package com.prueba.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import groovy.time.Duration;

public class LoginPage extends HomePage{
	
	private String txtFirstName="//input[@id='customer.firstName']";
	private String txtLastName="//input[@id='customer.lastName']";
	private String txtAddress="//input[@id='customer.address.street']";
	private String txtCity="//input[@id='customer.address.city']";
	private String txtState="//input[@id='customer.address.state']";
	private String txtZipCode="//input[@id='customer.address.zipCode']";
	private String txtPhone="//input[@id='customer.phoneNumber']";
	private String txtSSN="//input[@id='customer.ssn']";
	
	private String txtUsername="//input[@id='customer.username']";
	private String txtPassword="//input[@id='customer.password']";
	private String txtUsernamelogin="//input[@name='username']";
	private String txtPasswordlogin="//input[@name='password']";
	private String txtConfirm="//input[@id='repeatedPassword']";
	
	private String BtnRegister="//input[@value='Register']";
	private String linkRegister="//*[@id=\"loginPanel\"]/p[2]/a";
	private String Retroceder="//*[@id=\"leftPanel\"]/ul/li[8]/a";
	private String Login="//input[@value='Log In']";
	
	
	public void ingresoDatosPersonales(String FirstName, String LastName, String Address, String City, String State, String ZipCode, String Phone, String SSN) throws InterruptedException {
		
	
		getDriver().findElement(By.xpath(linkRegister)).click();
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(txtFirstName)).sendKeys(FirstName);
		getDriver().findElement(By.xpath(txtLastName)).sendKeys(LastName);
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(txtAddress)).sendKeys(Address);
		getDriver().findElement(By.xpath(txtCity)).sendKeys(City);
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(txtState)).sendKeys(State);
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(txtZipCode)).sendKeys(ZipCode);
		getDriver().findElement(By.xpath(txtPhone)).sendKeys(Phone);
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(txtSSN)).sendKeys(SSN);
	
		}
	
	public void ingresoDatosCuenta(String Username, String Password, String Confirm) throws InterruptedException {
		
		
		getDriver().findElement(By.xpath(txtUsername)).sendKeys(Username);
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(txtPassword)).sendKeys(Password);
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(txtConfirm)).sendKeys(Confirm);
	
		
		}
	
	public void ClicBotonRegistrar() throws InterruptedException {
		
		getDriver().findElement(By.xpath(BtnRegister)).click();
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(Retroceder)).click();
	}
	

	public void ingresoSession(String Username, String Password) throws InterruptedException {
		
	
		getDriver().findElement(By.xpath(txtUsernamelogin)).sendKeys(Username);
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(txtPasswordlogin)).sendKeys(Password);
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(Login)).click();
		Thread.sleep(5000);
	}
	
	

	
	
	
}
