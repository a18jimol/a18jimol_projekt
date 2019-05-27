package com.example.a18jimol_projekt;

public class Info {
    private String name;
    private String location;
    private int height;
    private String company;
    private int cost;
    private String category;


    public Info(){
        name="Saknar namn";
        location="Saknar plats";
        height=-1;
        company="Saknar företag";
        cost=-1;
        category="Ingen generation";
    }

    public Info(String n, String l, int h, String cy, int ct, String ca){
        name=n;
        location=l;
        height=h;
        company=cy;
        cost=ct;
        category=ca;
    }

    public String info(){
        String tmp=new String();
        tmp+= "Made by " +company+ "\n"+ "Originally released in " +cost+  " in " +location+ "\n"+height+"-bit"+ "\n" +category;
        return tmp;
    }
    public String title(){
        String tmp=new String();
        tmp+=name;
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
