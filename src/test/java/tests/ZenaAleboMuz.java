package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ZenaAleboMuz {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver74.exe");
        //1. otvorit prehliadac - potrebujeme dotiahnut kniznicu Seleniumu:pom.xml dependencies + TAB
        driver = new ChromeDriver();
        //2. otvorit stranku - do zatvorky metody .get vlozime adresu do uvodzoviek
        driver.get("http://localhost:81/zenaalebomuz.php");
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void testTheChioce() {

        //otestuje, ze ziadny radiobutton nie je vybraty
        Assert.assertFalse(driver.findElement(By.xpath("//label[1]/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[2]/input")).isSelected());
        //input[@value='Wurst']
        //label[text()='Zena']/input
        //ctrl+d skopiruje cely riadok

        //vyberie muz a otestuje hlasku Its wurst
        //zaroven otestujte, ze zena ostala nevybrata
        driver.findElement(By.xpath("//input[@value='wurst']")).click();
        Assert.assertEquals("It's wurst", driver.findElement(By.cssSelector("h1.description")).getText());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Zena']/input")).isSelected());

        //otestuje, ze obrazok je zobrazeny
        Assert.assertTrue(driver.findElement(By.xpath("//img")).isDisplayed());
    }
}
