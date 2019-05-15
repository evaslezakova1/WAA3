package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClickMeBaby {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver74.exe");
        //1. otvorit prehliadac - potrebujeme dotiahnut kniznicu Seleniumu:pom.xml dependencies + TAB
        driver = new ChromeDriver();
        //2. otvorit stranku - do zatvorky metody .get vlozime adresu do uvodzoviek
        driver.get("http://ajtyvit-app.westeurope.cloudapp.azure.com:8080/clickmebaby.php");
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void itShouldAssertClickText(){

        String number = driver.findElement(By.id("clicks")).getText();
        String label = driver.findElement(By.cssSelector("p.description")).getText();
        String click = "klik";

        for (int i = 0; i < 49; i++) {
            driver.findElement(By.id("clickMe")).click();
            if (number.equals("1")){
                Assert.assertEquals(click, label);
            } else if (Integer.valueOf(number) > 1 && Integer.valueOf(number) < 5) {
                Assert.assertEquals(click + "y", label);
            } else {
                Assert.assertEquals(click + "ov", label);
            }
        }
        
    }

}
