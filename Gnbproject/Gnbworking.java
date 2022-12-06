package Gnbproject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Gnbworking {
    public static void main(String ar[]) {
        try {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            driver.get("https://www.incometax.gov.in/iec/foportal");
            driver.manage().window().maximize();

            WebElement loginclick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login\"]/a")));
            loginclick.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("panAdhaarUserId")));
            driver.findElement(By.name("panAdhaarUserId")).sendKeys("AOVPP7527E");
            driver.findElement(By.xpath("//*[@id=\"maincontentid\"]/app-login/div/app-login-page/div/div[2]/div[1]/div[2]/button")).click();

            WebElement userid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordCheckBox")));
            userid.click();
            driver.findElement(By.id("loginPasswordField")).sendKeys("Eyedoc#11");
            driver.findElement(By.xpath("//*[@id=\"maincontentid\"]/app-login/div/app-password-page/div[1]/div[2]/div[1]/div[4]/mat-form-field/div/div[1]/div[4]/button/span/mat-icon")).click();
            driver.findElement(By.xpath("//*[@id=\"maincontentid\"]/app-login/div/app-password-page/div[1]/div[2]/div[1]/div[5]/button")).click();
            //for pop up
            Thread.sleep(5000);
            boolean LoginHere=driver.findElement(By.xpath("//button[contains(text(),' Login Here ')]")).isDisplayed();// xpath for login here
            System.out.println("click on ok:"+LoginHere);
            if(LoginHere)driver.findElement(By.xpath("//button[contains(text(),' Login Here ')]")).click();

             // for hover
            Actions action = new Actions(driver);
            Thread.sleep(2000);
            WebElement we = driver.findElement(By.xpath("//a[3]//span[1]"));//hover over service page
            action.moveToElement(we).perform();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div/div/div/span[6]/span/div[1]/button")));
            WebElement subEle = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/span[6]/span/div[1]/button"));//CLICK on AIS in service page
            action.moveToElement(subEle).click().perform();

            WebElement clickonproceed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"annualInformation\"]/div/div/div[3]/button[2]")));
            clickonproceed.click();//click on proceed

            Thread.sleep(2000);
            //we are handling more than 1 window by this instead of list we can use iterator mthod
            List<String> windowid = new ArrayList<String>(driver.getWindowHandles());
            //switch to new tab
            driver.switchTo().window(windowid.get(1));

            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"content-page\"]/div/app-ais-instructions/div/app-ais-dash-tabs/nav/a[2]")).click();//click on AIS in next tab

            WebElement clickontis = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content-page\"]/div/app-ais/div/div/div[2]/div[1]/app-ais-card/div/div[1]")));//click on TIS
            clickontis.click(); //click on TIS

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"overviewHeadingOne\"]")));
            List<WebElement> listno = driver.findElements(By.xpath("//*[@id=\"overviewHeadingOne\"]"));//Information category list of submenu

            String filename = "";
            int count = 1;
            int tableRow = 0;
            int tableColumn = 0;

            filename = "D:\\CustomersDetail.xlsx";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Data");

            for (WebElement ele : listno) {
                ele.click();
                Thread.sleep(2000);
                List<WebElement> submenu = driver.findElements(By.xpath("//*[@id=\"collapse0\"]/app-ais-summary-level-three-information/div/app-summary-grid/div/div[" + count + "]/table/tbody/tr"));
                if (!submenu.isEmpty()) {
                    for (WebElement el : submenu) {
                        el.click();
                        Thread.sleep(2000);
                        List<WebElement> subsubmenu = driver.findElements(By.xpath("/html/body/app-root/app-index/div/div/div/app-ais-form26/div/div/div[4]/div/div/div[2]/div/div[2]/div/div/div[2]/app-multiple-grid-table/div/table/tbody/tr"));
                        System.out.println("testttt = " + subsubmenu.size());
                        for (WebElement el1 : subsubmenu) {
                            Thread.sleep(2000);
                            el1.click();
                            Thread.sleep(1000);
                            WebElement table = driver.findElement(By.xpath("//*[@id=\"partB\"]/div/div/div[2]/div/div[2]/div/div/div[2]/app-multiple-grid-table/div/table/tbody/tr[2]/td/div/app-level-three-grid/div/div[2]/table/tbody"));
                            List<WebElement> rowsList = table.findElements(By.tagName("tr"));
                            List<WebElement> columnsList = null;
                            int columnCount = 0;
                            int rowCount = 0;
                            for (WebElement row1 : rowsList) {
                                System.out.println("row:"+row1.getText());
                                Row row = sheet.createRow(++rowCount);
                                Cell cell=row.createCell(columnCount);
                                cell.setCellValue(row1.getText());
                                //  columnsList = row.findElements(By.tagName("tr"));
//                                for (WebElement column : columnsList) {
//                                    System.out.println("ColumnData : " + column.getText());
//                                    System.out.println("layout : " + tableRow + " " + tableColumn + " " + column.getText());
//                                   // sheet.createRow(tableRow).createCell(tableColumn).setCellValue(column.getText());
//                                    tableColumn++;
//                                }
                                //  tableColumn = 0;
                                //tableRow++;
                            }
                            FileOutputStream fileOut = new FileOutputStream(filename);
                            workbook.write(fileOut);
                            fileOut.close();
                            tableRow = 0;
                            break;
                        }
                    }
                }

//

                count++;
            }
        } catch (Exception exception) {
            System.out.println("Exception = " + exception);
        }
    }


}


