package Control;

import Model.FactoryModel;
import Model.Model;
import Model.Worker;
import Model.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
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
    @FXML
    TextField surname;
    @FXML
    TextField cellnum;
    @FXML
    TextField email;
    @FXML
    TextField address;
    @FXML
    DatePicker date;
    @FXML
    TextField emergency_name;
    @FXML
    TextField emergency_surname;
    @FXML
    TextField emergency_cellnum;
    @FXML
    TextField emergency_email;
    @FXML
    HBox languages;


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
        // bordo rosso (correggere i campi rossi)

        String nome = name.getText();
        String regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nome)) {
            System.err.println("nome è sbagliato");
            return;
        }

        String cognome = surname.getText();
        if (!Pattern.matches(regex, cognome)) {
            System.err.println("cognome è sbagliato");
            return;
        }

        String cellulare = cellnum.getText();
        regex = "^[0-9]{10}$";
        if (!Pattern.matches(regex, cellulare)) {
            System.err.println("cellulare è sbagliato");
            return;
        }

        String postaelettronica = email.getText();
        regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        if (!Pattern.matches(regex, postaelettronica)) {
            System.err.println("email è sbagliato");
            return;
        }

        String indirizzo = address.getText();
        regex = "^[a-zA-Z]+ .+,? (?:n.)?[0-9]+.*$";
        if (!Pattern.matches(regex, cellulare)) {
            System.out.println("cellulare è sbagliato");
            return;
        }

        LocalDate data = date.getValue();
        if (data.getYear() < 1900 || data.getYear() > Year.now().getValue()) {
            System.out.println("data sbagliata");
            return;
        }

        String nome_emergenza = emergency_name.getText();
        regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nome_emergenza)) {
            System.err.println("nome_emergenza è sbagliato");
            return;
        }

        String cognome_emergenza = emergency_surname.getText();
        if (!Pattern.matches(regex, cognome_emergenza)) {
            System.err.println("cognome_emergenza è sbagliato");
            return;
        }

        String cellulare_emergenza = emergency_cellnum.getText();
        regex = "^[0-9]{10}$";
        if (!Pattern.matches(regex, cellulare_emergenza)) {
            System.err.println("cellulare_emergenza è sbagliato");
            return;
        }

        String postaelettronica_emergenza = emergency_email.getText();
        regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        if (!Pattern.matches(regex, postaelettronica_emergenza)) {
            System.err.println("email_emergenza è sbagliato");
            return;
        }

        List<Language> langlist = new ArrayList<>();
        for (Node node : languages.getChildren()) {
            CheckBox cb = ((CheckBox) node);
            if (cb.isSelected()) {
                langlist.add(Language.valueOf(cb.getText()));
            }
        }

        Worker newWorker = Model.getModel().createWorker();
        DaoEmployerImplement tmp = DaoEmployerImplement.getDao();
        tmp.addRecord();

    }

}
