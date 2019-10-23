

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BlockUser {

	Properties prop;

	@Test
	public void searchTweet() throws IOException
	{
		User p = new User();
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Online Test\\Desktop\\mahaboob\\MyProject\\src\\data.properties");
		prop.load(fis);
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/blocks";
		Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
		param("screen_name",p.getUsers()).
		when().
		post("/create.json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		
		String response = res.asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String user = js.get("name").toString();
		
		System.out.println("Blocked user is : "+user);
	}
}
