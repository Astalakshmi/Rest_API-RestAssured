package httpMethods;
import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;


public class POST_METHODS {
	
	/***** EXAMPLE 1 : create POST CREATE ******************/
	
	@Test(priority=1)
	void Post_Create() {
		Map<String,Object> data = new HashMap<String,Object>();
		JSONObject request = new JSONObject(data);
		request.put("name","Astalakshmi");
		request.put("job", "Software");
		//System.out.println(request.toJSONString());
		
		given()
			//.header("ContentType","application/Json")/* note: you can either one line 26 or line27 */
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.log().all();			
		
	}
	
	/***** EXAMPLE 2 : create POST REGISTER ******************/
	
	@Test(priority=2)
	void Post_REGISTER() {
		Map<String,Object> map = new HashMap<String,Object>();
		JSONObject obj = new JSONObject(map);
		obj.put("email","eve.holt@reqres.in");                    
		obj.put("password", "pistol");
		//System.out.println(request.toJSONString());
		
		given()
			//.header("ContentType","application/Json")/* note: you can either one line 26 or line27 */
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(obj.toJSONString())
		.when()
			.post("https://reqres.in/api/register")
		.then()
			.statusCode(200)
			.log().all();			
		
	}
	
}
