
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Butter {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("Ashok");
        list.add("Vinod");
        list.add("Kumar");
        list.add("Ashok");
        list.add("Mariyala");
        list.add("Venkata");
        list.add("Mariyala");
        list.add("Vinod");
        list.add("Ashok");
        list.add("Kumar");
        Map<String, Integer> counts = new HashMap<String, Integer>();

        for (String str : list) {
            if (counts.containsKey(str)) {
                counts.put(str, counts.get(str) + 1);
            } else {
                counts.put(str, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

    }
}
