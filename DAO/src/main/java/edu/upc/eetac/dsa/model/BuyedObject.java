package edu.upc.eetac.dsa.model;

public class BuyedObject {
    public int idBuyedObject;
    public String name;
    public double price;
    public String description;
    public int attribute;

    public BuyedObject(int idBuyedObject, String name, double price, String description, int attribute) {
        this.idBuyedObject = idBuyedObject;
        this.name = name;
        this.price = price;
        this.description = description;
        this.attribute = attribute;
    }

    public void setIdBuyedObject(int idBuyedObject) {
        this.idBuyedObject = idBuyedObject;
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
