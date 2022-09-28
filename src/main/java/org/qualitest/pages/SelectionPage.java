package org.qualitest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SelectionPage extends PageBase {
    public String dateString = "";
    public Double MaxPackage = 0.0;
    public SelectionPage(WebDriver driver){
        super(driver);
    }
    By checkAvailability=By.xpath("//div[@class='bookable-container bookable-location-3864']//h2");
    By checkAvailabilityCalender=By.xpath("//a[@class='text-center']");
    By availabilityS= By.xpath("//h4[contains(text(),\"Delux\")]/following::div[1]//div[@class='col-lg-1 col-md-2 col-sm-2 col-xs-3']");
    By rightArrow=By.xpath("//div[@class='icon-double-angle-right']");
    By leftArrow=By.xpath("//div[@class='icon-double-angle-left']");
    By serchForAvilableRooms= By.xpath("//input[@value='Search for available rooms']");
    By elementToFindExpensive=By.xpath("//tr[@class='room-type']//td[@class='text-right hidden-xs']//*[contains(text(),'EUR')]");

    public List<WebElement> checkAvailability(){
        return  driver.findElements(checkAvailability);
    }

    public WebElement checkAvailabilityCalender()
    { return driver.findElement(checkAvailabilityCalender);}

    public List<WebElement> availabilityS(){
        return  driver.findElements(availabilityS);
    }

    public void CheckAvailability() throws InterruptedException, ParseException {
        driver.switchTo().frame("clock_pms_iframe_1");
        ArrayList<String> roomType = new ArrayList<>();
        for (int i = 0; i < checkAvailability().size(); i++) {
            roomType.add(checkAvailability().get(i).getText());
        }
        if (!roomType.contains("Deluxe Appartment")) {
            click(checkAvailabilityCalender);
            int availableCount = 0;
            String availableDate = "";
            while (availableCount < 4) {
                ArrayList<String> availability = new ArrayList<>();
                for (int m = 0; m < availabilityS().size(); m++) {
                    availability.add(availabilityS().get(m).getText());
                }
                int i = 1;
                while (i <= availabilityS().size()) {
                    WebElement find = driver.findElement(By.xpath("//h4[contains(text(),\"Delux\")]/following::div[1]//div[@class='col-lg-1 col-md-2 col-sm-2 col-xs-3'][" + i + "]"));
                    List<WebElement> webEleListS = find.findElements(By.xpath(".//*"));
                    ArrayList<String> webEleList = new ArrayList<>();
                    for (int j = 0; j < webEleListS.size(); j++) {
                        webEleList.add(webEleListS.get(j).getAttribute("class"));
                    }
                    if (webEleList.get(4).contains("danger")) {
                        i++;
                        availableCount = 0;
                    } else if (webEleList.get(4).contains("success")) {
                        availableCount = availableCount + 1;
                        if (availableCount == 1) {
                            availableDate = availability.get(i - 1);
                        }
                        i++;
                    }
                    if (availableCount == 4) {
                        break;
                    }
                }
                Thread.sleep(4000);
                click(rightArrow);
                Thread.sleep(6000);
            }
            By _AvailableDate = By.xpath("//h4[contains(text(),'Delux')]/following::div[1]//a[@class='list-group-item small text-center']//b[text()='" + availableDate.split(" ")[0] + "']");
            if (!driver.findElements(_AvailableDate).isEmpty()) {
               click(_AvailableDate);
            } else {
                do {
                    click(leftArrow);
                    for (int m = 0; m < availabilityS().size(); m++) {
                        if (availabilityS().get(m).getText().contains(availableDate)) {
                            System.out.println("found available date");
                        }
                    }
                } while (driver.findElements(_AvailableDate).isEmpty());
                click(_AvailableDate);
            }
            dateString =driver.findElement(By.xpath("//input[@class='product_arrival_field form-control']")).getAttribute("value");
            DateFormat OldFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date _oldFormat = OldFormat.parse(dateString);
            DateFormat _dateString = new SimpleDateFormat("dd-MMM-yyyy");
            dateString = _dateString.format(_oldFormat);
            click(serchForAvilableRooms);
            List<WebElement> expensivePackages = driver.findElements(elementToFindExpensive);
            ArrayList<Double> expensivePackage = new ArrayList<>();
            for (int i = 0; i < expensivePackages.size(); i++) {
                expensivePackage.add(Double.parseDouble(expensivePackages.get(i).getAccessibleName().split(" ")[0].replace(",", "")));
            }
            MaxPackage = Collections.max(expensivePackage);
            List<WebElement> selectPackage = driver.findElements(By.xpath("//span[@class='pull-right']//a[@class='btn btn-success ']//i"));
            for (int i = 0; i < expensivePackages.size(); i++) {
                if (Double.parseDouble(expensivePackages.get(i).getAccessibleName().split(" ")[0].replace(",", "")) == Collections.max(expensivePackage)) {
                    selectPackage.get(i).click();
                }
            }
        } else {
            Thread.sleep(1000);
            List<WebElement> expensivePackages = driver.findElements(By.xpath("//h2[contains(text(),'Delux')]/following::div//tr[@class='room-type']//td[@class='text-right hidden-xs']//*[contains(text(),'EUR')]"));
            ArrayList<Double> expensivePackage = new ArrayList<>();
            for (int i = 0; i < expensivePackages.size(); i++) {
                expensivePackage.add(Double.parseDouble(expensivePackages.get(i).getAccessibleName().split(" ")[0].replace(",", "")));
            }
            MaxPackage = Collections.max(expensivePackage);
            List<WebElement> selectPackage = driver.findElements(By.xpath("//h2[contains(text(),'Delux')]/following::div//span[@class='pull-right']//a[@class='btn btn-success ']//i"));
            for (int i = 0; i < expensivePackages.size(); i++) {
                if (Double.parseDouble(expensivePackages.get(i).getAccessibleName().split(" ")[0].replace(",", "")) == Collections.max(expensivePackage)) {
                    selectPackage.get(i).click();
                }
            }
        }

    }
}
