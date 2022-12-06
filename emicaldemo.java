import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class emicaldemo {

    public void emicalculator(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://emicalculator.net/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Actions actions=new Actions(driver);
       WebElement HomeLoanAmount = driver.findElement(By.xpath("//div[@id='loanamountslider']//span[contains(@class,'ui-slider')]"));
        WebElement InterestRate = driver.findElement(By.xpath("//div[@id='loaninterestslider']//span[contains(@class,'ui-slider')]"));
        WebElement LoanTenure = driver.findElement(By.xpath("//div[@id='loantermslider']//span[contains(@class,'ui-slider')]"));
        actions.dragAndDropBy(HomeLoanAmount,82,0).build().perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions.dragAndDropBy(InterestRate,104,0).build().perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions.dragAndDropBy(LoanTenure,-109,0).build().perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement loanemi=driver.findElement(By.xpath("//div[@id='emiamount']//p//span"));
        String loanemitext=loanemi.getText();
       // if(loanemitext.equals())

    }
}
