package com.example.emae;

public class Client {
    private String idK;
    private String nameK;
    private String surnameK;

    public Client(String idK, String nameK, String surnameK) {
        this.idK = idK;
        this.nameK = nameK;
        this.surnameK = surnameK;
    }
    public String getIdK() {
        return idK;
    }
    public String getNameK() {
        return nameK;
    }
    public String getSurnameK() {
        return surnameK;
    }

    @Override
    public String toString() {
        return idK + " - " + nameK + " " + surnameK;
    }
}

