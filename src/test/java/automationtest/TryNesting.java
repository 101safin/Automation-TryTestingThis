package automationtest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TryNesting {
    public static void main(String[] args) throws InterruptedException {
        // WebDriver initialization
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        // Navigate to the website
        driver.get("https://trytestingthis.netlify.app/");
        driver.manage().window().maximize();
        
        // First and Last Name
        driver.findElement(By.id("fname")).sendKeys("Ahmed");
        driver.findElement(By.id("lname")).sendKeys("Khan");
        
        // RadioButton
        WebElement radio = driver.findElement(By.id("male"));
        radio.click();
        
        // Checking if radioButton is actually selected
        assert radio.isSelected();
        System.out.println(radio.isSelected());
        
        // Selecting option from dropdown
        WebElement dropdowntest = driver.findElement(By.id("option"));
        Select dropdown = new Select(dropdowntest);
        dropdown.selectByVisibleText("Option 2");  
        
        // Check if "Option 1" is selected and select checkbox "option2"
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        if (selectedOption.equals("Option 1")) {
            WebElement checkbox2 = driver.findElement(By.name("option2"));
            if (!checkbox2.isSelected()) {
                checkbox2.click();
                System.out.println("Checkbox 'option2' is selected because 'Option 1' is selected from dropdown.");
            }
        }
        
        // MultiSelect
        WebElement multiSelect = driver.findElement(By.id("owc"));
        Select select = new Select(multiSelect);
        
        if (select.isMultiple()) {
            select.selectByIndex(0);  // Select first option
            select.selectByValue("option 2");  // Select option with value "2"
            select.selectByVisibleText("Option 3");  // Select option with visible text "Option 3"
        }
        
        // Locate the checkboxes
        WebElement checkbox1 = driver.findElement(By.name("option1"));
        WebElement checkbox3 = driver.findElement(By.name("option3"));
        
        // Select checkboxes
        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }
        
        if (!checkbox3.isSelected()) {
            checkbox3.click();
        }
        
        // ColorPicker
        WebElement colorPick = driver.findElement(By.id("favcolor"));
        colorPick.sendKeys("#3A8E29");
        
        // Date (mm/dd/yyyy)
        WebElement date = driver.findElement(By.id("day"));
        date.sendKeys("11/03/1966");
        
        // Scroll Range
        WebElement scroll = driver.findElement(By.id("a"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = 90;", scroll);
        
        // File Upload
        WebElement file = driver.findElement(By.id("myfile"));
        file.sendKeys("C:\\Users\\User\\Downloads\\download (1).jpeg");
        
        // Increment range
        WebElement range = driver.findElement(By.id("quantity"));
        JavascriptExecutor sj = (JavascriptExecutor) driver;
        sj.executeScript("arguments[0].value = 3;", range);
        sj.executeScript("arguments[0].dispatchEvent(new Event('input'));", range);
        
        
        //Long Message
        WebElement message = driver.findElement(By.name("message"));
        message.clear();
        String longMessage = generateRandomString(50);
        message.sendKeys(longMessage);
        
//        Thread.sleep(5000);
        
        //Submit button
//        JavascriptExecutor javaS = (JavascriptExecutor) driver;
//        javaS.executeScript("window.scrollBy(0,500)");
//        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
//        javaS.executeScript("arguments[0].click();", submitButton);
        // driver.quit();
    }

	private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
	}
}
