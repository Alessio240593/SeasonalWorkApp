package Model;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Home extends Application {
    public static void main(String[] args){
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception {
        Parent content = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Login.fxml"));
            content = loader.load();

            Scene scene = new Scene(content, 600, 600);

            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon/icon.png"))));
            stage.setResizable(false);
            stage.setTitle("SeasonalWorkApp");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
