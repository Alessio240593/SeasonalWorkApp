package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.regex.Pattern;

public class AppController {
    @FXML
    Button exit;
    @FXML
    Button logOut;
    @FXML
    Button home;
    @FXML
    Button insertRecord;
    @FXML
    TextField name;

    public void homeHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        Utility.changeScene("Home.fxml", stage);
    }

    public void insertHandler(ActionEvent actionEvent) {
        Parent content2;
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        Utility.changeScene("Insert.fxml", stage);
        //da scommentare
        //SeasonalWorker worker = FactoryModel.getWorker("SEASONAL");
    }

    public void logOutHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        Utility.changeScene("Login.fxml", stage);
    }

    public void exitHandler(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void submitHandler(ActionEvent actionEvent) {
        //controlli vari zii
        String nome = name.getText();
        String regex = "^[a-zA-Z]+";
        System.out.println(Pattern.matches(regex, nome));
    }
}
