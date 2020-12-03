package edu.upc.eetac.dsa.model;

public class Map {
    private int idMap;
    private String name;
    private String description;
    private int enemiesNumber;

    public Map(int idMap, String name, String description, int enemiesNumber) {
        this.idMap = idMap;
        this.name = name;
        this.description = description;
        this.enemiesNumber = enemiesNumber;
    }

    public void setIdMap(int idMap) {
        this.idMap = idMap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnemiesNumber(int enemiesNumber) {
        this.enemiesNumber = enemiesNumber;
    }
}
