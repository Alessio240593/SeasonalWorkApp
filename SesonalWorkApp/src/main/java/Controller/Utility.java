package Controller;

import Model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utility {
    public static Map<String, String> states = Map.of("dataError","Incorrect red fields data", "contactError", "Incorrect contact data", "insertSuccess" ,  "Data insert successfully",
            "insertError", "Data already exists","duplicate", "Duplicate record",
            "removeError", "Data not exists","removeSuccess", "Data remove successfully", "duplicate workers", "duplicate worker entry", "duplicate field", "data already exist");

    public static List<Account> gsonReader(String path) {
        List<Account> result = null;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            Type listType = new TypeToken<ArrayList<Account>>(){}.getType();
            result = new Gson().fromJson(br, listType);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    public static boolean containsSameFilter(List<SearchModel> models, SearchModel model) {
        for (SearchModel m : models) {
            if (m.getFilter().equals(model.getFilter()) && m.getType() == null ||
                    m.getFilter().equals(model.getFilter()) && !m.getType().equals(model.getType()) ||
                    m.getFilter().equals(model.getFilter()) && m.getType().equals(model.getType())) {
                return true;
            }
        }
        return false;
    }

    public static List<SeasonalWorker> gsonWorkerReader(String path) {
        List<SeasonalWorker> result = null;

        File json = new File(path);

        if(!json.exists()) {
            try {
                json.createNewFile(); //creating it
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            Type listType = new TypeToken<ArrayList<SeasonalWorker>>(){}.getType();
            result = new Gson().fromJson(br, listType);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    public static void gsonWriter(String filePath, Gson gson, List<SeasonalWorker> workers){
        try(FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(workers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeScene(String currentScene, Stage stage){
        Parent content2;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Utility.class.getResource("/view/" + currentScene ));
            content2 = loader.load();
            Scene scene = new Scene(content2, 850, 980);
            stage.setMinWidth(930);
            stage.setMinHeight(1000);
            stage.getIcons().add(new Image(String.valueOf(Utility.class.getResource("/logo/icon.png"))));
            stage.setTitle("SeasonalWorkApp");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkPastExpDuplicate(Worker worker, Job job) {
        for (Job pastExp : worker.getPastExperience()) {
            if (pastExp.equals(job))
                return true;
        }
        return false;
    }

    public static SeasonalWorker getWorker(ChoiceBox box) {
        Stage stage = (Stage) box.getScene().getWindow();
        SeasonalWorker worker = (SeasonalWorker) stage.getUserData();
        return worker;
    }

    public static void setError(Node field, TextField error, String state)  {
        switch (state) {
            case "error":
                state = states.get("dataError");
                errorStyle(field, error);
                break;
            case "insertSuccess":
                state = states.get("insertSuccess");
                successStyle(field, error);
                break;
            case "insertError":
                state = states.get("insertError");
                errorStyle(field, error);
                break;
            case "duplicate":
                state = states.get("duplicate");
                errorStyle(field, error);
                break;
            case "removeError":
                state = states.get("removeError");
                errorStyle(field, error);
                break;
            case "removeSuccess":
                state = states.get("removeSuccess");
                successStyle(field, error);
                break;
            case "contactError":
                state = states.get("contactError");
                errorStyle(field, error);
                break;
            case "duplicate workers":
                state = states.get("duplicate workers");
                errorStyle(field, error);
                break;
            case "duplicate field":
                state = states.get("duplicate field");
                errorStyle(field, error);
                break;
        }

        error.setText(state);
    }

    public static void unSetError(Node field, TextField error) {
        field.getStyleClass().removeAll("error");
        error.setText("");
        error.setVisible(false);
        field.getStyleClass().add("field");
    }

    private static void errorStyle(Node field, TextField error) {
        field.getStyleClass().removeAll("field");
        field.getStyleClass().add("error");
        error.setVisible(true);
        error.setStyle("-fx-text-fill: red; -fx-font-size: 13px;-fx-font-weight: bolder; -fx-border-color: red");
    }

    private static void successStyle(Node field, TextField error) {
        field.getStyleClass().removeAll("error");
        field.getStyleClass().add("field");
        error.setVisible(true);
        error.setStyle("-fx-text-fill: green; -fx-font-size: 13px;-fx-font-weight: bolder; -fx-border-color: green");
    }

    public static String getItalianDate(SeasonalWorker tmp) {
        return (tmp.getBrithInfo().getBirthDate().getDayOfMonth() < 10 ? "0" + tmp.getBrithInfo().getBirthDate().getDayOfMonth() :
                tmp.getBrithInfo().getBirthDate().getDayOfMonth()) + "/" + (tmp.getBrithInfo().getBirthDate().getMonth().getValue() < 10 ? "0" +
                tmp.getBrithInfo().getBirthDate().getMonth().getValue() : tmp.getBrithInfo().getBirthDate().getMonth().getValue()) + "/" +
                tmp.getBrithInfo().getBirthDate().getYear();
    }

    public static String checkType(SearchModel fil) {
        Object state = null;
        Object filter = fil.getFilter();

        if(fil.getObjectType().equals("language")) {
            return "Language";
        }

       if(fil.getObjectType().equals("license")) {
           return "License";
       }

        if(fil.getObjectType().equals("activity area")) {
            return "City";
        }

        if(fil.getObjectType().equals("period")) {
            return "Season";
        }

        if(fil.getObjectType().equals("job")) {
            return "Jobs";
        }

        if(fil.getObjectType().equals("with vehicle")) {
            return "VehicleDisp";
        }

        if(fil.getFilter() instanceof String)
            return fil.getObjectType();

        return null;
    }

    public static boolean isAlphanumerical(String word) {
        for (char c : word.toCharArray()) {
            if(!Character.isAlphabetic(c) && !Character.isDigit(c) && c != ' ' && c != '.') {
                return false;
            }
        }
        return true;
    }
}
