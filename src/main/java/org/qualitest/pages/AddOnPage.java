package org.qualitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddOnPage {
    WebDriver driver;
    PageBase pageBase;
    public AddOnPage(WebDriver driver){
        this.driver=driver;
        pageBase=new PageBase(this.driver);
    }
    By _AddOn1=By.xpath("//*[contains(text(),'Airport')]/following::div[3]//input[@placeholder='Quantity']");
    By _AddOn2=By.xpath("//*[contains(text(),'Business')]/following::div[3]//input[@placeholder='Quantity']");
    By _AddSelectedService=By.xpath("//input[@value='Add the selected services >>']");

    public void addOnSelection() {
        pageBase.sendKey(_AddOn1,"1");
        pageBase.sendKey(_AddOn2,"1");
        pageBase.click(_AddSelectedService);
    }

}
