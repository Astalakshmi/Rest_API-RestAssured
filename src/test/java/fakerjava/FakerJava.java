package fakerjava;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerJava {

	@Test
	void Generate_fakerdata() {
		Faker faker = new Faker();
		String lastname = faker.name().lastName();
		System.out.println("lastname:"+lastname);
	}
}
