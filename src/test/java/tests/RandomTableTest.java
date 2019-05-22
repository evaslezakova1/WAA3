package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RandomTableTest extends TestBase {

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/tabulka.php");
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
        for (WebElement tableRow : returnList()) {
            Assert.assertFalse(tableRow.getText().isEmpty());

        }
    }

    @Test
    public void itShouldContainNameForEachRow() {
//        List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
//        for (WebElement tableRow : tableRows) {
//            Assert.assertFalse(tableRow.getText().isEmpty());
//        }
        List<WebElement> tableRows = returnList();
        for (WebElement tableRow : tableRows) {
            Assert.assertFalse(tableRow.findElement(By.xpath("//td[2]")).getText().isEmpty());
        }
    }

    private List<WebElement> returnList() {
        return driver.findElements(By.cssSelector("table tbody tr"));
    }
}
