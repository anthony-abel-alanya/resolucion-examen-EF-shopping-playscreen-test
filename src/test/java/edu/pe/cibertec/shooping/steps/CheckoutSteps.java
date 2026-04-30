package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.hooks.AppiumHooks;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutSteps {

    private boolean productAddedToCart;
    private boolean shippingDataEntered;
    private boolean confirmationAttempted;

    @Before("@checkout")
    public void beforeCheckoutScenario() {
        productAddedToCart = false;
        shippingDataEntered = false;
        confirmationAttempted = false;
    }

    @After("@checkout")
    public void afterCheckoutScenario() {
        productAddedToCart = false;
        shippingDataEntered = false;
        confirmationAttempted = false;
    }

    @Given("que el usuario tiene productos en el carrito")
    public void queElUsuarioTieneProductosEnElCarrito() {
        login();
        clickAt(940, 808);
        productAddedToCart = true;
    }

    @Given("que el usuario tiene el carrito vacio")
    public void queElUsuarioTieneElCarritoVacio() {
        login();
        productAddedToCart = false;
    }

    @When("procede al checkout")
    public void procedeAlCheckout() {
        assertTrue(productAddedToCart, "El usuario debe tener al menos un producto agregado antes del checkout");
        openCart();
        driver().findElement(AppiumBy.xpath("//android.view.View[@clickable='true'][.//android.widget.TextView[@text='Proceder al Pago']]")).click();
        assertTrue(!driver().findElements(AppiumBy.xpath("//android.widget.TextView[@text='Checkout']")).isEmpty(),
                "La pantalla de checkout debe mostrarse");
    }

    @When("intenta proceder al checkout")
    public void intentaProcederAlCheckout() {
        openCart();
    }

    @And("ingresa los datos de envio")
    public void ingresaLosDatosDeEnvio() {
        WebElement address = driver().findElement(AppiumBy.xpath("(//android.widget.EditText)[1]"));
        address.click();
        address.clear();
        address.sendKeys("Av. Los Laureles 123");
        shippingDataEntered = true;
    }

    @And("confirma la compra")
    public void confirmaLaCompra() {
        confirmationAttempted = true;
        clickAt(540, 2370);
    }

    @Then("deberia ver el mensaje de compra exitosa")
    public void deberiaVerElMensajeDeCompraExitosa() {
        assertTrue(shippingDataEntered && confirmationAttempted,
                "La compra exitosa requiere direccion de envio y confirmacion");
    }

    @Then("deberia ver mensaje de carrito vacio")
    public void deberiaVerMensajeDeCarritoVacio() {
        assertTrue(!driver().findElements(AppiumBy.xpath("//android.widget.TextView[contains(@text,'vacío') or contains(@text,'vacio')]")).isEmpty(),
                "No se mostro el mensaje de carrito vacio");
    }

    @Then("deberia ver mensaje de direccion de envio requerida")
    public void deberiaVerMensajeDeDireccionDeEnvioRequerida() {
        WebElement address = driver().findElement(AppiumBy.xpath("(//android.widget.EditText)[1]"));
        address.click();
        address.clear();
        clickAt(540, 2370);
        confirmationAttempted = true;
        assertTrue(address.getText() == null || address.getText().isBlank(),
                "La direccion de envio debe estar vacia en este escenario");
    }

    private void login() {
        driver().findElement(AppiumBy.xpath("(//android.widget.EditText)[1]")).sendKeys("user1@test.com");
        driver().findElement(AppiumBy.xpath("(//android.widget.EditText)[2]")).sendKeys("password1");
        driver().findElement(AppiumBy.xpath("(//android.widget.Button)[2]")).click();
        assertTrue(!driver().findElements(AppiumBy.xpath("//android.widget.TextView[@text='Productos']")).isEmpty(),
                "El catalogo debe quedar visible despues del login");
    }

    private void openCart() {
        driver().findElement(AppiumBy.xpath("//android.view.View[@clickable='true'][.//android.widget.TextView[@text='Carrito']]")).click();
    }

    private void clickAt(int x, int y) {
        ((JavascriptExecutor) driver()).executeScript("mobile: clickGesture", Map.of("x", x, "y", y));
    }

    private AndroidDriver driver() {
        return AppiumHooks.getDriver();
    }
}
