package org.qualitest.pages;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;

public class BookingTest extends GlobalData
{
   public WebDriver driver;
   @BeforeTest
   public void InitializeBrowser()
   {
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.get(Url);
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   }

    @Test
    public void booking() throws InterruptedException, ParseException, IOException {

        BookingPage bookingPage = new BookingPage(driver);
        SelectionPage selectionPage = new SelectionPage(driver);
        AddOnPage addOnPage = new AddOnPage(driver);
        ContactDetailsPage contactDetailsPage = new ContactDetailsPage(driver);
        BillingAddressCardDetailsPage billingAddressCardDetailsPage = new BillingAddressCardDetailsPage(driver);
        bookingPage.bookingPage();
        //checkavailability
        selectionPage.CheckAvailability();
        //addonpage
        addOnPage.addOnSelection();
        //contact details
        contactDetailsPage.ContactDetailsPageUpdate();
        //Validation
        if (contactDetailsPage._Arrival().equals(BookingDate())) {
            Assert.assertEquals(contactDetailsPage._Arrival(), BookingDate());
        } else
            Assert.assertEquals(contactDetailsPage._Arrival(),selectionPage.dateString, "arrival date matched");
        Assert.assertEquals(contactDetailsPage._no_of_nights(), "4");
        Assert.assertEquals(contactDetailsPage._room_type(), "Deluxe Appartment");
        Assert.assertEquals(contactDetailsPage._rate(), "Rack Rate Standard Max +");
        Assert.assertEquals(contactDetailsPage._addon(), "75.00 EUR");
        Assert.assertEquals(contactDetailsPage._total(), (selectionPage.MaxPackage + 75.0));
        new PageBase(driver).addScreenShot("bookingDetails.png",new PageBase(driver)._ScreenShotElement());
        contactDetailsPage.To_Click_Create_Booking();
        ///card details&Billing address
        billingAddressCardDetailsPage.InputBillingDetails();
        //ConfirmOrder
        Assert.assertEquals(billingAddressCardDetailsPage.To_Validate_BookingMsg(), "Check your e-mail for booking confirmation.");
        new PageBase(driver).addScreenShot("bookingconfirmation.png",new PageBase(driver)._ScreenShotElement());
    }
   @AfterTest
    public void CloseBrowser(){
       driver.quit();
   }

}



