package edu.upc.eetac.dsa.model;

import java.util.HashMap;

public class Bag {
    private int idBag;
    private String name;
    private int capacity;
    private String description;
    private HashMap<Integer, ContrabandObject> contrabandObjects;

    public Bag(int idBag, String name, int capacity, String description, HashMap<Integer, ContrabandObject> contrabandObjects) {
        this.idBag = idBag;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.contrabandObjects = contrabandObjects;
    }

    public void setIdBag(int idBag) {
        this.idBag = idBag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContrabandObjects(HashMap<Integer, ContrabandObject> contrabandObjects) {
        this.contrabandObjects = contrabandObjects;
    }
}