package edu.pe.cibertec.shooping.steps;

import edu.pe.cibertec.shooping.questions.TheProductList;
import edu.pe.cibertec.shooping.tasks.FilterByCategory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductSteps {
    @When("{word} selects the {string} category filter")
    public void selectsTheCategoryFilter(String actor, String category) {
        OnStage.theActorCalled(actor)
                .attemptsTo(FilterByCategory.of(category));
    }

    @Then("{word} should see the product {string}")
    public void should_see_the_product(String actor, String productName) {
        OnStage.theActorCalled(actor)
                .should(seeThat(TheProductList.contains(productName), equalTo(true)));
    }
}
