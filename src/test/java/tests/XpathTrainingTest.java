package tests;

//ctrl+alt+l naformatovanie stranky a riadkovania

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class XpathTrainingTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        //1. otvorit prehliadac - potrebujeme dotiahnut kniznicu Seleniumu:pom.xml dependencies + TAB
        driver = new ChromeDriver();
        //2. otvorit stranku - do zatvorky metody .get vlozime adresu do uvodzoviek
        driver.get("http://localhost:81/xpathtrainingcenter.php");
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void clickOnSecond() {
        String button = "Second button";
        driver.findElement(By.xpath("//button[contains(text(),'" + button + "')]")).click();

        String actualMessage = driver.findElement(By.cssSelector(".output h2 span")).getText();
        Assert.assertEquals("you clicked " + button.toLowerCase(), actualMessage);

        //html/body/div/div[1]/div/button[2]
        ///html/body/div[1]/div[4]/div/div/h2/span
        //button[contains(text(), 'Second button'
        // //button[contains(@class, 'btn-warning')] -> xpath
        // //button[contains(text(), 'Warning')]
        // .btn-warning   -> cssSelector
    }

    @Test
    public void enterText() {
        String message = "Eva Slezakova";
        driver.findElement(By.cssSelector("input")).sendKeys(message);
        driver.findElement(By.id("hitme")).click();

        String actualMessage = driver.findElement(By.cssSelector(".output h2 span")).getText();
        Assert.assertEquals("you entered " + message.toLowerCase(), actualMessage);
    }
}
