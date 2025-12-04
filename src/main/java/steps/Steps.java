package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Steps {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @Given("que eu abro a página {string}")
    public void abrirPagina(String url) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("clicar na barra de pesquisa")
    public void clicarnodropdowndeprodutos() {
        driver.findElement(By.xpath("//a[contains(@class,'astra-search-icon') and @role='button']\n"));
    }

    @And("digitar no campo de pesquisa {string}")
    public void digitarnocampodepesquisa() {
        driver.findElement(By.xpath("//*[@id=\"search-field\"]")).sendKeys("Pix");
    }

    @Then("deverá aparecer o card {string}")
    public void deveraaparecerocard(String texto){
        String textoElemento = driver.findElement(
                By.xpath("//h2[@class='entry-title ast-blog-single-element']//a")
        ).getText();

        Assert.assertEquals("O texto do card não corresponde!", texto, textoElemento);
    }

}