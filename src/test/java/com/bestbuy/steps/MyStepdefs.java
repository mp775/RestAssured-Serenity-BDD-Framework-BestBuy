package com.bestbuy.steps;

import com.bestbuy.productinfo.ProductSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

public class MyStepdefs {
    @Steps
    ProductSteps productSteps;
    static ValidatableResponse response;

    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {
        response = productSteps.getAllProducts();
    }

    @Then("^User must get back a valid status code (\\d+)$")
    public void userMustGetBackAValidStatusCode(int arg0) {
        response.statusCode(arg0);
    }

    @When("^I create a new user by providing the information name \"([^\"]*)\" type \"([^\"]*)\" upc \"([^\"]*)\" price \"([^\"]*)\"description \"([^\"]*)\"model \"([^\"]*)\"$")
    public void iCreateANewUserByProvidingTheInformationNameTypeUpcPriceDescriptionModel(String name, String type, String upc, double price, String description, String model) {
        // Write code here that turns the phrase above into concrete actions

        System.out.println("" + name + " " + type + " " + upc + " " + price + " " + description + "" + model);
        response = productSteps.createProduct(name, type, upc, price, description, model);
    }

    @Then("^I verify that the user with \"([^\"]*)\" is created$")
    public void iVerifyThatTheUserWithIsCreated(String name) {
        // Write code here that turns the phrase above into concrete actions



    }

    @Then("^I verify that the user with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" is created$")
    public void iVerifyThatTheUserWithIsCreated(String name, String type, String upc, double price, String description, String model) {
     String  actualName= response.extract().path("name");

        Assert.assertEquals(name,actualName);

        String  actualType= response.extract().path("type");

        Assert.assertEquals(type,actualType);

        String  actualUpc= response.extract().path("upc");

        Assert.assertEquals(upc,actualUpc);

       // double  actualPrice= Double.parseDouble(response.extract().path("price").toString());
      //  Assert.assertEquals(price,actualPrice);

        String  actualDescription= response.extract().path("description");

        Assert.assertEquals(description,actualDescription);

        String  actualModel= response.extract().path("model");

        Assert.assertEquals(model,actualModel);

    }


}

