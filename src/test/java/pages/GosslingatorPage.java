package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GosslingatorPage {

    @FindBy(id = "addRyan")
    private WebElement addRyanButton;

    @FindBy(css = "div.ryan-counter h3")
    private WebElement ryanDescription;

    private WebDriver pageDriver;

    public GosslingatorPage(WebDriver driver){
        this.pageDriver = driver;
        PageFactory.initElements(pageDriver, this);
    }

    //vytvaranie metod
    //private - iba v tejto triede sa da pouzit
    //void - metoda nevracia ziadnu hodnotu

    public void addRyan() {
//        WebElement addRyanButton = pageDriver.findElement(By.id("addRyan"));
        addRyanButton.click();
    }

    public String getRyanCounterNumber(){
        return pageDriver.findElement(By.id("ryanCounter")).getText();
    }

    public String getRyanCountLabel(){
        return pageDriver.findElement(By.cssSelector("div.ryan-counter h2")).getText();
    }

    public String getRyanTitleLable(){
        return ryanDescription.getText();
    }

    public int getNumberOfRyanImages(){
        return pageDriver.findElements(By.cssSelector("img")).size();
    }

}
