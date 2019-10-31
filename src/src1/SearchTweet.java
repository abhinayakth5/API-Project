package src1;

import java.io.IOException;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class SearchTweet extends Property1{

	@Test
	public void searchTweet() throws IOException
	{
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/search/tweets.json";
		Response res = Property1.property().param("q","Qualitest").
		when().
		get().
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		String response = res.asString();
		JsonPath js = new JsonPath(response);
		int count = js.get("statuses.size()");
		log.info(count);
		
		String resp;
		for(int i=0;i<count;i++)
		{
			String place = js.get("statuses["+i+"].user.location").toString();
			if(place.contains("India"))
			{
				resp = js.get("statuses["+i+"]").toString();
				log.info(resp);
			}
		}
	}
}
