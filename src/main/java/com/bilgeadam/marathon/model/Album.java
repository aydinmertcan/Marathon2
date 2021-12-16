package com.bilgeadam.marathon.model;

import com.bilgeadam.marathon.enums.EGenre;

public class Album {
    private String name;
    private double price;
    private byte[] picture;
    private double discountRate;
    private Artist artist;
    private EGenre genre;

    public Album() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public EGenre getGenre() {
        return genre;
    }

    public void setGenre(EGenre genre) {
        this.genre = genre;
    }
}
