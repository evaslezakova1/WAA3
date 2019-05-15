package tests;

//ctrl+alt+l naformatovanie stranky a riadkovania

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class XpathTrainingTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver74.exe");
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

    @Test
    public void itShouldSelectEachButton() {
        String message = "you clicked ";
        String[] buttonSelect = {"First one", "Second button", "Next button", "One more button", "Danger",
                "Success", "Warning", "Hit me!"};
        for (String s : buttonSelect) {
            driver.findElement(By.xpath("//button[contains(text(),'" + s + "')]")).click();
            String actualMessage = driver.findElement(By.cssSelector(".output h2 span")).getText();
            if (s.equals("Hit me!")) {
                Assert.assertEquals("you entered", actualMessage);
            } else {
                Assert.assertEquals(message + s.toLowerCase(), actualMessage);
            }
        }
    }

    @Test
    public void itShouldChooseEachValueSelectOne() {
        String message = "you have chosen moznost ";
        WebElement valueSelect = driver.findElement(By.xpath("//select[1]"));
        WebElement messageFinder = driver.findElement(By.cssSelector(".output h2 span"));
        for (int i = 1; i < 7; i++) {
            new Select(valueSelect).selectByIndex(i);
            String actualMessage = messageFinder.getText();
            Assert.assertEquals(message + i, actualMessage);
        }
        String optionZero = "vyber si moznost";
        new Select(valueSelect).selectByVisibleText(optionZero);
        Assert.assertEquals("you have chosen " + optionZero, messageFinder.getText());
    }

    @Test
    public void itShouldChooseEachValueSelectTwo() {
        String message = "you have chosen option ";
        WebElement valueSelect = driver.findElement(By.xpath("//select[2]"));
        WebElement messageFinder = driver.findElement(By.cssSelector(".output h2 span"));
        for (int i = 1; i < 6; i++) {
            new Select(valueSelect).selectByIndex(i);
            String actualMessage = messageFinder.getText();
            Assert.assertEquals(message + i, actualMessage);
        }
        String optionZero = "vyber si option";
        new Select(valueSelect).selectByVisibleText(optionZero);
        Assert.assertEquals("you have chosen " + optionZero, messageFinder.getText());
    }

}
