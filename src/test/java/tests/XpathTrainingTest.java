package tests;

//ctrl+alt+l naformatovanie stranky a riadkovania

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.XpathPage;

public class XpathTrainingTest extends TestBase {
    private XpathPage xPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/xpathtrainingcenter.php");
        xPage = new XpathPage(driver);
    }

    @Test
    public void clickOnSecond() {
        String button = "Second button";
        xPage.clickButton(button);
        Assert.assertEquals("you clicked " + button.toLowerCase(), xPage.getMessage());

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
        Assert.assertEquals("you entered " + message.toLowerCase(), xPage.getMessage());
    }

    @Test
    public void itShouldSelectEachButton() {
        String message = "you clicked ";
        String[] buttonSelect = {"First one", "Second button", "Next button", "One more button", "Danger",
                "Success", "Warning", "Hit me!"};
        for (String s : buttonSelect) {
            xPage.clickButton(s);
            if (s.equals("Hit me!")) {
                Assert.assertEquals("you entered", xPage.getMessage());
            } else {
                Assert.assertEquals(message + s.toLowerCase(), xPage.getMessage());
            }
        }
    }

    @Test
    public void itShouldChooseEachValueSelectOne() {
        String message = "you have chosen moznost ";
        WebElement valueSelect = driver.findElement(By.xpath("//select[1]"));

        xPage.selectLoop(valueSelect, message);

        String optionZero = "vyber si moznost";
        xPage.selectZero(optionZero, valueSelect);
    }

    @Test
    public void itShouldChooseEachValueSelectTwo() {
        String message = xPage.chosen + "option ";
        WebElement valueSelect = driver.findElement(By.xpath("//select[2]"));

        xPage.selectLoop(valueSelect, message);

        String optionZero = "vyber si option";
        xPage.selectZero(optionZero, valueSelect);
    }
}
