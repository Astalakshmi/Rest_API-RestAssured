package httpMethods;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class GET_METHODS {

	@Test(priority=1)
	 void Single_User() {
		given()
		
		.when()
			.get("https://reqres.in/api/users/2")
		.then()
			.statusCode(200)
			.body("data.id",equalTo(2))
			.log().all();	
	}
	
	@Test(priority=2)
	void Single_User_Not_Found() {
		given()
		.when()
			.get("https://reqres.in/api/users/23")
		.then()
			.statusCode(404)
			.log().all();
	}
	@Test(priority=3)
	
		void List_resource() {
			given()
			.when()
				.get("https://reqres.in/api/unknown")
			.then()
				.statusCode(200)
				.body("data[0].name",equalTo("cerulean"))
			    .body("data.year",hasItems(2000,2002))
			    .log().all();
		
	}
}
