package com.example.ksj;

public class brandData {
    private int image;
    private String brand;
    private String content;

    public brandData(int image, String brand, String content){
        this.image= image;
        this.brand=brand;
        this.content=content;
    }

    public int get_Image(){
        return image;
    }
    public String get_brand(){
        return brand;
    }
    public String get_content(){return content;}

    public void set_image(int image){
        this.image=image;
    }
    public void set_brand(String brand){
        this.brand=brand;
    }
    public void set_content(String content){
        this.content=content;
    }
}
