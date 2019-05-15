package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GosslingatorTest {
    private WebDriver driver;
    private WebElement addRyanButton;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver74.exe");
        //1. otvorit prehliadac - potrebujeme dotiahnut kniznicu Seleniumu:pom.xml dependencies + TAB
        driver = new ChromeDriver();
        //2. otvorit stranku - do zatvorky metody .get vlozime adresu do uvodzoviek
        driver.get("http://localhost:81/gosslingator.php");
        addRyanButton = driver.findElement(By.id("addRyan"));
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void itShouldAddOneRyan() {
        //3. kliknut na tlacidlo pridat
        // - v prvej casti si najdem element a v druhej zadam prikaz
        addRyanButton.click();
        driver.findElement(By.id("removeRyan")).click();
        addRyanButton.click();

        //4. overit vysledok
        Assert.assertEquals("1", driver.findElement(By.cssSelector("div.ryan-counter h2")).getText());
        Assert.assertEquals("ryan", driver.findElement(By.cssSelector("div.ryan-counter h3")).getText());
    }

    @Test
    public void itShouldAddTwoRyans() {
        //3. kliknut na tlacidlo pridat
        // - v prvej casti si najdem element a v druhej zadam prikaz
        addRyanButton.click();
        addRyanButton.click();

        //4. overit vysledok
        String ryanCount = driver.findElement(By.cssSelector("div.ryan-counter h2")).getText();
        String ryanTitle = driver.findElement(By.cssSelector("div.ryan-counter h3")).getText();
        Assert.assertEquals("2", ryanCount);
        System.out.println("Number of Ryans: " + ryanCount);
        System.out.println(ryanTitle);
        Assert.assertEquals("ryans", ryanTitle);
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
        Assert.assertEquals(0, driver.findElements(By.cssSelector("img")).size());
        for (int i = 0; i < 50; i++) {
            addRyanButton.click();
            String actualNrRyans = driver.findElement(By.id("ryanCounter")).getText();
            //musim int zmenit na string
            Assert.assertEquals(String.valueOf(i + 1), actualNrRyans);
            String actualLabelOfRyan = driver.findElement(By.cssSelector("div.ryan-counter h3")).getText();
            //if - do zatvorky dam podmienku a do {} vlozim akciu
            // ak chce pokracovat s else, tak hned else a do {} vlozim akciu
            //alternativa podmienky (i + 1 == 1)
            if (i < 1) {
                Assert.assertEquals("ryan", actualLabelOfRyan);
                //alternativa podmienky (i + 1 >= 2) -> samostatne if nie vetvene
            } else {
                Assert.assertEquals("ryans", actualLabelOfRyan);
            }
            Assert.assertEquals(i+1, driver.findElements(By.cssSelector("img")).size());
        }
        String alertMessage = driver.findElement(By.cssSelector("h1.tooManyRyans")).getText();
        System.out.println(alertMessage);
        Assert.assertEquals("NUMBER OF\n" +
                "RYANS\n" +
                "IS TOO DAMN\n" +
                "HIGH", alertMessage);
        driver.findElement(By.cssSelector("img")).click();
    }

    @Test
    public void usingWhileCycle() {
        String actualNrRyans = driver.findElement(By.id("ryanCounter")).getText();
        int clicksLimit = 30;
        int clicks = 0;
        // while cyclus - vykona sa vzdy, ak je podmienka true
        // pre while sa rozhodneme, ak nevieme jednoznacne povedat, do kedy by for bezal
        // zaroven = &&
        // alebo = ||
        while (!actualNrRyans.equals("50") && clicks < clicksLimit) {
            addRyanButton.click();
            actualNrRyans = driver.findElement(By.id("ryanCounter")).getText();
            clicks++;
        }
    }

    @Test
    public void itShouldDislpayNoRyanOnPageOpen() {
        // findElements najde vsetky elementy splnajuce podmienku a vypise ich do Listu
        // .size() je velkost zoznamu - kolko elementov je v Liste
        Assert.assertEquals(0, driver.findElements(By.cssSelector("img")).size());
    }
}
