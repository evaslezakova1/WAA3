package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConchitaPage {

    private WebDriver pageDriver;

    public ConchitaPage(WebDriver driver) {
        this.pageDriver = driver;
    }

    public void clickOnMan() {
        pageDriver.findElement(By.xpath("//input[@value='wurst']")).click();
    }

    public void isImgDisplayed() {
        Assert.assertTrue(pageDriver.findElement(By.xpath("//img")).isDisplayed());
    }

    public String getText() {
        return pageDriver.findElement(By.cssSelector("h1.description")).getText();
    }

    public void testButton(String s) {
        Assert.assertFalse(pageDriver.findElement(By.xpath(s)).isSelected());
    }
}
