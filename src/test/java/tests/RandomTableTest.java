package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


public class RandomTableTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver74.exe");
        //1. otvorit prehliadac - potrebujeme dotiahnut kniznicu Seleniumu:pom.xml dependencies + TAB
        driver = new ChromeDriver();
        //2. otvorit stranku - do zatvorky metody .get vlozime adresu do uvodzoviek
        driver.get("http://localhost:81/tabulka.php");
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findLastRow() {
        //vypisem posledny riadok
        System.out.println(driver.findElement(By.xpath("//table/tbody/tr[last()]")).getText());
        //vypisem meno z predposledneho riadku
        System.out.println(driver.findElement(By.xpath("//table/tbody/tr[last()-1]/td[2]")).getText());
    }

    @Test
    public void listOfAllRowInTable() {
        List<WebElement> tableRows = driver.findElements(By.cssSelector("table tbody tr"));
        for (WebElement tableRow : tableRows) {
            Assert.assertFalse(tableRow.getText().isEmpty());

        }
    }

    @Test
    public void itShouldContainNameForEachRow() {
//        List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
//        for (WebElement tableRow : tableRows) {
//            Assert.assertFalse(tableRow.getText().isEmpty());
//        }
        List<WebElement> tableRows = driver.findElements(By.cssSelector("table tbody tr"));
        for (WebElement tableRow : tableRows) {
            Assert.assertFalse(tableRow.findElement(By.xpath("//td[2]")).getText().isEmpty());
        }
    }
}
