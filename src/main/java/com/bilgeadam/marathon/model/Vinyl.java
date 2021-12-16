package com.bilgeadam.marathon.model;

import com.bilgeadam.marathon.enums.EGenre;
import com.bilgeadam.marathon.enums.EVinlyDiameter;
import com.bilgeadam.marathon.enums.EVinylRPM;

import java.util.Date;

public class Vinyl {
    private long id;
    private String name;
    private long price;
    private long discountRate;
    private byte[] photo;
    private Artist artist;
    private EGenre genre;
    private Date createdDate;
    private EVinlyDiameter diameter;
    private EVinylRPM speed;

    public Vinyl() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(long discountRate) {
        this.discountRate = discountRate;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public EVinlyDiameter getDiameter() {
        return diameter;
    }

    public void setDiameter(EVinlyDiameter diameter) {
        this.diameter = diameter;
    }

    public EVinylRPM getSpeed() {
        return speed;
    }

    public void setSpeed(EVinylRPM speed) {
        this.speed = speed;
    }
}
