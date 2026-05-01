package edu.pe.cibertec.shooping.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage {

    public static final Target CART_TAB = Target.the("cart tab")
            .located(AppiumBy.xpath("//android.widget.TextView[@text='Carrito']"));

    public static final Target PROCEED_TO_CHECKOUT = Target.the("proceed to checkout (carrito)")
            .located(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Proceder\")"));

    public static final Target EMPTY_CART_MESSAGE = Target.the("empty cart message")
            .located(AppiumBy.xpath("//*[contains(@text,'carrito') and (contains(@text,'vacio') or contains(@text,'vacío'))]"));

    public static final Target FIRST_ADD_BUTTON = Target.the("first add-to-cart control")
            .located(AppiumBy.xpath(
                    "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View"
                            + "/android.view.View/android.view.View[4]/android.view.View[1]/android.view.View[3]"
                            + "/android.widget.Button"));
}
