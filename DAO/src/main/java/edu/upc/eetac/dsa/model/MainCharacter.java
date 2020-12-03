package edu.upc.eetac.dsa.model;

public class MainCharacter {
    private String name;
    private int velocity;
    private double suspicionPercentage;
    private Bag bag;
    // puede ir tambien algun elemento relacionado con su imagen en unity


    public MainCharacter(String name, int velocity, double suspicionPercentage, Bag bag) {
        this.name = name;
        this.velocity = velocity;
        this.suspicionPercentage = suspicionPercentage;
        this.bag = bag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setSuspicionPercentage(double suspicionPercentage) {
        this.suspicionPercentage = suspicionPercentage;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }
}
