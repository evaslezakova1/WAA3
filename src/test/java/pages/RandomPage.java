package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RandomPage {

    private WebDriver pageDriver;

    public RandomPage(WebDriver driver) {
        this.pageDriver = driver;
    }

    public List<WebElement> returnList() {
        return pageDriver.findElements(By.cssSelector("table tbody tr"));
    }
}
