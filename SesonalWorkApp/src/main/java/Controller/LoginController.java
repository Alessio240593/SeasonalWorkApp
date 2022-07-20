package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button singIn;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField errorField;

    public void signInHandler(ActionEvent actionEvent) {
        DaoEmployerImplement tmp = DaoEmployerImplement.getDao();
        if (tmp.login(username.getText(), password.getText())){
            Stage stage = (Stage) username.getScene().getWindow();
            Utility.changeScene("Home.fxml", stage);
        }
        else{
            if(username.getText().equals("") && password.getText().equals("")) {
                errorField.setVisible(true);
                errorField.setText("Please insert credentials");
                errorField.setStyle("-fx-text-fill: red; -fx-font-size: 14px; -fx-font-weight: bolder; -fx-font-family: cursive;");
            }
            else if(password.getText().equals("")){
                errorField.setVisible(true);
                errorField.setText("Please insert password");
                errorField.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder; -fx-font-family: cursive;");
            }
            else if(username.getText().equals("")){
                errorField.setVisible(true);
                errorField.setText("Please insert username");
                errorField.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder; -fx-font-family: cursive;");
            }
            else{
                errorField.setVisible(true);
                errorField.setText("Incorrect credentials");
                errorField.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder; -fx-font-family: cursive;");
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
