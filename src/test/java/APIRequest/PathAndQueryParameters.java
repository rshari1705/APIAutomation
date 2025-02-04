package APIRequest;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
public class PathAndQueryParameters {
	
	@Test
	void testPathAndQueryParameters() {
		
		given()
			.pathParam("Pathpara", "users")
			.queryParam("page",2)
			.queryParam("id",5)
		.when()
			.get("https://reqres.in/api/{Pathpara}")
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
