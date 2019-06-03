package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.SavingsCalculatorPage;

public class SavingsCalculatorTest extends TestBase {

    private SavingsCalculatorPage sCalcPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/savingscalculator.php");
        sCalcPage = new SavingsCalculatorPage(driver);
    }

    @Test
    public void ItShouldEnableButton() {

        String[] selectedFund = sCalcPage.getNameOfFunds();

        sCalcPage.enterValues();

        for (String fundToSelect : selectedFund) {
            sCalcPage.selectFund(fundToSelect);
            Assert.assertTrue(sCalcPage.buttonIsEnabled());
        }
    }

    @Test
    public void ItShouldValidateSumes() {

        String[] selectedFund = sCalcPage.getNameOfFunds();

        sCalcPage.enterValues();

        for (String fundToSelect : selectedFund) {
            sCalcPage.selectFund(fundToSelect);
            Assert.assertTrue(sCalcPage.totalIncomeAssert());
            Assert.assertTrue(sCalcPage.interestIncomeAssert());
        }
    }

    @Test
    public void ItShouldValidateRisk() {

        String[] selectedFund = sCalcPage.getNameOfFunds();

        sCalcPage.enterValues();

        for (String fundToSelect : selectedFund) {
            sCalcPage.selectFund(fundToSelect);
            Assert.assertTrue(sCalcPage.riskLevelAssert());
        }
    }

    @Test
    public void ItShouldDisplayNewEntry() {

        String[] selectedFund = sCalcPage.getNameOfFunds();

        int i = 1;
        for (String fundToSelect : selectedFund) {
            sCalcPage.selectFund(fundToSelect);
            sCalcPage.enterValues();
            sCalcPage.clickOnApplyBtn();

            Assert.assertTrue(sCalcPage.lastEntryDisplayed());
            Assert.assertEquals(fundToSelect, sCalcPage.getFundDescription());
            Assert.assertEquals(String.valueOf(i), sCalcPage.getNumberOfEntries());
            i++;
        }
    }
}
