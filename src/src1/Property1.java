package src1;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;




public class Property1 {
	
	public Logger log=LogManager.getLogger(Property1.class.getName());
	static Properties prop=new Properties();
	
	String createKey=prop.getProperty("CreateKey");
	String createValue=prop.getProperty("Createvalue");
	String UKey=prop.getProperty("Ukey");
	String UValue=prop.getProperty("Uvalue");
	//String createKey=prop.getProperty("CreateKey");
	//String craeteValue=prop.getProperty("Createvalue");
	//String createKey=prop.getProperty("CreateKey");
	//String craeteValue=prop.getProperty("Createvalue");
	
	
	
  
	 String CreateURI=prop.getProperty("createURI");
	 String BlockUserURI=prop.getProperty("BlockURI");
 
	
  
    public static  RequestSpecification property() throws IOException {
	   FileInputStream fis = new FileInputStream("C:\\Users\\Online Test\\git\\API-Project\\MyProject\\src\\data.properties");
	   prop.load(fis);
	   return  given().auth().oauth(prop.getProperty("ConsumerKey"), prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret"));
		
	}
    public String getUsers() throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Online Test\\git\\API-Project\\MyProject\\src\\data.properties");
		prop.load(fis);
		RestAssured.baseURI = "https://api.twitter.com/1.1/users/search.json";
		Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"),prop.getProperty("Token"),prop.getProperty("TokenSecret")).
		param("q","Barack Obama").
		when().
		get().
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		
		String response = res.asString();
		//log.info(response);
		
		JsonPath js = new JsonPath(response);
		int count = js.get("size()");
		//log.info(count);
		
		for(int i=0;i<count;i++)
		{
			String User = js.get("["+i+"].screen_name");
			//log.info(User);
			if(User.equalsIgnoreCase(prop.getProperty("User")))
			{
				return User;
			}
		}
		return null;
	}

}
 