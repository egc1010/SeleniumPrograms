package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pom.base.Base;

import java.util.List;

// 1. Con "extends Base" le decimos que use las herramientas de tu clase Base
public class InventoryPage extends Base {
	
	private By lblProducto = By.cssSelector(".title");

    //  Declaramos la variable local de la clase
  //  private WebDriver driver; 
	
    // 2. El constructor que conecta el navegador con la clase Base padre
    public InventoryPage(WebDriver driver) 
    {
        super(driver);// Se lo mandas a padre
        //this.driver = driver; 
    }
    
    public String obtenerTextoEtiquetaInventory() {
    	esperarElemento(lblProducto,1);
        return obtenerTexto(lblProducto); 
    }
    
 // 1. HERRAMIENTA NUEVA: Devuelve cuántas etiquetas del carrito encuentra (0 si está vacío)
    public int obtenerVisibilidadCarrito() {
        return driver.findElements(By.cssSelector("span.shopping_cart_badge")).size();
    }

    // 2. HERRAMIENTA NUEVA: Hace clic en los dos productos específicos por su ID
    public void agregarChaquetaYCamisa() {
        hacerClic(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        hacerClic(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
    }

    // 3. HERRAMIENTA NUEVA: Extrae el número del carrito convertido a entero
    public int obtenerNumeroDeArticulosEnCarrito() {
        List<WebElement> insignia = driver.findElements(By.cssSelector("span.shopping_cart_badge"));
       
        if (insignia.isEmpty()) {
            return 0;
        }
        String cantidadTexto = insignia.get(0).getText();
        return Integer.parseInt(cantidadTexto);
    }
    
    public int obtenerCantidadDeArticulos() {
        // Usas "driver" directamente porque tu mamá (Base) te lo da
        return driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).size(); 
    }

    
    // Acción B: Tu bucle for usando tu herramienta "hacerClic"
    public void agregarTodosLosArticulos(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            // 3. ¡MIRA ESTA LÍNEA! Usamos tu método "hacerClic" con el By adentro
            hacerClic(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
            try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        }
    }    
 
}