package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FellowshipTest extends TestBase {

        @Before
        public void openPage() {
            driver.get(BASE_URL + "/fellowship.php");
        }

    @Test
    public void itShouldContainNameForEachFellow() {
        List<WebElement> listOfFellows = returnList();
        //zadefinujem si novy String zoznam AraryList
        List<String> fellowNames = new ArrayList<String>();
        for (WebElement listOfFellow : listOfFellows) {
            //ziskam z elementu mena
            String names = nameOfFellows(listOfFellow);
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
        String initialPoints = getInitialPoints("//*[@id=\"app\"]/div/div/div/h2");
        Assert.assertEquals("25", initialPoints);
        Integer initialPointsNumber = Integer.valueOf(initialPoints);
        //overit, ze kazdy fellow ma zobrazeny a vyplneny pocet bodov
        List<WebElement> listOfFellows = returnList();
        //zoznam tych, co si ma vybrat
        List<String> chooseYourFellow = Arrays.asList("Frodo", "Samwise", "Legolas", "Aragorn", "Boromir", "Meriadoc");

        for (WebElement listOfFellow : listOfFellows) {
            String names = nameOfFellows(listOfFellow);
            String points = listOfFellow.findElement(By.cssSelector("div[class='fellow-points col-md-6'] h2")).getText();
            Assert.assertFalse(points.isEmpty());
            Integer pointsNumber = Integer.valueOf(points);
            //ak sa meno nachadza na zozname, kliknut a overit, ci sa spravne aktualizoval pocet ostavajucich bodov
            if (chooseYourFellow.contains(names)) {
                listOfFellow.click();
                if (names.equals("Meriadoc")) {
                    //uz sa minuli vsetky body, overi hlasku Complete
                    String newPoints = getInitialPoints("//*[@id=\"app\"]/div/div/div/h3");
                    Assert.assertEquals("Complete", newPoints);
                } else {
                    //body sa este neminuli, tak pokracuje dalej vo vyberani
                    String newPoints = getInitialPoints("//*[@id=\"app\"]/div/div/div/h2");
                    Integer newPointsNumber = Integer.valueOf(newPoints);
                    Integer result = initialPointsNumber - pointsNumber;
                    Assert.assertEquals(newPointsNumber, result);
                    initialPointsNumber = result;
                }
            }
        }
    }

    private String getInitialPoints(String s) {
        return driver.findElement(By.xpath(s)).getText();
    }

    private List<WebElement> returnList(){
            return driver.findElements(By.cssSelector("ul.list-of-fellows li"));
    }

    private String nameOfFellows(WebElement listOfFellow){
            return listOfFellow.findElement(By.cssSelector("h1")).getText();
    }



}
