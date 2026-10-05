package pom.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pom.pages.InventoryPage;
import pom.pages.LoginPageSauce; 
import pom.base.Base;


public class LoginNegativo {
    
    private WebDriver driver;
    private LoginPageSauce loginSauce; 
    
  @Parameters({"Browser"}) 
    @BeforeMethod
    public void setUp(@Optional("chrome") String browserName) {
    	
        System.out.println("Iniciando pruebas en el navegador: " + browserName);
        
        // Evaluamos qué navegador se pidió en el archivo XML
        if (browserName.equalsIgnoreCase("chrome")) {
            // Configuración para Google Chrome
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
            
        } else if (browserName.equalsIgnoreCase("firefox")) {
            // Tu configuración de siempre para Firefox
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            driver = new FirefoxDriver(firefoxOptions);
        }
        
        else {
        	throw new IllegalArgumentException("no se encontró el navegador "+browserName);
        	
        }
  
        
    	    
        // Tu preparación de siempre: abre el navegador y hace el Login
        
        driver.manage().window().maximize();
        driver.get("https://saucedemo.com");
		
        loginSauce = new LoginPageSauce(driver);
       
        
        Assert.assertEquals(loginSauce.obtenerTextoBotonLogin(), "Login", "No cargó la página de inicio");
        System.out.println("¡Confirmado! El navegador abrió la pantalla de login con éxito.");
        
}  
  @Test
  public void validarLoginConCredencialesInvalidas() {
	  
	  loginSauce.iniciarSesion("standard_user", "secret"); 
	 
	  Assert.assertTrue(loginSauce.obtenerTextoErrorLogin().contains("Epic sadface"),"el texto no coincide.");
	  }
  
   

    @AfterMethod
    public void tearDown() {
        // Cerramos el navegador limpiamente al terminar
        if (driver != null) {
            driver.quit(); 
        }
    }
}

