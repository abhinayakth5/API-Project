 package src1;

import java.io.IOException;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class ListTweetsofUser extends Property1{

	

	@Test
	public void TweetsList() throws IOException
	{
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res = Property1.property().
		param("screen_name",getUsers()).
		when().
		get("/user_timeline.json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		
		log.info(getUsers());
		String response = res.asString();
		//log.info(response);

		JsonPath js = new JsonPath(response);
		 String Text = js.get("text").toString();
		 log.info(Text);
		
	}
}
