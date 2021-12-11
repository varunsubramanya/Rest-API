package Demo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Body.PostBody;
import Body.PutBody;
import RestAssuredUtility.Json;

public class Put {

	public static void main(String[] args) {
		PostBody pb=new PostBody();

		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().queryParam("Key", "quick123").header("Content-Type","application/json")
		.body(pb.addPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		
		//System.out.println(response+"/n");
		
		//Using PUT request to update the body
		
		JsonPath js=new JsonPath(response);
		String placeID = js.getString("place_id");
		
		String newAddress = "70 Summer walk, USA";
		
		given().queryParam("Key", "quick123").header("Content-Type","application/json")
		.body(PutBody.updateBody())
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));//validating the status method
		
		//using get method after updating body
		
		String getplace = given().log().all().queryParam("Key", "qaclick123").queryParam("place_id", placeID)
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(getplace);
		String actualAddress = Json.jsonPath(getplace, "address");
		
		Assert.assertEquals(newAddress, actualAddress);
		
		
		
		
	}
}