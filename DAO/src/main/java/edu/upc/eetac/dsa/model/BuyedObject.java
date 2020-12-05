package edu.upc.eetac.dsa.model;

public class BuyedObject {
    private int idBuyedObject;
    private String name;
    private double price;
    private String description;
    private int attribute;

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
