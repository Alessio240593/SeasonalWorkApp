package Control;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private VBox leftSide;
    @FXML
    private VBox rightSide;
    @FXML
    private Button reset;
    @FXML
    private Button singIn;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField errorField;
    @FXML
    private HBox box;


    public void signInHandler(ActionEvent actionEvent) {
        DaoEmployerImplement tmp = DaoEmployerImplement.getDao();
        if (tmp.login(username.getText(), password.getText())){
            Stage stage = (Stage) username.getScene().getWindow();
            Utility.changeScene("Home.fxml", stage);
        }
        else{
            if(username.getText().equals("") && password.getText().equals("")) {
                errorField.setVisible(true);
                errorField.setText("Credenziali non inserite");
                errorField.setStyle("-fx-text-fill: red; -fx-font-size: 14px; -fx-font-weight: bolder; -fx-font-family: cursive;");
            }
            else if(password.getText().equals("")){
                errorField.setVisible(true);
                errorField.setText("Password non inserita");
                errorField.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder;");
            }
            else if(username.getText().equals("")){
                errorField.setVisible(true);
                errorField.setText("Username non inserito");
                errorField.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder;");
            }
            else{
                errorField.setVisible(true);
                errorField.setText("Le credenziali non sono corrette");
                errorField.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder;");
            }
        }
    }

    public void resetHandler(ActionEvent actionEvent) {
        username.setText("");
        password.setText("");
    }

    @FXML
    private void initialize() {
        username.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                singIn.requestFocus();
                signInHandler(null);
            }
        });

        password.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                singIn.requestFocus();
                signInHandler(null);
            }
        });
    }
}
