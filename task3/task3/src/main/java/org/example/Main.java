package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();

        Map<Integer, String> map = getMapFromJson(args[0]);

        try {
            JsonNode rootNode = objectMapper.readTree(new File(args[1]));
            fillValues(rootNode, map);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(args[2]), rootNode);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private static Map<Integer, String> getMapFromJson(String path) {
        ObjectMapper mapper = new ObjectMapper();

        Map<Integer, String> map = new HashMap<>();

        try {
            JsonNode rootNode = mapper.readTree(new File(path));
            JsonNode valuesArray = rootNode.path("values");

            for (JsonNode jsonNode : valuesArray) {
                int id = jsonNode.path("id").asInt();
                String value = jsonNode.get("value").asText();
                map.put(id, value);
            }


        } catch (IOException e) {
            System.out.println(e);
        }

        return map;
    }


    private static void fillValues(JsonNode jsonNode, Map<Integer, String> map) {
        if (jsonNode.isObject()) {
            ObjectNode objectNode = (ObjectNode) jsonNode;

            if (objectNode.has("id") && objectNode.has("value")) {
                int id = objectNode.get("id").asInt();
                if (map.containsKey(id))  objectNode.put("value", id);
            }

            if (objectNode.has("values") && objectNode.get("values").isArray()) {
                for (JsonNode node : objectNode.get("values")) fillValues(node, map);
            }

            if (objectNode.has("tests") && objectNode.get("tests").isArray()) {
                for (JsonNode node : objectNode.get("tests")) fillValues(node, map);
            }

        } else if(jsonNode.isArray()) {
            for (JsonNode node : jsonNode) fillValues(node, map);
        }
    }
}