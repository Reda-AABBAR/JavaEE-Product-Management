package convertor;

import java.util.Map;

import entity.Creation;
import entity.TypeProduit;

public class MapToJson {
	public static String convertHashMapToJson(Map<String, Object> hashMap) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        // Iterate through the entries in the HashMap
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            // Append key and value to the JSON string
            jsonBuilder.append("\"").append(entry.getKey()).append("\":");

            // Handle different value types
            if (entry.getValue() instanceof String) {
                jsonBuilder.append("\"").append(entry.getValue()).append("\"");
                
            }
            else if(entry.getValue() instanceof Creation)
           	 jsonBuilder.append(((Creation)entry.getValue()).toJson());
            else if(entry.getValue() instanceof TypeProduit)
              	 jsonBuilder.append(((TypeProduit)entry.getValue()).toJson());
            else {
                jsonBuilder.append(entry.getValue());
            }

            // Add a comma for the next entry
            jsonBuilder.append(",");
        }

        // Remove the trailing comma if there are entries
        if (!hashMap.isEmpty()) {
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);
        }

        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }
}
