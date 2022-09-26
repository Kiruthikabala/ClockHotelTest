package org.qualitest.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class GlobalData{
    public static String Url="https://www.clock-software.com/demo-clockpms/index.html";
    public String BookingDate()
    {
        DateFormat _bookingDate = new SimpleDateFormat("dd-MM-yyyy");
        Date todaysDate = new Date();
        return _bookingDate.format(todaysDate);
    }
    public static int no_of_nights=4;
    public static String _email="abc@gmail.com";
    public static String _lastname="abcd";
    public static String _firstname="abcd";
    public static int _phone=1234445667 ;
    public static String cardNumber="4444333322221111";
    public static String  _cardType="VISA";
    public static String  expiredMonth="08";
    public static String  expiredYear="2026";
    public static String  address="No45,Flat60";
    public static String  zip="NG7 9CG";
    public static String  city="Nottingham";
    public static String  state="Nottingamshire";
    public static String  country="United Kingdom";

}

