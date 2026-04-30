package edu.pe.cibertec.shooping.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.targets.Target;

public class ProfilePage {

    private static final Target USER_MENU_BUTTON = Target
            .the("user menu button")
            .located(AppiumBy.xpath("//android.widget.Button[contains(@content-desc,'user') or contains(@content-desc,'perfil') or contains(@content-desc,'profile')]"));

    private static final Target LOGGED_USER_DATA = Target
            .the("logged user data")
            .located(AppiumBy.xpath("//android.widget.TextView[contains(@text,'user1@test.com') or contains(@text,'Andrea') or contains(@text,'Perfil')]"));

    private static final Target LOGOUT_BUTTON = Target
            .the("logout button")
            .located(AppiumBy.xpath("//android.widget.TextView[@text='Cerrar sesión' or @text='Cerrar sesion' or @text='Logout']"));

    private ProfilePage() {
    }

    public static Target userMenuButton() {
        return USER_MENU_BUTTON;
    }

    public static Target logoutButton() {
        return LOGOUT_BUTTON;
    }

    public static boolean isUserDataVisibleFor(Actor actor) {
        return LOGGED_USER_DATA.resolveFor(actor).isVisible();
    }

    public static boolean canLogout(Actor actor) {
        return LOGOUT_BUTTON.resolveFor(actor).isVisible();
    }
}
