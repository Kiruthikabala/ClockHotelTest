package org.qualitest.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;


public class PageBase {
    WebDriver driver;
    WebDriverWait wait;
    public PageBase(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }
    public void click (By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }
    public void sendKey (By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }
    public String getText (By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }
    public WebElement  _ScreenShotElement() {
        return driver.findElement(By.xpath("//h2[text()='Clock PMS+ Demo Hotel Web Reservation System.']"));
    }
    public void addScreenShot(String destination, WebElement element) throws IOException {
        TakesScreenshot _screenShot= ((TakesScreenshot)driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",element );
        File file=_screenShot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File(destination));
    }

}
