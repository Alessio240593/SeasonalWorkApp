package Control;

import Model.Account;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static List<Account> gsonReader(String path) {
        List<Account> result = null;
        //System.out.println(System.getenv("PWD"));

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            Type listType = new TypeToken<ArrayList<Account>>(){}.getType();
            result = new Gson().fromJson(br, listType);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    public void gsonWriter(String filePath, Gson gson, Launcher l){
        try(FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(l, writer);
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
            Scene scene = new Scene(content2, 850, 900);
            stage.getIcons().add(new Image(String.valueOf(Utility.class.getResource("/icon/icon.png"))));
            stage.setTitle("SeasonalWorkApp");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
