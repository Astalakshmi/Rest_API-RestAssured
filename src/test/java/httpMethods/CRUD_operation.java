package httpMethods;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class CRUD_operation {
						/************ GET METHOD ******************/
	int id;
	@Test(priority = 1)
	void getList_of_Users() 
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();  // display entire response in console window
	}
	
				/************ POST METHOD ******************/
	@Test(priority=2)
	void Post_Create() {
		Map<String,Object> data = new HashMap<String,Object>();
		JSONObject request = new JSONObject(data);
		request.put("name","Astalakshmi");
		request.put("job", "Software ENGINEER");
		//System.out.println(request.toJSONString());
		
		id =given()
			//.header("ContentType","application/Json")/* note: you can either one line 26 or line27 */
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		/*.then()
			.statusCode(201)
			.log().all();*/		
		
	}
	/************ PUT METHOD ******************/
	
	@Test(priority=3,dependsOnMethods = {"Post_Create"})
	void PUT_UPDATE() {
		//Map<String,Object> data = new HashMap<String,Object>();
		JSONObject request = new JSONObject();
		request.put("name","MANJU");
		request.put("job", "DOCTOR");
		
		
		given()
			
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.put("https://reqres.in/api/users/" + id)
		.then()
			.statusCode(200)
			.log().all();			
		
	}
	/************ PATCH METHOD ******************/
	@Test(priority=4)
	void PATCH_UPDATE() {
		//Map<String,Object> data = new HashMap<String,Object>();
		JSONObject request = new JSONObject();
		request.put("name","MANJU AMULRAJ");
		request.put("job", "DOCTOR");
		
		
		given()
			
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.patch("https://reqres.in/api/users/" + id)
		.then()
			.statusCode(200)
			.log().all();			
		
	}
	/************ DELETE METHOD ******************/
	@Test(priority=5)
	void DELETE() {
		
		given()
		.when()
			.delete("https://reqres.in/api/users/" + id)
		.then()
			.statusCode(204)
			.log().all();			
		
	}
}
