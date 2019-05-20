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
    public void itShouldAddNewMessage() {
        Integer pocetOdkazov = Integer.valueOf(driver.findElement(By.cssSelector("h3.sin-header span")).getText());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        String title = "Titulok " + timestamp;
        //String where = "Komu";
        String body = "Sem by isiel text";
        WebElement button = driver.findElement(By.xpath("//button[contains(text(), '+')]"));

        //ul.list-of-sins li:last-child
        driver.findElement(By.xpath("//input[1]")).sendKeys(title);
        driver.findElement(By.xpath("//input[2]")).sendKeys(person.getFirstName() + " " + person.getLastName());
        driver.findElement(By.xpath("//textarea")).sendKeys(body);
        button.click();

        WebElement listItem = driver.findElement(By.cssSelector("ul.list-of-sins > li:last-child"));
        //overim ze sa pridal novy zaznam do zoznamu
        Assert.assertTrue(listItem.getText().contains(title));
        //overenie linku
        Assert.assertTrue(listItem.findElement(By.cssSelector("div.description a")).isDisplayed());
        Assert.assertEquals("detail", listItem.findElement(By.cssSelector("div.description a")).getText());
        Assert.assertEquals(
                Integer.valueOf(pocetOdkazov + 1),
                Integer.valueOf(driver.findElement(By.cssSelector("h3.sin-header span")).getText())
        );
        listItem.click();
        //overim detail zaznamu
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement detail = driver.findElement(By.cssSelector("div.content"));
        Assert.assertEquals(title, detail.findElement(By.cssSelector("h4.title")).getText());
        Assert.assertEquals(person.getFirstName() + " " + person.getLastName(), detail.findElement(By.cssSelector("h4.recipent")).getText());
        Assert.assertEquals(body, detail.findElement(By.cssSelector("p")).getText());
    }

    @Test
    public void itShouldAddNewMessageWithHashtaghs() throws InterruptedException {
        Integer pocetOdkazov = Integer.valueOf(driver.findElement(By.cssSelector("h3.sin-header span")).getText());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String title = "Titulok s Hashtaghmi " + timestamp;
        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        String body = "Sem by isiel text pre hestegove spravy";
        WebElement button = driver.findElement(By.xpath("//button[contains(text(), '+')]"));

        driver.findElement(By.xpath("//input[1]")).sendKeys(title);
        driver.findElement(By.xpath("//input[2]")).sendKeys(person.getFirstName() + " " + person.getLastName());
        driver.findElement(By.xpath("//textarea")).sendKeys(body);
        driver.findElement(By.cssSelector("input[value='sport']")).click();
        driver.findElement(By.cssSelector("input[value='jedlo']")).click();
        driver.findElement(By.cssSelector("input[value='moda']")).click();
        driver.findElement(By.cssSelector("input[value='praca']")).click();
        driver.findElement(By.cssSelector("input[value='martin jakubec']")).click();
        button.click();

        WebElement listItem = driver.findElement(By.cssSelector("ul.list-of-sins > li:last-child"));
        //overim ze sa pridal novy zaznam do zoznamu
        Assert.assertTrue(listItem.getText().contains(title));
        //overenie linku
        Assert.assertTrue(listItem.findElement(By.cssSelector("div.description a")).isDisplayed());
        Assert.assertEquals("detail", listItem.findElement(By.cssSelector("div.description a")).getText());
        Assert.assertEquals(
                Integer.valueOf(pocetOdkazov + 1),
                Integer.valueOf(driver.findElement(By.cssSelector("h3.sin-header span")).getText())
        );
        listItem.click();
        //overim detail zaznamu

        Thread.sleep(1000);

        WebElement detail = driver.findElement(By.cssSelector("div.content"));
        Assert.assertEquals(title, detail.findElement(By.cssSelector("h4.title")).getText());
        Assert.assertEquals(person.getFirstName() + " " + person.getLastName(), detail.findElement(By.cssSelector("h4.recipent")).getText());
        Assert.assertEquals(body, detail.findElement(By.cssSelector("p")).getText());

    }


}


