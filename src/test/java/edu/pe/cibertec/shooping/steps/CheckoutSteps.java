package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.tasks.Login;
import edu.pe.cibertec.shooping.ui.CheckoutPage;
import edu.pe.cibertec.shooping.ui.LoginScreen;
import edu.pe.cibertec.shooping.ui.ShippingPage;
import edu.pe.cibertec.shooping.ui.TheMainScreen;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutSteps {

    private Actor actor;

    @Before(value = "@checkout", order = 1)
    public void beforeCheckout() {
        actor = OnStage.theActorCalled("Andrea");
    }

    @After("@checkout")
    public void afterCheckout() {

    }

    @Given("que el usuario tiene productos en el carrito")
    public void queElUsuarioTieneProductosEnElCarrito() {
        ensureLoggedInAndOnCatalog();
        actor.attemptsTo(
                Click.on(CheckoutPage.FIRST_ADD_BUTTON),
                Click.on(CheckoutPage.CART_TAB));
    }

    @Given("que el usuario tiene el carrito vacio")
    public void queElUsuarioTieneElCarritoVacio() {
        ensureLoggedInAndOnCatalog();
        actor.attemptsTo(Click.on(CheckoutPage.CART_TAB));
    }

    private void ensureLoggedInAndOnCatalog() {
        if (Visibility.of(LoginScreen.EMAIL_FIELD).asBoolean().answeredBy(actor)) {
            actor.attemptsTo(Login.withCredentials("user1@test.com", "password1"));
        }
        if (!TheMainScreen.isVisible().answeredBy(actor)) {
            actor.attemptsTo(Click.on(TheMainScreen.INICIO_TAB));
        }
        assertTrue(
                TheMainScreen.isVisible().answeredBy(actor),
                "Se esperaba estar en la pantalla de Productos / catálogo (tras login o tab Inicio)");
    }

    @When("procede al checkout")
    public void procedeAlCheckout() {
        actor.attemptsTo(
                Click.on(CheckoutPage.PROCEED_TO_CHECKOUT.waitingForNoMoreThan(Duration.ofSeconds(15))));
    }

    @When("intenta proceder al checkout")
    public void intentaProcederAlCheckout() {

        if (proceedToCheckoutCtaPresent(actor, Duration.ofSeconds(3))) {
            actor.attemptsTo(
                    Click.on(CheckoutPage.PROCEED_TO_CHECKOUT.waitingForNoMoreThan(Duration.ofSeconds(12))));
        }
    }

    private static boolean proceedToCheckoutCtaPresent(Actor actor, Duration timeout) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            new FluentWait<>(driver)
                    .withTimeout(timeout)
                    .pollingEvery(Duration.ofMillis(200))
                    .ignoring(StaleElementReferenceException.class)
                    .until(d -> !d.findElements(
                                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Proceder\")"))
                            .isEmpty());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @And("ingresa los datos de envio")
    public void ingresaLosDatosDeEnvio() {
        actor.attemptsTo(
                Enter.theValue("Av. Siempre Viva 742").into(ShippingPage.shippingField(1)),
                Enter.theValue("Lima").into(ShippingPage.shippingField(2)),
                Enter.theValue("15001").into(ShippingPage.shippingField(3)),
                Enter.theValue("4111111111111111").into(ShippingPage.shippingField(4)),
                Enter.theValue("123").into(ShippingPage.shippingField(5)),
                Enter.theValue("12/30").into(ShippingPage.shippingField(6)));
    }

    @And("ingresa los datos de envio sin direccion")
    public void ingresaLosDatosDeEnvioSinDireccion() {

        actor.attemptsTo(
                Enter.theValue("Lima").into(ShippingPage.shippingField(2)),
                Enter.theValue("15001").into(ShippingPage.shippingField(3)),
                Enter.theValue("4111111111111111").into(ShippingPage.shippingField(4)),
                Enter.theValue("123").into(ShippingPage.shippingField(5)),
                Enter.theValue("12/30").into(ShippingPage.shippingField(6)));
    }

    @And("confirma la compra")
    public void confirmaLaCompra() {
        revealCheckoutConfirmControl(actor);
        actor.attemptsTo(
                Click.on(ShippingPage.CONFIRM_PURCHASE.waitingForNoMoreThan(Duration.ofSeconds(25))));
    }

    private static void revealCheckoutConfirmControl(Actor actor) {
        AndroidDriver driver = (AndroidDriver) BrowseTheWeb.as(actor).getDriver();
        Dimension size = driver.manage().window().getSize();
        for (int i = 0; i < 3; i++) {
            driver.executeScript(
                    "mobile: swipeGesture",
                    Map.of(
                            "left", size.width / 4,
                            "top", size.height / 6,
                            "width", size.width / 2,
                            "height", (int) (size.height * 0.55),
                            "direction", "up",
                            "percent", 0.6));
        }
        try {
            driver.findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                                    + "new UiSelector().textContains(\"Confirmar\"));"));
        } catch (org.openqa.selenium.NoSuchElementException ignored) {

        }
    }

    @Then("deberia ver el mensaje de compra exitosa")
    public void deberiaVerElMensajeDeCompraExitosa() {
        actor.attemptsTo(
                WaitUntil.the(ShippingPage.SUCCESS_MESSAGE, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(Duration.ofSeconds(20)));
    }

    @Then("deberia ver mensaje de carrito vacio")
    public void deberiaVerMensajeDeCarritoVacio() {
        assertTrue(
                Visibility.of(CheckoutPage.EMPTY_CART_MESSAGE).asBoolean().answeredBy(actor),
                "Se esperaba mensaje de carrito vacío");
    }

    @Then("deberia ver un mensaje de direccion requerida")
    public void deberiaVerUnMensajeDeDireccionRequerida() {
        actor.attemptsTo(
                WaitUntil.the(ShippingPage.ADDRESS_REQUIRED_MESSAGE, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(Duration.ofSeconds(22)));
    }
}
