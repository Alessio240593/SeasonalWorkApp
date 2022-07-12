package Control;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
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

    public void gsonReader() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (BufferedReader br = new BufferedReader(
                new FileReader("/home/alessio/Scrivania/prova.json"))) {
            //convert the json string back to object

            Type listType = new TypeToken<ArrayList<Launcher>>(){}.getType();
            List<Launcher> countryObj = new Gson().fromJson(br, listType);

            System.out.println("Name Of Country: "+countryObj.get(0).name);

            System.out.println("Population: "+countryObj.get(1).age);

            Launcher l1 = new Launcher();
            l1.age = 59;
            l1.name = "fausto";

            countryObj.add(l1);

            gsonWriterList("/home/alessio/Scrivania/prova.json", gson, countryObj);

        }
        catch (IOException ex) {
            System.out.println(ex.getCause());
        }
    }

    public void gsonWriterList(String filePath, Gson gson, List<Launcher> l){
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
        l.rep.name = "ciao";
        l.rep.age = 90;
        Gson gsonBuilder = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        ArrayList<Launcher> allItems = new ArrayList<>();
        allItems.add(l);
        allItems.add(new Launcher());
        allItems.add(new Launcher());

        System.out.println(gsonBuilder.toJson(allItems));

        try(FileWriter writer = new FileWriter("/home/alessio/Scrivania/prova.json")) {
            gsonBuilder.toJson(allItems, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }



        //l.gsonWriter("/home/alessio/Scrivania/prova.json", gsonBuilder, l);
        //l.gsonReader();
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







