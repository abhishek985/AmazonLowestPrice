package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyValues {

	private PropertyValues() {
		throw new IllegalStateException("PropertyValues class");
	}

	static Properties prop;
	public static String getPropertyValue(String key){
		String value="";
		try {
			prop=new Properties();
			prop.load(new FileInputStream
					(new File("/Users/b0206672//eclipse-workspace/webautomation/src/main/java/config/config.properties")));
			value= prop.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}
