package jsonvalidation;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonObjectDataValidation {
    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader("src/test/resources/data.json"));

            String name = (String) object.get("name");
            String age = String.valueOf(object.get("age"));

            JSONObject obj = (JSONObject) object.get("address");
            String city = (String) obj.get("zip");

            System.out.println("name: " + name);
            System.out.println("age: " + age);
            System.out.println("city: " + city);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
}
