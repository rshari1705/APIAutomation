package api.endpoints;
/*
 * swagger URI--> https://petstore.swagger.io/v2/
 * Create user:https://petstore.swagger.io/v2/user
 * Get user:https://petstore.swagger.io/v2/user/{username}
 * update user:https://petstore.swagger.io/v2/user/{username}
 * delete user:https://petstore.swagger.io/v2/user/{username}
 * */
public class Routes {
	
	public static String base_url ="https://petstore.swagger.io/v2";
	
	//User Module
	
	public static String post_url= base_url+"/user";
	public static String get_url= base_url+"/user/{username}";
	public static String put_url= base_url+"/user/{username}";
	public static String delete_url= base_url+"/user/{username}";
	
	//Pet Module
	
	public static String pet_post_url= base_url+"/pet";
	public static String pet_get_url= base_url+"/pet/{petId}";
	public static String pet_put_url= base_url+"/pet";
	public static String pet_delete_url= base_url+"/pet/{petId}";

}
