package APIRequest;

import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ParsingJsonResponseData {
	
	@Test 
	void testJsonResponse() {
		/*given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/students")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("students[1].class",equalTo("9th Grade"));*/
		
		Response res = given()
			.contentType(ContentType.JSON)
			
		.when()
			.get("http://localhost:3000/students");
	    JSONArray jsonArray = new JSONArray(res.asString());

        // Iterate through the array of objects
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject bookObject = jsonArray.getJSONObject(i);
            String bookTitle = bookObject.getString("class");
            System.out.println(bookTitle);
		
	}

}}
