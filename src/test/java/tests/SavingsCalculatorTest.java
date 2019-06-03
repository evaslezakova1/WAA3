package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.OdkazovacPage;

public class SavingsCalculatorTest extends TestBase {

//    private SavingsCalculatorPage sCalcPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/savingscalculator.php");
//        sCalcPage = new SavingsCalculatorPage(driver);
    }

    @Test
    public void ItShouldEnableButton() {

        String[] selectedFund = {"Handelsbanken Aktiv 100", "Hoggwart's Fund",
                "Fellowship investment group", "McDuck's safe", "Batman's Cave Development",
                "Death Star real estate", "Tom & Jerry corp"};

        driver.findElement(By.id("oneTimeInvestmentInput")).sendKeys("1000");
        driver.findElement(By.id("yearsInput")).sendKeys("2");
        driver.findElement(By.id("emailInput")).sendKeys("eva.sle@test.com");

        for (String fundToSelect : selectedFund) {
            WebElement fundSelect = driver.findElement(By.cssSelector("select"));
            new Select(fundSelect).selectByVisibleText(fundToSelect);
            Assert.assertTrue(driver.findElement(By.cssSelector(".btn-success")).isEnabled());
        }
    }

    @Test
    public void ItShouldValidateSumes() {

        String[] selectedFund = {"Handelsbanken Aktiv 100", "Hoggwart's Fund",
                "Fellowship investment group", "McDuck's safe", "Batman's Cave Development",
                "Death Star real estate", "Tom & Jerry corp"};

        driver.findElement(By.id("oneTimeInvestmentInput")).sendKeys("1000");
        driver.findElement(By.id("yearsInput")).sendKeys("2");
        driver.findElement(By.id("emailInput")).sendKeys("eva.sle@test.com");

        for (String fundToSelect : selectedFund) {
            WebElement fundSelect = driver.findElement(By.cssSelector("select"));
            new Select(fundSelect).selectByVisibleText(fundToSelect);
            Assert.assertTrue(
                    driver.findElement(By.xpath("//div[contains(@class, 'result')]/div[1]/p")).
                            getAttribute("class").isEmpty());
            Assert.assertTrue(
                    driver.findElement(By.xpath("//div[contains(@class, 'result')]/div[2]/p")).
                            getAttribute("class").isEmpty());
        }

    }

    @Test
    public void ItShouldValidateRisk() {

        String[] selectedFund = {"Handelsbanken Aktiv 100", "Hoggwart's Fund",
                "Fellowship investment group", "McDuck's safe", "Batman's Cave Development",
                "Death Star real estate", "Tom & Jerry corp"};

        driver.findElement(By.id("oneTimeInvestmentInput")).sendKeys("1000");
        driver.findElement(By.id("yearsInput")).sendKeys("2");
        driver.findElement(By.id("emailInput")).sendKeys("eva.sle@test.com");

        for (String fundToSelect : selectedFund) {
            WebElement fundSelect = driver.findElement(By.cssSelector("select"));
            new Select(fundSelect).selectByVisibleText(fundToSelect);
            Assert.assertTrue(
                    driver.findElement(By.xpath("//div[contains(@class, 'result')]/div[3]/p")).
                            getAttribute("class").isEmpty());
        }
    }

    @Test
    public void ItShouldDisplayNewEntry() {

        String[] selectedFund = {"Handelsbanken Aktiv 100", "Hoggwart's Fund",
                "Fellowship investment group", "McDuck's safe", "Batman's Cave Development",
                "Death Star real estate", "Tom & Jerry corp"};


        int i = 1;
        for (String fundToSelect : selectedFund) {
            WebElement fundSelect = driver.findElement(By.cssSelector("select"));
            new Select(fundSelect).selectByVisibleText(fundToSelect);
            driver.findElement(By.id("oneTimeInvestmentInput")).sendKeys("1000");
            driver.findElement(By.id("yearsInput")).sendKeys("2");
            driver.findElement(By.id("emailInput")).sendKeys("eva.sle@test.com");
            driver.findElement(By.cssSelector(".btn-success")).click();

            Assert.assertTrue(driver.findElement(By.cssSelector(".saving-list > li:first-child")).isDisplayed());
            Assert.assertEquals(fundToSelect,
                    driver.findElement(By.cssSelector(".saving-list > li:first-child .fund-description")).getText());
            Assert.assertEquals(String.valueOf(i),
                    driver.findElement(By.cssSelector(" .recent-requests-header span")).getText());
            i++;
        }

    }
}
