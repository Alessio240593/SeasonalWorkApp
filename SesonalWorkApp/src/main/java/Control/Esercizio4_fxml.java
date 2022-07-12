package Control;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Esercizio4_fxml extends Application {
    @FXML
    public Button but;
    @FXML
    public Button prevbtn;

    public void start(Stage stage) throws Exception {
        Parent content = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/prova.fxml"));
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

    @FXML
    public void initialize() {
    }


    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void handle2(ActionEvent actionEvent) {
                try {
                    Stage stage1 = (Stage) prevbtn.getScene().getWindow();
                    FXMLLoader loader2 = new FXMLLoader();
                    loader2.setLocation(getClass().getResource("/view/prova.fxml"));
                    Parent content1 = loader2.load();

                    Scene scene = new Scene(content1);
                    stage1.setScene(scene);
                    stage1.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    public void handle(ActionEvent actionEvent) {
                try {
                    Stage stage = (Stage) but.getScene().getWindow();
                    FXMLLoader loader1 = new FXMLLoader();
                    loader1.setLocation(getClass().getResource("/view/prova2.fxml"));
                    Parent content = loader1.load();

                    Scene scene = new Scene(content);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }
}