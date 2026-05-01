package edu.pe.cibertec.shooping.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class LoginScreen {

    public static final Target EMAIL_FIELD = Target
            .the("email field")
            .located(AppiumBy.xpath("(//android.widget.EditText)[1]"));

    public static final Target PASSWORD_FIELD = Target
            .the("password field")
            .located(AppiumBy.xpath("(//android.widget.EditText)[2]"));

    public static final Target LOGIN_BUTTON = Target
            .the("login button")
            .located(AppiumBy.xpath("//android.widget.ScrollView/android.view.View[1]/android.widget.Button"));

    public static final Target LOGIN_ERROR_MESSAGE = Target.the("login error message")
            .located(AppiumBy.xpath(
                    "//android.widget.Toast[string-length(@text)>0]"
                            + "|//*[contains(@text,'Error') or contains(@text,'error') or contains(@text,'incorrect') "
                            + "or contains(@text,'Invalid') or contains(@text,'invalid') or contains(@text,'fall') "
                            + "or contains(@text,'credencial') or contains(@text,'inv')]"));
}
