package Demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Body.PostBody;
import RestAssuredUtility.Json;
import io.restassured.RestAssured;

public class DynamiJsonWithDataprovider {
	@Test(dataProvider="detailsBook")	//specify dataprovider here
	private void addBook(String isbn, String aisleNum) {	//declare parameters in test method
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().header("Content-Type","application/json").
		body(PostBody.addBook(isbn,aisleNum)).	//pass dataprovider here
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).body("ID",equalTo(isbn+aisleNum)).extract().response().asString();//concatinating dataprovider
		
		String id = Json.jsonPath(response, "ID");
		System.out.println(id);

	}
	
	@DataProvider
	public Object[][]  detailsBook() {
		Object[][] objArr = new Object[][] {{"asfffd","45547"},{"adggvnr","74555"},{"advvsfhg","57958"}};
		return objArr;
	}

}
