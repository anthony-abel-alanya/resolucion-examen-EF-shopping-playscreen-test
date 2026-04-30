package edu.pe.cibertec.shooping.ui;
import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage {

    private static final Target CART_BUTTON = Target
            .the("cart button")
            .located(AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'cart') or contains(@content-desc,'Carrito')]"));

    private static final Target CHECKOUT_BUTTON = Target
            .the("checkout button")
            .located(AppiumBy.xpath("//android.widget.Button[@text='Checkout' or @text='CHECKOUT' or contains(@content-desc,'Checkout')]"));

    private static final Target SUCCESS_MESSAGE = Target
            .the("success purchase message")
            .located(AppiumBy.xpath("//android.widget.TextView[contains(@text,'exitosa') or contains(@text,'Exitosa') or contains(@text,'successful') or contains(@text,'Success')]"));

    private static final Target EMPTY_CART_MESSAGE = Target
            .the("empty cart message")
            .located(AppiumBy.xpath("//android.widget.TextView[contains(@text,'carrito vacio') or contains(@text,'Carrito vacio') or contains(@text,'empty cart')]"));

    private static final String DEFAULT_PRODUCT = "Laptop HP Pavilion";

    private CheckoutPage() {
    }

    public static Target cartButton() {
        return CART_BUTTON;
    }

    public static Target checkoutButton() {
        return CHECKOUT_BUTTON;
    }

    public static Target addProductButton(String productName) {
        return Target.the("add to cart button for " + productName)
                .located(AppiumBy.xpath("//android.widget.TextView[@text='" + productName + "']/following-sibling::android.widget.Button"));
    }

    public static boolean isSuccessMessageVisibleFor(Actor actor) {
        return SUCCESS_MESSAGE.resolveFor(actor).isVisible();
    }

    public static boolean isEmptyCartMessageVisibleFor(Actor actor) {
        return EMPTY_CART_MESSAGE.resolveFor(actor).isVisible();
    }

    public static String defaultProduct() {
        return DEFAULT_PRODUCT;
    }
}
