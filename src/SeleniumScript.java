import org.openqa.selenium.By;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class SeleniumScript {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rdcs2\\eclipse-workspace\\Selenium\\chromedriver.exe");
		WebDriver ebayDriver = new ChromeDriver();
		ebayDriver.get("https://ebay.com");
		
		WebElement searchInput = ebayDriver.findElement(By.xpath("//input[@placeholder='Search for anything']"));
		searchInput.sendKeys("iphone");
		Thread.sleep(2000);
		searchInput.submit();
	    Thread.sleep(2000);
	    List<String> nameResults = new ArrayList<>();
	    List<String> priceResults = new ArrayList<>();
	    
	    String xpathName = "//h3[contains(@class, 's-item__title')]";
	    
	    List<WebElement> listNames = ebayDriver.findElements(By.xpath(xpathName));
	    for(WebElement names : listNames) {
	    	nameResults.add(names.getText());
	    }
	    Thread.sleep(2000);
	    
	    String xpathPrice = "//span[@class='s-item__price']";
	    
	    List<WebElement> listPrices = ebayDriver.findElements(By.xpath(xpathPrice));
	    
	    for(WebElement price : listPrices) {
	    	priceResults.add(price.getText());
	    }
	    Thread.sleep(2000);
	    try {
	    	FileWriter fw = new FileWriter("ebayFile.txt");
	    	for (int itr = 0; itr < priceResults.size(); itr++) {
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