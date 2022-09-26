package org.qualitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingPage extends GlobalData  {
    WebDriver driver;
    PageBase pageBase;
    public BookingPage(WebDriver driver){
        this.driver=driver;
        pageBase=new PageBase(this.driver);
    }

    By arrival=By.xpath("//input[@id='date-start']");
    By nights=By.xpath("//input[@id='to-place']");
    By book=By.xpath("//div[@class='col-xs-12']//input[@name='commit']");

    public void bookingPage() {
        pageBase.sendKey(arrival,BookingDate());
        driver.findElement(nights).clear();
        pageBase.sendKey(nights,""+no_of_nights);
        pageBase.click(book);
    }
}
