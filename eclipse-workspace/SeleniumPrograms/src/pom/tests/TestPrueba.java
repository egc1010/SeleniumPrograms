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

import pom.pages.InventoryPage;
import pom.pages.LoginPageSauce; 



public class TestPrueba {
    
    private WebDriver driver;
    private LoginPageSauce loginSauce; 
    private InventoryPage tienda;
    
  @Parameters({"Browser"}) 
    @BeforeMethod
    public void setUp(@Optional("chrome") String browserName) {
    	
        System.out.println("Iniciando pruebas en el navegador: " + browserName);
        
        // Evaluamos qué navegador se pidió en el archivo XML
        if (browserName.equalsIgnoreCase("chrome")) {
            // Configuración para Google Chrome
            ChromeOptions chromeOptions = new ChromeOptions();
          //  chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
            
        } else if (browserName.equalsIgnoreCase("firefox")) {
            // Tu configuración de siempre para Firefox
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            driver = new FirefoxDriver(firefoxOptions);
        }
        
    	    
        // Tu preparación de siempre: abre el navegador y hace el Login
        
        driver.manage().window().maximize();
        driver.get("https://saucedemo.com");
		
        loginSauce = new LoginPageSauce(driver);
        
        Assert.assertEquals(loginSauce.obtenerTextoBotonLogin(), "Login", "No cargó la página de inicio");
        System.out.println("¡Confirmado! El navegador abrió la pantalla de login con éxito.");
        loginSauce.iniciarSesion("standard_user", "secret_sauce");
    }
  @Test
  public void estoyEnLaTienda() {
	  
	     tienda = new InventoryPage(driver);
	    Assert.assertEquals(tienda.obtenerTextoEtiquetaInventory(), "Products", "No esta en productos");

	  
  }
  
  
    @Test
    
    public void probar2Elementos() {
    	
    	// 1. Instanciamos la página
         tienda = new InventoryPage(driver);
        
        // 2. Validación de inicio (Misma lógica: busca que el tamaño sea 0)
        Assert.assertEquals(tienda.obtenerVisibilidadCarrito(), 0, "Debe ser vacio al inicio");
        System.out.println("tiene 0 articulos AL INICIO");
        
        // 3. Mandamos a la página a hacer los dos clics específicos
        tienda.agregarChaquetaYCamisa();
        
        // 4. Validación final: Aseguramos que la etiqueta exista en la pantalla
        Assert.assertTrue(tienda.obtenerVisibilidadCarrito() > 0, "mayor a 0 ");
        
        // 5. Validación del número exacto: Extraemos el entero y evaluamos si es mayor a 1 (osea, 2)
        int cantidadNumerica = tienda.obtenerNumeroDeArticulosEnCarrito();
        Assert.assertTrue(cantidadNumerica == 2, "La cantidad de artículos  coincide.");
        System.out.println("tiene " + cantidadNumerica + " articulos Al final");
    }

    @Test
    public void testAgregarTodosLosArticulos() {
        // 2. CREAMOS EL OBJETO: Instanciamos la página pasándole nuestro driver activo
         tienda = new InventoryPage(driver);
        
        // 3. OBTENEMOS LOS DATOS: Le preguntamos a la página cuántos artículos ve
        int totalArticulos = tienda.obtenerCantidadDeArticulos();
        
        System.out.println("tiene " + tienda.obtenerNumeroDeArticulosEnCarrito()+ " articulos en carrito al inicio");
        
        // 4. VALIDAMOS: Hacemos la aserción de TestNG de toda la vida
        Assert.assertEquals(totalArticulos, 6, "no se encontraron los 6 artículos esperados");
        
        // 5. EJECUTAMOS LA ACCIÓN: Le ordenamos a la página que corra el bucle for por nosotros
        tienda.agregarTodosLosArticulos(totalArticulos);
        
        Reporter.log("¡Prueba Terminada! El carrito se llenó correctamente con " + totalArticulos + " artículos.", true);
    }

    @AfterMethod
    public void tearDown() {
        // Cerramos el navegador limpiamente al terminar
        if (driver != null) {
            driver.quit(); 
        }
    }
}