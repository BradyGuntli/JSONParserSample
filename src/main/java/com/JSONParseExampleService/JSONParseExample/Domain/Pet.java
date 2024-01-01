package com.JSONParseExampleService.JSONParseExample.Domain;

public class Pet {

    private String petName;
    private String petType;
    private int petAge;
    private String petBreed;
    private String petFavoriteToy;

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetFavoriteToy() {
        return petFavoriteToy;
    }

    public void setPetFavoriteToy(String petFavoriteToy) {
        this.petFavoriteToy = petFavoriteToy;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petName='" + petName + '\'' +
                ", petType='" + petType + '\'' +
                ", age=" + petAge +
                ", breed='" + petBreed + '\'' +
                ", favoriteToy='" + petFavoriteToy + '\'' +
                '}';
    }
}
