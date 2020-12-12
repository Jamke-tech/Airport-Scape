package edu.upc.eetac.dsa.model;

public class BuyedObject {
    public int id;
    public String name;
    public double price;
    public String description;
    public int attribute;

    public BuyedObject(int id, String name, double price, String description, int attribute) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.attribute = attribute;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }
}
