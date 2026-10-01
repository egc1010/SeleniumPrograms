package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pom.base.Base;

public class LoginPageSauce extends Base {

    // 1. Localizadores de la pantalla
    private By txtUsuario = By.cssSelector("input[name='user-name']");
    private By txtPassword = By.cssSelector("input[name='password']");
    private By btnLogin = By.id("login-button");
    private By lblErrorLogin = By.cssSelector("[data-test='error']");

  // 2. Driver de regreso a padre    
    public LoginPageSauce(WebDriver driver) {
        super(driver); 
    }

    // 3. Método de acción
    public void iniciarSesion(String usuario, String password) {
        // DETALLE CRUCIAL: Debes llamar a tus métodos heredados (type y hacerClic)
    	esperarElemento(txtUsuario,1);
    	type(txtUsuario, usuario);
        type(txtPassword, password);
        hacerClic(btnLogin);
    }
    
    public String obtenerTextoBotonLogin() {
    	
        return obtenerAtributo(btnLogin, "value");   
        }
    
    public String obtenerTextoErrorLogin(){
    	esperarElemento(lblErrorLogin,1);
        return obtenerTexto(lblErrorLogin); 
    }
}
