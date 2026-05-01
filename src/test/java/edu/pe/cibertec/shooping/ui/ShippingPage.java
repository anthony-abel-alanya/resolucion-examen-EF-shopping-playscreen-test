package edu.pe.cibertec.shooping.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class ShippingPage {

    private static final String CHECKOUT_FORM_EDITTEXTS =
            "(//android.widget.ScrollView)[last()]//android.widget.EditText";

    public static Target shippingField(int index1Based) {
        return Target.the("shipping / payment field " + index1Based)
                .located(AppiumBy.xpath("(" + CHECKOUT_FORM_EDITTEXTS + ")[" + index1Based + "]"));
    }

    public static final Target CONFIRM_PURCHASE = Target.the("confirm purchase")
            .located(AppiumBy.xpath(
                    "(//android.widget.ScrollView)[last()]//android.widget.Button[last()]"
                            + "|//android.widget.ScrollView/android.view.View[4]/android.widget.Button"
                            + "|(//android.widget.ScrollView)[last()]/android.view.View[4]/android.widget.Button"
                            + "|//androidx.compose.ui.platform.ComposeView//android.widget.Button[contains(@text,'Confirmar')]"
                            + "|//androidx.compose.ui.platform.ComposeView//android.view.View[contains(@text,'Confirmar')]"
                            + "|(//androidx.compose.ui.platform.ComposeView//android.widget.Button)[last()]"
                            + "|//*[@clickable='true' and (contains(@text,'Confirmar') or contains(@content-desc,'Confirmar'))]"
                            + "|//android.widget.TextView[contains(@text,'Confirmar Compr')]"
                            + "|//*[contains(@text,'Confirmar') and contains(@text,'Compra')]"));

    public static final Target SUCCESS_MESSAGE = Target.the("purchase success message")
            .located(AppiumBy.xpath(
                    "//*[contains(@text,'exitos') or contains(@text,'Exitos') or contains(@text,'EXITOS') "
                            + "or contains(@text,'éxito') or contains(@text,'Éxito') or contains(@text,'exito') "
                            + "or contains(@text,'realizada') or contains(@text,'realizado') or contains(@text,'confirmad') "
                            + "or contains(@text,'Gracias') or contains(@text,'success') or contains(@text,'Success')]"));

    public static final Target ADDRESS_REQUIRED_MESSAGE = Target.the("address required message")
            .located(AppiumBy.androidUIAutomator(
                    "new UiSelector().textMatches(\"(?i).*requer.*\")"));
}
