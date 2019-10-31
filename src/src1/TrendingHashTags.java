package src1;



import java.io.IOException;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TrendingHashTags extends Property1 {

	

	@Test
	public void DisplayHashtag() throws IOException
	{

		RestAssured.baseURI="https://api.twitter.com/1.1/trends";
		Response res=Property1.property().	
		when().
		get("/available.json").then().assertThat().statusCode(200).contentType(ContentType.JSON).
		extract().response();
		
		String response=res.asString();
		JsonPath js=new JsonPath(response);
		int count=js.get("size()");
		for(int i=0;i<count;i++)
		{
			String country=js.get("["+i+"].country").toString();
			if(country.equalsIgnoreCase(prop.getProperty("location1")))
			{
				String id=js.get("["+i+"].parentid").toString();
				Response res1=Property1.property().
						param("id",id).
						when().
						get("/place.json").then().assertThat().statusCode(200).contentType(ContentType.JSON).
						extract().response();
						String response1=res1.asString();
						JsonPath js1=new JsonPath(response1);
						int count1=js1.get("["+0+"].trends.size()");
						for(int j=0;j<count1;j++)
						{
							String AllHashtags = js1.getString("["+0+"].trends["+j+"].name");
							String RequiredHastag = js1.getString("["+0+"].trends["+j+"]");
							if(AllHashtags.charAt(0)=='#' && j<=5)
							{
								log.info(RequiredHastag);
							}
						}
						break;
			}
		}
	}
}
