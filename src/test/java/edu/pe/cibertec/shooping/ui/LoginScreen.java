package edu.pe.cibertec.shooping.ui;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
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
            .located(AppiumBy.xpath("(//android.widget.Button)[2]"));

    public static final Target LOGIN_ERROR_MESSAGE = Target
            .the("login error message")
            .located(AppiumBy.xpath("//android.widget.TextView[contains(@text,'incorrect') or contains(@text,'invalid') or contains(@text,'error') or contains(@text,'credenciales')]"));

    private LoginScreen() {
    }

    public static boolean isVisibleFor(AndroidDriver driver) {
        return !driver.findElements(AppiumBy.xpath("//android.widget.TextView[@text='Shopping Cart']")).isEmpty()
                && !driver.findElements(AppiumBy.xpath("(//android.widget.EditText)[1]")).isEmpty();
    }

    public static boolean isErrorMessageVisibleFor(AndroidDriver driver) {
        return !driver.findElements(AppiumBy.xpath("//android.widget.TextView[contains(@text,'incorrect') or contains(@text,'invalid') or contains(@text,'error') or contains(@text,'Credenciales')]")).isEmpty()
                || isVisibleFor(driver);
    }
}
