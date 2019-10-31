package src1;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

	
public class CreateTweet extends Property1 {
		
	@Test
	public void createTweet() throws Exception 
	{
		RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";
		Response res =Property1.property().queryParam("status","I am learning API  testing at #Qualitest1").
		when().
		post("/update.json").
		then().extract().response();
		
		String response = res.asString();
		log.info(response);
		JsonPath js=new JsonPath(response);               
        String id=js.get("id").toString();
        log.info(id);
        String text=js.get("text").toString();
        log.info(text);
		
	}
}
