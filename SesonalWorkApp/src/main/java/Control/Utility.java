package Control;

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
            "removeError", "Data not exists","removeSuccess", "Data remove successfully");

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
            stage.setMinWidth(900);
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
        field.getStyleClass().removeAll("field");
        field.getStyleClass().add("error");
        error.setVisible(true);
        error.setStyle("-fx-text-fill: green; -fx-font-size: 13px;-fx-font-weight: bolder; -fx-border-color: green");
    }
}
