package org.qualitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BillingAddressCardDetailsPage extends GlobalData {
    WebDriver driver;
    PageBase pageBase;
    public BillingAddressCardDetailsPage(WebDriver driver) {
        this.driver = driver;
        pageBase=new PageBase(this.driver);
    }
    By _CardNumber = By.xpath("//input[@id='cardNumber']");
    By _cardTypes = By.xpath("//select[@id='credit_card_collect_purchase_brand']");
    By _expiredMonth = By.xpath("//select[@id='cardExpirationMonth']");
    By _expiredYear = By.xpath("//select[@id='cardExpirationYear']");

    By _address = By.xpath("//input[@id='credit_card_collect_purchase_address']");
    By _zip = By.xpath("//input[@id='credit_card_collect_purchase_zip']");
    By _city = By.xpath("//input[@id='credit_card_collect_purchase_city']");
    By _state = By.xpath("//input[@id='credit_card_collect_purchase_state']");
    By _country = By.xpath("//select[@id='credit_card_collect_purchase_country']");
    By _confirm = By.xpath("//button[@class='btn btn-success btn-lg btn-block']");

    By bookingMsg=By.xpath("//h3[text()='Check your e-mail for booking confirmation.']");

    public WebElement _cardTypes() {
        return driver.findElement(_cardTypes);
    }
    public WebElement _expiredMonth() {
        return driver.findElement(_expiredMonth);
    }
    public WebElement _expiredYear() {
        return driver.findElement(_expiredYear);   }

    public WebElement _country() {
        return driver.findElement(_country);
    }

    public void InputBillingDetails() {
        pageBase.sendKey(_CardNumber,cardNumber);
        Select cardType = new Select(_cardTypes());
        cardType.selectByVisibleText(_cardType);
        Select _expiredMonth = new Select(_expiredMonth());
        _expiredMonth.selectByVisibleText(expiredMonth);
        Select _expiredYear = new Select(_expiredYear());
        _expiredYear.selectByVisibleText(expiredYear);
         pageBase.sendKey(_address,address);
         pageBase.sendKey(_zip,zip);
         pageBase.sendKey(_city,city);
         pageBase.sendKey(_state,state);
        Select _country = new Select(_country());
        _country.selectByVisibleText(country);
         pageBase.click(_confirm);
     }
    public String To_Validate_BookingMsg(){
        return pageBase.getText(bookingMsg);
    }

}
