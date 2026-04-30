package edu.pe.cibertec.shooping.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.targets.Target;

public class ShippingPage {

    private static final Target SHIPPING_ADDRESS_FIELD = Target
            .the("shipping address field")
            .located(AppiumBy.xpath("//android.widget.EditText[contains(@text,'Dirección') or contains(@text,'Direccion') or contains(@text,'Address')]"));

    private static final Target CONFIRM_PURCHASE_BUTTON = Target
            .the("confirm purchase button")
            .located(AppiumBy.xpath("//android.widget.Button[@text='Confirmar compra' or @text='Confirm Purchase' or contains(@content-desc,'Confirm')]"));

    private static final Target REQUIRED_ADDRESS_MESSAGE = Target
            .the("required shipping address message")
            .located(AppiumBy.xpath("//android.widget.TextView[contains(@text,'direccion') or contains(@text,'Dirección') or contains(@text,'required') or contains(@text,'obligatoria')]"));

    private ShippingPage() {
    }

    public static Target shippingAddressField() {
        return SHIPPING_ADDRESS_FIELD;
    }

    public static Target confirmPurchaseButton() {
        return CONFIRM_PURCHASE_BUTTON;
    }

    public static boolean isRequiredAddressMessageVisibleFor(Actor actor) {
        return REQUIRED_ADDRESS_MESSAGE.resolveFor(actor).isVisible();
    }
}
