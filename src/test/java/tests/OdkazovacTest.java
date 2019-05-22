package tests;

import base.TestBase;
import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.Timestamp;

public class OdkazovacTest extends TestBase {

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/odkazovac.php");
    }

    @Test
    public void itShouldAddNewMessage() throws InterruptedException {
        int pocetOdkazov = Integer.valueOf(driver.findElement(By.cssSelector("h3.sin-header span")).getText());
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        String title = titleWithTimestamp();
        String author = person.getFirstName() + " " + person.getLastName();
        String body = "Sem by isiel text";

        enterNoteData(title, author, body);
        submitNote();

        //ul.list-of-sins li:last-child
        checkNoteInList(title, pocetOdkazov);
        getLastNoteFromList().click();
        //overim detail zaznamu
        Thread.sleep(1000);

        checkNoteDetail(title, author, body);
    }

    @Test
    public void itShouldAddNewMessageWithHashtaghs() throws InterruptedException {
        int pocetOdkazov = Integer.valueOf(driver.findElement(By.cssSelector("h3.sin-header span")).getText());
        String title = titleWithTimestamp();
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        String author = person.getFirstName() + " " + person.getLastName();
        String body = "Sem by isiel text pre hestegove spravy";

        enterNoteData(title, author, body);
        addTags();
        submitNote();
        checkNoteInList(title, pocetOdkazov);
        getLastNoteFromList().click();
        //overim detail zaznamu
        Thread.sleep(1000);
        checkNoteDetail(title, author, body);

    }

    private void enterNoteData(String title, String author, String body) {
        driver.findElement(By.xpath("//input[1]")).sendKeys(title);
        driver.findElement(By.xpath("//input[2]")).sendKeys(author);
        driver.findElement(By.xpath("//textarea")).sendKeys(body);
    }

    private void submitNote() {
        driver.findElement(By.xpath("//button[contains(text(), '+')]")).click();
    }

    private WebElement getLastNoteFromList() {
        return driver.findElement(By.cssSelector("ul.list-of-sins > li:last-child"));
    }

    private void checkNoteDetail(String title, String author, String body) {
        WebElement detail = driver.findElement(By.cssSelector("div.content"));
        Assert.assertEquals(title, getDescriptionLink(detail, "h4.title").getText());
        Assert.assertEquals(author, getDescriptionLink(detail, "h4.recipent").getText());
        Assert.assertEquals(body, getDescriptionLink(detail, "p").getText());
    }

    private void checkNoteInList(String title, int pocetOdkazov) {
        WebElement listItem = getLastNoteFromList();
        //overim ze sa pridal novy zaznam do zoznamu
        Assert.assertTrue(listItem.getText().contains(title));
        //overenie linku
        Assert.assertTrue(getDescriptionLink(listItem, "div.description a").isDisplayed());
        Assert.assertEquals("detail", getDescriptionLink(listItem, "div.description a").getText());
        Assert.assertEquals(
                Integer.valueOf(pocetOdkazov + 1),
                Integer.valueOf(driver.findElement(By.cssSelector("h3.sin-header span")).getText())
        );
    }

    private WebElement getDescriptionLink(WebElement listItem, String s) {
        return listItem.findElement(By.cssSelector(s));
    }

    private void addTags() {
        String[] selectHashtagh = {"sport", "jedlo", "moda", "praca", "martin jakubec"};
        for (String s : selectHashtagh) {
            driver.findElement(By.cssSelector("input[value='" + s + "']"));
        }
    }

    private String titleWithTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return "Titulok s Hashtaghmi " + timestamp;
    }
}


