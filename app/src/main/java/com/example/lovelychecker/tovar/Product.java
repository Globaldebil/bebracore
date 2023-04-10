package com.example.lovelychecker.tovar;

import java.util.Map;
public class Product {
    private String id;
    private String title;
    private byte[] image;
    private Double priceFrom;
    private Double priceTo;
    private Map<String, Object> characteristics;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }

    public Map<String, Object> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Map<String, Object> characteristics) {
        this.characteristics = characteristics;
    }
}
