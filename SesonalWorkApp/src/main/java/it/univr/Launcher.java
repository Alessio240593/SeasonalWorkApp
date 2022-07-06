package it.univr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Launcher {
    @SerializedName("age")
    int age;
    @SerializedName("first_name")
    String name;
    @SerializedName("Words")
    List list;
    @SerializedName("obj")
    Launcher rep;

    public int setAge(int age){
        this.age = age;
        return this.age;
    }

    public void gsonWriter(String filePath, Gson gson, Launcher l){
        try(FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(l, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Launcher l = new Launcher();
        l.setAge(13);
        l.name = "pincopanco";
        l.list = new ArrayList<String>();
        l.list.add("ciao");
        l.list.add("come");
        l.list.add("12");
        l.rep = new Launcher();
        l.rep = l;
        Gson gsonBuilder = new GsonBuilder()
        .setPrettyPrinting()
                .create();

        l.gsonWriter("/home/alessio/Scrivania/prova.json", gsonBuilder, l);

        /*GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String empJson = gsonBuilder.setPrettyPrinting()
                .create()
                .toJson(l);*/

        // Print and display
        System.out.println(
                "Emp json in pretty print format:" + gsonBuilder.toJson(l));

        //Main.main(args);
    }
}