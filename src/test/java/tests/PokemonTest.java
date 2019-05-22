package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.PokemonPage;

public class PokemonTest extends TestBase {

    private PokemonPage pokPage;

    @Before
    public void openPage() {
        driver.get(BASE_URL + "/vybersi.php");
        pokPage = new PokemonPage(driver);
    }

    @Test
    public void itShouldSelectPikachu() {

        //ideme si zadefinovat pole, aby sme si zvolili postupne vsetkych pokemonov
        String[] selectedPokemons = {"Pikachu", "Bulbasaur", "Charmander", "Squirtle", "Diglett", "Geodude"};
        //skratka je "iter" + tab
        for (String pokemon : selectedPokemons) {
            //vyberiem Pokemona
            pokPage.selectPokemon(pokemon);
            Assert.assertEquals(pokPage.getExpectedMessage(pokemon), pokPage.getActualMessage());
        }
    }


}
