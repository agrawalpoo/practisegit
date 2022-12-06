package Gnbproject;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class GNB_newcode {
    public  void getStart() {
       ConfigUtility configUtility1 = new ConfigUtility();
        try {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            driver.get(configUtility1.getProperty("WEBSITE_ADDRESS"));
            driver.manage().window().maximize();
            WebElement loginclick= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(configUtility1.getProperty("LOGIN_BUTTON"))));
            loginclick.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(configUtility1.getProperty("PANADHAARUSERID"))));
            driver.findElement(By.name(configUtility1.getProperty("PANADHAARUSERID"))).sendKeys(configUtility1.getProperty("USERNAME"));
            driver.findElement(By.xpath(configUtility1.getProperty("CONTINUE_LOGIN"))).click();

            WebElement userid =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(configUtility1.getProperty("PASSWORDCHECKBOX"))));
            userid.click();
            driver.findElement(By.id(configUtility1.getProperty("LOGINPASSWORDFIELD"))).sendKeys(configUtility1.getProperty("PASSWORD"));
            driver.findElement(By.xpath(configUtility1.getProperty("CLICKONEYE"))).click();
            driver.findElement(By.xpath(configUtility1.getProperty("CONTINUE_LOGIN2"))).click();
            //for pop up or alert message
            Thread.sleep(3000);
            boolean loginHere = driver.findElement(By.xpath(configUtility1.getProperty("LOGIN_HERE"))).isDisplayed();
            System.out.println("t element="+loginHere);
            if(loginHere) driver.findElement(By.xpath(configUtility1.getProperty("LOGIN_HERE"))).click();
            Thread.sleep(3000);
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("window.scrollBy(0,-400);");
            // for hover
            Actions action = new Actions(driver);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(configUtility1.getProperty("HEADER_MENU"))));//for Header menu
            WebElement we = driver.findElement(By.xpath(configUtility1.getProperty("HOVER_SERVICE")));//hover over service page
            action.moveToElement(we).perform();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(configUtility1.getProperty("CLICK_AIS_SERVICE"))));
            WebElement subEle = driver.findElement(By.xpath(configUtility1.getProperty("CLICK_AIS_SERVICE")));
            action.moveToElement(subEle).click().perform();//CLICK on AIS in service page

            WebElement clickonproceed=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(configUtility1.getProperty("CLICK_PROCEED"))));
            clickonproceed.click();//click on proceed

            Thread.sleep(1000);
            //we are handling more than 1 window by this
            List<String> windowid = new ArrayList<String>(driver.getWindowHandles());
            //switch to new tab
            driver.switchTo().window(windowid.get(1));

            WebElement clickonAis=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(configUtility1.getProperty("CLICK_AIS"))));
            clickonAis.click();//click on AIS in next tab

            WebElement clickontis=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(configUtility1.getProperty("CLICK_TIS"))));
            clickontis.click(); //click on TIS

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(configUtility1.getProperty("INFORMATION_CATEGORY"))));
            List<WebElement> listno = driver.findElements(By.xpath(configUtility1.getProperty("INFORMATION_CATEGORY")));//Information category list of TIS
            js.executeScript("window.scrollBy(0,100);");
            String filename = "";
            int count = 1;
            int tableRow = 0;
            int tableColumn = 0;

            filename = "D:\\CustomersDetail.xlsx";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Data");
            System.out.println("aaya");
            for (WebElement ele : listno) {
                ele.click();
                Thread.sleep(1000);
                List<WebElement> submenu = driver.findElements(By.xpath(configUtility1.getProperty("MENU_TABLE")));//table accordian
                if (!submenu.isEmpty()) {
                    for (WebElement el : submenu) {
                        el.click();
                        Thread.sleep(1000);
                        List<WebElement> subsubmenu = driver.findElements(By.xpath(configUtility1.getProperty("CLICK_SUBMENU")));
                        for (WebElement el1 : subsubmenu) {
                            el1.click();
                            Thread.sleep(1000);
                            WebElement table = driver.findElement(By.xpath(configUtility1.getProperty("INNER_TABLE")));
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
                count++;
            }
        } catch (Exception exception) {
            System.out.println("Exception = " + exception);
        }
    }
}
