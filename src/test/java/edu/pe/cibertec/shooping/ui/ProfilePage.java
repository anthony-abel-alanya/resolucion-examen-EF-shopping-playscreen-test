package edu.pe.cibertec.shooping.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class ProfilePage {

    private ProfilePage() {}

    public static final Target USER_MENU = Target.the("user / profile menu")
            .located(AppiumBy.androidUIAutomator(
                    "new UiSelector().descriptionContains(\"Perfil\")"));

    public static final Target USER_MENU_TEXT = Target.the("user / profile menu text")
            .located(AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"Perfil\")"));

    public static final Target LOG_OUT = Target.the("log out")
            .located(AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"android.widget.Button\").instance(2)"));

    public static final Target LOGOUT_DIALOG = Target.the("logout confirmation dialog")
            .located(AppiumBy.androidUIAutomator(
                    "new UiSelector().textMatches(\"(?i).*cerrar.*sesion.*\")"));

    public static Target userEmailVisible(String email) {
        return Target.the("logged-in user email: " + email)
                .located(AppiumBy.xpath("//*[contains(@text,\"" + email + "\")]"));
    }

}
