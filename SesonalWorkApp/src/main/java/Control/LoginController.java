package Control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button reset;
    @FXML
    private Button singIn;
    @FXML
    private TextField username;
    @FXML
    private TextField password;


    public void setListener(ActionEvent actionEvent) {
        username.setText("");
        password.setText("");
    }

    public void setListener2(ActionEvent actionEvent) {
            DaoEmployerImplement tmp = DaoEmployerImplement.getDao();
            if (tmp.login(username.getText(), password.getText())){
                Stage stage = (Stage) username.getScene().getWindow();

                Parent content2;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/prova.fxml"));
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
            else{
                System.out.println("error");
            }
    }
}
