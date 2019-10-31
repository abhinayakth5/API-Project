package src1;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DisplayingUsername extends Property1 {
	
	
	@Test
	public void username() throws Exception 
	{
		
		RestAssured.baseURI = "https://api.twitter.com/1.1/search";
		Response res = Property1.property().param(UKey,UValue).
		when().
		get("/tweets.json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		
		String response = res.asString();
		JsonPath js = new JsonPath(response);
		int count = js.get("statuses.size()");
		log.info(count);
		
		for(int i=0;i<count;i++)
		{
			String UserName = js.get("statuses["+i+"].user.screen_name");
			log.info(UserName);
		}
	}
}
