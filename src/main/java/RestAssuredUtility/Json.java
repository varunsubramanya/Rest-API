package RestAssuredUtility;

import io.restassured.path.json.JsonPath;

public class Json {
public static String jsonPath(String jsonInString,String key) {
	JsonPath jsg=new JsonPath(jsonInString);
	String actualValue = jsg.getString(key);
	return actualValue;

}
}
