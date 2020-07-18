package steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class CreateBlobReadFromJSONDataSteps {

	private Response response;
	private RequestSpecification request;
	private String requestBody;


	@Before
	public void setBaseURI() {
		System.out.println("** Test run started.. ");
		System.out.println();
		RestAssured.baseURI = "https://jsonblob.com";
		RestAssured.basePath = "/api/jsonBlob";
		
		requestBody = "{\r\n" + 
				"	\"brands\": \r\n" + 
				"	[\r\n" + 
				"		{\r\n" + 
				"		\"brand\": \"Nike\",\r\n" + 
				"		\"productType\": \"UK Brand\",\r\n" + 
				"		\"productSystemID\": 110989 \r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"		\"brand\": \"Gap\",\r\n" + 
				"		\"productType\": \"US Brand\",\r\n" + 
				"		\"productSystemID\": 345634 \r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}";
		
	}
	
	@After
	public void afterTest() {
		System.out.println("** Test run completed. ");
		System.out.println();
	}
	
	@Given("the API for jsonbolb")
	public void the_api() throws IOException {
		
		request = 	
				RestAssured.
				given().
					contentType(ContentType.JSON).	
					headers("Content-Type", ContentType.JSON).
					headers("Accept", ContentType.JSON).log().all().
					body(requestBody);		
	}

	@When("I post the request")
	public void i_post_the_request() {		 
	    response = 
	    		request.
				when().
					post(); // POST
	    
	    System.out.println("Response - " + response.asString());
	}
	
	@Then("the response status code is {string}")
	public void the_response_status_code_is(String SC_CREATED) {
		SC_CREATED = "200";
	    response.
	    	then().
	    		assertThat().statusCode(is(equalTo(Integer.parseInt(SC_CREATED))));
	}

	
}
