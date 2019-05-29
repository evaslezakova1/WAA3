package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.KalkulackaPage;

public class KalkulackaTest extends TestBase {

    private KalkulackaPage kalkPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/kalkulacka.php");
        kalkPage = new KalkulackaPage(driver);
    }

    @Test
    public void itShouldCount() {
        String first = "2";
        String second = "3";
        kalkPage.sendFirstInput(first);
        kalkPage.sendSecondInput(second);
        kalkPage.clickOnCount();

        int sucet = Integer.valueOf(first) + Integer.valueOf(second);
        String vysledok = first + "+" + second + " = " + sucet;
        int resultVysledok = Integer.valueOf(kalkPage.getResult());
        Assert.assertEquals(sucet, resultVysledok);

        String zobrazenyVysledok = kalkPage.getZobrazenyVysledok();

        kalkPage.compareResults(vysledok, zobrazenyVysledok);
        kalkPage.compareCountOfResults();
    }

    @Test
    public void itShouldDeduct() {
        String first = "10";
        String second = "3";
        kalkPage.sendFirstInput(first);
        kalkPage.sendSecondInput(second);
        kalkPage.clickOnDeduct();

        int rozdiel = Integer.valueOf(first) - Integer.valueOf(second);
        String vysledok = first + "-" + second + " = " + rozdiel;
        int resultVysledok = Integer.valueOf(kalkPage.getResult());
        Assert.assertEquals(rozdiel, resultVysledok);

        String zobrazenyVysledok = kalkPage.getZobrazenyVysledok();

        kalkPage.compareResults(vysledok, zobrazenyVysledok);
        kalkPage.compareCountOfResults();
    }

    @Test
    public void itShouldMultiply() {
        String first = "10";
        String second = "3";
        kalkPage.sendFirstInput(first);
        kalkPage.sendSecondInput(second);
        kalkPage.clickOnMultiply();

        int nasobok = Integer.valueOf(first) * Integer.valueOf(second);
        String vysledok = first + "x" + second + " = " + nasobok;
        int resultVysledok = Integer.valueOf(kalkPage.getResult());
        Assert.assertEquals(nasobok, resultVysledok);

        String zobrazenyVysledok = kalkPage.getZobrazenyVysledok();

        kalkPage.compareResults(vysledok, zobrazenyVysledok);
        kalkPage.compareCountOfResults();
    }

    @Test
    public void itShouldDivide() {
        String first = "10";
        String second = "2";
        kalkPage.sendFirstInput(first);
        kalkPage.sendSecondInput(second);
        kalkPage.clickOnDivide();

        int podiel = Integer.valueOf(first) / Integer.valueOf(second);
        String vysledok = first + "/" + second + " = " + podiel;
        int resultVysledok = Integer.valueOf(kalkPage.getResult());
        Assert.assertEquals(podiel , resultVysledok);

        String zobrazenyVysledok = kalkPage.getZobrazenyVysledok();

        kalkPage.compareResults(vysledok, zobrazenyVysledok);
        kalkPage.compareCountOfResults();
    }

    @Test
    public void itShouldReset() {
        String first = "10";
        String second = "2";
        kalkPage.sendFirstInput(first);
        kalkPage.sendSecondInput(second);
        kalkPage.clickOnReset();

        kalkPage.assertValuesAfterReset("firstInput");
        kalkPage.assertValuesAfterReset("secondInput");
        kalkPage.ifResultIsDisplayed();

    }




}
