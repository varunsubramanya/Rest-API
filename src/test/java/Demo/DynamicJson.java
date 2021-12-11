package Demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import Body.PostBody;
import RestAssuredUtility.Json;
import io.restassured.RestAssured;

public class DynamicJson {
	@Test(dataProvider="detailsBook")
	private void addBook() {
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().header("Content-Type","application/json").
		body(PostBody.addBook("asjhjd","123345")).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).body("ID",equalTo("asdfmnb192837")).extract().response().asString();
		
		String id = Json.jsonPath(response, "ID");
		System.out.println(id);

	}

}
