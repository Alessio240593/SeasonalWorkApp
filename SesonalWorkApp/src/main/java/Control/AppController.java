package Control;

import Model.*;
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

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class AppController {
    @FXML
    public Button add;
    @FXML
    public Button submit;
    @FXML
    public HBox season;
    @FXML
    public Button exit;
    @FXML
    public Button logOut;
    @FXML
    public Button home;
    @FXML
    public Button insertRecord;
    @FXML
    public TextField name;
    @FXML
    public TextField surname;
    @FXML
    public TextField cellnum;
    @FXML
    public TextField email;
    @FXML
    public TextField address;
    @FXML
    public DatePicker date;
    @FXML
    public TextField emergency_name;
    @FXML
    public TextField emergency_surname;
    @FXML
    public TextField emergency_cellnum;
    @FXML
    public TextField emergency_email;
    @FXML
    public HBox languages;
    @FXML
    public TextField errorField;
    @FXML
    public Button next;
    @FXML
    public HBox license1;
    @FXML
    public HBox license2;
    @FXML
    public HBox license3;
    @FXML
    public HBox city1;
    @FXML
    public HBox city2;
    @FXML
    public ToggleButton veichle;

    public void setError(TextField field)  {
        field.getStyleClass().remove("field");
        field.getStyleClass().add("error");
        errorField.setVisible(true);
        errorField.setText("Dati inseriti nei campi rossi non validi");
        errorField.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder;");
    }

    public void unSetError(TextField field) {
        field.getStyleClass().remove("error");
        field.getStyleClass().add("field");
    }

    public void homeHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        Utility.changeScene("Home.fxml", stage);
    }

    public void insertHandler(ActionEvent actionEvent) {
        Parent content2;
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        Utility.changeScene("Insert.fxml", stage);
    }

    public void logOutHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        Utility.changeScene("Login.fxml", stage);
    }

    public void exitHandler(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void nextHandler(ActionEvent actionEvent) {
        /*String nome = name.getText();
        String regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nome)) {
            System.err.println("nome è sbagliato");
            setError(name);
        } else {
            unSetError(name);
        }

        String cognome = surname.getText();
        if (!Pattern.matches(regex, cognome)) {
            System.err.println("cognome è sbagliato");
            setError(surname);
        } else {
            unSetError(surname);
        }

        String cellulare = cellnum.getText();
        regex = "^[0-9]{10}$";
        if (!Pattern.matches(regex, cellulare)) {
            System.err.println("cellulare è sbagliato");
            setError(cellnum);
        } else {
            unSetError(cellnum);
        }

        String postaelettronica = email.getText();
        regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        if (!Pattern.matches(regex, postaelettronica)) {
            System.err.println("email è sbagliato");
            setError(email);
        } else {
            unSetError(email);
        }

        String indirizzo = address.getText();
        regex = "^[a-zA-Z]+ .+,? (?:n.)?[0-9]+.*$";
        if (!Pattern.matches(regex, cellulare)) {
            System.out.println("indirizzo è sbagliato");
            setError(address);
        }
        else {
            unSetError(address);
        }

        LocalDate data = date.getValue();

        if (data == null) {
            System.out.println("data sbagliata");
            date.getStyleClass().remove("field");
            date.getStyleClass().add("error");
            errorField.setVisible(true);
            errorField.setText("Dati inseriti nei campi rossi non validi");
            errorField.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder;");
        } else if ((data.getYear() < 1900 || data.getYear() >= (Year.now().getValue() - 16))) {
            System.out.println("data sbagliata");
            date.getStyleClass().remove("field");
            date.getStyleClass().add("error");
            errorField.setVisible(true);
            errorField.setText("Dati inseriti nei campi rossi non validi");
            errorField.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder;");
        } else {
            date.getStyleClass().remove("error");
            date.getStyleClass().add("field");
        }

        String nome_emergenza = emergency_name.getText();
        regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nome_emergenza) || nome_emergenza.equals(nome)) {
            System.err.println("nome_emergenza è sbagliato");
            setError(emergency_name);
        } else {
            unSetError(emergency_name);
        }

        String cognome_emergenza = emergency_surname.getText();
        if (!Pattern.matches(regex, cognome_emergenza) || cognome_emergenza.equals(cognome)) {
            System.err.println("cognome_emergenza è sbagliato");
            setError(emergency_surname);
        } else {
            unSetError(emergency_surname);
        }

        String cellulare_emergenza = emergency_cellnum.getText();
        regex = "^[0-9]{10}$";
        if (!Pattern.matches(regex, cellulare_emergenza) || cellulare_emergenza.equals(cellulare)) {
            System.err.println("cellulare_emergenza è sbagliato");
            setError(emergency_cellnum);
        } else {
            unSetError(emergency_cellnum);
        }

        String postaelettronica_emergenza = emergency_email.getText();
        regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        if (!Pattern.matches(regex, postaelettronica_emergenza) || postaelettronica_emergenza.equals(postaelettronica)) {
            System.err.println("email_emergenza è sbagliato");
            setError(emergency_email);
        } else {
            unSetError(emergency_email);
        }

        List<Language> langlist = new ArrayList<>();
        for (Node node : languages.getChildren()) {
            CheckBox cb = ((CheckBox) node);
            if (cb.isSelected()) {
                langlist.add(Language.valueOf(cb.getText().toUpperCase()));
            }
        }

        List<License> licenselist = new ArrayList<>();
        for (Node node : license1.getChildren()) {
            CheckBox cb = ((CheckBox) node);
            if (cb.isSelected()) {
                licenselist.add(License.valueOf(cb.getText().toUpperCase()));
                System.out.println(License.valueOf(cb.getText().toUpperCase()));
            }
        }
        for (Node node : license2.getChildren()) {
            CheckBox cb = ((CheckBox) node);
            if (cb.isSelected()) {
                licenselist.add(License.valueOf(cb.getText().toUpperCase()));
                System.out.println(License.valueOf(cb.getText().toUpperCase()));
            }
        }
        for (Node node : license3.getChildren()) {
            CheckBox cb = ((CheckBox) node);
            if (cb.isSelected()) {
                licenselist.add(License.valueOf(cb.getText().toUpperCase()));
                System.out.println(License.valueOf(cb.getText().toUpperCase()));
            }
        }


        List<City> citieslist = new ArrayList<>();
        for (Node node : city1.getChildren()) {
            CheckBox cb = ((CheckBox) node);
            if (cb.isSelected()) {
                citieslist.add(City.valueOf(cb.getText().toUpperCase()));
                System.out.println(City.valueOf(cb.getText().toUpperCase()));
            }
        }
        for (Node node : city2.getChildren()) {
            CheckBox cb = ((CheckBox) node);
            if (cb.isSelected()) {
                citieslist.add(City.valueOf(cb.getText().toUpperCase()));
                System.out.println(City.valueOf(cb.getText().toUpperCase()));
            }
        }

        List<Season> seasonlist = new ArrayList<>();
        for (Node node : season.getChildren()) {
            CheckBox cb = ((CheckBox) node);
            if (cb.isSelected()) {
                seasonlist.add(Season.valueOf(cb.getText().toUpperCase()));
                System.out.println(Season.valueOf(cb.getText().toUpperCase()));
            }
        }

        Boolean veicolo = veichle.isSelected();
        System.out.println(veicolo);


        if (name.getStyleClass().toString().contains("error") || surname.getStyleClass().toString().contains("error") || cellnum.getStyleClass().toString().contains("error") ||
                address.getStyleClass().toString().contains("error") || email.getStyleClass().toString().contains("error") || emergency_name.getStyleClass().toString().contains("error") ||
                emergency_surname.getStyleClass().toString().contains("error") || emergency_cellnum.getStyleClass().toString().contains("error") ||
                emergency_email.getStyleClass().toString().contains("error") || data == null || date.getStyleClass().toString().contains("error")) {
            System.out.println("c'è qualche campo sbagliato");
        } else {
            */
            Stage stage = (Stage) next.getScene().getWindow();
            Parent content2;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Utility.class.getResource("/view/InsertExp.fxml"));
                content2 = loader.load();
                Scene scene = new Scene(content2, 850, 900);
                stage.getIcons().add(new Image(String.valueOf(Utility.class.getResource("/icon/icon.png"))));
                stage.setUserData(Model.getModel().createWorker("SEASONAL", "via prova 3", new ArrayList<Job>(), null, null,
                        null, true, null, null, null, null, 123));
                stage.setTitle("SeasonalWorkApp");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
    }

    public void submitHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) add.getScene().getWindow();
        Worker worker = (SeasonalWorker) stage.getUserData();
        DaoEmployerImplement tmp = DaoEmployerImplement.getDao();
        tmp.addRecord(worker);
    }

    public void addHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) add.getScene().getWindow();
        Worker worker = (SeasonalWorker) stage.getUserData();

        //controllare i campi e ottenere i valori

        Job tmp = Model.getModel().createJob(Season.SUMMER, "Ale", "Operaio", City.ROVIGO, 13.444, Jobs.BUSDRIVER, 2017);
        if (!Utility.checkPastExpDuplicate(worker, tmp))
            worker.getPastExperience().add(tmp);
        System.out.println(worker);
    }
}
