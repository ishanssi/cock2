package com.ishan.Cocktailsapp.adapters;

import java.io.Serializable;

public class popularcocktailsmodel implements Serializable {


    private static final long serialVersionUID = 1L;
    String drinkID,drinkName,category,imageurl,instruction,ing1,ing2,ing3,ing4,ing5;
    String measure1,measure2,measure3,measure4,measure5;



    public popularcocktailsmodel() {

    }

    public popularcocktailsmodel(String drinkID, String drinkName, String category, String imageurl, String instruction, String ing1, String ing2, String ing3, String ing4, String ing5, String measure1, String measure2, String measure3, String measure4, String measure5) {
        this.drinkID = drinkID;
        this.drinkName = drinkName;
        this.category = category;
        this.imageurl = imageurl;
        this.instruction = instruction;
        this.ing1 = ing1;
        this.ing2 = ing2;
        this.ing3 = ing3;
        this.ing4 = ing4;
        this.ing5 = ing5;
        this.measure1 = measure1;
        this.measure2 = measure2;
        this.measure3 = measure3;
        this.measure4 = measure4;
        this.measure5 = measure5;
    }



    public String getDrinkID() {
        return drinkID;
    }

    public void setDrinkID(String drinkID) {
        this.drinkID = drinkID;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getIng1() {
        return ing1;
    }

    public void setIng1(String ing1) {
        this.ing1 = ing1;
    }

    public String getIng2() {
        return ing2;
    }

    public void setIng2(String ing2) {
        this.ing2 = ing2;
    }

    public String getIng3() {
        return ing3;
    }

    public void setIng3(String ing3) {
        this.ing3 = ing3;
    }

    public String getIng4() {
        return ing4;
    }

    public void setIng4(String ing4) {
        this.ing4 = ing4;
    }

    public String getIng5() {
        return ing5;
    }

    public void setIng5(String ing5) {
        this.ing5 = ing5;
    }

    public String getMeasure1() {
        return measure1;
    }

    public void setMeasure1(String measure1) {
        this.measure1 = measure1;
    }

    public String getMeasure2() {
        return measure2;
    }

    public void setMeasure2(String measure2) {
        this.measure2 = measure2;
    }

    public String getMeasure3() {
        return measure3;
    }

    public void setMeasure3(String measure3) {
        this.measure3 = measure3;
    }

    public String getMeasure4() {
        return measure4;
    }

    public void setMeasure4(String measure4) {
        this.measure4 = measure4;
    }

    public String getMeasure5() {
        return measure5;
    }

    public void setMeasure5(String measure5) {
        this.measure5 = measure5;
    }
}
