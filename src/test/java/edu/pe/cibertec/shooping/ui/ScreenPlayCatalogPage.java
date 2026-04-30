package edu.pe.cibertec.shooping.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;

public class ScreenPlayCatalogPage {

    private static final Target CATALOG_TITLE = Target
            .the("catalog title")
            .located(AppiumBy.xpath("//android.widget.TextView[@text='Productos']"));

    private static final Target SEARCH_FIELD = Target
            .the("catalog search field")
            .located(AppiumBy.xpath("//android.widget.EditText"));

    private static final Target PRODUCT_NAMES = Target
            .the("catalog product names")
            .located(AppiumBy.xpath("//android.widget.TextView"));

    private static final String ELECTRONICS_REFERENCE_PRODUCT = "Laptop HP Pavilion";

    private ScreenPlayCatalogPage() {
    }

    public static Target searchField() {
        return SEARCH_FIELD;
    }

    public static Target categoryFilter(String category) {
        return Target.the(category + " category filter")
                .located(AppiumBy.xpath("//android.widget.TextView[@text='" + category + "']"));
    }

    public static Target productByName(String productName) {
        return Target.the("product named " + productName)
                .located(AppiumBy.xpath("//android.widget.TextView[@text='" + productName + "']"));
    }

    public static Target productContaining(String text) {
        return Target.the("product containing " + text)
                .located(AppiumBy.xpath("//android.widget.TextView[contains(@text,'" + text + "')]"));
    }

    public static boolean isCatalogVisibleFor(Actor actor) {
        return CATALOG_TITLE.resolveFor(actor).isVisible();
    }

    public static boolean hasAvailableProducts(Actor actor) {
        List<String> visibleTexts = PRODUCT_NAMES.resolveAllFor(actor).texts();
        return visibleTexts.stream()
                .filter(text -> text != null && !text.isBlank())
                .anyMatch(text -> !text.equalsIgnoreCase("Productos"));
    }

    public static boolean containsProductMatching(Actor actor, String text) {
        return productContaining(text).resolveFor(actor).isVisible();
    }

    public static boolean containsProduct(Actor actor, String productName) {
        return productByName(productName).resolveFor(actor).isVisible();
    }

    public static boolean showsProductsForCategory(Actor actor, String category) {
        if ("Electrónica".equalsIgnoreCase(category) || "Electronica".equalsIgnoreCase(category)) {
            return containsProduct(actor, ELECTRONICS_REFERENCE_PRODUCT);
        }
        return hasAvailableProducts(actor);
    }
}
