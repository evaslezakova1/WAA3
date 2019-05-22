package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GosslingatorPage {

    private WebDriver pageDriver;

    public GosslingatorPage(WebDriver driver){
        this.pageDriver = driver;
    }

    //vytvaranie metod
    //private - iba v tejto triede sa da pouzit
    //void - metoda nevracia ziadnu hodnotu

    public void addRyan() {
        WebElement ryanButton = pageDriver.findElement(By.id("addRyan"));
        ryanButton.click();
    }

    public String getRyanCounterNumber(){
        return pageDriver.findElement(By.id("ryanCounter")).getText();
    }

    public String getRyanCountLabel(){
        return pageDriver.findElement(By.cssSelector("div.ryan-counter h2")).getText();
    }

    public String getRyanTitleLable(){
        return pageDriver.findElement(By.cssSelector("div.ryan-counter h3")).getText();
    }

    public int getNumberOfRyanImages(){
        return pageDriver.findElements(By.cssSelector("img")).size();
    }

}
