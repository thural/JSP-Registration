package com.bdc.firstservletapp.utils;

import com.bdc.firstservletapp.beans.Question;

import java.lang.reflect.Field;
import java.util.List;

public class CustomJsonSerializer {

    public static String serializeObject(Object object) {
        if (object instanceof String) return (String) object;

        StringBuilder sb = new StringBuilder();
        Class<?> objClass = object.getClass();
        Field[] fields = objClass.getDeclaredFields();

        sb.append("{");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            try {
                String name = field.getName();
                Object value = field.get(object);
                sb.append("\"").append(name).append("\"").append(":");
                if (value instanceof String) {
                    sb.append("\"").append(value).append("\"");
                } else if (value.getClass().isArray()) {
                    Object[] objectArray = (Object[]) value;
                    serializeArray(sb, objectArray);
                } else {
                    throw new RuntimeException("unsupported object for custom serialization");
                }
                if (i != fields.length - 1) sb.append(",");
            } catch (Exception e) {
                System.out.println("error during custom json serialization: " + e.getMessage());
            }
        }

        sb.append("}");

        return sb.toString();
    }

    private static void serializeArray(StringBuilder sb, Object[] objectArray) {
        sb.append("[");
        for (int j = 0; j < objectArray.length; j++) {
            sb.append("\"").append(serializeObject(objectArray[j])).append("\"");
            if (j != objectArray.length - 1) sb.append(",");
        }
        sb.append("]");
    }

    private static void serializeList(StringBuilder sb, List<Object> list) {
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append("\"").append(serializeObject(list.get(i))).append("\"");
            if (i != list.size() - 1) sb.append(",");
        }
        sb.append("]");
    }

    public static String serializeList(List<Question> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(serializeObject(list.get(i)));
            if (i != list.size() - 1) sb.append(",");
        }
        sb.append("]");
        System.out.println("serialized quiz list: " + sb);
        return sb.toString();
    }

    public static String formatQuizResultJSON(String data) {
        return data.replaceAll("name", "questionName")
                .replaceAll("value", "userAnswer")
                .replaceAll("correctAnswer", "answer");
    }
}