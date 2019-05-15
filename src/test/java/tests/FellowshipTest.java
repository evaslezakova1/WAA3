package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FellowshipTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver74.exe");
        //1. otvorit prehliadac - potrebujeme dotiahnut kniznicu Seleniumu:pom.xml dependencies + TAB
        driver = new ChromeDriver();
        //2. otvorit stranku - do zatvorky metody .get vlozime adresu do uvodzoviek
        driver.get("http://localhost:81/fellowship.php");
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void itShouldContainNameForEachFellow() {
        List<WebElement> listOfFellows = driver.findElements(By.cssSelector("ul.list-of-fellows li"));
        //zadefinujem si novy String zoznam AraryList
        List<String> fellowNames = new ArrayList<String>();
        for (WebElement listOfFellow : listOfFellows) {
            //ziskam z elementu mena
            String names = listOfFellow.findElement(By.cssSelector("h1")).getText();
            Assert.assertFalse(names.isEmpty());
            //naplnim ArrayList menami z elementu
            fellowNames.add(names);
        }
        System.out.println(fellowNames);
        Assert.assertTrue(fellowNames.contains("Gandalf"));
        Assert.assertTrue(fellowNames.contains("Frodo"));
    }

    @Test
    public void domaca() {
        //overit inicialny pocet bodov 25
        String initialPoints = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/h2")).getText();
        Assert.assertEquals("25", initialPoints);
        Integer initialPointsNumber = Integer.valueOf(initialPoints);
        //overit, ze kazdy fellow ma zobrazeny a vyplneny pocet bodov
        List<WebElement> listOfFellows = driver.findElements(By.cssSelector("ul.list-of-fellows li"));
        //zoznam tych, co si ma vybrat
        List<String> chooseYourFellow = Arrays.asList("Frodo", "Samwise", "Legolas", "Aragorn", "Boromir", "Meriadoc");

        for (WebElement listOfFellow : listOfFellows) {
            String names = listOfFellow.findElement(By.cssSelector("h1")).getText();
            String points = listOfFellow.findElement(By.cssSelector("div[class='fellow-points col-md-6'] h2")).getText();
            Assert.assertFalse(points.isEmpty());
            Integer pointsNumber = Integer.valueOf(points);
            //ak sa meno nachadza na zozname, kliknut a overit, ci sa spravne aktualizoval pocet ostavajucich bodov
            if (chooseYourFellow.contains(names)) {
                listOfFellow.click();
                if (names.equals("Meriadoc")) {
                    //uz sa minuli vsetky body, overi hlasku Complete
                    String newPoints = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/h3")).getText();
                    Assert.assertEquals("Complete", newPoints);
                } else {
                    //body sa este neminuli, tak pokracuje dalej vo vyberani
                    String newPoints = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/h2")).getText();
                    Integer newPointsNumber = Integer.valueOf(newPoints);
                    Integer result = initialPointsNumber - pointsNumber;
                    Assert.assertEquals(newPointsNumber, result);
                    initialPointsNumber = result;
                }
            }
        }
    }
}
