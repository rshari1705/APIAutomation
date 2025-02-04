package APIRequest;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
public class PostRequestWays {
	//@Test
	void PostUsingOrgJson() {
		JSONObject data= new JSONObject();
		data.put("id","01");
		data.put("name","testuser");
		data.put("class","01");
		data.put("sex","male");
		given()
				.contentType("application/json")
				.body(data.toString())
			.when()
				.post("http://localhost:3000/students")
			
			.then()
				.statusCode(201)
				.log().all();
	}
	//@Test
	void PostUsingPOJO() {
		POJO_postRequset data= new POJO_postRequset();
		data.setId("02");
		data.setName("TestUser02");
		data.setClass("9");
		data.setSex("female");
		given()
				.contentType("application/json")
				.body(data)
			.when()
				.post("http://localhost:3000/students")
			
			.then()
				.statusCode(201)
				.log().all();
	}
	@Test
	void PostUsingExternalFile() throws FileNotFoundException {
		File json =new File("src/test/resources/body.json");
		FileReader rd = new FileReader(json);
		JSONTokener jt = new JSONTokener(rd);
		JSONObject data= new JSONObject(jt);
		given()
				.contentType("application/json")
				.body(data.toString())
			.when()
				.post("http://localhost:3000/students")
			
			.then()
				.statusCode(201)
				.log().all();
	}
	

}
