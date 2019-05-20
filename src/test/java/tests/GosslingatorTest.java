package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GosslingatorTest extends TestBase {


    @Before
    public void openPage() {
        driver.get(BASE_URL + "/gosslingator.php");
    }

    @Test
    public void itShouldAddOneRyan() {
        //3. kliknut na tlacidlo pridat
        // - v prvej casti si najdem element a v druhej zadam prikaz
        addRyan();

        //4. overit vysledok
        Assert.assertEquals("1", getRyanCountLabel());
        Assert.assertEquals("ryan", getRyanTitleLable());
    }

    @Test
    public void itShouldAddTwoRyans() {
        //3. kliknut na tlacidlo pridat
        // - v prvej casti si najdem element a v druhej zadam prikaz
        addRyan();
        addRyan();
        //4. overit vysledok
        Assert.assertEquals("2", getRyanCountLabel());
        System.out.println("Number of Ryans: " + getRyanCountLabel());
        System.out.println(getRyanTitleLable());
        Assert.assertEquals("ryans", getRyanTitleLable());
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
        Assert.assertEquals(0, getNumberOfRyanImages());
        for (int i = 0; i < 50; i++) {
            addRyan();

            //musim int zmenit na string
            Assert.assertEquals(String.valueOf(i + 1), getRyanCounterNumber());
            //if - do zatvorky dam podmienku a do {} vlozim akciu
            // ak chce pokracovat s else, tak hned else a do {} vlozim akciu
            //alternativa podmienky (i + 1 == 1)
            if (i < 1) {
                Assert.assertEquals("ryan", getRyanTitleLable());
                //alternativa podmienky (i + 1 >= 2) -> samostatne if nie vetvene
            } else {
                Assert.assertEquals("ryans", getRyanTitleLable());
            }
            Assert.assertEquals(i+1, getNumberOfRyanImages());
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
        while (!getRyanCounterNumber().equals("50") && clicks < clicksLimit) {
            addRyan();
            clicks++;
        }
    }

    @Test
    public void itShouldDislpayNoRyanOnPageOpen() {
        // findElements najde vsetky elementy splnajuce podmienku a vypise ich do Listu
        // .size() je velkost zoznamu - kolko elementov je v Liste
        Assert.assertEquals(0, getNumberOfRyanImages());
    }

    //vytvaranie metod
    //private - iba v tejto triede sa da pouzit
    //void - metoda nevracia ziadnu hodnotu
    private void addRyan(){
        WebElement ryanButton =  driver.findElement(By.id("addRyan"));
        ryanButton.click();
    }

    private String getRyanCounterNumber(){
        return driver.findElement(By.id("ryanCounter")).getText();
    }

    private String getRyanCountLabel(){
        return driver.findElement(By.cssSelector("div.ryan-counter h2")).getText();
    }

    private String getRyanTitleLable(){
        return driver.findElement(By.cssSelector("div.ryan-counter h3")).getText();
    }

    private int getNumberOfRyanImages(){
        return driver.findElements(By.cssSelector("img")).size();
    }
}
