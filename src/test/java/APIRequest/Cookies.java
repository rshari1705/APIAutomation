package APIRequest;
import static io.restassured.RestAssured.*;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
public class Cookies 
{
	//@Test
	void getCookiesInfo() 
	{
		Response res=given()
		
	.when()
		.get("https://www.google.co.in/");
	
	Map<String,String>cookies_values=res.getCookies();
	
	for(String k:cookies_values.keySet()) 
		{
			String Cookies_Value=res.getCookie(k);
			System.out.println(k+" : "+Cookies_Value);
		
		}
	}
	
	@Test
	void getHeaderInfo() {
		
		Response res=given()
				
				.when()
					.get("https://www.google.co.in/");
				
				Headers Headers_value=res.getHeaders();
				
				for(Header hd: Headers_value) 
					{
						
						System.out.println(hd.getName()+" : "+hd.getValue());
					
					}
		
		
	}
}


