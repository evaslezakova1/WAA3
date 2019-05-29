package tests;

import base.TestBase;
import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import models.Note;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.OdkazovacPage;

public class OdkazovacTest extends TestBase {

    private OdkazovacPage odkPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/odkazovac.php");
        odkPage = new OdkazovacPage(driver);
    }

    @Test
    public void itShouldAddNewMessage(){
        int pocetOdkazov = Integer.valueOf(odkPage.pocetOdkazov());
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        String title = odkPage.titleWithTimestamp();
        String author = person.getFirstName() + " " + person.getLastName();
        String body = "Sem by isiel text";

        Note noteToAdd = new Note(title, author, body);
        odkPage.enterNoteData(noteToAdd);
        odkPage.submitNote();

        //ul.list-of-sins li:last-child
        odkPage.checkNoteInList(title, pocetOdkazov);
        odkPage.getLastNoteFromList().click();
        //overim detail zaznamu

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.content")));


        odkPage.checkNoteDetail(title, author, body);
    }

    @Test
    public void itShouldAddNewMessageWithHashtaghs(){
        int pocetOdkazov = Integer.valueOf(odkPage.pocetOdkazov());

        String title = odkPage.titleWithTimestamp();
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        String author = person.getFirstName() + " " + person.getLastName();
        String body = "Sem by isiel text pre hestegove spravy";

        Note noteToAdd = new Note(title, author, body);
        odkPage.enterNoteData(noteToAdd);
        odkPage.addTags();
        odkPage.submitNote();
        odkPage.checkNoteInList(noteToAdd.getTitle(), pocetOdkazov);
        odkPage.getLastNoteFromList().click();
        //overim detail zaznamu

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.content")));

        odkPage.checkNoteDetail(noteToAdd.getTitle(), noteToAdd.getAuthor(), noteToAdd.getMessage());

    }


}


