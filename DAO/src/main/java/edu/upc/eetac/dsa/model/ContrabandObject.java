package edu.upc.eetac.dsa.model;

public class ContrabandObject {
    public int idContrabandObject;
    public String name;
    public String description;
    public int value;

    public ContrabandObject(int idContrabandObject, String name, String description, int value) {
        this.idContrabandObject = idContrabandObject;
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public void setIdContrabandObject(int idContrabandObject) {
        this.idContrabandObject = idContrabandObject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
