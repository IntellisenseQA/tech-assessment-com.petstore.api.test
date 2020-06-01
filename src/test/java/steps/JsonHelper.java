package steps;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonHelper {

    public static List<String> filterJsonArray(String array, String keyOne, Object valueOne, String keyTwo, Object valueTwo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map[] nodes = mapper.readValue(array, HashMap[].class);
        List<String> filteredPets= new ArrayList<>();
        for (Map node : nodes) {
            if (node.containsKey(keyOne) && node.containsKey(keyTwo)) {
                if (node.get(keyOne).equals(valueOne) && node.get(keyTwo).equals(valueTwo)) {
                    filteredPets.add(mapper.writeValueAsString(node));
                }
            }
        }

        return filteredPets;
    }
}
