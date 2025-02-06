package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.endpoints.UserEndPoint;
import api.payload.Pet;
import io.restassured.response.Response;

public class PetTests {
	static Faker faker;
	Pet pet;
	public Logger logger=LogManager.getLogger(this.getClass());
	@BeforeClass
	public void setup() {
		
			 faker = new Faker();

	        // Creating a new Pet object with fake data
	         pet = new Pet();

	        // Set fake data for the Pet object
	        pet.setId(faker.number().randomDigit()); // Random digit as id
	        pet.setName(faker.animal().name()); // Random pet name (e.g., dog, cat)
	        pet.setStatus("available"); // Hardcoded status for simplicity

	        // Set Category with fake data
	        Pet.Category category = new Pet.Category();
	        category.setId(faker.number().randomDigit()); // Random id for category
	        category.setName(faker.commerce().department()); // Random category name (e.g., Dogs, Cats, etc.)
	        pet.setCategory(category);

	        // Set photoUrls with fake URLs
	        pet.setPhotoUrls(new String[]{
	            faker.internet().url(),
	            faker.internet().url()
	        });

	        // Set Tags with fake data
	        Pet.Tag tag = new Pet.Tag();
	        tag.setId(faker.number().randomDigit()); // Random id for tag
	        tag.setName(faker.lorem().word()); // Random tag name (e.g., playful, friendly)
	        pet.setTags(new Pet.Tag[]{tag});
		
	}
	
	@Test(priority=1)
	public void testPostUser(){
		logger.info("******Creating Pet***");
		Response response=PetEndPoints.createPet(pet);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******Pet is created***");
	}
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("******Reading User info***");
		Response response=PetEndPoints.readPet(this.pet.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******User info Readed***");
	}
	@Test(priority=3)
	public void testupdateUserByName()
	{
		logger.info("******Updating user info***");
		  pet.setId(faker.number().randomDigit()); // Random digit as id
	        pet.setName(faker.animal().name()); // Random pet name (e.g., dog, cat)
	        pet.setStatus("available"); // Hardcoded status for simplicity

	        // Set Category with fake data
	        Pet.Category category = new Pet.Category();
	        category.setId(faker.number().randomDigit()); // Random id for category
	        category.setName(faker.commerce().department()); // Random category name (e.g., Dogs, Cats, etc.)
	        pet.setCategory(category);

	        // Set photoUrls with fake URLs
	        pet.setPhotoUrls(new String[]{
	            faker.internet().url(),
	            faker.internet().url()
	        });

	        // Set Tags with fake data
	        Pet.Tag tag = new Pet.Tag();
	        tag.setId(faker.number().randomDigit()); // Random id for tag
	        tag.setName(faker.lorem().word()); // Random tag name (e.g., playful, friendly)
	        pet.setTags(new Pet.Tag[]{tag});
		
	Response response=PetEndPoints.UpdatePet(pet);
	response.then().log().body().statusCode(200);
	
	Assert.assertEquals(response.getStatusCode(), 200);
	//checking data after updation
	
	Response responseAfterUpdate=PetEndPoints.readPet(this.pet.getId());
	responseAfterUpdate.then().log().all();
	Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	logger.info("******Updated user info***");
	}
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("******Deleting user info***");
		Response response=PetEndPoints.deleteUser(this.pet.getId());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******Deleted user info***");
	}

}
