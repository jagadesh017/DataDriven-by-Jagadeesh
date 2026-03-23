package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilities {


    public static Object fetchPropertyFile(String key) throws IOException {

        FileInputStream file = new FileInputStream("./config.properties");

        Properties property = new Properties();
        property.load(file);
        property.get(key);
        return property.get(key);

    }
}
