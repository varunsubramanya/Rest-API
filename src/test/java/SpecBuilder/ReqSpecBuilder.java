package SpecBuilder;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class ReqSpecBuilder {
public static void main(String[] args) {
	
	RequestSpecification Req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
	.addQueryParam("Key", "quick123").setContentType(ContentType.JSON)
	.build();
	//RestAssured.baseURI="https://rahulshettyacademy.com";
	//String response = given().queryParam("Key", "quick123")
	 RequestSpecification req = given().spec(Req)
	.body("");
	 
	ResponseSpecification Res = new ResponseSpecBuilder().expectStatusCode(200)
	.expectContentType(ContentType.JSON).build();
	
	String RESPONSE = req.when().post("maps/api/place/add/json")
	.then().spec(Res).extract().response().asString();
	
}
}