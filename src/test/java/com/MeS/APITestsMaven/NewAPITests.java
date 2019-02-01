package com.MeS.APITestsMaven;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class NewAPITests {

	@BeforeClass
	public void beforeClass() {
		RestAssured.baseURI = "http://ec2-52-91-203-95.compute-1.amazonaws.com:3000/";
	}

	@Test
	public void post_NewData() {
		given().contentType(ContentType.JSON)
		.body("{" + "\"id\": 11000," + "\"postId\": 11000," + "\"name\": \"AJ Post Req\","
				+ "\"email\": \"aj@aj.com\"," + "\"body\": \"Testing POST method , current time 06:50:46.973\""
				+ "}")
		.when().post("/comments").then().statusCode(201);
	}

	@Test
	public void get_ResponseCodeAndContentType() {
		given().when().get("/comments").then().statusCode(200).and().contentType(ContentType.JSON);
	}

	@Test
	public void get_ResponseBody() {
		String expected = "Anubha";
		Response res = given().when().get("/comments");
		Assert.assertTrue(res.getBody().asString().contains(expected));
	}

	@Test
	public void put_UpdateData() {
		given().contentType(ContentType.JSON)
		.body("{" + "\"id\": 11000," + "\"postId\": 11000," + "\"name\": \"PUT Request AJ\","
				+ "\"email\": \"aj@aj.com\"," + "\"body\": \"Testing PUT method , current time 06:50:46.973\""
				+ "}")
		.when().put("/comments/11000").then().statusCode(200);
	}

	@Test
	public void delete_Data() {
		given().contentType(ContentType.JSON).when().delete("/comments/11000").then().statusCode(500);
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Post, Get, Put and Delete Methods demonstrated");
	}

}
