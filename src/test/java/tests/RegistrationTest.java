package tests;

import base.TestBase;
import com.devskiller.jfairy.producer.person.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.RegistrationPage;

public class RegistrationTest extends TestBase {

    private RegistrationPage regPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/registracia.php");
        regPage = new RegistrationPage(driver);
    }

    @Test
    public void itShouldRegisterValid() {
        Person person = regPage.getPerson();

        regPage.sendLoginData("email", person.getEmail());
        regPage.sendLoginData("name", person.getFirstName());
        regPage.sendLoginData("surname", person.getLastName());
        regPage.sendLoginData("password", person.getPassword());
        regPage.sendLoginData("password-repeat", person.getPassword());
        regPage.clickElementOnPage(By.id("checkbox"));
        regPage.clickElementOnPage(By.cssSelector(".btn-success"));

        //overit cez boolean, ci je zobrazeny element
        Assert.assertTrue(regPage.isDisplayed(".alert-success"));
        Assert.assertEquals("Registracia uspesna!",
                regPage.getText());
    }

    @Test
    public void itShouldRegisterInvalid() {
        Person person = regPage.getPerson();

        regPage.sendLoginData("email", person.getEmail());
        regPage.sendLoginData("name", person.getFirstName());
        regPage.sendLoginData("surname", person.getLastName());
        regPage.sendLoginData("password", person.getPassword());
        regPage.clickElementOnPage(By.id("checkbox"));
        regPage.clickElementOnPage(By.cssSelector(".btn-success"));

        //overit cez boolean, ci je zobrazeny element
        Assert.assertTrue(regPage.isDisplayed(".alert-danger"));
        Assert.assertEquals("Registracia neuspesna!",
                regPage.getText());
    }


}