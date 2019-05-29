package pages;

import models.Note;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Timestamp;

public class OdkazovacPage {

    private WebDriver pageDriver;

    public OdkazovacPage(WebDriver driver) {
        this.pageDriver = driver;
    }

    public void enterNoteData(Note note) {
        titleInput(note.getTitle(), "//input[1]");
        titleInput(note.getAuthor(), "//input[2]");
        titleInput(note.getMessage(), "//textarea");
    }

    private void titleInput(String title, String s) {
        pageDriver.findElement(By.xpath(s)).sendKeys(title);
    }

    public void submitNote() {
        pageDriver.findElement(By.xpath("//button[contains(text(), '+')]")).click();
    }

    public WebElement getLastNoteFromList() {
        return pageDriver.findElement(By.cssSelector("ul.list-of-sins > li:last-child"));
    }

    public void checkNoteDetail(String title, String author, String body) {
        WebElement detail = pageDriver.findElement(By.cssSelector("div.content"));
        Assert.assertEquals(title, getDescriptionLink(detail, "h4.title").getText());
        Assert.assertEquals(author, getDescriptionLink(detail, "h4.recipent").getText());
        Assert.assertEquals(body, getDescriptionLink(detail, "p").getText());
    }

    public void checkNoteInList(String title, int pocetOdkazov) {
        WebElement listItem = getLastNoteFromList();
        //overim ze sa pridal novy zaznam do zoznamu
        Assert.assertTrue(listItem.getText().contains(title));
        //overenie linku
        Assert.assertTrue(getDescriptionLink(listItem, "div.description a").isDisplayed());
        Assert.assertEquals("detail", getDescriptionLink(listItem, "div.description a").getText());
        Assert.assertEquals(
                Integer.valueOf(pocetOdkazov + 1),
                Integer.valueOf(pageDriver.findElement(By.cssSelector("h3.sin-header span")).getText())
        );
    }

    private WebElement getDescriptionLink(WebElement listItem, String s) {
        return listItem.findElement(By.cssSelector(s));
    }

    public void addTags() {
        String[] selectHashtagh = {"sport", "jedlo", "moda", "praca", "martin jakubec"};
        for (String s : selectHashtagh) {
            pageDriver.findElement(By.cssSelector("input[value='" + s + "']"));
        }
    }

    public String titleWithTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return "Titulok s Hashtaghmi " + timestamp;
    }

    public String pocetOdkazov() {
        return pageDriver.findElement(By.cssSelector("h3.sin-header span")).getText();
    }
}
