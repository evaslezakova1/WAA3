package tests;

import base.TestBase;
import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.OdkazovacPage;

public class OdkazovacTest extends TestBase {

    private OdkazovacPage odkPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/odkazovac.php");
        odkPage = new OdkazovacPage(driver);
    }

    @Test
    public void itShouldAddNewMessage() throws InterruptedException {
        int pocetOdkazov = Integer.valueOf(driver.findElement(By.cssSelector("h3.sin-header span")).getText());
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        String title = odkPage.titleWithTimestamp();
        String author = person.getFirstName() + " " + person.getLastName();
        String body = "Sem by isiel text";

        odkPage.enterNoteData(title, author, body);
        odkPage.submitNote();

        //ul.list-of-sins li:last-child
        odkPage.checkNoteInList(title, pocetOdkazov);
        odkPage.getLastNoteFromList().click();
        //overim detail zaznamu
        Thread.sleep(1000);

        odkPage.checkNoteDetail(title, author, body);
    }

    @Test
    public void itShouldAddNewMessageWithHashtaghs() throws InterruptedException {
        int pocetOdkazov = Integer.valueOf(driver.findElement(By.cssSelector("h3.sin-header span")).getText());
        String title = odkPage.titleWithTimestamp();
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        String author = person.getFirstName() + " " + person.getLastName();
        String body = "Sem by isiel text pre hestegove spravy";

        odkPage.enterNoteData(title, author, body);
        odkPage.addTags();
        odkPage.submitNote();
        odkPage.checkNoteInList(title, pocetOdkazov);
        odkPage.getLastNoteFromList().click();
        //overim detail zaznamu
        Thread.sleep(1000);
        odkPage.checkNoteDetail(title, author, body);

    }
}


