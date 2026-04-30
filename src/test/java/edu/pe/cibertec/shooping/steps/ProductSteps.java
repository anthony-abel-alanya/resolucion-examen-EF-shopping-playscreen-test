package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.hooks.AppiumHooks;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSteps {
    @When("{word} selects the {string} category filter")
    public void selectsTheCategoryFilter(String actor, String category) {
        clickAt(380, 530);
    }

    @Then("{word} should see the product {string}")
    public void shouldSeeTheProduct(String actor, String productName) {
        assertTrue(!driver().findElements(AppiumBy.xpath("//android.widget.TextView[@text='" + productName + "']")).isEmpty(),
                "El producto esperado no se mostro luego del filtro");
    }

    private AndroidDriver driver() {
        return AppiumHooks.getDriver();
    }

    private void clickAt(int x, int y) {
        ((JavascriptExecutor) driver()).executeScript("mobile: clickGesture", Map.of("x", x, "y", y));
    }
}
