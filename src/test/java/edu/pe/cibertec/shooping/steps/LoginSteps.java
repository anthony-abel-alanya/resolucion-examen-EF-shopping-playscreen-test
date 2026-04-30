package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.hooks.AppiumHooks;
import edu.pe.cibertec.shooping.ui.LoginScreen;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    private String email;
    private String password;

    @Given("Andrea opens the shopping Cart application")
    public void opensTheShoopingCartApplication() {
        assertTrue(LoginScreen.isVisibleFor(driver()),
                "La pantalla de login debe mostrarse al abrir la aplicacion");
    }

    @When("she enters her email {string} and password {string}")
    public void entersHerEmailAndPassword(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @And("she taps the Login button")
    public void tapsTheLoginButton() {
        typeInto("(//android.widget.EditText)[1]", email);
        typeInto("(//android.widget.EditText)[2]", password);
        driver().findElement(AppiumBy.xpath("(//android.widget.Button)[2]")).click();
        pause();
    }

    @Then("she should see the main screen of the application")
    public void checksTheMainScreenOfTheApplication() {
        boolean visible = !driver().findElements(AppiumBy.xpath("//android.widget.TextView[@text='Productos']")).isEmpty()
                || !driver().findElements(AppiumBy.xpath("//android.widget.TextView[contains(@text,'Buscar productos')]")).isEmpty()
                || !driver().findElements(AppiumBy.xpath("//android.widget.TextView[contains(@text,'Electr')]")).isEmpty();
        assertTrue(visible,
                "El usuario deberia acceder exitosamente a la pantalla principal");
    }

    @Then("she should see an error message for invalid credentials")
    public void sheShouldSeeAnErrorMessageForInvalidCredentials() {
        assertTrue(LoginScreen.isErrorMessageVisibleFor(driver()),
                "Deberia mostrarse un mensaje de error cuando el login falla");
    }

    private AndroidDriver driver() {
        return AppiumHooks.getDriver();
    }

    private void typeInto(String xpath, String value) {
        WebElement field = driver().findElement(AppiumBy.xpath(xpath));
        field.click();
        field.sendKeys(value);
    }

    private void pause() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
