package stepdefnitaion;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PlaceValidationStepdef extends Utils {

    ResponseSpecification resspec;
    RequestSpecification res;
    Response response1;
    RequestSpecification req;
    TestDataBuild testDataBuild = new TestDataBuild();
    public static String placeID=null;

    @Given("i sent the request for Add place with {string} {string} {string}")
    public void i_sent_the_request_for_add_place_with(String name, String language, String addresss) throws IOException {
        res = given().spec(requstSpecification(name, language, addresss)).body(testDataBuild.addPlacePaylod(name, language, addresss));
    }

    @When("i calls {string} with {string} http request")
    public void i_calls_with_http_request(String resource, String httpMethod) {

        APIResource apiResource = APIResource.valueOf(resource);
        System.out.println(apiResource.getResource());
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(httpMethod.equalsIgnoreCase("POST"))
            response1 = res.when().post(apiResource.getResource());
        else if(httpMethod.equalsIgnoreCase("GET"))
            response1 = res.when().get(apiResource.getResource());
    }

    @Then("i should see the successful response with the status code  {int}")
    public void i_should_see_the_successful_response_with_the_status_code(Integer int1) {
        Assert.assertEquals(response1.getStatusCode(), 200);
    }

    @Then("i see the {string} in the response in the body as {string}")
    public void i_see_the_in_the_response_in_the_body_as(String key, String expectedValue) {
        String responseString = response1.asString();
        JsonPath jsonPath = new JsonPath(responseString);
        String actualValue = jsonPath.getString(key);
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resourceAPI) throws IOException {
//  Request Specification
         placeID = getJsonPath(response1,"place_id");
        res= given().spec(requestSpecification()).queryParams("place_id", placeID);
        i_calls_with_http_request(resourceAPI, "GET");
        String actualName = getJsonPath(response1,"name");
        Assert.assertEquals(expectedName, actualName);
    }

    @Given("DeletePlace Payload")
    public void deleteplace_Payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions

        res =given().spec(requestSpecification()).body(testDataBuild.deletePlacePayload(placeID));
    }


}
