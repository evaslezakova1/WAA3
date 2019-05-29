package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class KalkulackaPage {
    private WebDriver pageDriver;

    public KalkulackaPage(WebDriver driver) {
        this.pageDriver = driver;
        PageFactory.initElements(pageDriver, this);
    }

    @FindBy(id = "result")
    private WebElement findResult;

    @FindBy(id = "firstInput")
    private WebElement findFirstInput;

    @FindBy(id = "secondInput")
    private WebElement findSecondInput;

    @FindBy(id = "reset")
    private WebElement findReset;

    @FindBy(id = "divide")
    private WebElement findDivide;

    @FindBy(id = "multiply")
    private WebElement findMultiply;

    @FindBy(id = "deduct")
    private WebElement findDeduct;

    @FindBy(id = "count")
    private WebElement findCount;

    @FindBy(xpath = "//div[2]/ul/li[last()]")
    private WebElement displayedResult;

    public void ifResultIsDisplayed() {
        Assert.assertFalse(findResult.isDisplayed());
    }

    public String getResult() {
        return findResult.getText();
    }

    public String getZobrazenyVysledok(){
        return displayedResult.getText();
    }

    public void compareResults(String vysledok, String zobrazenyVysledok){
        if(!vysledok.equals(zobrazenyVysledok)){
            System.out.println("Nespravne zobrazeny vypocet!");
            Assert.fail();
        }
    }

    public void compareCountOfResults() {
        List<WebElement> elems = pageDriver.findElements(By.xpath("//div[2]/ul/li"));
        int pocetOdkazov = elems.size();
        if (pocetOdkazov > 1) {
            System.out.println("Ulozene viac ako 1x!");
            Assert.fail();
        }
    }

    public void assertValuesAfterReset(String s) {
        Assert.assertTrue(pageDriver.findElement(By.id(s)).getAttribute("value").isEmpty());
    }

    public void sendFirstInput(String s){
        findFirstInput.sendKeys(s);
    }

    public void sendSecondInput(String s){
        findSecondInput.sendKeys(s);
    }

    public void clickOnReset(){
        findReset.click();
    }

    public void clickOnDivide(){
        findDivide.click();
    }

    public void clickOnMultiply(){
        findMultiply.click();
    }

    public void clickOnDeduct(){
        findDeduct.click();
    }

    public void clickOnCount(){
        findCount.click();
    }
}
