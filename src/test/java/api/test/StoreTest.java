package api.test;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTest {
	static Faker faker;
	Store store;
	public Logger logger=LogManager.getLogger(this.getClass());
	
	@BeforeClass
	public void setup() {
		
			 faker = new Faker();

	        // Creating a new store object with fake data
			 store =new Store();

			 store.setId(faker.number().randomNumber()); // Random number as ID
			 store.setPetId(faker.number().randomNumber()); // Random number for Pet ID
			 store.setQuantity(faker.number().numberBetween(1, 100)); // Random quantity between 1 and 100
			    
			    // Set shipDate with a random date (e.g., within the last month)
			 store.setShipDate(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 30)));

			    // Set status with a random choice from predefined statuses
			 store.setStatus(faker.options().option("shipped", "pending", "delivered", "cancelled"));

			    // Set complete as a random boolean value
			 store.setComplete(faker.bool().bool());
		
	}
	
	//Updated by athira
	@Test(priority=1)
	public void storePostOder(){
		
		logger.info("******Creating Store Oder***");
		
		Response response=StoreEndPoints.createStoreOder(store);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******Store order  is created***");
	}
	@Test(priority=2)
	public void storeGetByOderId()
	{
		logger.info("******Reading Oder  info***");
		Response response=StoreEndPoints.readStore(this.store.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******Order info Readed***");
	}

	/*
	 * @Test(priority=3) public void storeUpdateUserByName() {
	 * logger.info("******Updating Order info***");
	 * store.setId(faker.number().randomNumber()); // Random number as ID
	 * store.setPetId(faker.number().randomNumber()); // Random number for Pet ID
	 * store.setQuantity(faker.number().numberBetween(1, 100)); // Random quantity
	 * between 1 and 100
	 * 
	 * // Set shipDate with a random date (e.g., within the last month)
	 * store.setShipDate(LocalDateTime.now().minusDays(faker.number().numberBetween(
	 * 1, 30)));
	 * 
	 * // Set status with a random choice from predefined statuses
	 * store.setStatus(faker.options().option("shipped", "pending", "delivered",
	 * "cancelled"));
	 * 
	 * // Set complete as a random boolean value
	 * store.setComplete(faker.bool().bool());
	 * 
	 * Response response=StoreEndPoints.UpdateStore(store);
	 * response.then().log().body().statusCode(200);
	 * 
	 * Assert.assertEquals(response.getStatusCode(), 200); //checking data after
	 * updation
	 * 
	 * Response responseAfterUpdate=StoreEndPoints.readStore(this.store.getId());
	 * responseAfterUpdate.then().log().all();
	 * Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	 * logger.info("******Updated order info***"); }
	 */
	@Test(priority=4)
	public void storeDeleteUserByName()
	{
		logger.info("******Deleting oder info***");
		Response response=StoreEndPoints.deleteUser(this.store.getId());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("******Deleted order info***");
	}

}
