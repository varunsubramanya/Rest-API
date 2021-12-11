package Demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import JavaUtility.JsonRead;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class addPlaceWithFilesRead {

	@Test
	public void addPlace() throws IOException {
		

		System.out.println("me doing in api resyassured change");
		System.out.println("restassured ");
		System.out.println("no");
		

		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().queryParam("Key", "quick123").header("Content-Type","application/json")
		.body(JsonRead.readJson())
		.when().post("maps/api/place/add/json")
		.then().assertThat().log().all().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		
		//System.out.println(response+"/n");
		
		//Using PUT request to update the body
		
		JsonPath js=new JsonPath(response);
		String placeID = js.getString("place_id");
		
		String newAddress = "70 winter walk, USA";
		
		/*given().queryParam("Key", "quick123").header("Content-Type","application/json")
		.body(PutBody.updateBody())
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));//validating the status method
		
		//using get method after updating body
		
		String getplace = given().log().all().queryParam("Key", "qaclick123").queryParam("place_id", placeID)
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(getplace);
		String actualAddress = Json.jsonPath(getplace, "address");
		
		Assert.assertEquals(newAddress, actualAddress);*/

	
	}
	
	
}
