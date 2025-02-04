package APIRequest;
import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPrequests {
	int id;
	@Test(priority=1)
	void getUsers() {
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then().statusCode(200)
			.body("page",equalTo(2))
			.log().all();
		
	}
	@Test(priority=2)
	void CreateUser() {
		
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("name", "tester");
		hm.put("job", "QA");
		
		id=given()
			.contentType("application/json")
			.body(hm)
		.when()
			.post("https://reqres.in/api/users").jsonPath().getInt("id");
		
		/*.then()
			.statusCode(201)
			.log().all();*/
	}
	
	@Test(priority=3,dependsOnMethods={"CreateUser"})
	void UpdateUser() {
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("name", "tester");
		hm.put("job", "Testet");
		
		given()
			.contentType("application/json")
			.body(hm)
		.when()
			.put("https://reqres.in/api/users/"+id)
			
		
		.then()
			.statusCode(200)
			.log().all();
		
		
	}
	@Test(priority=4)
	void DeleteUser() {
		
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("name", "tester");
		hm.put("job", "QA");
		
		given()
			
		.when()
			.delete("https://reqres.in/api/users")
		
		.then()
			.statusCode(204)
			.log().all();
	}
	
}
