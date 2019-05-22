package pages;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver pageDriver;

    public RegistrationPage(WebDriver driver) {
        this.pageDriver = driver;
    }

    public void clickElementOnPage(By checkbox) {
        pageDriver.findElement(checkbox).click();
    }

    public String getText() {
        return pageDriver.findElement(By.cssSelector(".alert")).getText();
    }

    public boolean isDisplayed(String s) {
        return pageDriver.findElement(By.cssSelector(s)).isDisplayed();
    }

    public Person getPerson() {
        Fairy fairy = Fairy.create();
        return fairy.person();
    }
}
