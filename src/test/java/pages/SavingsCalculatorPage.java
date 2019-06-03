package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SavingsCalculatorPage {

    private WebDriver pageDriver;

    public SavingsCalculatorPage(WebDriver driver) {
        this.pageDriver = driver;
        PageFactory.initElements(pageDriver, this);
    }

    @FindBy(id = "oneTimeInvestmentInput")
    private WebElement fundInput;

    @FindBy(id = "yearsInput")
    private WebElement yearsInput;

    @FindBy(id = "emailInput")
    private WebElement emailInput;

    @FindBy(css = ".btn-success")
    private WebElement buttonApply;

    @FindBy(xpath = "//div[contains(@class, 'result')]/div[1]/p")
    private WebElement totalIncome;

    @FindBy(xpath = "//div[contains(@class, 'result')]/div[2]/p")
    private WebElement interestIncome;

    @FindBy(xpath = "//div[contains(@class, 'result')]/div[3]/p")
    private WebElement riskLevel;

    @FindBy(css = ".saving-list > li:first-child")
    private WebElement lastEntry;

    @FindBy(css = ".saving-list > li:first-child .fund-description")
    private WebElement fundDescription;

    @FindBy(css = ".recent-requests-header span")
    private WebElement numberOfEntries;

    public String getNumberOfEntries(){
        return numberOfEntries.getText();
    }

    public String getFundDescription(){
        return fundDescription.getText();
    }

    public boolean lastEntryDisplayed() {
        return lastEntry.isDisplayed();
    }

    public boolean riskLevelAssert() {
        return riskLevel.getAttribute("class").isEmpty();
    }

    public boolean totalIncomeAssert() {
        return totalIncome.getAttribute("class").isEmpty();
    }

    public boolean interestIncomeAssert() {
        return interestIncome.getAttribute("class").isEmpty();
    }

    public void enterValues() {
        fundInput.sendKeys("1000");
        yearsInput.sendKeys("2");
        emailInput.sendKeys("eva.sle@test.com");
    }

    public boolean isEnabled() {
        return buttonApply.isEnabled();
    }

    public void clickOnApplyBtn() {
        buttonApply.click();
    }

    public String[] getNameOfFunds() {
        return new String[]{"Handelsbanken Aktiv 100", "Hoggwart's Fund",
                "Fellowship investment group", "McDuck's safe", "Batman's Cave Development",
                "Death Star real estate", "Tom & Jerry corp"};
    }

    public void selectFund(String fundToSelect) {
        WebElement fundSelect = pageDriver.findElement(By.cssSelector("select"));
        new Select(fundSelect).selectByVisibleText(fundToSelect);
    }
}
