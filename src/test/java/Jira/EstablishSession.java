package Jira;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class EstablishSession {

	/*
	 * 
	 */
	@Test
	public void login() {
		RestAssured.baseURI="http://localhost:8080";
		
		/*creating session id*/
		
		SessionFilter session=new SessionFilter();
		
		String response = given().header("Content-Type","application/json").
				body("{ \"username\": \"varuns1996\", \"password\": \"Jira@562107\" }").
				filter(session).
				when().post("/rest/auth/1/session").
				then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js= new JsonPath(response);
		String sessionid = js.getString("session.value");
		System.out.println(sessionid);

/*	//creating Issue
	String Createresponse = given().header("Content-Type","application/json").header("Cookie",sessionid).body("{\r\n"
			+ "\"fields\":{\r\n"
			+ "    \"project\":\r\n"
			+ "    {\r\n"
			+ "        \"key\":\"AP\"\r\n"
			+ "        },\r\n"
			+ "        \"summary\":\"Editing comment Creditcard Defect\",\r\n"
			+ "        \"description\":\"creating my bug to edit comment\",\r\n"
			+ "        \"issuetype\":{\r\n"
			+ "            \"name\":\"Bug\"\r\n"
			+ "            }\r\n"
			+ "            }\r\n"
			+ "            }").
	when().post("/rest/api/2/issue").
	then().assertThat().log().all().statusCode(201).extract().response().asString();
	
	JsonPath js1=new JsonPath(Createresponse);
	String IssueKey = js1.getString("key");
	String issueID = js1.getString("id");
	String IssueLink = js1.getString("self");
	
	System.out.println(IssueLink);*/
		
		/* Add comment using POST*/  
		
	String Add_Comment_response = given().pathParam("issueID", "10005").header("Content-Type","application/json").//header("Cookie",sessionid).not working
	body("{\r\n"
			+ "    \"body\": \"this comment is added from Restassured using POST\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}").
	filter(session).
	when().post("/rest/api/2/issue/{issueID}/comment").		//ID will find any value is passed in pathparam in run time
	then().assertThat().statusCode(201).extract().response().asString();
	
	JsonPath js1=new JsonPath(Add_Comment_response);
	String Add_comment_ResponseLink = js1.getString("self");// o/p: http://localhost:8080/rest/api/2/issue/10004/comment/10002
	String commentID = js1.getString("id");
	System.out.println(Add_comment_ResponseLink);
	
	
		/*Edit comment using PUT*/
	
	String editedComment = "this comment is updated using PUT through eclipse while attachment to verify at using Assert";
	
	given().pathParam("issueID", "10005").pathParam("commentID", commentID).header("Content-Type","application/json").
	body("{\r\n"
			+ "    \"body\": \""+editedComment+"\",\r\n"
			+ "    \"visibility\": {\r\n"
			+ "        \"type\": \"role\",\r\n"
			+ "        \"value\": \"Administrators\"\r\n"
			+ "    }\r\n"
			+ "}").
	filter(session).
	when().put("/rest/api/2/issue/{issueID}/comment/{commentID}").
	then().assertThat().statusCode(200).extract().response().asString();
	
	/*adding attachments*/
	
	given().pathParam("issueID","10005").header("X-Atlassian-Token","no-check").header("Content-Type","multipart/form-data").
	multiPart("file",new File("./src/test/resources/jira.txt")).
	filter(session).
	when().post("/rest/api/2/issue/{issueID}/attachments").
	then().assertThat().statusCode(403);

	
	/*Get issues*/
	
	String getResponse = given().filter(session).pathParam("issueID", "10005").when().//queryParam("fields", "issuetype").//queryParam("fields", "project").//to get contents only from project
	get("/rest/api/2/issue/{issueID}").
	then().log().all().assertThat().statusCode(200).extract().response().asString();
	System.out.println(getResponse);
	
		/*retreive and compare comment created in script*/
	
	JsonPath js2=new JsonPath(getResponse);
	int commentsCount = js2.getInt("fields.comment.comments.size()");
	
	for(int i=0;i<commentsCount;i++) {
		String commentIDvalues = js2.getString("fields.comment.comments["+i+"].id").toString();
		if(commentIDvalues.equalsIgnoreCase(commentID)) {
			String updatedComment = js2.getString("fields.comment.comments["+i+"].body").toString();
			System.out.println(updatedComment);
			Assert.assertEquals(editedComment,updatedComment);
		}
		
	}

	}
}
