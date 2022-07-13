package Control;

import Model.FactoryModel;
import Model.SeasonalWorker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ExitController {
    @FXML
    Button exit;
    @FXML
    Button logOut;
    public void exitHandler(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void logOutHandler(ActionEvent actionEvent) {
        Parent content2;
        Stage stage = (Stage) logOut.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Login.fxml"));
            content2 = loader.load();

            Scene scene = new Scene(content2, 600, 600);

            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/icon/icon.png"))));
            stage.setResizable(false);
            stage.setTitle("SeasonalWorkApp");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void insertHandler(ActionEvent actionEvent) {
        //controlli vari zii

        //qui hai controllato bene

        //da scommentare
        //SeasonalWorker worker = FactoryModel.getWorker("SEASONAL");
    }
}
