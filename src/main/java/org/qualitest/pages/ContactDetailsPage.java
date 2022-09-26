package org.qualitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ContactDetailsPage extends GlobalData {
    WebDriver driver;
    PageBase pageBase;
    public ContactDetailsPage(WebDriver driver) {
        this.driver = driver;
        pageBase=new PageBase(this.driver);
    }

    By _Email = By.xpath("//input[@title='E-mail']");
    By _LastName = By.xpath("//input[@id='booking_guest_attributes_last_name']");
    By _FirstName = By.xpath("//input[@id='booking_guest_attributes_first_name']");
    By _Phone = By.xpath("//input[@id='booking_guest_attributes_phone_number']");
    By _CreditCard = By.xpath("//input[@id='booking_payment_service_credit_card_collect']");
    By _TermsConditions = By.xpath("//input[@title='I agree with the hotel and guarantee policy']");
    By _arrival = By.xpath("//*[text()='Arrival']/following::div[1]");
    By _No_of_nights = By.xpath("//*[text()='Stay']/following::div[1]");
    By _Room_type = By.xpath("//*[text()='Room Type']/following::div[1]");
    By _Rate = By.xpath("//*[text()='Rate']/following::div[1]");
    By _Add_on = By.xpath("//*[text()='Extra Services']/following::div[1]");
    By _Total = By.xpath("//div[@class='row total_charges']//*[contains(text(),'EUR')]");
    By create_Booking=By.xpath("//input[@value='Create Booking']");

    public void ContactDetailsPageUpdate() {
        pageBase.sendKey(_Email,_email);
        pageBase.sendKey(_LastName,_lastname);
        pageBase.sendKey(_FirstName,_firstname);
        pageBase.sendKey(_Phone,"" + _phone);
        pageBase.click(_CreditCard);
        pageBase.click(_TermsConditions);
    }
    public String _Arrival() {
           return pageBase.getText(_arrival).replace(" ", "-");
    }
    public String _no_of_nights() {
        return pageBase.getText(_No_of_nights);
    }
    public String _room_type() {
        return pageBase.getText(_Room_type);
    }
    public String _rate() {
        return pageBase.getText(_Rate);
    }
    public String _addon() {
        return  pageBase.getText(_Add_on);
    }
    public Double _total() {
        return Double.parseDouble(pageBase.getText(_Total).split(" ")[0].replace(",", ""));

    }
    public void To_Click_Create_Booking() {
        pageBase.click(create_Booking);
    }

}
