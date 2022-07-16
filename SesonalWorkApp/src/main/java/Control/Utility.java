package Control;

import Model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utility {
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
            stage.setMinHeight(900);
            stage.getIcons().add(new Image(String.valueOf(Utility.class.getResource("/icon/icon.png"))));
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
}
