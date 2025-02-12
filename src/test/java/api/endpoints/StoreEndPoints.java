package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {
public static Response createStoreOder(Store payload) {
		
		Response response =given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(Routes.store_post_url);
			
			return response;	
		
	}

public static Response readStore(Long Id) {
	
	Response response =given()
			.pathParam("orderId", Id)
		.when()
			.get(Routes.store_get_url);
		
		return response;	
	
}
public static Response UpdateStore(Store payload)
{
Response response =given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.body(payload)
	
.when()
	.put(Routes.store_put_url);

return response;
}
public static Response deleteUser(Long Id)
{
Response response =given()
		.pathParam("orderId", Id)
.when()
	.delete(Routes.store_delete_url);

return response;
}
}
