package edu.pe.cibertec.shooping.ui;
import net.serenitybdd.screenplay.Question;

public class HomePage {

    private HomePage() {}

    public static Question<Boolean> isVisible() {
        return TheMainScreen.isVisible();
    }
}
