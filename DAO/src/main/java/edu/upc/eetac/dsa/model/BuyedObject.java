package edu.upc.eetac.dsa.model;

public class BuyedObject {

    public int id;
    public int idObject;
    public String nickNameBuyer;

    public BuyedObject(int id, int idObject, String nickNameBuyer) {
        this.id = id;
        this.idObject = idObject;
        this.nickNameBuyer = nickNameBuyer;
    }

    public BuyedObject() {

    }

    public int getIdObject() {
        return idObject;
    }

    public void setIdObject(int idObject) {
        this.idObject = idObject;
    }

    public String getNickNameBuyer() {
        return nickNameBuyer;
    }

    public void setNickNameBuyer(String nickNameBuyer) {
        this.nickNameBuyer = nickNameBuyer;
    }

    public int getId() {
        return id;
    }

    public String getIdComprador() {
        return nickNameBuyer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdComprador(String nickNameBuyer) {
        this.nickNameBuyer = nickNameBuyer;
    }
}
