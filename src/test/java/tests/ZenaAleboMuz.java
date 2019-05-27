package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.ConchitaPage;

public class ZenaAleboMuz extends TestBase {
    private ConchitaPage conPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/zenaalebomuz.php");
        conPage = new ConchitaPage(driver);
    }

    @Test
    public void testTheChioce() {

        //otestuje, ze ziadny radiobutton nie je vybraty
        conPage.testButton("//label[1]/input");
        conPage.testButton("//label[2]/input");
        //input[@value='Wurst']
        //label[text()='Zena']/input
        //ctrl+d skopiruje cely riadok
        //vyberie muz a otestuje hlasku Its wurst
        //zaroven otestujte, ze zena ostala nevybrata
        conPage.clickOnMan();
        Assert.assertEquals("It's wurst", conPage.getText());
        conPage.testButton("//label[text()='Zena']/input");

        //otestuje, ze obrazok je zobrazeny
        conPage.isImgDisplayed();
    }


}
