package tests.api;

import base.BaseApi;
import base.api.Paths;
import io.restassured.module.jsv.JsonSchemaValidator;
import models.requestModels.template.TemplateRq;
import models.responseModels.template.TemplateRs;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TemplateTest extends BaseApi {
    private String path;

    @BeforeClass
    public void setup() {
        path = Paths.PEOPLE.toString();
    }

    /**
     * Use https://jsonschema.net/ to generate schema
     * For larger projects always cast response/request body to POJO object
     * For generating POJO class use http://www.jsonschema2pojo.org/
     * Also you can use extension to Intellij to generate builder class
     */

    @Test
    public void getPeople() {
        TemplateRs templateRs = given()
                .when().get(String.format(path, 1))
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/template/" +
                        "template-schema.json"))
                .body("name", Matchers.equalTo("Luke Skywalker"))
                .body("height", Matchers.equalTo("172"))
                .body("mass", Matchers.equalTo("77"))
                .body("hair_color", Matchers.equalTo("blond"))
                .extract().body().as(TemplateRs.class);
    }

    @Test(enabled = false) // IMPORTANT!!!! Endpoint doesn't exist, test will fail, it's only example
    public void createHuman() {
        TemplateRq templateRq = TemplateRq.builder()
                .setName("John")
                .setSurname("Travolta")
                .setAge(20)
                .build();

        given() // IMPORTANT!!!! Endpoint doesn't exist, test will fail, it's only example
                .body(templateRq.getViewModel().toString())
                .when().post(path)
                .then()
                .statusCode(201);
    }
}
