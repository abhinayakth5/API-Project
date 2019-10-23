

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateTweet {

	Properties prop;

	@Test
	public void createTweet() throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Online Test\\Desktop\\mahaboob\\MyProject\\src\\data.properties");
		prop.load(fis);
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
		queryParam("status","I am learning API  testing  #Qualitest").
		when().
		post("/update.json").
		then().extract().response();
		
		String response = res.asString();
		System.out.println(response);
		
	}
}
