package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OdkazovacTest extends TestBase {

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/odkazovac.php");
    }

    @Test
    public void itShouldAddNewMessage() {
        Integer pocetOdkazov = Integer.valueOf(driver.findElement(By.cssSelector("h3.sin-header span")).getText());
        String title = "Titulok";
        String where = "Komu";
        String body = "Sem by isiel text";
        WebElement button = driver.findElement(By.xpath("//button[contains(text(), '+')]"));

        //ul.list-of-sins li:last-child
        driver.findElement(By.xpath("//input[1]")).sendKeys(title);
        driver.findElement(By.xpath("//input[2]")).sendKeys(where);
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
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        WebElement detail = driver.findElement(By.cssSelector("div.content"));
        Assert.assertEquals(title, detail.findElement(By.cssSelector("h4.title")).getText());
        Assert.assertEquals(where, detail.findElement(By.cssSelector("h4.recipent")).getText());
        Assert.assertEquals(body, detail.findElement(By.cssSelector("p")).getText());
    }


}


