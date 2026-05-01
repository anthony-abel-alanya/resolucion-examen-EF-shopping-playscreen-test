package edu.pe.cibertec.shooping.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.targets.Target;

public class TheMainScreen {

    public static final Target INICIO_TAB = Target.the("Inicio tab")
            .located(AppiumBy.xpath(
                    "//android.widget.TextView[@text='Inicio'] | //*[@content-desc='Inicio']"));

    private static final Target HOME_INDICATOR = Target
            .the("main / catalog screen indicator")
            .located(AppiumBy.xpath(
                    "//android.widget.TextView[@text='Productos' or @text='Inicio' "
                            + "or contains(@text,'Producto')]"));

    public static Question<Boolean> isVisible() {
        return Visibility.of(HOME_INDICATOR).asBoolean();
    }
}
