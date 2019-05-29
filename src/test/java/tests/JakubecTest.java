package tests;
import base.TestBase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JakubecTest extends TestBase{

    @Before
    public void openPage(){
        driver.get(BASE_URL + "/geriatriahladasuperstar.php");
    }

    @Test
    public void itShouldDisplayImage() {
        driver.findElement(By.id("showHim")).click();
        By locator = By.cssSelector("img.superstar");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        Assert.assertTrue(driver.findElement(locator).isDisplayed());

    }
}
