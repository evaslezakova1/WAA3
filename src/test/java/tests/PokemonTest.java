package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class PokemonTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver74.exe");
        //1. otvorit prehliadac - potrebujeme dotiahnut kniznicu Seleniumu:pom.xml dependencies + TAB
        driver = new ChromeDriver();
        //2. otvorit stranku - do zatvorky metody .get vlozime adresu do uvodzoviek
        driver.get("http://localhost:81/vybersi.php");
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void itShouldSelectPikachu() {

        String message = "I choose you ";
        WebElement pokemonSelect = driver.findElement(By.cssSelector("select"));
        //ideme si zadefinovat pole, aby sme si zvolili postupne vsetkych pokemonov
        String[] selectedPokemons={"Pikachu", "Bulbasaur", "Charmander", "Squirtle", "Diglett", "Geodude"};
        //skratka je "iter" + tab
        for (String pokemon : selectedPokemons) {
            //vyberiem Pokemona
            new Select(pokemonSelect).selectByVisibleText(pokemon);
            String actualMessage = driver.findElement(By.cssSelector("div.pokemon h3")).getText();
            Assert.assertEquals(message + pokemon + " !", actualMessage);
        }
    }
}
