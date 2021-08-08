package com.ishan.Cocktailsapp.adapters;

public class cocktailsmodel {

    String ID, Name, Imgurl;

    public cocktailsmodel(String ID, String name, String imgurl) {
        this.ID = ID;
        Name = name;
        Imgurl = imgurl;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImgurl() {
        return Imgurl;
    }

    public void setImgurl(String imgurl) {
        Imgurl = imgurl;
    }
}
