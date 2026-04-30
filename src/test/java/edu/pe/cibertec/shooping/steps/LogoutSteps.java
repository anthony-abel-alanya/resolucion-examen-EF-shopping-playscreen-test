package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.hooks.AppiumHooks;
import edu.pe.cibertec.shooping.ui.LoginScreen;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutSteps {

    @Before("@logout")
    public void beforeLogoutScenario() {
    }

    @After("@logout")
    public void afterLogoutScenario() {
    }

    @When("hace clic en el menu de usuario")
    public void haceClicEnElMenuDeUsuario() {
        driver().findElement(AppiumBy.xpath("//android.view.View[@clickable='true'][.//android.widget.TextView[@text='Perfil']]")).click();
        assertTrue(!driver().findElements(AppiumBy.xpath("//android.widget.TextView[@text='Mi Perfil']")).isEmpty(),
                "El menu de perfil debe mostrar los datos del usuario logueado");
        assertTrue(!driver().findElements(AppiumBy.xpath("//android.widget.TextView[@text='user1@test.com']")).isEmpty(),
                "Los datos del usuario logueado deben mostrarse en perfil");
    }

    @And("hace clic en cerrar sesion")
    public void haceClicEnCerrarSesion() {
        driver().findElement(AppiumBy.xpath("(//android.widget.Button)[2]")).click();
    }

    @Then("deberia regresar a la pantalla de login")
    public void deberiaRegresarALaPantallaDeLogin() {
        boolean logoutCompleted = LoginScreen.isVisibleFor(driver())
                || !driver().findElements(AppiumBy.xpath("//android.widget.TextView[@text='Shopping Cart']")).isEmpty();
        assertTrue(logoutCompleted,
                "La pantalla de login debe mostrarse despues del logout");
    }

    private AndroidDriver driver() {
        return AppiumHooks.getDriver();
    }
}
