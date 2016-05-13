package com.example.hulk.golf;

/**
 * Created by hulk on 5/10/16.
 */
public class Regist_con {
    private String theName,theLocation,theAge,imageEncoded;



    public Regist_con(String name,String location, String age, String theImage){
        this.theName = name;
        this.theAge = age;
        this.theLocation = location;
        this.imageEncoded = imageEncoded;
    }

    public String getName(){
        return theName;
    }
      public String getAge(){
        return theAge;
    }
    public String getLocation(){
        return theLocation;
    }
    public String getImageEncoded(){
        return imageEncoded;
    }

    public Regist_con(){}


}
