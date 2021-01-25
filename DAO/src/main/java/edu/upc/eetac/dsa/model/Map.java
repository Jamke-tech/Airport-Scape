package edu.upc.eetac.dsa.model;

public class Map {
    public int id;
    public String name;
    public String stringMap;

    public Map(int id, String name, String stringMap) {
        this.id = id;
        this.name = name;
        this.stringMap = stringMap;
    }

    public Map() {

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
