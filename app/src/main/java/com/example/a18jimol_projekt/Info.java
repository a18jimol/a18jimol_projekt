package com.example.a18jimol_projekt;

public class Info {
    private String name;
    private String location;
    private int height;



    public Info(){
        name="Saknar namn";
        location="Saknar plats";
        height=-1;
    }

    public Info(String n, String l, int h){
        name=n;
        location=l;
        height=h;
    }

    public String info(){
        String tmp=new String();
        tmp+=name+" is located in " +location+ " and is "+height+" years old. ";
        return tmp;
    }
    public String hej(){
        String tmp=new String();
        tmp+=name+"test";
        return tmp;
    }
    public void setName(String n){
        name=n;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
