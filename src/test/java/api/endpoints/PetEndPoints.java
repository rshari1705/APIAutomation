package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Pet;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {
	
	public static Response createPet(Pet payload) {
		
		Response response =given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(Routes.pet_post_url);
			
			return response;	
		
	}
public static Response readPet(int Id) {
		
		Response response =given()
				.pathParam("petId", Id)
			.when()
				.get(Routes.pet_get_url);
			
			return response;	
		
	}
public static Response UpdatePet(Pet payload)
{
	Response response =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
	.when()
		.put(Routes.pet_put_url);
	
	return response;
}
public static Response deleteUser(int Id)
{
	Response response =given()
			.pathParam("petId", Id)
	.when()
		.delete(Routes.pet_delete_url);
	
	return response;
}

}
