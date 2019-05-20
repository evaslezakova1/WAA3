package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class ClickMeBaby extends TestBase {

    @Before
    public void openPage() {
        driver.get("http://ajtyvit-app.westeurope.cloudapp.azure.com:8080/clickmebaby.php");
    }

    @Test
    public void itShouldAssertClickText() {

        String number = driver.findElement(By.id("clicks")).getText();
        String label = driver.findElement(By.cssSelector("p.description")).getText();
        String click = "klik";

        for (int i = 0; i < 49; i++) {
            driver.findElement(By.id("clickMe")).click();
            if (number.equals("1")) {
                Assert.assertEquals(click, label);
            } else if (Integer.valueOf(number) > 1 && Integer.valueOf(number) < 5) {
                Assert.assertEquals(click + "y", label);
            } else {
                Assert.assertEquals(click + "ov", label);
            }
        }

    }

}
