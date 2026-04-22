package jsonvalidation;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.logging.LogManager;

public class JsonArrayDataValidation {

    public static void main(String[] args) {
        try{
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray)
                    parser.parse(new FileReader("src/test/resources/test.json"));
            for(Object obj : jsonArray){
                JSONObject innerArray = (JSONObject) obj;
                if(innerArray.containsKey("user1")){
                    JSONObject user = (JSONObject) innerArray.get("user1");
                    long id = (long) user.get("id");
                    System.out.println(id);
                }if(innerArray.containsKey("user2")){
                    JSONObject user = (JSONObject) innerArray.get("user2");
                    String name = (String) user.get("name");
                    System.out.println(name);
                }
                if(innerArray.containsKey("user3")){
                    JSONObject user = (JSONObject) innerArray.get("user3");
                    String email = (String) user.get("email");
                    System.out.println(email);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
