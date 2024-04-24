package petStore;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class Petstore_PetModule {
@Test(priority=2)
	void Get_FindbyStatus() {
		given()
			.pathParam("variable_name","findByStatus")
			.queryParam("status","available")
		
		.when()
			.get("https://petstore3.swagger.io/api/v3/pet/{variable_name}")
		.then()
			.statusCode(200)
			.log().all();	
	}
	@Test(priority=3)
	void Get_FindbyTags() {
		given()
			.pathParam("variable_name_findbytag","findByTags")
			.queryParam("tags","string")
	
		.when()
			.get("https://petstore3.swagger.io/api/v3/pet/{variable_name_findbytag}")
		.then()
			.statusCode(200)
			.log().all();
	}

	@Test(priority=1)
	 void Post_NewPetToStore() throws FileNotFoundException 
	{
		File file = new File(".\\body_pet.json");
		FileReader filereader = new FileReader(file);
		JSONTokener token = new JSONTokener(filereader);
		JSONObject obj = new JSONObject(token);
		
		given()
			.contentType("application/json")
			.body(obj.toString())
		
		.when()
			.post("https://petstore3.swagger.io/api/v3/pet")
			
		.then()
			.statusCode(200)
			.body("name",equalTo("doggie"))
			.body("status",equalTo("available"))
			.log().all();
		 
	}	
	
	
	}
