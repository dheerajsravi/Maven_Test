package com.gnts.infog.InfoGlogin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

public class WebFunction {

	public static WebDriver driver;
	public static WebDriverWait wait;

	static Logger log = Logger.getLogger(WebFunction.class);
	static int targetDay = 0;
	static int targetMonth = 0, targetYear = 0;
	static int currenttDate = 0, currenttMonth = 0, currenttYear = 0;
	static int jumMonthBy = 0;
	static boolean increment = true;

	// WEB_BROWSER_INVOKE_FUNCTION

	public static TestStatus WEB_BROWSER_INVOKE(String BrowserName) {

		TestStatus test = new TestStatus();
		test.setStatus("PASS");

		if (BrowserName == null || BrowserName == "") {

			System.out.println("The browsername is blank");
			test.setStatus("FAIL");
			test.setErrormsg("Browsername is empty");
			log.error("Browser name is empty");

		} else {

			try {

				switch (BrowserName) {

				case "firefox":
					driver = new FirefoxDriver();
					log.info("Firefox browser invoked");
					test.setStatus("PASS");
					break;
				case "chrome":
					System.setProperty("webdriver.chrome.driver",
							"F:\\Selenium supported files\\chromedriver_win32\\chromedriver.exe");
					driver = new ChromeDriver();
					log.info("Chrome browser invoked");
					test.setStatus("PASS");
					break;

				case "Internet explorer":
					System.setProperty("webdriver.ie.driver",
							"F:\\Selenium supported files\\IEDriverServer_x64_3.3.0\\IEDriverServer.exe");
					driver = new InternetExplorerDriver();
					log.info("IE browser invoked");
					test.setStatus("PASS");
					break;

				default:
					
					log.error("Browser name is invalid");
					test.setStatus("FAIL");
					break;

				}
			} catch (Exception e) {
				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg(e.getMessage());
				log.error(e.getMessage());
			}

		}
		return test;

	}

	// Web browser navigate function
	// Give to URL data

	public static TestStatus WEB_BROWSER_NAVIGATE(String UrlData) {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		if (UrlData == "") {
			log.error("URL is rmpty");
			test.setStatus("FAIL");
			test.setErrormsg("URL is empty");
			return test;

		} else {
			try {

				wait = new WebDriverWait(driver, 50);
				driver.get(UrlData);
				test.setStatus("PASS");
				test.setErrormsg("Open the infog home page");
				log.info("Open the infog home page");

			} catch (Exception e) {
				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg(e.getMessage());
				log.error(e.getMessage());
				return test;
 }
		}
		return test;
}

	// WEB_VIEW_FULLSCREEN FUNCTION

	public static TestStatus WEB_BROWSER_FULLSCREEN() {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try {
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			test.setStatus("PASS");
			test.setErrormsg("View the full screen");
			log.info("View the full screen");

		} catch (Exception e) {
			e.printStackTrace();
			test.setStatus("FAIL");
			log.error(e.getMessage());
			test.setErrormsg(e.getMessage());
            return test;
}
		return test;

	}

	// Click,Link function
	// Pass parameter locator type and element(id,xpath,classname)

	public static TestStatus WEB_ELEMENT_CLICK(String Locator, String path, String actualValue) throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		if (Locator == "" || path == "") {
            test.setStatus("FAIL");
			test.setErrormsg("Parameter is empty");
			log.error("Parameter is empty");
			return test;
		} else {
			try {
            switch (Locator) {

				case "xpath":

					WebElement button = driver.findElement(By.xpath(path));
					if (button.isDisplayed() || button.isEnabled()) {
						String se = driver.findElement(By.xpath(path)).getText();
						System.out.println(se);
                        test.setStatus("PASS");
						test.setErrormsg(actualValue);
						test.setElement(se);
						driver.findElement(By.xpath(path)).click();
						log.info("Element clicked");
						break;
					} else {
						test.setStatus("FAIL");
						test.setErrormsg("Element disable");
						log.error("Element disable");
						return test;
						}	

						
				case "className":
					
					WebElement button1 = driver.findElement(By.className(path));
					if (button1.isDisplayed() || button1.isEnabled()) {
						driver.findElement(By.className(path)).click();
						String se = driver.findElement(By.className(path)).getText();
						System.out.println(se);
						test.setStatus("PASS");
						test.setErrormsg("Element clicked");
						log.info("Element clicked");
						test.setElement(se);
						break;
					} else {
						test.setStatus("FAIL");
						test.setErrormsg("Element disable");
						log.error("Element disable");
						return test;
						
					}

				case "id":
					WebElement button2 = driver.findElement(By.id(path));
					if (button2.isDisplayed() || button2.isEnabled()) {
						String se = button2.getText();
						System.out.println(se);
						test.setStatus("PASS");
						test.setElement(se);
						test.setErrormsg("Element clicked");
						driver.findElement(By.id(path)).click();
						log.info("Element clicked");
						break;
					} else {
						test.setStatus("FAIL");
						test.setErrormsg("Element disable");
						log.error("Element disable");
						return test;
						
					}

				}
			} catch (NullPointerException e) {
				// TODO: handle exception
				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg("NULL POINTER EXCEPTION");
				log.info("Null Pointer Exception");
				return test;
				
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg("NO SUCH ELEMENT EXCEPTION");
				log.info("No such element exception");
				return test;
				}

			catch (Exception e) {

				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg(e.getMessage());
				log.info(e.getMessage());
				return test;
				}
		}
		return test;
}

	// Web_Edit_Type function
	// Pass parameter locator type and Button location
	// element(id,xpath,classname)

	public static TestStatus WEB_EDIT_TYPE(String Locator, String path, String value, String actualValue) {
		TestStatus test = new TestStatus();

		test.setStatus("PASS");
		if (Locator.isEmpty() || path.isEmpty() || value.isEmpty()) {
			System.out.println("The parameter passed is blank");
			test.setStatus("FAIL");
			test.setErrormsg("PARAMETER IS EMPTY");
			return test;

		} else {
			try {

				switch (Locator) {

				case "xpath":

					WebElement button = driver.findElement(By.xpath(path));
					if (button.isDisplayed() || button.isEnabled()) {
						
						driver.findElement(By.xpath(path)).sendKeys(value);
						log.info("Type in text field:"+ value);
						test.setStatus("PASS");
						return test;
						
					} else {
						test.setStatus("FAIL");
						test.setErrormsg("TEXT FIELD DISABLED");
						log.error("Text field disable");
						return test;
					}

				case "className":

					WebElement button1 = driver.findElement(By.className(path));
					if (button1.isDisplayed() || button1.isEnabled()) {
					    driver.findElement(By.className(path)).sendKeys(value);
						log.info("Type in text field:"+value);
						test.setStatus("PASS");
						break;
						
					} else {
						test.setStatus("FAIL");
						test.setErrormsg("TEXT FIELD DISABLED");
						log.error("Text field disable");
						return test;
					}

				case "id":

					String se = driver.findElement(By.id(path)).getText();
					System.out.println(se);
					WebElement button2 = driver.findElement(By.id(path));
					if (button2.isDisplayed() || button2.isEnabled()) {
						driver.findElement(By.id(path)).sendKeys(value);
						log.info("Type in text field:"+value);
						test.setStatus("PASS");
						break;
					} else {
						test.setStatus("FAIL");
						test.setErrormsg("TEXT FIELD DISABLED");
						log.error("Text field disable");
						return test;
					}

				}

			} catch (NullPointerException e) {
				// TODO: handle exception
				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg("NullPointerException");
				log.error("NullPointerException");
				return test;
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg("No Such element");
				log.error("No Such element");
				return test;

			} catch (Exception e) {

				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg(e.getMessage());
				log.error(e.getMessage());
				return test;
			}

		}
		return test;

	}

	// Invoice name select Dropdown function
	
	public static TestStatus INVOICE_NAME_SELECT(String Value) throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");

		try {

			WebElement ee = driver.findElement(By.id("invoiceTo"));
			ee.click();
			log.info("Click on invoice name dropdown");

			List<WebElement> elements = ee.findElements(By.className("ui-select-choices-row-inner"));

			for (WebElement element : elements) {
				if (element.findElement(By.tagName("span")).getText().equals(Value)) {
					element.click();
					log.info("Select value from Invoice name dropdown ");
					test.setStatus("PASS");
					test.setErrormsg("VALUE IS MATCHED");
					break;
					

				} else {
					System.out.println("VALUE NOT MATCHED");
					test.setStatus("FAIL");
					test.setErrormsg("VALUE NOT MATCHED");
					log.error("Invoice name is not matched");

				}
			}

		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
			test.setStatus("FAIL");
			log.error("Null pointer exception");
			test.setErrormsg("NULL POINTER EXCEPTION");
			return test;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.setStatus("FAIL");
			log.error("No such element");
			test.setErrormsg("NO SUCH ELEMENT EXCEPTION");
			return test;

		}

		catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			log.error(e.getMessage());
			test.setErrormsg(e.getMessage());
			return test;

		}
		
		return test;
	}
	// LOGIN_FUNCTION

	public static TestStatus LOGIN_FUNCTION(String[] actualValue) throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");

		
     try{
		driver.findElement(By.xpath(".//*[@id='signin-form']/div[3]/div/div/button")).submit();
        log.info("Click on signIn button");
		for (String errorMsg : actualValue) {

			boolean alertMessage = driver.getPageSource().contains(errorMsg);
			System.out.println(alertMessage);
			if (alertMessage) {
				test.setStatus("FAIL");
				test.setErrormsg(errorMsg);
				log.error(errorMsg);
				return test;
			}

		}

		test.setStatus("PASS");
		test.setErrormsg("System provided Login Successfully");
		log.info("Login Successfully");
		return test;
     }
     catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			log.error(e.getMessage());
			test.setErrormsg(e.getMessage());
			return test;

		}
		
	}

	

	// Signup_function

	public static TestStatus SIGNUP_FUNCTION(String[] actualValue) throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
	try{
          driver.findElement(By.id("btnSignup")).click();
          log.info("click on create account button");
		for (String errorMsg : actualValue) {

			boolean alertMessage = driver.getPageSource().contains(errorMsg);
			System.out.println(alertMessage);
			if (alertMessage) {
				test.setStatus("PASS");
				test.setErrormsg(errorMsg);
				log.error(errorMsg);
				return test;
			}

		}
	
		test.setStatus("PASS");
		test.setErrormsg("System provide signup Successfully");
        log.info("Create account successfully");
		return test;

	}
	catch (Exception e) {

		e.printStackTrace();
		test.setStatus("FAIL");
		log.error(e.getMessage());
		test.setErrormsg(e.getMessage());
		return test;

	}
	
}
	

	

	// Invoice _Submit function

	public static TestStatus INVOICE_SUBMIT(String[] actualValue) throws AWTException {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
     try{
		String totalvalue = driver
				.findElement(
						By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div/div/form/div[4]/div[4]/div"))
				.getText();
		System.out.println(totalvalue);
		test.setElement(totalvalue);
		driver.findElement(
				By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div/div/form/div[2]/div[1]/div[4]/button"))
				.click();
		log.info("Click on invoice submit button");

		for (String errorMsg : actualValue) {

			boolean alertMessage = driver.getPageSource().contains(errorMsg);
			System.out.println(alertMessage);
			if (alertMessage) {
				test.setStatus("PASS");
				test.setErrormsg(errorMsg);
				log.info(errorMsg);
				return test;
			}

		}

		test.setStatus("PASS");
		test.setErrormsg("Invoice created successfully");
		log.info("Invoice created successfully");
        return test;
     }
		catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			log.error(e.getMessage());
			test.setErrormsg(e.getMessage());
			return test;

		}
	}

	// threadsleep
	// Browser action wait and action, depands upon time

	public static TestStatus THREADSLEEP() throws InterruptedException {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try {

			Thread.sleep(5000L);
			test.setStatus("PASS");
			log.info("Wait");
			test.setErrormsg("WAIT");
			return test;

		} catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			return test;
		}

	}
	
	//Signup industry,country,state and city dropdown select

	public static TestStatus SIGNUP_DROPDOWN_SELECT(String id, String value) throws InterruptedException {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		if (id == "" || value == "") {
			
			test.setStatus("FAIL");
			log.error("Parameter is empty");
			test.setErrormsg("PARAMETER IS EMPTY");
			return test;
		} else
			try {
				{

					WebElement reg_area = driver.findElement(By.id(id));
					reg_area.click();
                    log.info("click on dropdown");
					WebElement chosenResult = reg_area.findElement(By.className("chosen-results"));
					List<WebElement> dropDownChoices = chosenResult.findElements(By.className("active-result"));
                    boolean isFound = false;

					for (WebElement webElement : dropDownChoices) {
						System.out.println("Drop down value : " + webElement.getText());
						String sValue = webElement.getText();

						if (value.equals(sValue)) {

							webElement.click();
							log.info("System selected the value is"+webElement.getText());
							isFound = true;
							test.setStatus("PASS");
							test.setErrormsg("System selected the value");
							test.setElement(value);
							System.out.println("Drop down value selected is : " + webElement.getText());
							return test;

						}

						else {
							test.setStatus("FAIL");
							log.error("Value is not match");
							System.out.println("value is not match" + sValue);

						}

					}

					if (isFound) {

						System.out.println("Testcase is pass");
					} else {

						System.out.println("Testcase is fail");
						test.setStatus("FAIL");
						test.setErrormsg("VALUE NOT MATCHED");
						test.setElement(value);
						return test;

					}

				}

			}

			catch (NullPointerException e) {
				// TODO: handle exception
				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg("NullPointerException");
				return test;
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg("No Such element");
				return test;

			} catch (Exception e) {

				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg(e.getMessage());
				return test;
			}

		return test;
	}
	
	//Select product from dropdown

	public static TestStatus INVOICE_DROPDOWN(String path, String value) throws InterruptedException {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try {

			WebElement se = driver.findElement(By.xpath(path));
			se.click();
			log.info("Dropdown select");
			WebElement choicesList = se.findElement(By.className("ui-select-choices-group"));

			List<WebElement> choices = se.findElements(By.className("ui-select-choices-row-inner"));
			// List<WebElement> spans = driver.findElements(By.tagName("span"));

			for (WebElement choice : choices) {

				WebElement span = choice.findElement(By.tagName("span"));
				String text = span.getText();
				System.out.println(text);
				if (text.equals(value)) {
					choice.click();
					log.info("Value is match"+value);
					boolean alertMessage = driver.getPageSource().contains("Product already exist.");
					System.out.println(alertMessage);
					if (alertMessage) {
						test.setStatus("FAIL");
						log.error("Product already exist.");
						test.setErrormsg("Product already exist.");
						System.out.println("Product already exist.");
						break;
					}

					System.out.println("Value is Match");
					test.setStatus("PASS");
					break;
				}

				else {
					test.setStatus("FAIL");
					log.error("Value not match");
					System.out.println("Value not Match");
					test.setErrormsg("Value not match");
				}
			}

			System.out.println("LOOP END");

		} catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			log.error(e.getMessage());
			test.setErrormsg(e.getMessage());
			return test;
		}

		return test;

	}
	
		// web get cell data

	public static TestStatus WEB_GET_CELL_DATA(String value, String actualvalue) {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try {
			int Row_count = driver
					.findElements(By
							.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]"))
					.size();
			System.out.println("Number Of Rows = " + Row_count);
             log.info("Row count is"+Row_count);
			int Col_count = driver
					.findElements(By
							.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div/div/div[2]/div/div[1]/table/tbody/tr[1]/td"))
					.size();
			 log.info("Row count is"+Col_count);
			System.out.println("Number Of Columns = " + Col_count);

			String first_part = "html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div/div/div[2]/div/div[1]/table/tbody/tr[";
			String second_part = "]";
			String third_part = "/td[";
			String fourth_part = "]";
			String fifth_part = "/td[4]/a";

			for (int i = 1; i <= Row_count; i++) {
				for (int j = 1; j <= Col_count; j++) {

					String final_xpath = first_part + i + second_part + third_part + j + fourth_part;
					System.out.println(final_xpath);
					String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
					System.out.println(Table_data + "  ");
					

					if (Table_data.equals(value)) {
						System.out.println("Value is Match:" + value);
						WebElement actionCell = driver.findElement(By.xpath(first_part + i + second_part + fifth_part));
						System.out.println(actionCell);
						actionCell.click();
						log.info("View the data");
						test.setErrormsg("Success");
						return test;
						
						

					}
				}
			}

		} 
		 catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			return test;
		}
		return test;

	}
	// Brands edit function

	public static TestStatus EDIT_BRANDS() {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
     try{
		driver.findElement(By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[6]/div/div/form/div[2]/input[2]"))
				.clear();
		log.info("Clear the brands name");
		driver.findElement(By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[6]/div/div/form/div[2]/input[2]"))
				.sendKeys("Fridge");
		log.info("Edit the brands name");
		WebElement ee = driver.findElement(By.id("baloonmodel"));
		ee.click();
		log.info("Select brands status dropdown");

		List<WebElement> elements = ee.findElements(By.className("ui-select-choices-row-inner"));

		for (WebElement element : elements) {
			if (element.findElement(By.tagName("span")).getText().equals("InActive")) {
				element.click();
				test.setStatus("PASS");
                log.info("Value is matched");
				System.out.println("VALUE IS MATCHED");
				test.setErrormsg("Value is matched");
				break;

			}
			else{
				test.setStatus("FAIL");
                log.error("Value is not matched");
                test.setErrormsg("Value is matched");
				
			}

		}
     }
     catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			return test;
		}
		return test;
	}

	// brands_save_function

	public static TestStatus BRANDS_SAVE(String[] actualValue) throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
  try{
	    driver.findElement(By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[6]/div/div/div/div/button")).click();
        log.info("Click on brand save button");
		for (String errorMsg : actualValue) {

			boolean alertMessage = driver.getPageSource().contains(errorMsg);
			System.out.println(alertMessage);
			if (alertMessage) {
				test.setStatus("PASS");
				test.setStatus_message(errorMsg);
				return test;
			}
			else{
				test.setStatus("FAIL");
				test.setErrormsg("Enter valid brand name");
				return test;
			}

		}
  }
  catch (Exception e) {

		e.printStackTrace();
		test.setStatus("FAIL");
		test.setErrormsg(e.getMessage());
		log.error(e.getMessage());
		return test;
	}
		return test;

	}

	// CATEGORY_SAVE_FUNCTION

	public static TestStatus CATEGORY_SAVE(String[] actualValue) throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
try{
		

		driver.findElement(By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[6]/div/div/div/div/button")).click();
		log.info("Click on category save button");

		for (String errorMsg : actualValue) {

			boolean alertMessage = driver.getPageSource().contains(errorMsg);
			System.out.println(alertMessage);
			if (alertMessage) {
				test.setStatus("PASS");
				test.setErrormsg(errorMsg);
				log.info(errorMsg);
				return test;

			}
			else{
				test.setStatus("FAIL");
				test.setErrormsg("Enter valid Category name");
				log.info("Enter valid Category name");
				return test;
				
			}

		}
	}
		catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			return test;
		}

		return test;

	}
	// Category edit function

	public static TestStatus EDIT_CATEGORY() {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
    try{
		driver.findElement(By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[6]/div/div/form/div[2]/input[2]"))
				.clear();
		log.info("Text field is clear");
		driver.findElement(By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[6]/div/div/form/div[2]/input[2]"))
				.sendKeys("Fridge");
		log.info("Type in text field");
		WebElement ee = driver.findElement(By.id("baloonmodel"));
		ee.click();
		log.info("Category status select from dropdown");
        List<WebElement> elements = ee.findElements(By.className("ui-select-choices-row-inner"));
     for (WebElement element : elements) {
			if (element.findElement(By.tagName("span")).getText().equals("InActive")) {
				element.click();
				test.setStatus("PASS");
                System.out.println("VALUE IS MATCHED");
				test.setErrormsg("Value is matched");
				break;

			}

		}
    }
    catch (Exception e) {

		e.printStackTrace();
		test.setStatus("FAIL");
		test.setErrormsg(e.getMessage());
		log.error(e.getMessage());
		return test;
	}
		return test;
	}
	
	//Select UOM dropdown 

	public static TestStatus SELECT_UOM(String path, String value, String actualvalue) {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try{
		WebElement ee = driver.findElement(By.xpath(path));
		ee.click();
		log.info("Click on UOM ");
		List<WebElement> elements = ee.findElements(By.className("ui-select-choices-row-inner"));

		for (WebElement element : elements) {
			if (element.findElement(By.tagName("span")).getText().equals(value)) {
				System.out.println(element);
				element.click();
				log.info("Selected uom is"+value);
				test.setStatus("PASS");
                System.out.println("VALUE IS MATCHED");
				test.setErrormsg("Value is matched");
				break;

			}

		}
		}
		  catch (Exception e) {

				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg(e.getMessage());
				log.error(e.getMessage());
				return test;
			}
		return test;
	}

	// product_save function

	public static TestStatus PRODUCT_SAVE(String[] actualValue) throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");

		try{

		driver.findElement(By
				.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div/div/div/div[2]/div/div/div/div/div/button"))
				.click();
        log.info("click on product save button");
		for (String errorMsg : actualValue) {

			boolean alertMessage = driver.getPageSource().contains(errorMsg);
			System.out.println(alertMessage);
			if (alertMessage) {
				test.setStatus("PASS");
				test.setErrormsg(errorMsg);
				log.info(errorMsg);
				return test;

			} else {
				test.setStatus("FAIL");
				test.setErrormsg("Mandatory field required");
				log.error("Mandatory field required");
			}

		}
		}
		catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			return test;
		}

		return test;

	}

	// View product

	public static TestStatus VIEW_PRODUCT(String value, String actualvalue) {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try {
			int Row_count = driver
					.findElements(By
							.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div/div/div[1]/table/tbody/tr/td[1]"))
					.size();
			System.out.println("Number Of Rows = " + Row_count);
            log.info("Product Number Of Rows = " + Row_count);
			int Col_count = driver
					.findElements(By
							.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div/div/div[1]/table/tbody/tr[1]/td"))
					.size();
			System.out.println("Number Of Columns = " + Col_count);
			log.info("Product Number Of Rows = " + Col_count);
			String first_part = "html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div/div/div/div[1]/div/div/div[1]/table/tbody/tr[";
			String second_part = "]";
			String third_part = "/td[";
			String fourth_part = "]";
			String fifth_part = "/td[6]/a";

			for (int i = 1; i <= Row_count; i++) {
				for (int j = 1; j <= Col_count; j++) {

					String final_xpath = first_part + i + second_part + third_part + j + fourth_part;
					System.out.println(final_xpath);
					String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
					System.out.println(Table_data + "  ");
					// Thread.sleep(500);

					if (Table_data.equals(value)) {
						System.out.println("Value is Match:" + value);
						WebElement actionCell = driver.findElement(By.xpath(first_part + i + second_part + fifth_part));
						System.out.println(actionCell);
						actionCell.click();
						log.info("View the product"+value);
						test.setErrormsg("success");
						return test;

					}
				}
			}

		
		} catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			log.error(e.getMessage());
			test.setErrormsg(e.getMessage());
			return test;
		}
		return test;

	}
	
	// Product_update function

	public static TestStatus PRODUCT_UPDATE(String[] actualValue) throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");

		try{

		driver.findElement(By
				.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div[2]/div/div/div/div/div/div/div[3]/div/div/div/div/div/button"))
				.click();
         
		for (String errorMsg : actualValue) {

			boolean alertMessage = driver.getPageSource().contains(errorMsg);
			System.out.println(alertMessage);
			if (alertMessage) {
				test.setStatus("PASS");
				test.setErrormsg(errorMsg);
				log.info(errorMsg);
				return test;

			} else {
				test.setStatus("FAIL");
				test.setErrormsg("Mandatory field required");
				log.error("Mandatory field required");
			}
		}

		}
		catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			return test;
		}

		return test;

	}
	
	//Ledger groups select function

	public static TestStatus LEDGER_CREATE_DROPDOWN(String path, String value, String actualvalue) {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try{
		WebElement ee = driver.findElement(By.xpath(path));
		ee.click();
        log.info("Click on dropdown");
		List<WebElement> elements = driver.findElements(By.className("ui-select-choices-row-inner"));

		for (WebElement element : elements) {
			if (element.findElement(By.tagName("span")).getText().equals(value)) {
				System.out.println(element);
				element.click();
				test.setStatus("PASS");
                log.info("Selected name is"+value);
				System.out.println("VALUE IS MATCHED");
				test.setErrormsg("Value is matched");
				break;

			}

		}
		}
		catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			return test;
		}
		return test;
	}
	
	//Ledger save function

	public static TestStatus LEDGER_ADD_SAVE(String[] actualValue) throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
 try{		
      driver.findElement(By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div[2]/div/div/div/div/div/div/div[2]/div/div/div/div/div/form/div[2]/button")).click();
      log.info("Click on ledger save button");
		for (String errorMsg : actualValue) {

			boolean alertMessage = driver.getPageSource().contains(errorMsg);
			System.out.println(alertMessage);
			if (alertMessage) {
				test.setStatus("PASS");
				test.setErrormsg(errorMsg);
				log.info(errorMsg);
				return test;

			}

		}
 }
 catch (Exception e) {

		e.printStackTrace();
		test.setStatus("FAIL");
		test.setErrormsg(e.getMessage());
		log.error(e.getMessage());
		return test;
	}

		return test;

	}
	
	//Ledger save function

		public static TestStatus LEDGER_EDIT_SAVE(String[] actualValue) throws Exception {
			TestStatus test = new TestStatus();
			test.setStatus("PASS");
	 try{		
	      driver.findElement(By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div[2]/div/div/div/div/div/div/div[3]/div/div/div/div/div/form/div[2]/button")).click();
	      log.info("Click on ledger save button");
			for (String errorMsg : actualValue) {

				boolean alertMessage = driver.getPageSource().contains(errorMsg);
				System.out.println(alertMessage);
				if (alertMessage) {
					test.setStatus("PASS");
					test.setErrormsg(errorMsg);
					log.info(errorMsg);
					return test;

				}

			}
	 }
	 catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			return test;
		}

			return test;

		}


	//View ledger function

	public static TestStatus VIEW_LEDGER(String value, String actualvalue) {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try {
			int Row_count = driver
					.findElements(By
							.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div[2]/div/div/div/div/div/div/div[1]/div/div/div[1]/table/tbody/tr/td[1]"))
					.size();
			System.out.println("Number Of Rows = " + Row_count);

			int Col_count = driver
					.findElements(By
							.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div[2]/div/div/div/div/div/div/div[1]/div/div/div[1]/table/tbody/tr[1]/td"))
					.size();
			System.out.println("Number Of Columns = " + Col_count);

			String first_part = "html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div[2]/div/div/div/div/div/div/div[1]/div/div/div[1]/table/tbody/tr[";
			String second_part = "]";
			String third_part = "/td[";
			String fourth_part = "]";
			String fifth_part = "/td[5]/a";

			for (int i = 1; i <= Row_count; i++) {
				for (int j = 1; j <= Col_count; j++) {

					String final_xpath = first_part + i + second_part + third_part + j + fourth_part;
					System.out.println(final_xpath);
					String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
					System.out.println(Table_data + "  ");
					// Thread.sleep(500);

					if (Table_data.equals(value)) {
						System.out.println("Value is Match:" + value);
						WebElement actionCell = driver.findElement(By.xpath(first_part + i + second_part + fifth_part));
						System.out.println(actionCell);
						test.setStatus("PASS");
						actionCell.click();
						log.info("View the ledger:"+value);
						test.setErrormsg("success");
						return test;

					}
				}
			}

		

		} catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			return test;
		}
		return test;

	}
	// Give to date and Pick calendar
	// Pass parameter set date, forward button xpath, reverse button xpath,
	// dayofmonth xpath

	public static TestStatus WEB_PICDATE(String fwdbutton, String revbutton, String dayofmonth, String value)
			throws InterruptedException {

		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		if (fwdbutton.isEmpty() || revbutton.isEmpty() || dayofmonth.isEmpty()) {
			// System.out.println("The parameter passed is blank");
			test.setStatus("FAIL");
			test.setErrormsg("PARAMETER IS EMPTY");
			log.error("Parameter is empty");
			return test;
		} else {
			try {
				String dateToSet = value;

				LocalDate currentDate = LocalDate.now();

				LocalDate targetDate = LocalDate.parse(dateToSet, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
				int dayofMonth = targetDate.getDayOfMonth();
				currentDate = currentDate.with(TemporalAdjusters.firstDayOfMonth());
				targetDate = targetDate.with(TemporalAdjusters.firstDayOfMonth());

				if (targetDate.isAfter(currentDate))

				{
					// Forward condition

					Period p = Period.between(currentDate, targetDate);
					System.out.println("years : " + p.getYears());
					System.out.println("MOnths: " + p.getMonths());
					jumMonthBy = p.getYears() * 12 + p.getMonths();

				}

				else if (currentDate.isAfter(targetDate)) {
					// Backward condition

					Period p = Period.between(targetDate, currentDate);
					System.out.println("years : " + p.getYears());
					System.out.println(" Months: " + p.getMonths());
					jumMonthBy = p.getYears() * 12 + p.getMonths();
					increment = false;
				} else {
					jumMonthBy = 1;
				}

				// increment=true :Move to calendar Forward button (Based on
				// jumpmonth)

				for (int i = 0; i < jumMonthBy; i++) {
					if (increment) {
						driver.findElement(By.xpath(fwdbutton)).click();
					}

					// increment=false :Move to calendar back button (Based on
					// jumpmonth)

					else {
						driver.findElement(By.xpath(revbutton)).click();
					}
					Thread.sleep(1000);

				}

				System.out.println(dayofMonth);

				List<WebElement> allDates = driver.findElements(By.xpath(dayofmonth));

				for (WebElement ele : allDates) {
					String date = ele.getText();

					if (!ele.getAttribute("class").equals("old day")
							&& date.equalsIgnoreCase(dateToSet.split("-")[0].replaceFirst("^0", ""))) {
						System.out.println(date);
						ele.click();
						test.setStatus("PASS");
						test.setErrormsg("DATE SELECTED");
						log.info("Date selected");
						return test;

					}

				}
			} 

			catch (Exception e) {

				e.printStackTrace();
				test.setStatus("FAIL");
				test.setErrormsg(e.getMessage());
				log.error(e.getMessage());
				return test;

			}
		}
		return test;
	}

	//Invoice delete function

	public static TestStatus INVOICE_DELETE(String value, String actualvalue) {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try {
			int Row_count = driver
					.findElements(By
							.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div/div/form/div[4]/div[1]/table/tbody/tr/td[1]"))
					.size();
			System.out.println("Number Of Rows = " + Row_count);
		    log.info("Number Of Rows = " + Row_count);
			int Col_count = driver
					.findElements(By
							.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div/div/form/div[4]/div[1]/table/tbody/tr[1]/td"))
					.size();
			System.out.println("Number Of Columns = " + Col_count);
			log.info("Number Of Colums = " + Col_count);
			String first_part = "html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div/div/form/div[4]/div[1]/table/tbody/tr[";
			String second_part = "]";
			String third_part = "/td[";
			String fourth_part = "]";
			String fifth_part = "/td[7]/div/button";

			for (int i = 1; i <= Row_count; i++) {
				for (int j = 1; j <= Col_count; j++) {

					String final_xpath = first_part + i + second_part + third_part + j + fourth_part;
					System.out.println(final_xpath);
					String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
					System.out.println(Table_data + "  ");
					if (Table_data.equals(value)) {
						System.out.println("Value is Match:" + value);
						WebElement actionCell = driver.findElement(By.xpath(first_part + i + second_part + fifth_part));
						System.out.println(actionCell);
						actionCell.click();
						log.info("Invoice deleted row is"+value);
						String totalvalue = driver
								.findElement(By
										.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div/div/form/div[4]/div[4]/div"))
								.getText();
						System.out.println(totalvalue);
						test.setStatus("PASS");
						test.setErrormsg("success");
						test.setElement(totalvalue);
						log.info("Invoce totalvalue="+totalvalue);
						return test;

					}
				}
			}

		
		} catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			return test;
		}
		return test;

	}
	
	//Add new product row 

	public static TestStatus AddNEW_PRODUCT_ROW() throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try{
        driver.findElement(
				By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div/div/form/div[4]/div[2]/button")).click();
		test.setStatus("PASS");
		log.info("Added in new product row");
		return test;

	}
		catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
		}
		return test;

	}
	
	//Invoice full screen mode

	public static TestStatus INVOICE_FULLSCREENMODE() throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
       try{
		driver.findElement(
				By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div/div/form/div[2]/div[1]/div[2]/a"))
				.click();
		log.info("View invoice fullscreen mode");
		test.setStatus("PASS");
		return test;

	}
       catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			
		}
       return test;
	}
	
	//Invoice cancel

	public static TestStatus INVOICE_CANCEL() {
		// TODO Auto-generated method stub
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try{

		driver.findElement(
				By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[1]/div/div/form/div[2]/div[1]/div[3]/a"))
				.click();
		test.setStatus("PASS");
		log.info("Invoice cancelled");
		return test;

	}
		catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			
		}
       return test;
	}
	
	// View the invoice

	public static TestStatus VIEW_INVOICE(String value) {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
		try {
			int Row_count = driver
					.findElements(By
							.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div[3]/div[2]/div[1]/table/tbody/tr/td[1]"))
					.size();
			System.out.println("Number Of Rows = " + Row_count);
			log.info("Number Of Rows = " + Row_count);
			int Col_count = driver
					.findElements(By
							.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div[3]/div[2]/div[1]/table/tbody/tr[1]/td"))
					.size();
			System.out.println("Number Of Columns = " + Col_count);
            log.info("Number Of Columns = " + Col_count);
			String first_part = "html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div[3]/div[2]/div[1]/table/tbody/tr[";
			String second_part = "]";
			String third_part = "/td[";
			String fourth_part = "]";
			String fifth_part = "/td[8]/a";

			for (int i = 1; i <= Row_count; i++) {
				for (int j = 1; j <= Col_count; j++) {

					String final_xpath = first_part + i + second_part + third_part + j + fourth_part;
					System.out.println(final_xpath);
					String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
					System.out.println(Table_data + "  ");
					

					if (Table_data.equals(value)) {
						System.out.println("Value is Match:" + value);
						WebElement actionCell = driver.findElement(By.xpath(first_part + i + second_part + fifth_part));
						System.out.println(actionCell);
						// WebElement actionCell =
						// driver.findElement(By.cssSelector("a[class='btn
						// btn-xs btn-success view-btn']"));
						actionCell.click();
						String totalvalue = driver
								.findElement(By
										.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div/div[3]/div/div[2]/div/div[1]/div/div/div/div[1]/div/div[2]/table/tbody/tr[1]/td[2]"))
								.getText();
						System.out.println(totalvalue);
						test.setStatus("PASS");
						log.info("View the invoice"+value);
						test.setErrormsg("success");
						test.setElement(totalvalue);
						return test;

					}
				}
			}

		}  catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
			test.setErrormsg(e.getMessage());
			log.error(e.getMessage());
			return test;
		}
		return test;

	}
	
	//Receipt name select

	public static TestStatus RECIEPT_NAME_SELECT(String value) {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");

		try {

			WebElement ee = driver.findElement(By.id("receiptTo"));
			ee.click();
            log.info("Click on recieptname dropdown");
			List<WebElement> elements = ee.findElements(By.className("ui-select-choices-row-inner"));

			for (WebElement element : elements) {
				if (element.findElement(By.tagName("span")).getText().equals(value)) {
					element.click();
					log.info("Receipt name selected:"+value);
					test.setStatus("PASS");
					test.setErrormsg("VALUE IS MATCHED");
					System.out.println("VALUE IS MATCHED");
					break;

				} else {
					System.out.println("VALUE NOT MATCHED");
					test.setStatus("FAIL");
					log.error("Value not matched:"+value);
					test.setErrormsg("VALUE NOT MATCHED");

				}
			}

		} 

		catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
            log.info(e.getMessage());
			test.setErrormsg(e.getMessage());
			return test;

		}
		return test;

	}
	
	//Select Reciept mode 

	public static TestStatus RECEIPT_MODE() {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");

		try {

			WebElement ee = driver.findElement(By.id("receiptMode"));
			ee.click();
            log.info("Click on receipt mode");
			List<WebElement> elements = ee.findElements(By.className("ui-select-choices-row-inner"));

			for (WebElement element : elements) {
				if (element.findElement(By.tagName("span")).getText().equals("Cash In Hand")) {
					element.click();
					log.info("Receipt mode selected");
					test.setStatus("PASS");
					test.setErrormsg("VALUE IS MATCHED");
					System.out.println("VALUE IS MATCHED");
					break;

				} else {
					System.out.println("VALUE NOT MATCHED");
					test.setStatus("FAIL");
					log.error("Value not matched");
					test.setErrormsg("VALUE NOT MATCHED");

				}
			}

		} 

		

		catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
            log.error(e.getMessage());
			test.setErrormsg(e.getMessage());
			return test;

		}
		return test;

	}

	// Receipt_Save function

	public static TestStatus RECIEPT_SAVE(String[] actualValue) throws AWTException {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
try{
		String payment = driver
				.findElement(By
						.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div[3]/form/div[2]/div[2]/div[2]/div[2]/div"))
				.getText();
		System.out.println(payment);
		test.setElement(payment);
		String balancevalue = driver
				.findElement(By
						.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div[3]/form/div[2]/div[2]/div[1]/div/table/tbody/tr[1]/td[6]/p"))
				.getText();
		System.out.println(balancevalue);
		log.info(balancevalue);
		driver.findElement(By
				.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div[3]/form/div[1]/div[1]/div[3]/button"))
				.click();
        log.info("Click on receipt save button");
		for (String errorMsg : actualValue) {

			boolean alertMessage = driver.getPageSource().contains(errorMsg);
			System.out.println(alertMessage);
			if (alertMessage) {
				test.setStatus("FAIL");
				log.error(errorMsg);
				test.setErrormsg(errorMsg);
				return test;
			}

		}

		test.setStatus("PASS");
		test.setErrormsg("Reciept created successfully");
        log.info("Reciept created successfully");
		return test;

	}
catch (Exception e) {

	e.printStackTrace();
	test.setStatus("FAIL");
    log.error(e.getMessage());
	test.setErrormsg(e.getMessage());
	

}
return test;
	}
	
	//Receipt add row
	

	public static TestStatus RECEIPT_ADDROW() throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
 try{
		driver.findElement(By
				.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div[3]/form/div[2]/div[2]/div[1]/div/div/button"))
				.click();
		log.info("add receipt row");
		test.setStatus("PASS");
		return test;

	}
 catch (Exception e) {

		e.printStackTrace();
		test.setStatus("FAIL");
	    log.error(e.getMessage());
		test.setErrormsg(e.getMessage());
		

	}
	return test;
		}
   
	//Receipt fullscreen mode

	public static TestStatus RECEIPT_FULLSCREENMODE() throws Exception {
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
    try{
		driver.findElement(
				By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div[3]/form/div[1]/div[1]/div[2]/a"))
				.click();
		test.setStatus("PASS");
		return test;

	}
    catch (Exception e) {

		e.printStackTrace();
		test.setStatus("FAIL");
	    log.error(e.getMessage());
		test.setErrormsg(e.getMessage());
		

	}
	return test;
		}
	
	//Receipt cancelled

	public static TestStatus RECEIPT_CANCEL() {
		// TODO Auto-generated method stub
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
try{
		driver.findElement(
				By.xpath("html/body/div[4]/div[1]/div[2]/div[1]/div/div[2]/div/div[3]/form/div[1]/div[1]/div[4]/a"))
				.click();
		log.info("Click reciept cancel button");
		test.setStatus("PASS");
		return test;

	}
	 catch (Exception e) {

			e.printStackTrace();
			test.setStatus("FAIL");
		    log.error(e.getMessage());
			test.setErrormsg(e.getMessage());
			

		}
		return test;
	}
	
	//Page_up function
	
	public static TestStatus PAGE_UP() {
		// TODO Auto-generated method stub
		TestStatus test = new TestStatus();
		test.setStatus("PASS");
try{
	
	Robot robot = new Robot();
	robot.keyPress(KeyEvent.VK_PAGE_UP);
	robot.keyRelease(KeyEvent.VK_PAGE_UP);
	test.setStatus("PASS");
	log.info("Page up");
	test.setStatus_message("System scroll pageup");
}
	
catch (Exception e) {

	e.printStackTrace();
	test.setStatus("FAIL");
    log.error(e.getMessage());
	test.setErrormsg(e.getMessage());
	

}
return test;
}	
}


