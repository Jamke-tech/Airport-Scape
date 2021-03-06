package edu.upc.eetac.dsa.model;

public class Objects {
        public int id;
        public String name;
        public int price;
        public String description;
        public int attribute;
        public boolean bag;
        public String urlImage;

    public Objects(int id, String name, int price, String description, int attribute, boolean bag, String urlImage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.attribute = attribute;
        this.bag = bag;
        this.urlImage = urlImage;
    }

    public Objects() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setId(int id) {
        this.id = id;
    }

        public void setName(String name) {
        this.name = name;
    }

        public void setPrice(int price) {
        this.price = price;
    }

        public void setDescription(String description) {
        this.description = description;
    }

        public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    public boolean isBag() { return bag;}

    public void setBag(boolean bag) { this.bag = bag; }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
