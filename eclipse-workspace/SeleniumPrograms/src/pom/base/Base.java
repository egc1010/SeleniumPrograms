package pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

    
	public class Base {
	    
	    // El driver que ya conoces
	    protected WebDriver driver;

	    // El constructor que solo recibe el navegador
	    public Base(WebDriver driver) 
	    {
	        this.driver = driver;
	    }

	    // El clic normal de toda la vida
	    public void hacerClic(By localizador) 
	    {
	        driver.findElement(localizador).click();
	    }

	    // El sendKeys normal de toda la vida
	    public void type(By locator, String text)
	    {	
	    	 driver.findElement(locator).clear();
	        driver.findElement(locator).sendKeys(text);
	    }
	    
	    public WebElement esperarElemento(By locator, int segundos) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(segundos));
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }
	    
	    public String obtenerTexto(By locator)
	    {
	        return driver.findElement(locator).getText();
	    }
	    public String obtenerAtributo(By locator, String atributo) {
	        return driver.findElement(locator).getAttribute(atributo);
	    }
	}

