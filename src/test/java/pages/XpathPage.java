package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class XpathPage {

    private WebDriver pageDriver;
    public String chosen = "you have chosen ";

    public XpathPage(WebDriver driver) {
        this.pageDriver = driver;
    }

    public void clickButton(String s){
        pageDriver.findElement(By.xpath("//button[contains(text(),'" + s + "')]")).click();
    }

    public String getMessage(){
        return pageDriver.findElement(By.cssSelector(".output h2 span")).getText();
    }

    public void selectLoop(WebElement valueSelect, String message){
        for (int i = 1; i < 6; i++) {
            new Select(valueSelect).selectByIndex(i);
            Assert.assertEquals(message + i, getMessage());
        }
    }

    public void selectZero(String optionZero, WebElement valueSelect){
        new Select(valueSelect).selectByVisibleText(optionZero);
        Assert.assertEquals(chosen + optionZero, getMessage());
    }
}
