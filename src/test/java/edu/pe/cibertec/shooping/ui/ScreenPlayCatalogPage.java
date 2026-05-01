package edu.pe.cibertec.shooping.ui;
import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class ScreenPlayCatalogPage {

    public static final Target SEARCH_FIELD = Target
            .the("product search field")
            .located(AppiumBy.className("android.widget.EditText"));

    public static Target productContaining(String partialName) {
        return Target
                .the("Product containing: " + partialName)
                .located(AppiumBy.xpath("//android.widget.TextView[contains(@text,'" + partialName + "')]"));
    }
}
