package simpleBooksAPI;
import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import com.github.javafaker.Faker;

public class Books {

	public static String base_url="https://simple-books-api.glitch.me";
	
	@Test(priority=1)
	void Get_Status() {   
		
		given()
			.pathParam("status_name","status")
			
		.when()
			.get(base_url+"/{status_name}")
		.then()
			.statusCode(200)
			.log().all();	
	}

	@Test(priority=2)
	void Get_List_of_Books() {   
		
		given()
			.pathParam("books_name","books")
			.queryParam("type","fiction")
			.queryParam("limit", 15)
			
		.when()
			.get(base_url+"/{books_name}")
		.then()
			.statusCode(200)
			.log().all();	
	}
	@Test(priority=3)
	void Get_Singlebook() {   
		
		given()
			.pathParam("books_name","books")
			.param("bookid_name",":bookId")
			
		.when()
			.get(base_url+"/{books_name}","/{bookid_name}")   // used two pathparam in single in request separated by comma(,)
		.then()
			.statusCode(200)
			.log().all();	
	}
	   //*************API KEY Authentication**********************//
	//@Test(priority=4)
		void Post_API_Key() {   
			
		Map<String,Object> map = new HashMap<String,Object>();
		JSONObject obj = new JSONObject(map);
		obj.put("clientName","book");                    
		obj.put("clientEmail","navisha@gmail.com");
		
			given()
			    .contentType(ContentType.JSON).accept(ContentType.JSON)
			    .body(obj.toJSONString())
				.pathParam("apiclients_name","api-clients")
						
			.when()
				.post(base_url+"/{apiclients_name}")  
			.then()
				.statusCode(201)
				.log().all();	
		}
//    "accessToken": "800ab44a91610434e2ee5c1e5705191c4073d04677bfdf4e5fae750d79c85681"
	
	@Test(priority=5)
	void Post_Submit_an_Order() {   
		
		String accessToken = "800ab44a91610434e2ee5c1e5705191c4073d04677bfdf4e5fae750d79c85681";
		
		Map<String,Object> mapping = new HashMap<String,Object>();
		JSONObject object = new JSONObject(mapping);
		object.put("bookId",1);                    
		object.put("customerName","John");
		
		given()
			.headers("Authorization","Bearer "+accessToken)
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(object.toJSONString())
			.pathParam("order_name","orders")
					
		.when()
			.post(base_url+"/{order_name}")  
		.then()
			.statusCode(201)
			.log().all();	
	}
	@Test(priority=6)
	void Get_All_Orders() {   
		
		String accessToken = "800ab44a91610434e2ee5c1e5705191c4073d04677bfdf4e5fae750d79c85681";
			
		given()
			.headers("Authorization","Bearer "+accessToken)
			.pathParam("order_name","orders")
					
		.when()
			.get(base_url+"/{order_name}")  
		.then()
			.statusCode(200)
			.log().all();	
	}
	
	@Test(priority=7)
	void Get_An_Orders() {   
		
		String accessToken = "800ab44a91610434e2ee5c1e5705191c4073d04677bfdf4e5fae750d79c85681";
	
		given()
			.headers("Authorization","Bearer "+accessToken)
			.pathParam("order_name","orders")
			.queryParam("orderid_name",":orderId")	//previous i used write in pathparam struck with error so i changed to queryparam
					
		.when()
			.get(base_url+"/{order_name}")
			
		.then()
			.statusCode(200)
			.log().all();	
	}
	
					// "id": "E5FS7S36_-oLTyWF0eYW1",
	

	
	@Test(priority=8)
	void Update_an_Order() {  
		
		
		String accessToken = "800ab44a91610434e2ee5c1e5705191c4073d04677bfdf4e5fae750d79c85681";
	
			Faker faker = new Faker();
			String lastname = faker.name().lastName();
			System.out.println("lastname:"+lastname);
		
		
		Map<String,Object> map1 = new HashMap<String,Object>();
		JSONObject obj1 = new JSONObject(map1);
		
		obj1.put("customerName","John"+lastname);
		System.out.println("lastname:"+lastname); // generate faker lastname
		
		 String id = "E5FS7S36_-oLTyWF0eYW1";
		 String orderid_name= new String(id);
	
		given()
			.headers("Authorization","Bearer "+accessToken)
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.body(obj1.toJSONString())
			.pathParam("order_name","orders")
			.queryParam("orderid_name",":orderId")
					
		.when()
			.patch(base_url+"/{order_name}")
			
		.then()
			.statusCode(200)
			.body("customerName",equalTo("John"+lastname))
			.log().all();	
	}

	
	@Test(priority=10)
	void Delete_an_Order() {
		String accessToken = "800ab44a91610434e2ee5c1e5705191c4073d04677bfdf4e5fae750d79c85681";
		
		String id = "E5FS7S36_-oLTyWF0eYW1";
		String orderid_name= new String(id);
		given()
			.headers("Authorization","Bearer "+accessToken)
			.contentType(ContentType.JSON).accept(ContentType.JSON)
			.pathParam("order_name","orders")
			.queryParam("orderid_name",":orderId")
		.when()
			.delete(base_url+"/{order_name}")
		.then()
			.statusCode(204)
			.log().all();			
		
	}
}
