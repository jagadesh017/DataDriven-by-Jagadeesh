package queries;

import java.util.HashMap;
import java.util.Map;

public class ExampleTest {
    public static void main(String[] args) {
        
        String name ="testing";


        Map<Character, Integer> map = new HashMap<>();

        for(Character ch : name.toCharArray())
        {
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        System.out.println(map);
        
    }

}
