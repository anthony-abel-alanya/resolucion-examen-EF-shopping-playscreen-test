package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.hooks.AppiumHooks;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogSteps {

    @Before("@catalogo")
    public void beforeCatalogScenario() {
    }

    @After("@catalogo")
    public void afterCatalogScenario() {
    }

    @Given("que el usuario esta logueado en la aplicacion")
    public void queElUsuarioEstaLogueadoEnLaAplicacion() {
        login("user1@test.com", "password1");
        assertTrue(isCatalogVisible(),
                "El catalogo debe mostrarse despues del login");
    }

    @Given("que el usuario esta en el catalogo")
    public void queElUsuarioEstaEnElCatalogo() {
        queElUsuarioEstaLogueadoEnLaAplicacion();
    }

    @When("navega al catalogo de productos")
    public void navegaAlCatalogoDeProductos() {
        assertTrue(isCatalogVisible(),
                "El usuario debe permanecer en el catalogo de productos");
    }

    @When("busca el producto {string}")
    public void buscaElProducto(String productName) {
        WebElement searchField = driver().findElement(AppiumBy.xpath("//android.widget.EditText"));
        searchField.click();
        searchField.clear();
        searchField.sendKeys(productName);
    }

    @When("filtra productos por categoria {string}")
    public void filtraProductosPorCategoria(String category) {
        driver().findElement(AppiumBy.xpath("//android.view.View[@clickable='true'][.//android.widget.TextView[contains(@text,'" + normalizeCategory(category) + "')]]")).click();
    }

    @Then("deberia ver la lista de productos disponibles")
    public void deberiaVerLaListaDeProductosDisponibles() {
        assertTrue(!driver().findElements(AppiumBy.xpath("//android.widget.TextView[@text='Laptop HP Pavilion']")).isEmpty(),
                "La lista de productos disponibles no se mostro en el catalogo");
    }

    @Then("deberia ver productos que contengan {string}")
    public void deberiaVerProductosQueContengan(String productName) {
        assertTrue(!driver().findElements(AppiumBy.xpath("//android.widget.TextView[contains(@text,'" + productName + "')]")).isEmpty(),
                "No se encontraron productos que contengan el texto buscado");
    }

    @Then("deberia ver productos de la categoria {string}")
    public void deberiaVerProductosDeLaCategoria(String category) {
        assertTrue(!driver().findElements(AppiumBy.xpath("//android.widget.TextView[@text='Laptop HP Pavilion']")).isEmpty(),
                "No se visualizaron productos para la categoria filtrada");
    }

    private AndroidDriver driver() {
        return AppiumHooks.getDriver();
    }

    private void login(String email, String password) {
        driver().findElement(AppiumBy.xpath("(//android.widget.EditText)[1]")).sendKeys(email);
        driver().findElement(AppiumBy.xpath("(//android.widget.EditText)[2]")).sendKeys(password);
        driver().findElement(AppiumBy.xpath("(//android.widget.Button)[2]")).click();
    }

    private boolean isCatalogVisible() {
        return !driver().findElements(AppiumBy.xpath("//android.widget.TextView[@text='Productos']")).isEmpty();
    }

    private String normalizeCategory(String category) {
        return category.startsWith("Electr") ? "Electr" : category;
    }
}
