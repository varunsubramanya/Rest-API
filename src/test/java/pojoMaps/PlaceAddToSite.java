package pojoMaps;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;


public class PlaceAddToSite {
	@Test
	public void ADDPLACE() {
		
	
	
	
	AddPlace ap =new AddPlace();
	ap.setAccuracy(50);
	ap.setAddress("Bang");
	ap.setLanguage("kannada");
	
	ap.setName("jgfo");
	ap.setPhone_number("96875");
	ap.setWebsite("www.gmaps.com");
	
	List<String> mylist = new ArrayList<String>();
	mylist.add("bike");
	mylist.add("car");
	ap.setTypes(mylist);
	
	Location l=new Location();
	l.setLat(335.9823);
	l.setLng(-8709.3543);
	
	ap.setLocation(l);
	
	//System.out.println(ap.getAccuracy());
	RestAssured.baseURI="https://rahulshettyacademy.com";
	
	String response = given().queryParam("Key", "quick123")
	.body(ap)
	.when().post("maps/api/place/add/json")
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	System.out.println(response);
}
}