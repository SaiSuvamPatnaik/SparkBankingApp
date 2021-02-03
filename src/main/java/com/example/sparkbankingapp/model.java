package com.example.sparkbankingapp;

public class model {

    String Name,Amount;

    model(){

    }


    public model(String name, String amount) {
        Name = name;
        Amount = amount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
