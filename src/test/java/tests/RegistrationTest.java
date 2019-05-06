package tests;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver74.exe");
        //1. otvorit prehliadac - potrebujeme dotiahnut kniznicu Seleniumu:pom.xml dependencies + TAB
        driver = new ChromeDriver();
        //2. otvorit stranku - do zatvorky metody .get vlozime adresu do uvodzoviek
        driver.get("http://localhost:81/registracia.php");
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void itShouldRegisterValid() {
        Fairy fairy = Fairy.create();
        Person person = fairy.person();

        driver.findElement(By.name("email")).sendKeys(person.getEmail());
        driver.findElement(By.name("name")).sendKeys(person.getFirstName());
        driver.findElement(By.name("surname")).sendKeys(person.getLastName());
        driver.findElement(By.name("password")).sendKeys(person.getPassword());
        driver.findElement(By.name("password-repeat")).sendKeys(person.getPassword());
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.cssSelector(".btn-success")).click();

        //overit cez boolean, ci je zobrazeny element
        Assert.assertTrue(driver.findElement(By.cssSelector(".alert-success")).isDisplayed());
        Assert.assertEquals("Registracia uspesna!",
                driver.findElement(By.cssSelector(".alert")).getText());
    }

    @Test
    public void itShouldRegisterInvalid() {
        Fairy fairy = Fairy.create();
        Person person = fairy.person();

        driver.findElement(By.name("email")).sendKeys(person.getEmail());
        driver.findElement(By.name("name")).sendKeys(person.getFirstName());
        driver.findElement(By.name("surname")).sendKeys(person.getLastName());
        driver.findElement(By.name("password")).sendKeys(person.getPassword());
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.cssSelector(".btn-success")).click();

        //overit cez boolean, ci je zobrazeny element
        Assert.assertTrue(driver.findElement(By.cssSelector(".alert-danger")).isDisplayed());
        Assert.assertEquals("Registracia neuspesna!",
                driver.findElement(By.cssSelector(".alert")).getText());
    }
}