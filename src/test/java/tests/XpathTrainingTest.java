package tests;

//ctrl+alt+l naformatovanie stranky a riadkovania

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class XpathTrainingTest extends TestBase {
    private String chosen = "you have chosen ";

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/xpathtrainingcenter.php");
    }

    @Test
    public void clickOnSecond() {
        String button = "Second button";
        clickButton(button);
        Assert.assertEquals("you clicked " + button.toLowerCase(), getMessage());

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
        Assert.assertEquals("you entered " + message.toLowerCase(), getMessage());
    }

    @Test
    public void itShouldSelectEachButton() {
        String message = "you clicked ";
        String[] buttonSelect = {"First one", "Second button", "Next button", "One more button", "Danger",
                "Success", "Warning", "Hit me!"};
        for (String s : buttonSelect) {
            clickButton(s);
            if (s.equals("Hit me!")) {
                Assert.assertEquals("you entered", getMessage());
            } else {
                Assert.assertEquals(message + s.toLowerCase(), getMessage());
            }
        }
    }

    @Test
    public void itShouldChooseEachValueSelectOne() {
        String message = "you have chosen moznost ";
        WebElement valueSelect = driver.findElement(By.xpath("//select[1]"));

        selectLoop(valueSelect, message);

        String optionZero = "vyber si moznost";
        selectZero(optionZero, valueSelect);
    }

    @Test
    public void itShouldChooseEachValueSelectTwo() {
        String message = chosen + "option ";
        WebElement valueSelect = driver.findElement(By.xpath("//select[2]"));

        selectLoop(valueSelect, message);

        String optionZero = "vyber si option";
        selectZero(optionZero, valueSelect);
    }

    private void clickButton(String s){
        driver.findElement(By.xpath("//button[contains(text(),'" + s + "')]")).click();
    }

    private String getMessage(){
        return driver.findElement(By.cssSelector(".output h2 span")).getText();
    }

    private void selectLoop(WebElement valueSelect, String message){
        for (int i = 1; i < 6; i++) {
            new Select(valueSelect).selectByIndex(i);
            Assert.assertEquals(message + i, getMessage());
        }
    }

    private void selectZero(String optionZero, WebElement valueSelect){
        new Select(valueSelect).selectByVisibleText(optionZero);
        Assert.assertEquals(chosen + optionZero, getMessage());
        }

}
