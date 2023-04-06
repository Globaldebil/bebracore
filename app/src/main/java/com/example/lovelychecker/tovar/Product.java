package com.example.lovelychecker.tovar;
class Screen{
    double diagonal;
    String resolution,type;
    short frequency;
}

class Processor{
    String value;
    short cores;
    Processor(String value, short cores){
        this.value = value;
        this.cores = cores;
    }
}

class RAM{
    int value;
    String measure;
    RAM(int value, String measure){
        this.value = value;
        this.measure = measure;
    }
}

class ROM{
    int value;
    String measure;
    ROM(int value, String measure){
        this.value = value;
        this.measure = measure;
    }
}

class Cam{
    String mpics,resolution;
    short count;
    Cam(String mpics, String resolution, short count){
        this.mpics = mpics;
        this.resolution = resolution;
        this.count = count;
    }
}

class Inface{
    String collection,headphones;
    Inface(String collection, String headphones){
        this.collection = collection;
        this.headphones = headphones;
    }
}

class Wireless{
    String mobile_wireless, wifi;
    boolean ufc_support, gps_support, glonass_support;
    float bluetooth;
    Wireless(boolean ufc_support,boolean gps_support,boolean glonass_support,float bluetooth, String mobile_wireless, String wifi){
        this.ufc_support = ufc_support;
        this.gps_support = gps_support;
        this.glonass_support = glonass_support;
        this.bluetooth = bluetooth;
        this.mobile_wireless = mobile_wireless;
        this.wifi = wifi;
    }
}

class Battery{
    String type, capacity;
    boolean fast_charge_support;
    Battery(String type, String capacity, boolean fast_charge_support){
        this.type = type;
        this.capacity = capacity;
        this.fast_charge_support = fast_charge_support;
    }
}
public class Product {
    int price;
    String name;
    public Product(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
