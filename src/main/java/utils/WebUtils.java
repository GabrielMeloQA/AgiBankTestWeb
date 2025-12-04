package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebUtils {

    private WebDriver driver;

    public void pegarLocalizacaoEClick(){
        WebElement elemento = driver.findElement((By.xpath("//a[contains(@class,'astra-search-icon') and @role='button']\n")));

        Point posicao = elemento.getLocation();

        int x = posicao.getX();
        int y = posicao.getY();

        System.out.println("Coordenada X: " + x);
        System.out.println("Coordenada Y: " + y);

        Actions actions = new Actions(driver);
        actions.moveByOffset(x, y).click().perform();
    }

}
