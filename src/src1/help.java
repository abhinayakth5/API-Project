package src1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class help {
	public Logger log=LogManager.getLogger(Property1.class.getName());

	Properties prop;
	   
	  
	   public void property() throws IOException {
		   prop =new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Online Test\\git\\API-Project\\MyProject\\src\\data.properties");
		prop.load(fis);
		}
}
