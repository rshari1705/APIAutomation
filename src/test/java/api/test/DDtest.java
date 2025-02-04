package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoint;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDtest {
	@Test(priority=1,dataProvider="Data",dataProviderClass= DataProviders.class)
	public void testPostUser(String id,String userName,String fname,String lname,String email,String pwd,String phn,String statuscode){
		User userPayload= new User();
		userPayload.setId(Integer.parseInt(id)); // Generates a random ID
        userPayload.setUsername(userName); // Random username
        userPayload.setFirstName(fname); // Random first name
        userPayload.setLastName(lname); // Random last name
        userPayload.setEmail(email); // Random email
        userPayload.setPassword(pwd); // Random password
        userPayload.setPhone(phn); // Random phone number
        userPayload.setUserStatus(Integer.parseInt(statuscode));
    		Response response=UserEndPoint.createUser(userPayload);
    		//response.then().log().all();
    		
    		Assert.assertEquals(response.getStatusCode(), 200);
    	
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass= DataProviders.class)
	public void testDeleteUserByName(String userName )
	{
		Response getResponse = UserEndPoint.readUser(userName);
	    Assert.assertEquals(getResponse.getStatusCode(), 200, "User does not exist.");
		
		Response response=UserEndPoint.deleteUser(userName);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
