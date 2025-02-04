package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	public Logger logger=LogManager.getLogger(this.getClass());
	//Logger log;
	@BeforeClass
	public void setup() {
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode()); // Generates a random ID
        userPayload.setUsername(faker.name().username()); // Random username
        userPayload.setFirstName(faker.name().firstName()); // Random first name
        userPayload.setLastName(faker.name().lastName()); // Random last name
        userPayload.setEmail(faker.internet().emailAddress()); // Random email
        userPayload.setPassword(faker.internet().password()); // Random password
        userPayload.setPhone(faker.phoneNumber().phoneNumber()); // Random phone number
        //userPayload.setUserStatus(faker.number().numberBetween(0, 2)); // Random user status (0 or 1)
        
        //logs
     // Logger log=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testPostUser(){
		logger.info("******Creating User***");
		Response response=UserEndPoint.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******User is created***");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("******Reading User info***");
		Response response=UserEndPoint.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******User info Readed***");
	}
	@Test(priority=3)
	public void testupdateUserByName()
	{
		logger.info("******Updating user info***");
		userPayload.setFirstName(faker.name().firstName()); // Random first name
        userPayload.setLastName(faker.name().lastName()); 
        userPayload.setEmail(faker.internet().emailAddress());
	Response response=UserEndPoint.UpdateUser(this.userPayload.getUsername(),userPayload);
	response.then().log().body().statusCode(200);
	
	Assert.assertEquals(response.getStatusCode(), 200);
	//checking data after updation
	
	Response responseAfterUpdate=UserEndPoint.readUser(this.userPayload.getUsername());
	responseAfterUpdate.then().log().all();
	Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	logger.info("******Updated user info***");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("******Deleting user info***");
		Response response=UserEndPoint.deleteUser(this.userPayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******Deleted user info***");
	}
}
