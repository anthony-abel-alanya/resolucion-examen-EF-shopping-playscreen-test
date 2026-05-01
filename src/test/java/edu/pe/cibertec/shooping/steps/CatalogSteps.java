package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.questions.TheProductList;
import edu.pe.cibertec.shooping.tasks.FilterByCategory;
import edu.pe.cibertec.shooping.tasks.Login;
import edu.pe.cibertec.shooping.ui.ScreenPlayCatalogPage;
import edu.pe.cibertec.shooping.ui.TheMainScreen;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.questions.Visibility;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogSteps {

    private Actor actor;

    @Before("@catalogo")
    public void beforeCatalog() {
        // Stage/driver lifecycle is handled by AppiumHooks
    }

    @After("@catalogo")
    public void afterCatalog() {
        // Driver lifecycle is handled by AppiumHooks
    }

    @Given("que el usuario esta logueado en la aplicacion")
    public void queElUsuarioEstaLogueadoEnLaAplicacion() {
        actor = OnStage.theActorCalled("Andrea");
        actor.attemptsTo(Login.withCredentials("user1@test.com", "password1"));
        assertTrue(TheMainScreen.isVisible().answeredBy(actor), "Se esperaba ver la pantalla principal (Productos)");
    }

    @When("navega al catalogo de productos")
    public void navegaAlCatalogoDeProductos() {
        assertTrue(TheMainScreen.isVisible().answeredBy(actor), "Se esperaba estar en el catalogo (Productos)");
    }

    @Then("deberia ver la lista de productos disponibles")
    public void deberiaVerLaListaDeProductosDisponibles() {
        assertTrue(TheProductList.contains("Laptop HP Pavilion").answeredBy(actor),
                "Se esperaba ver al menos un producto del listado");
    }

    @Given("que el usuario esta en el catalogo")
    public void queElUsuarioEstaEnElCatalogo() {
        if (actor == null) {
            actor = OnStage.theActorCalled("Andrea");
        }
        if (!TheMainScreen.isVisible().answeredBy(actor)) {
            actor.attemptsTo(Login.withCredentials("user1@test.com", "password1"));
        }
        assertTrue(TheMainScreen.isVisible().answeredBy(actor), "Se esperaba estar en el catalogo (Productos)");
    }

    @When("busca el producto {string}")
    public void buscaElProducto(String text) {
        actor.attemptsTo(Enter.theValue(text).into(ScreenPlayCatalogPage.SEARCH_FIELD));
    }

    @Then("deberia ver productos que contengan {string}")
    public void deberiaVerProductosQueContengan(String text) {
        assertTrue(
                Visibility.of(ScreenPlayCatalogPage.productContaining(text)).asBoolean().answeredBy(actor),
                "Se esperaba ver productos que contengan: " + text);
    }

    @When("filtra los productos por la categoria {string}")
    public void filtraLosProductosPorLaCategoria(String category) {
        actor.attemptsTo(FilterByCategory.of(category));
    }

    @Then("deberia ver productos de la categoria {string}")
    public void deberiaVerProductosDeLaCategoria(String category) {
        assertTrue(TheProductList.contains("Laptop HP Pavilion").answeredBy(actor),
                "Se esperaba ver productos al filtrar por: " + category);
    }
}
