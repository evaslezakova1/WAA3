package tests;

import base.TestBase;
import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class RegistrationTest extends TestBase {

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/registracia.php");
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