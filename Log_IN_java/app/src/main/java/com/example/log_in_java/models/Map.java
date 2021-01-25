package com.example.log_in_java.models;

public class Map {

    private static Map mapinstance;

    private int id;
    private String name;
    private String stringMap;



    public Map(){}

    public static synchronized Map getInstance(){
        if(mapinstance == null) {
            mapinstance = new Map();
        }
        return mapinstance;
    }

    public Map(int id, String name, String stringMap) {
        this.id = id;
        this.name = name;
        this.stringMap = stringMap;
    }

    public static Map getMapinstance() {
        return mapinstance;
    }

    public static void setMapinstance(Map mapinstance) {
        Map.mapinstance = mapinstance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStringMap() {
        return stringMap;
    }

    public void setStringMap(String stringMap) {
        this.stringMap = stringMap;
    }
}
