package com.demoblaze.smoke;

import java.util.concurrent.TimeUnit;

import java.util.Random;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

public class Assignment {
	public WebDriver driver;
	// declare a public string with random number to use it in signup and log in
	// then print the userName after every testcase
	public String subUserName = "osama";
	Random random = new Random();

	public int num = random.nextInt(150);
	public String userName = "osama" + num;

	@BeforeClass
	public void beforeClass() {

		driver = new ChromeDriver();
		System.out.println("UserName: " + userName);
	}

	@Test
	public void openDemoblazeSite() {
		driver.get("https://www.demoblaze.com/");
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void Register() throws InterruptedException {
		WebElement Menu_SignUp = driver.findElement(By.id("signin2"));
		Menu_SignUp.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement signUserName = driver.findElement(By.id("sign-username"));
		signUserName.sendKeys(userName);

		WebElement signPassword = driver.findElement(By.id("sign-password"));
		signPassword.sendKeys("123456");

		WebElement SignUp = driver
				.findElement(By.cssSelector("#signInModal > div > div > div.modal-footer > button.btn.btn-primary"));
		SignUp.click();
		Thread.sleep(3000);

		// Switching to Alert
		Alert alert = driver.switchTo().alert();
		String ExpectedTitle = "Sign up successful.";
		// Capturing alert message.
		String alertMessage = driver.switchTo().alert().getText();
		Assert.assertEquals(ExpectedTitle, alertMessage);

		// Displaying alert message
		System.out.println(alertMessage);
		Thread.sleep(5000);

		// Accepting alert
		alert.accept();
	}

	@Test(priority = 2)
	public void Login() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Menu_LogIn = driver.findElement(By.id("login2"));
		Menu_LogIn.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement LoginUserName = driver.findElement(By.id("loginusername"));
		LoginUserName.sendKeys(userName);

		WebElement LoginPassword = driver.findElement(By.id("loginpassword"));
		LoginPassword.sendKeys("123456");

		WebElement LogIn = driver
				.findElement(By.cssSelector("#logInModal > div > div > div.modal-footer > button.btn.btn-primary"));
		LogIn.click();
		Thread.sleep(3000);
		String ExpectedTitle = "Welcome " + userName;
		String ActualTitle = driver.findElement(By.id("nameofuser")).getText();
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		System.out.println("Actual title: " + ActualTitle);

		Thread.sleep(3000);

	}

	@Test(priority = 3)
	public void CategoriesItem() throws InterruptedException {
		// Iphone List
		WebElement Phones = driver.findElement(By.linkText("Phones"));
		Phones.click();
		Thread.sleep(4000);
		String iphoneRandomProductActualTitle = driver.findElement(By.linkText("HTC One M9")).getText();
		Assert.assertEquals("HTC One M9", iphoneRandomProductActualTitle);

		try {
			if (driver.findElement(By.cssSelector("#tbodyid > div:nth-child(1) > div")).isDisplayed()) {

				System.out.println("Iphone list has items? " + "True");
				System.out.println("Iphones list random item actualTitle: " + iphoneRandomProductActualTitle);

			}

		} catch (NoSuchElementException e) {
			System.out.println("Iphone list has items? " + "False");
		}

		// Laptops List

		WebElement Laptops = driver.findElement(By.linkText("Laptops"));
		Laptops.click();
		Thread.sleep(4000);
		String laptopsRandomProductActualTitle = driver.findElement(By.linkText("2017 Dell 15.6 Inch")).getText();
		Assert.assertEquals("2017 Dell 15.6 Inch", laptopsRandomProductActualTitle);

		try {
			if (driver.findElement(By.cssSelector("#tbodyid > div:nth-child(1) > div")).isDisplayed()) {

				System.out.println("Laptops list has items? " + "True");
				System.out.println("Laptops list random item actualTitle: " + laptopsRandomProductActualTitle);

			}

		} catch (NoSuchElementException e) {
			System.out.println("Laptops list has items? " + "False");
		}

		// Monitors List
		WebElement Monitors = driver.findElement(By.linkText("Monitors"));
		Monitors.click();
		Thread.sleep(4000);
		String monitorRandomProductActualTitle = driver.findElement(By.linkText("ASUS Full HD")).getText();
		Assert.assertEquals("ASUS Full HD", monitorRandomProductActualTitle);

		try {
			if (driver.findElement(By.cssSelector("#tbodyid > div:nth-child(1) > div")).isDisplayed()) {

				System.out.println("Monitors list has items? " + "True");
				System.out.println("Monitors list random item actualTitle: " + monitorRandomProductActualTitle);

			}

		} catch (NoSuchElementException e) {
			System.out.println("Monitors list has items? " + "False");
		}

	}

	@Test(priority = 4)
	public void addItem() throws InterruptedException {
		Thread.sleep(3000);
		WebElement selectItem = driver.findElement(By.className("card-img-top"));
		selectItem.click();
		Thread.sleep(2000);
		WebElement AddToCart = driver.findElement(By.cssSelector("#tbodyid > div.row > div > a"));
		AddToCart.click();
		Thread.sleep(3000);

		Alert alert = driver.switchTo().alert();
		String ExpectedTitle = "Product added.";
		// Capturing alert message.
		String alertMessage = driver.switchTo().alert().getText();
		Assert.assertEquals(ExpectedTitle, alertMessage);

		// Displaying alert message
		System.out.println(alertMessage);
		Thread.sleep(5000);

		// Accepting alert
		alert.accept();
	}

	@Test(priority = 5)
	public void RemoveItemFromCart() throws InterruptedException {

		WebElement Cart = driver.findElement(By.id("cartur"));
		Cart.click();
		Thread.sleep(5000);
		WebElement deleteItem = driver.findElement(By.cssSelector("#tbodyid > tr:nth-child(1) > td:nth-child(4) > a"));
		deleteItem.click();
		Thread.sleep(4000);

		try {
			if (driver.findElement(By.cssSelector("#tbodyid > tr:nth-child(1) > td:nth-child(4) > a")).isDisplayed()) {

				System.out.println("Item not deleted");
			}

		} catch (NoSuchElementException e) {
			System.out.println("Item is deleted");
		}

		WebElement homePage = driver.findElement(By.cssSelector("#navbarExample > ul > li.nav-item.active > a"));
		homePage.click();

	}

	@Test(priority = 6)
	public void checkout() throws InterruptedException {
		Thread.sleep(2000);

		addItem();

		WebElement Cart = driver.findElement(By.id("cartur"));
		Cart.click();
		Thread.sleep(3000);

		WebElement placeOrder = driver.findElement(By.cssSelector("#page-wrapper > div > div.col-lg-1 > button"));
		placeOrder.click();

		Thread.sleep(2000);
		driver.findElement(By.id("name")).sendKeys(userName);
		driver.findElement(By.id("country")).sendKeys("Jordan");
		driver.findElement(By.id("city")).sendKeys("Amman");
		driver.findElement(By.id("card")).sendKeys("1411 4444 4444 4444");
		driver.findElement(By.id("month")).sendKeys("06");
		driver.findElement(By.id("year")).sendKeys("2026");
		WebElement Purchase = driver
				.findElement(By.cssSelector("#orderModal > div > div > div.modal-footer > button.btn.btn-primary"));
		Purchase.click();

		Thread.sleep(2000);

		String PurchaseSuccessMessage = "Thank you for your purchase!";
		// Capturing alert message.
		String getMessage = driver.findElement(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible > h2"))
				.getText();
		Assert.assertEquals(PurchaseSuccessMessage, getMessage);

		// Displaying alert message
		System.out.println(getMessage);

		WebElement confirmSuccessMessage = driver.findElement(By.className("sa-confirm-button-container"));
		confirmSuccessMessage.click();

	}
	
	
	
	@AfterSuite  
	public void after_suite()  
	{  
        driver.close();
        driver.quit();
	}  

}
