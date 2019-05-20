package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class ZenaAleboMuz extends TestBase {

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/zenaalebomuz.php");
    }

    @Test
    public void testTheChioce() {

        //otestuje, ze ziadny radiobutton nie je vybraty
        Assert.assertFalse(driver.findElement(By.xpath("//label[1]/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[2]/input")).isSelected());
        //input[@value='Wurst']
        //label[text()='Zena']/input
        //ctrl+d skopiruje cely riadok

        //vyberie muz a otestuje hlasku Its wurst
        //zaroven otestujte, ze zena ostala nevybrata
        driver.findElement(By.xpath("//input[@value='wurst']")).click();
        Assert.assertEquals("It's wurst", driver.findElement(By.cssSelector("h1.description")).getText());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Zena']/input")).isSelected());

        //otestuje, ze obrazok je zobrazeny
        Assert.assertTrue(driver.findElement(By.xpath("//img")).isDisplayed());
    }
}
