package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.GosslingatorPage;

public class GosslingatorTest extends TestBase {
    private GosslingatorPage gossPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/gosslingator.php");
        gossPage = new GosslingatorPage(driver);
    }

    @Test
    public void itShouldAddOneRyan() {

        //3. kliknut na tlacidlo pridat
        // - v prvej casti si najdem element a v druhej zadam prikaz
        gossPage.addRyan();

        //4. overit vysledok
        Assert.assertEquals("1", gossPage.getRyanCountLabel());
        Assert.assertEquals("ryan", gossPage.getRyanTitleLable());
    }

    @Test
    public void itShouldAddTwoRyans() {
        //3. kliknut na tlacidlo pridat
        // - v prvej casti si najdem element a v druhej zadam prikaz
        gossPage.addRyan();
        gossPage.addRyan();

        //4. overit vysledok
        Assert.assertEquals("2", gossPage.getRyanCountLabel());
        System.out.println("Number of Ryans: " + gossPage.getRyanCountLabel());
        System.out.println(gossPage.getRyanTitleLable());
        Assert.assertEquals("ryans", gossPage.getRyanTitleLable());
    }

    @Test
    public void itShouldDisplayTitle() {
        System.out.println(driver.findElement(By.cssSelector(".ryan-title")).getText());
        Assert.assertEquals("GOSLINGATE ME", driver.findElement(By.cssSelector(".ryan-title")).getText());
        //5. zatvorit prehliadac
    }

    @Test
    public void itShouldAddFiftyRyans() {
//      ALT + J oznacenie kazdeho dalsieho vyskytu
        // for cyclus: zadefinujem si premennu, ktorej urcim hodnotu 0: (int i=0;
        // urcim, kolkokrat ma opakovat cyklus (aj prve cislo je iteracia): i<50;
        // zvysim i o 1: i++)
        // do tela vlozim prikaz, ktory sa ma cyklit: {addRyanButton.click();}
        // skratka je fori + tab
        Assert.assertEquals(0, gossPage.getNumberOfRyanImages());
        for (int i = 0; i < 50; i++) {
            gossPage.addRyan();

            //musim int zmenit na string
            Assert.assertEquals(String.valueOf(i + 1), gossPage.getRyanCounterNumber());
            //if - do zatvorky dam podmienku a do {} vlozim akciu
            // ak chce pokracovat s else, tak hned else a do {} vlozim akciu
            //alternativa podmienky (i + 1 == 1)
            if (i < 1) {
                Assert.assertEquals("ryan", gossPage.getRyanTitleLable());
                //alternativa podmienky (i + 1 >= 2) -> samostatne if nie vetvene
            } else {
                Assert.assertEquals("ryans", gossPage.getRyanTitleLable());
            }
            Assert.assertEquals(i + 1, gossPage.getNumberOfRyanImages());
        }
        String alertMessage = driver.findElement(By.cssSelector("h1.tooManyRyans")).getText();
        System.out.println(alertMessage);
        Assert.assertEquals("NUMBER OF\n" +
                "RYANS\n" +
                "IS TOO DAMN\n" +
                "HIGH", alertMessage);

    }

    @Test
    public void usingWhileCycle() {
        int clicksLimit = 30;
        int clicks = 0;
        // while cyclus - vykona sa vzdy, ak je podmienka true
        // pre while sa rozhodneme, ak nevieme jednoznacne povedat, do kedy by for bezal
        // zaroven = &&
        // alebo = ||
        while (!gossPage.getRyanCounterNumber().equals("50") && clicks < clicksLimit) {
            gossPage.addRyan();
            clicks++;
        }
    }

    @Test
    public void itShouldDislpayNoRyanOnPageOpen() {
        // findElements najde vsetky elementy splnajuce podmienku a vypise ich do Listu
        // .size() je velkost zoznamu - kolko elementov je v Liste
        Assert.assertEquals(0, gossPage.getNumberOfRyanImages());
    }
}
