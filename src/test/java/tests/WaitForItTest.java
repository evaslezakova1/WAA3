package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForItTest extends TestBase {

    @Before
    public void openPage(){
        driver.get(BASE_URL + "/waitforit.php");
    }

    @Test
    public void waitForValue() {
        String expectedText = "dary !!!";
        driver.findElement(By.id("startWaitForText")).click();
        WebElement input = driver.findElement(By.id("waitForTextInput"));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeToBe(input, "value", expectedText));
        Assert.assertEquals(expectedText, input.getAttribute("value"));
    }

    @Test
    public void waitForClass() {
        String errorMessage = "error";

        driver.findElement(By.id("startWaitForProperty")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeContains(By.id("waitForProperty"), "class", errorMessage));
    }
}
