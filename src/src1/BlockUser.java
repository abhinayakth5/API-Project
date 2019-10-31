package src1;


import static io.restassured.RestAssured.given;
import java.io.IOException;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BlockUser extends Property1{

	

	@Test
	public void block() throws IOException
	{
	
	
		RestAssured.baseURI = BlockUserURI;
		Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
		param("screen_name",getUsers()).
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
