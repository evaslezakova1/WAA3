package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.RandomPage;

import java.util.List;

public class RandomTableTest extends TestBase {

    private RandomPage rndPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/tabulka.php");
        rndPage = new RandomPage(driver);
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
        for (WebElement tableRow : rndPage.returnList()) {
            Assert.assertFalse(tableRow.getText().isEmpty());
        }
    }

    @Test
    public void itShouldContainNameForEachRow() {
//        List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
//        for (WebElement tableRow : tableRows) {
//            Assert.assertFalse(tableRow.getText().isEmpty());
//        }
        List<WebElement> tableRows = rndPage.returnList();
        for (WebElement tableRow : tableRows) {
            Assert.assertFalse(tableRow.findElement(By.xpath("//td[2]")).getText().isEmpty());
        }
    }


}
