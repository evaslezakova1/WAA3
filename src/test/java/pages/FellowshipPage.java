package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FellowshipPage {

    private WebDriver pageDriver;

    public FellowshipPage(WebDriver driver){
        this.pageDriver = driver;
    }

    public String getInitialPoints(String s) {
        return pageDriver.findElement(By.xpath(s)).getText();
    }

    public List<WebElement> returnList() {
        return pageDriver.findElements(By.cssSelector("ul.list-of-fellows li"));
    }

    public String nameOfFellows(WebElement listOfFellow) {
        return listOfFellow.findElement(By.cssSelector("h1")).getText();
    }
}
