package ru.IrenaDemchenkova;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        String json = readString(new File("result.json"));
        List<Employee> list = jsonToList(json);
        for (Employee employee :
                list) {
            System.out.println(employee.toString());
        }
    }

    public static String readString(File filename) {
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String s;
            while ((s = br.readLine()) != null) {
                jsonBuilder.append(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return jsonBuilder.toString();
    }

    public static List<Employee> jsonToList (String json) throws ParseException {
       List<Employee> jsonToList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        JSONArray arrayJson = (JSONArray) parser.parse(json);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        for (int i = 0; i<arrayJson.size(); i++){
           String str = arrayJson.get(i).toString();
           jsonToList.add(gson.fromJson(str, Employee.class));
        }
        return jsonToList;
        }
    }