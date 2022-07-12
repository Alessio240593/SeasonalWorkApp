package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

            Scene scene = new Scene(content);

            //scene.getStylesheets().add( getClass().getResource( "css/Prova.css" ).toExternalForm() );
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

        //Button but = (Button) content.lookup("#but");


    }
}
