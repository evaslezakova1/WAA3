package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class GosslingatorTest {

    @Test
    public void itShouldAddOneRyan() {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        //1. otvorit prehliadac - potrebujeme dotiahnut kniznicu Seleniumu:pom.xml dependencies + TAB
        WebDriver driver = new ChromeDriver();
        //2. otvorit stranku - do zatvorky metody .get vlozime adresu do uvodzoviek
        driver.get("http://localhost:81/gosslingator.php");

        //3. kliknut na tlacidlo pridat
        // - v prvej casti si najdem element a v druhej zadam prikaz
        driver.findElement(By.id("addRyan")).click();
        driver.findElement(By.id("removeRyan")).click();
        driver.findElement(By.id("addRyan")).click();


        //4. overit vysledok
        Assert.assertEquals("1", driver.findElement(By.id("ryanCounter")).getText());

        //5. zatvorit prehliadac
        driver.close();
        //6. ukoncit session
        driver.quit();
    }
}
