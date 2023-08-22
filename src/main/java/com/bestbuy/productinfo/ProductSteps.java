package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints1;
import com.bestbuy.model.ProductPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.RestRequests;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductSteps {
    @Step("This will get all products")

        public ValidatableResponse getAllProducts() {
            ValidatableResponse response = SerenityRest.given()
                    .when()
                    .get().then();

            response.statusCode(200);
            response.log().all();
            return response;
    }

    @Step("Creating Product :name:{0},type :{1},upc :{2},price :{3},description :{4},model :{5}")
    public ValidatableResponse createProduct(String name, String type, String upc, double price, String description, String model) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);

     return    SerenityRest.given()
                .contentType(ContentType.JSON)
                .basePath("/products")
                .header("Accept", "application/json")
                .when()
                .body(productPojo)

                .post()
                .then().log().body().statusCode(201);

    }

    @Step("This will get  product name and description")
    public void getProductNameAndDescription() {

        Response response = RestAssured.given().queryParam("$select[]", "name").queryParam("$select[]", "description")
                .basePath("/products")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Step("This will get  price will give you maximum price")
    public void getMaximumPrice() {
        SerenityRest.given().queryParam("$sort[price]", "-1")
                .basePath("/products")
                .header("Content-Type", "application/json")
                .when()
                .get()
                .then().log().body().statusCode(200);


    }
    @Step("Update the user Information and verify the updated information")
      public void updateProduct(String name, String type, String upc, double price, String description, String model,int id) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParams("id", id)
                .body(productPojo)
                .when()
                .patch(EndPoints1.UPDATE_Product_BY_id)
                .then().log().all().statusCode(200);

    }

    @Step("Delete the product")
    public void deleteProduct(int id) {
        RestRequests.given()
                .pathParam("id", id)
                .when()
                .delete(EndPoints1.DELETE_Product_BY_ID)
                .then()
                .statusCode(200);

        RestRequests.given()
                .pathParam("id", id)
                .when()
                .get(EndPoints1.DELETE_Product_BY_ID)
                .then().statusCode(404);


    }




}