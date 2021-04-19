import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class targetSelenium {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rdcs2\\eclipse-workspace\\Selenium\\chromedriver.exe");
		WebDriver targetDriver = new ChromeDriver();
		targetDriver.get("https://target.com");
		
		WebElement searchInput = targetDriver.findElement(By.xpath("//input[@type='search']"));
		searchInput.sendKeys("iphone");
		Thread.sleep(2000);
		searchInput.submit();
	    Thread.sleep(2000);
	    List<String> nameResults = new ArrayList<>();
	    List<String> priceResults = new ArrayList<>();
	    
	    JavascriptExecutor js = (JavascriptExecutor) targetDriver;
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    
	    
	    String xpathName = "//a[contains(@data-test, 'product-title')]";
	    
	    List<WebElement> listNames = targetDriver.findElements(By.xpath(xpathName));
	    for(WebElement names : listNames) {
	    	nameResults.add(names.getText());
	    }
	    Thread.sleep(2000);
	    
	    String xpathPrice = "//div[contains(@data-test, 'current-price')]";
	    
	    List<WebElement> listPrices = targetDriver.findElements(By.xpath(xpathPrice));
	    
	    for(WebElement price : listPrices) {
	    	priceResults.add(price.getText());
	    }
	    Thread.sleep(2000);
	    try {
	    	FileWriter fw = new FileWriter("targetFile.txt");
	    	for (int itr = 0; itr < nameResults.size(); itr++) {
				fw.write("Product: " + nameResults.get(itr) + ", Price: " + priceResults.get(itr));
				fw.write("\r\n");
	    	}
	    	fw.close();
	    	}catch(IOException e) {
	    	System.out.println("An error occurred.");
	        e.printStackTrace();
	}
	}
	}