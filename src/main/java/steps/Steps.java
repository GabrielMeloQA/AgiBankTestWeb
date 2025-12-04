package steps;

import driverFactory.DriverFactory;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Steps {

    private WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterAll
    public static void kill() {
        DriverFactory.quitDriver();
    }

    @Given("que eu abro a página {string}")
    public void abrirPagina(String url) {
        driver = DriverFactory.getDriver();
        driver.get(url);
    }

    @When("clicar na barra de pesquisa")
    public void clicarnodropdowndeprodutos() throws InterruptedException {
        Thread.sleep(10000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('.ast-search-icon .astra-search-icon').click();");
    }

    @And("digitar no campo de pesquisa {string}")
    public void digitarnocampodepesquisa(String texto) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".ast-header-search .search-field")
        ));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('.ast-header-search .search-field').value='" + texto + "';");

        driver.findElement(By.cssSelector(".ast-header-search .search-field"))
                .sendKeys(Keys.ENTER);
    }

    @Then("os artigos referentes a pesquisa deverão ser carregados")
    public void deveraaparecerocard() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> artigos = driver.findElements(By.cssSelector("h2.entry-title a"));
        Assert.assertTrue("Nenhum artigo foi carregado!", artigos.size() > 0);
    }

    @Given("que eu esteja na página atual")
    public void queeuestounapaginadepesquisa(){
        driver = DriverFactory.getDriver();
    }

    @Then("deverá ser possível visualizar uma mensagem amigável ao cliente informando que não há resultados")
    public void deveraserpossivelvisualizarumamensagemamigavelaoclienteinformandoquenaoharesultados(){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String textoMensagem = (String) js.executeScript(
                "return document.querySelector('.no-results').innerText;");

        String esperado = "Lamentamos, mas nada foi encontrado para sua pesquisa, tente novamente com outras palavras.";

        Assert.assertTrue(
                "Mensagem não corresponde! Esperado: " + esperado + " | Obtido: " + textoMensagem,
                textoMensagem.contains(esperado));
    }

}