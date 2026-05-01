package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.ui.HomePage;
import edu.pe.cibertec.shooping.ui.LoginScreen;
import edu.pe.cibertec.shooping.ui.ProfilePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutSteps {

    private Actor actor;

    @Before(value = "@logout", order = 1)
    public void beforeLogout() {
        actor = OnStage.theActorCalled("Andrea");
    }

    @After("@logout")
    public void afterLogout() {
        // Driver: AppiumHooks
    }

    @When("hace clic en el menu de usuario")
    public void haceClicEnElMenuDeUsuario() {
        if (Visibility.of(ProfilePage.USER_MENU).asBoolean().answeredBy(actor)) {
            actor.attemptsTo(Click.on(ProfilePage.USER_MENU.waitingForNoMoreThan(Duration.ofSeconds(12))));
        } else {
            actor.attemptsTo(Click.on(ProfilePage.USER_MENU_TEXT.waitingForNoMoreThan(Duration.ofSeconds(12))));
        }
        actor.attemptsTo(
                WaitUntil.the(ProfilePage.LOGOUT_DIALOG, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(Duration.ofSeconds(12)));
    }

    @And("hace clic en cerrar sesion")
    public void haceClicEnCerrarSesion() {
        actor.attemptsTo(Click.on(ProfilePage.LOG_OUT.waitingForNoMoreThan(Duration.ofSeconds(12))));
    }

    @Then("deberia regresar a la pantalla de login")
    public void deberiaRegresarALaPantallaDeLogin() {
        assertTrue(
                Visibility.of(LoginScreen.EMAIL_FIELD).asBoolean().answeredBy(actor),
                "Se esperaba el campo de email en la pantalla de login");
        assertTrue(
                Visibility.of(LoginScreen.LOGIN_BUTTON).asBoolean().answeredBy(actor),
                "Se esperaba el boton de login");
        assertFalse(
                HomePage.isVisible().answeredBy(actor),
                "No se esperaba seguir en la pantalla principal");
    }
}
