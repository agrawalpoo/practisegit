package Gnbproject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testbrowse {
    public static void main(String ar[]) {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.get("https://html.com/input-type-file/");
            driver.manage().window().maximize();
            //copy id or xpath of browse and location of file not click on browse
            driver.findElement(By.id("fileupload")).sendKeys("C:\\Users\\hp\\Downloads/holidays.jpeg");
}}
