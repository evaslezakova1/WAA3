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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        String title = "Titulok " + timestamp;
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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String title = "Titulok s Hashtaghmi " + timestamp;
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
        Assert.assertEquals(title, detail.findElement(By.cssSelector("h4.title")).getText());
        Assert.assertEquals(author, detail.findElement(By.cssSelector("h4.recipent")).getText());
        Assert.assertEquals(body, detail.findElement(By.cssSelector("p")).getText());
    }

    private void checkNoteInList(String title, int pocetOdkazov){
        WebElement listItem = getLastNoteFromList();
        //overim ze sa pridal novy zaznam do zoznamu
        Assert.assertTrue(listItem.getText().contains(title));
        //overenie linku
        Assert.assertTrue(listItem.findElement(By.cssSelector("div.description a")).isDisplayed());
        Assert.assertEquals("detail", listItem.findElement(By.cssSelector("div.description a")).getText());
        Assert.assertEquals(
                Integer.valueOf(pocetOdkazov + 1),
                Integer.valueOf(driver.findElement(By.cssSelector("h3.sin-header span")).getText())
        );
    }

    private void addTags(){
        String[] selectHashtagh = {"sport", "jedlo", "moda", "praca", "martin jakubec"};
        for (String s : selectHashtagh) {
            driver.findElement(By.cssSelector("input[value='" + s + "']"));
        }
    }
}


