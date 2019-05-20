package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PokemonTest extends TestBase {

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/vybersi.php");
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
