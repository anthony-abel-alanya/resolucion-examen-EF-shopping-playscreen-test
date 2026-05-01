package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.tasks.Login;
import edu.pe.cibertec.shooping.ui.HomePage;
import edu.pe.cibertec.shooping.ui.LoginScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.questions.Visibility;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    private String email;
    private String password;

    @Given("Andrea opens the shopping Cart application")
    public void opensTheShoopingCartApplication() {
        OnStage.theActorCalled("Andrea");
    }

    @When("she enters her email {string} and password {string}")
    public void entersHerEmailAndPassword(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @And("she taps the Login button")
    public void tapsTheLoginButton() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(Login.withCredentials(email, password));
    }

    @Then("she should see the main screen of the application")
    public void checksTheMainScreenOfTheApplication() {
        Actor andrea = OnStage.theActorInTheSpotlight();
        assertTrue(
                HomePage.isVisible().answeredBy(andrea),
                "Se esperaba acceso exitoso a la pantalla principal (home / catalogo)");
    }

    @Then("she should see a login error message")
    public void sheShouldSeeALoginErrorMessage() {
        Actor andrea = OnStage.theActorInTheSpotlight();
        assertTrue(
                Visibility.of(LoginScreen.LOGIN_ERROR_MESSAGE).asBoolean().answeredBy(andrea),
                "Se esperaba un mensaje de error visible tras un login fallido");
    }

}
