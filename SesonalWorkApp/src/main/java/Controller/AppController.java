package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
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
    public ToggleButton vehicle;
    @FXML
    public TextField nameAzienda;
    @FXML
    public TextField retribuzione;
    @FXML
    public TextField annoassunzione;
    @FXML
    public ChoiceBox citta;
    @FXML
    public ChoiceBox periodo;
    @FXML
    public ChoiceBox job;
    @FXML
    public TextArea mansioni;
    @FXML
    public VBox menuBox;
    @FXML
    public TextField nazionalità;
    @FXML
    public TextField cittàNascita;
    @FXML
    public TextField errorEmergencyField;
    @FXML
    public TextField expErrorField;
    @FXML
    public VBox vbox;
    @FXML
    public Button updateRecord;
    @FXML
    public ChoiceBox<String> workerId;
    @FXML
    public Button nextUpdate;
    @FXML
    public TextField updateCellnum;
    @FXML
    public TextField updateEmail;
    @FXML
    public TextField updateAddress;
    @FXML
    public ChoiceBox updateActivityArea;
    @FXML
    public TextField updateEmergencyName;
    @FXML
    public TextField updateEmergencySurname;
    @FXML
    public TextField updateEmergencyEmail;
    @FXML
    public TextField updateEmergencyCellnum;
    @FXML
    public ChoiceBox updateperiodBox;
    @FXML
    public ChoiceBox updateLanguagesBox;
    @FXML
    public ChoiceBox updateLicenseBox;
    @FXML
    public ChoiceBox updateWithVehicle;
    @FXML
    public Button nextUpdateField;
    @FXML
    public ChoiceBox insertActivityArea;
    @FXML
    public ChoiceBox insertLanguage;
    @FXML
    public TextField updateErrorField;
    @FXML
    public TextField licenseFeedback;
    @FXML
    public TextField cityFeedback;
    @FXML
    public TextField languageFeedback;
    @FXML
    public TextField periodFeedback;
    @FXML
    public TextField insertLanguageFeedback;
    @FXML
    public TextField insertCityFeedback;
    @FXML
    public TextField updateNameAzienda;
    @FXML
    public TextField updateRetribuzione;
    @FXML
    public TextField updateAnnoassunzione;
    @FXML
    public ChoiceBox updateCitta;
    @FXML
    public ChoiceBox updatePeriodo;
    @FXML
    public ChoiceBox updateJob;
    @FXML
    public TextArea updateMansioni;
    @FXML
    public TextField updateExpErrorField;

    @FXML
    private void initialize() {
        /*topBox.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                topBox.setMinWidth(topBox.getWidth() + t1.intValue() + 500);
            }
        });

        menuBox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                menuBox.setMinHeight(menuBox.getHeight() + t1.intValue());
            }
        });*/
    }

    private void insertNotSuccess(TextField feedback) {
        feedback.setVisible(true);
        feedback.getStyleClass().removeAll("success");
        feedback.getStyleClass().add("error");
        feedback.setStyle("-fx-text-fill: red; -fx-font-size: 13px;-fx-font-weight: bolder; -fx-border-color: red");
        feedback.setText("Data already exists");
    }

    private void insertSuccess(TextField feedback) {
        feedback.setVisible(true);
        feedback.getStyleClass().removeAll("error");
        feedback.getStyleClass().add("success");
        feedback.setStyle("-fx-text-fill: green; -fx-font-size: 13px;-fx-font-weight: bolder; -fx-border-color: green");
        feedback.setText("Data insert successfully");
    }

    private void removeNotSuccess(TextField feedback) {
        feedback.setVisible(true);
        feedback.getStyleClass().removeAll("success");
        feedback.getStyleClass().add("error");
        feedback.setStyle("-fx-text-fill: red; -fx-font-size: 13px;-fx-font-weight: bolder; -fx-border-color: red");
        feedback.setText("Data not exists");
    }

    private void removeSuccess(TextField feedback) {
        feedback.setVisible(true);
        feedback.getStyleClass().removeAll("error");
        feedback.getStyleClass().add("success");
        feedback.setStyle("-fx-text-fill: green; -fx-font-size: 13px;-fx-font-weight: bolder; -fx-border-color: green");
        feedback.setText("Data remove successfully");
    }

    public void setError(Node field, TextField error)  {
        field.getStyleClass().removeAll("field");
        field.getStyleClass().add("error");
        error.setVisible(true);
        error.setText("Incorrect red fields data");
        error.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder; -fx-border-color: red");
    }

    public void setDupError(Node field, TextField error)  {
        field.getStyleClass().removeAll("field");
        field.getStyleClass().add("error");
        error.setVisible(true);
        error.setText("Duplicate record");
        error.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder; -fx-border-color: red");
    }

    public void setEmergengyError(TextField field)  {
        field.getStyleClass().removeAll("field");
        field.getStyleClass().add("error");
        errorEmergencyField.setVisible(true);
        errorEmergencyField.setText( "Incorrect emergency contact");
        errorEmergencyField.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder; -fx-border-color: red");
    }

    public void unSetError(Node field, TextField error) {
        field.getStyleClass().removeAll("error");
        error.setText("");
        error.setVisible(false);
        field.getStyleClass().add("field");
    }


    public void homeHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        Utility.changeScene("Home.fxml", stage);
    }

    public void insertHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        Utility.changeScene("Insert.fxml", stage);
    }

    public void logOutHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        Utility.changeScene("Login.fxml", stage);
    }

    public void updateHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) updateRecord.getScene().getWindow();
        String path = System.getenv("PWD") + "/src/resources/database/workers.json";
        Utility.changeScene("UpdateChoice.fxml", stage);

        List<SeasonalWorker> workers = Utility.gsonWorkerReader(path);
        ChoiceBox<String> check = (ChoiceBox<String>) stage.getScene().lookup("#workerId");

        ObservableList<String> list = FXCollections.observableArrayList();

        for (SeasonalWorker w : workers) {
            list.add(String.valueOf(w.getId()));
        }

        check.setItems(list);
        TableView<TableViewModel> table = (TableView<TableViewModel>) stage.getScene().lookup("#table");

        check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(check.getValue() != null) {
                    int id = Integer.parseInt(check.getValue());
                    Worker tmp = null;
                    for (SeasonalWorker w : workers) {
                        if (w.getId() == id) {
                            tmp = Model.getModel().createWorker("SEASONAL", w.getAddress(), w.getPastExperience(), w.getBrithInfo(), w.getLanguages(), w.getLicense(),
                            w.isWithVehicle(),  w.getActivityArea(), w.getPeriod(), w.getEmergencyContact() , w.getRecord());
                        }
                    }
                    stage.setUserData(tmp);

                    String tmpDate = (tmp.getBrithInfo().getBirthDate().getDayOfMonth() < 10 ? "0" + tmp.getBrithInfo().getBirthDate().getDayOfMonth() :
                            tmp.getBrithInfo().getBirthDate().getDayOfMonth()) + "/" + (tmp.getBrithInfo().getBirthDate().getMonth().getValue() < 10 ? "0" +
                            tmp.getBrithInfo().getBirthDate().getMonth().getValue() : tmp.getBrithInfo().getBirthDate().getMonth().getValue()) + "/" +
                            tmp.getBrithInfo().getBirthDate().getYear();

                    final ObservableList<TableViewModel> data = FXCollections.observableArrayList(
                            Model.getModel().createRecord(tmp.getRecord().getName(), tmp.getRecord().getSurname(), tmp.getRecord().getCellnum(),
                                    tmp.getRecord().getEmail(), tmpDate , tmp.getBrithInfo().getBirthplace()));

                    table.setItems(data);
                }
            }
        });
    }

    public void exitHandler(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void nextInsertHandler(ActionEvent actionEvent) {

        String nome = name.getText();
        String regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nome)) {
            System.err.println("nome è sbagliato");
            setError(name, errorField);
        } else {
            unSetError(name, errorField);
        }

        String cognome = surname.getText();
        if (!Pattern.matches(regex, cognome)) {
            System.err.println("cognome è sbagliato");
            setError(surname, errorField);
        } else {
            unSetError(surname, errorField);
        }

        String cellulare = cellnum.getText();
        regex = "^[0-9]{10}$";
        if (!Pattern.matches(regex, cellulare)) {
            System.err.println("cellulare è sbagliato");
            setError(cellnum, errorField);
        } else {
            unSetError(cellnum, errorField);
        }

        String postaelettronica = email.getText();
        regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        if (!Pattern.matches(regex, postaelettronica)) {
            System.err.println("email è sbagliato");
            setError(email, errorField);
        } else {
            unSetError(email, errorField);
        }

        String indirizzo = address.getText();
        //TODO controllare regex
        /*
        regex = "^[a-zA-Z]+ .+,? (?:n.)?[0-9]+.*$";
        if (!Pattern.matches(regex, cellulare)) {
            System.out.println("indirizzo è sbagliato");
            setError(address, errorField);
        }
        else {
            unSetError(address);
        }*/

        LocalDate data = date.getValue();

        if (data == null) {
            System.out.println("data sbagliata");
            setError(date, errorField);
        } else if ((data.getYear() < 1900 || data.getYear() >= (Year.now().getValue() - 16))) {
            setError(date, errorField);
        } else {
            unSetError(date, errorField);
        }

        String nazionalita = nazionalità.getText();
        regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nazionalita)) {
            System.err.println("nazionalità è sbagliato");
            setError(nazionalità, errorField);
        } else {
            unSetError(nazionalità, errorField);
        }

        String luogoNascita = cittàNascita.getText();
        regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, luogoNascita)) {
            System.err.println("luogoNascita è sbagliato");
            setError(cittàNascita, errorField);
        } else {
            unSetError(cittàNascita, errorField);
        }

        String nome_emergenza = emergency_name.getText();
        regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nome_emergenza)) {
            System.err.println("nome_emergenza è sbagliato");
            setError(emergency_name, errorField);
        }
        else {
            unSetError(emergency_name, errorField);
        }

        String cognome_emergenza = emergency_surname.getText();
        if (!Pattern.matches(regex, cognome_emergenza)) {
            System.err.println("cognome_emergenza è sbagliato");
            setError(emergency_surname, errorField);
        }
        else {
            unSetError(emergency_surname, errorField);
        }

        String cellulare_emergenza = emergency_cellnum.getText();
        regex = "^[0-9]{10}$";
        if (!Pattern.matches(regex, cellulare_emergenza)) {
            System.err.println("cellulare_emergenza è sbagliato");
            setError(emergency_cellnum, errorField);
        }
        else if(cellulare_emergenza.equals(cellulare)){
            setEmergengyError(emergency_cellnum);
        }
        else if(!cellulare_emergenza.equals(cellulare)){
            unSetError(emergency_cellnum, errorEmergencyField);
        }
        else {
            unSetError(emergency_cellnum, errorField);
        }

        String postaelettronica_emergenza = emergency_email.getText();
        regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        if (!Pattern.matches(regex, postaelettronica_emergenza)) {
            System.err.println("email_emergenza è sbagliato");
            setError(emergency_email, errorField);
        }
        else if(postaelettronica_emergenza.equals(postaelettronica)){
            setEmergengyError(emergency_email);
        }
        else if(!postaelettronica_emergenza.equals(postaelettronica)){
            unSetError(emergency_email, errorEmergencyField);
        }
        else {
            unSetError(emergency_email, errorField);
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

        List<Season> seasonlist = new ArrayList<>();
        for (Node node : season.getChildren()) {
            CheckBox cb = ((CheckBox) node);
            if (cb.isSelected()) {
                seasonlist.add(Season.valueOf(cb.getText().toUpperCase()));
                System.out.println(Season.valueOf(cb.getText().toUpperCase()));
            }
        }

        Boolean veicolo = vehicle.isSelected();
        System.out.println(veicolo);


        if (name.getStyleClass().toString().contains("error") || surname.getStyleClass().toString().contains("error") || cellnum.getStyleClass().toString().contains("error") ||
                address.getStyleClass().toString().contains("error") || email.getStyleClass().toString().contains("error") || emergency_name.getStyleClass().toString().contains("error") ||
                emergency_surname.getStyleClass().toString().contains("error") || emergency_cellnum.getStyleClass().toString().contains("error") ||
                emergency_email.getStyleClass().toString().contains("error") || nazionalità.getStyleClass().toString().contains("error") || cittàNascita.getStyleClass().toString().contains("error") ||
                data == null || date.getStyleClass().toString().contains("error")) {
            System.out.println("c'è qualche campo sbagliato");
        } else {
            Stage stage = (Stage) next.getScene().getWindow();
            Parent content2;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Utility.class.getResource("/view/InsertExp.fxml"));
                content2 = loader.load();
                Scene scene = new Scene(content2, 850, 900);
                stage.setMinWidth(850);
                stage.setMinHeight(1000);
                stage.getIcons().add(new Image(String.valueOf(Utility.class.getResource("/logo/icon.png"))));

                //occhio a BirthData
                stage.setUserData(Model.getModel().createWorker("SEASONAL", indirizzo, new ArrayList<Job>(),
                        new BirthData(data, nazionalita, luogoNascita), new ArrayList<Language>(), licenselist, veicolo,
                            new ArrayList<City>(), seasonlist, new Person(new Record(nome_emergenza, cognome_emergenza,
                                cellulare_emergenza, postaelettronica_emergenza)),
                                new Record(nome, cognome, cellulare, postaelettronica)));
                //stage.setUserData(new SeasonalWorker(12));
                stage.setTitle("SeasonalWorkApp");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void submitHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) add.getScene().getWindow();
        SeasonalWorker worker = (SeasonalWorker) stage.getUserData();
        DaoEmployerImplement tmp = DaoEmployerImplement.getDao();
        tmp.addRecord(worker);
        Utility.changeScene("Home.fxml", (Stage) submit.getScene().getWindow());
        TextField homeFeedback = (TextField) stage.getScene().lookup("#homeFeedback");
        homeFeedback.setVisible(true);
        homeFeedback.setStyle("-fx-text-fill: green; -fx-font-size: 20px;-fx-font-weight: bolder;-fx-text-alignment: center;");
        homeFeedback.setText("Record insert successfully");
    }

    public void addExpHandler(ActionEvent actionEvent) {

        //da sistemare tutte le regex che hanno scassato il cazzo
        String nomeAzienda = nameAzienda.getText();
        String regex = "[a-zA-z,.]+";
        if (!Pattern.matches(regex, nomeAzienda)) {
            System.err.println("nomeAzienda è sbagliato");
            setError(nameAzienda, expErrorField);
        } else {
            unSetError(nameAzienda, expErrorField);
        }

        String retribution = retribuzione.getText();
        regex = "^[+]?([0-9]*[.])?[0-9]+$";
        if (!Pattern.matches(regex, retribution) || Double.parseDouble(retribution) > 10000) {
            System.err.println("retribuzione è sbagliato");
            setError(retribuzione, expErrorField);
        } else {
            unSetError(retribuzione, expErrorField);
        }

        Stage stage = (Stage) add.getScene().getWindow();
        String annoAssunzione = annoassunzione.getText();
        regex = "^[0-9]{4}$";
        if ((!Pattern.matches(regex, annoAssunzione) || Integer.parseInt(annoAssunzione) < 1900) ||
                Integer.parseInt(annoAssunzione) < LocalDate.now().getYear() - 5 ||
                Integer.parseInt(annoAssunzione) < (((SeasonalWorker) stage.getUserData()).getBrithInfo().getBirthDate().getYear() + 16)) {
            System.err.println("annoAssunzione è sbagliato");
            setError(annoassunzione, expErrorField);
        } else {
            unSetError(annoassunzione, expErrorField);
        }

        String city = (String) citta.getValue();
        if (city == null) {
            System.err.println("city è sbagliato");
            setError(citta, expErrorField);
        } else {
            unSetError(citta, expErrorField);
        }

        String period = (String) periodo.getValue();
        if (period == null) {
            System.err.println("period è sbagliato");
            setError(periodo, expErrorField);
        } else {
            unSetError(periodo, expErrorField);
        }

        String lavoro = (String) job.getValue();
        if (lavoro == null) {
            System.err.println("lavoro è sbagliato");
            setError(job, expErrorField);
        } else {
            unSetError(job, expErrorField);
        }

        String mansion = mansioni.getText();

        if (nameAzienda.getStyleClass().toString().contains("error") ||
                retribuzione.getStyleClass().toString().contains("error") ||
                annoassunzione.getStyleClass().toString().contains("error") ||
                city == null || period == null || lavoro == null
        ) {
            System.out.println("c'è qualche campo sbagliato");
        } else {
            Worker worker = (SeasonalWorker) stage.getUserData();

            Job tmp = Model.getModel().createJob(Season.valueOf(period), nomeAzienda, mansion, City.valueOf(city),
                    Double.parseDouble(retribution), Jobs.valueOf(lavoro), Integer.parseInt(annoAssunzione));

            if (!Utility.checkPastExpDuplicate(worker, tmp)) {
                worker.getPastExperience().add(tmp);
                nameAzienda.setText("");
                retribuzione.setText("");
                annoassunzione.clear();
                citta.setValue(null);
                periodo.setValue(null);
                job.setValue(null);
                mansioni.setText("");
                System.out.println(worker);
                insertSuccess(expErrorField);
                unSetError(nameAzienda, expErrorField);
                unSetError(retribuzione, expErrorField);
                unSetError(annoassunzione, expErrorField);
                unSetError(citta, expErrorField);
                unSetError(periodo, expErrorField);
                unSetError(job, expErrorField);
            } else {
                setDupError(nameAzienda, expErrorField);
                setDupError(retribuzione, expErrorField);
                setDupError(annoassunzione, expErrorField);
                setDupError(citta, expErrorField);
                setDupError(periodo, expErrorField);
                setDupError(job, expErrorField);
            }
        }
    }

    public void nextUpdateHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        TableView<TableViewModel> table = (TableView<TableViewModel>) stage.getScene().lookup("#table");

        if(stage.getUserData() != null) {
            Utility.changeScene("Update.fxml", stage);
        }
    }

    public void updateFieldHandler(ActionEvent actionEvent) {
        String regex;
        Stage stage = (Stage) updateAddress.getScene().getWindow();
        SeasonalWorker oldWorker = (SeasonalWorker) stage.getUserData();

        String indirizzoAggiornato = updateAddress.getText();
        if (!indirizzoAggiornato.isEmpty() || oldWorker.getAddress().equals(indirizzoAggiornato)) {
            regex = "^[a-z]+ .+,? (?:n.)?[0-9]+.*$";
            if (!Pattern.matches(regex, indirizzoAggiornato)) {
                System.err.println("indirizzo aggiornato è sbagliato");
                setError(updateAddress, updateErrorField);
            }
            else {
                unSetError(updateAddress, updateErrorField);
            }
        } else {
            indirizzoAggiornato = null;
        }

        String emailAggiornata = updateEmail.getText();
        if (!emailAggiornata.isEmpty()) {
            regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
            if (!Pattern.matches(regex, emailAggiornata) || oldWorker.getRecord().getEmail().equals(emailAggiornata)) {
                System.err.println("email aggiornata è sbagliata");
                setError(updateEmail, updateErrorField);
            } else {
                unSetError(updateEmail, updateErrorField);
            }
        } else {
            emailAggiornata = null;
        }

        String cellulareAggiornato = updateCellnum.getText();
        if (!cellulareAggiornato.isEmpty()) {
            regex = "^[0-9]{10}$";
            if (!Pattern.matches(regex, cellulareAggiornato) || oldWorker.getRecord().getEmail().equals(cellulareAggiornato)) {
                System.err.println("cellulare aggiornato è sbagliato");
                setError(updateCellnum, updateErrorField);
            } else {
                unSetError(updateCellnum, updateErrorField);
            }
        } else {
            cellulareAggiornato = null;
        }

        String val = (String) updateWithVehicle.getValue();
        Boolean veicoloAggiornato = null;
        if (val != null) {
            veicoloAggiornato = val.equals("YES") ? true : false;
        }

        String nomeEmergenzaAggiornato = updateEmergencyName.getText();
        if (!nomeEmergenzaAggiornato.isEmpty()) {
            regex = "^[a-zA-Z]+";
            if (!Pattern.matches(regex, nomeEmergenzaAggiornato) || nomeEmergenzaAggiornato.equals(oldWorker.getEmergencyContact().getRecord().getName()) ||
            nomeEmergenzaAggiornato.equals(oldWorker.getRecord().getName())) {
                System.err.println("nome emergenza aggiornato è sbagliato");
                setError(updateEmergencyName, updateErrorField);
            } else {
                unSetError(updateEmergencyName, updateErrorField);
            }
        } else {
            nomeEmergenzaAggiornato = null;
        }

        String cognomeEmergenzaAggiornato = updateEmergencySurname.getText();
        if (!cognomeEmergenzaAggiornato.isEmpty()) {
            regex = "^[a-zA-Z]+";
            if (!Pattern.matches(regex, cognomeEmergenzaAggiornato) || cognomeEmergenzaAggiornato.equals(oldWorker.getEmergencyContact().getRecord().getSurname()) ||
                    cognomeEmergenzaAggiornato.equals(oldWorker.getRecord().getSurname())) {
                System.err.println("cognome emergenza aggiornato è sbagliato");
                setError(updateEmergencySurname, updateErrorField);
            } else {
                unSetError(updateEmergencySurname, updateErrorField);
            }
        } else {
            cognomeEmergenzaAggiornato = null;
        }

        String emailEmergenzaAggiornato = updateEmergencyEmail.getText();
        if (!emailEmergenzaAggiornato.isEmpty()) {
            regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
            if (!Pattern.matches(regex, emailEmergenzaAggiornato) || emailEmergenzaAggiornato.equals(oldWorker.getEmergencyContact().getRecord().getEmail()) ||
                    emailEmergenzaAggiornato.equals(oldWorker.getRecord().getEmail())) {
                System.err.println("email emergenza aggiornata è sbagliata");
                setError(updateEmergencyEmail, updateErrorField);
            } else {
                unSetError(updateEmergencyEmail, updateErrorField);
            }
        } else {
            emailEmergenzaAggiornato = null;
        }

        String cellulareEmergenzaAggiornato = updateEmergencyCellnum.getText();
        if (!cellulareEmergenzaAggiornato.isEmpty()) {
            regex = "^[0-9]{10}$";
            if (!Pattern.matches(regex, cellulareEmergenzaAggiornato) || cellulareEmergenzaAggiornato.equals(oldWorker.getEmergencyContact().getRecord().getCellnum()) ||
                    cellulareEmergenzaAggiornato.equals(oldWorker.getRecord().getCellnum())) {
                System.err.println("cellulare emergenza aggiornato è sbagliato");
                setError(updateEmergencyCellnum, updateErrorField);
            } else {
                unSetError(updateEmergencyCellnum, updateErrorField);
            }
        } else {
            cellulareEmergenzaAggiornato = null;
        }

        // TODO
        // aggiungere i controlli sui campi mancanti
        if ((indirizzoAggiornato != null && !updateAddress.getStyleClass().toString().contains("error")) ||
                (emailAggiornata != null && !updateEmail.getStyleClass().toString().contains("error")) ||
                (cellulareAggiornato != null && !updateCellnum.getStyleClass().toString().contains("error"))||
                (nomeEmergenzaAggiornato != null && !updateEmergencyName.getStyleClass().toString().contains("error")) ||
                (cognomeEmergenzaAggiornato != null && !updateEmergencySurname.getStyleClass().toString().contains("error")) ||
                (emailEmergenzaAggiornato != null && !updateEmergencyEmail.getStyleClass().toString().contains("error")) ||
                (cellulareEmergenzaAggiornato != null && !updateEmergencyCellnum.getStyleClass().toString().contains("error")) ||
                veicoloAggiornato != null)
        {
            // SFILZA controlli per tenere traccia dei campi modificati
            if (indirizzoAggiornato != null) {
                oldWorker.setAddress(indirizzoAggiornato);
            }
            if (emailAggiornata != null) {
                oldWorker.getRecord().setEmail(emailAggiornata);
            }
            if (cellulareAggiornato != null) {
                oldWorker.getRecord().setCellnum(cellulareAggiornato);
            }
            if (nomeEmergenzaAggiornato != null) {
                oldWorker.getEmergencyContact().getRecord().setName(nomeEmergenzaAggiornato);
            }
            if (cognomeEmergenzaAggiornato != null) {
                oldWorker.getEmergencyContact().getRecord().setSurname(cognomeEmergenzaAggiornato);
            }
            if (emailEmergenzaAggiornato != null) {
                oldWorker.getEmergencyContact().getRecord().setEmail(emailEmergenzaAggiornato);
            }
            if (cellulareEmergenzaAggiornato != null) {
                oldWorker.getEmergencyContact().getRecord().setCellnum(cellulareEmergenzaAggiornato);
            }
        }

        Parent content3;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Utility.class.getResource("/view/UpdateExp.fxml"));
            content3 = loader.load();
            Scene scene = new Scene(content3, 850, 950);
            stage.setMinWidth(1000);
            stage.setMinHeight(1000);
            stage.getIcons().add(new Image(String.valueOf(Utility.class.getResource("/logo/icon.png"))));

            stage.setTitle("SeasonalWorkApp");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateExpHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        SeasonalWorker tmp = (SeasonalWorker) stage.getUserData();
        DaoEmployerImplement dao = DaoEmployerImplement.getDao();
        dao.updateRecord(tmp);
        //pass data to dao in order to write it on json
        Utility.changeScene("Home.fxml", stage);
        TextField homeFeedback = (TextField) stage.getScene().lookup("#homeFeedback");
        homeFeedback.setVisible(true);
        homeFeedback.setStyle("-fx-text-fill: green; -fx-font-size: 20px;-fx-font-weight: bolder;-fx-text-alignment: center;");
        homeFeedback.setText("Record update successfully");
    }

    public void addActivityArea(ActionEvent actionEvent) {
        SeasonalWorker worker = getWorker(updateActivityArea);

        if (worker.getActivityArea() == null) {
            worker.setActivityArea(new ArrayList<City>());
        }

        if(updateActivityArea.getValue() != null) {
            City city = City.valueOf((String)updateActivityArea.getValue());
            if (city != null && !worker.getActivityArea().contains(city)) {
                worker.getActivityArea().add(city);
                insertSuccess(cityFeedback);
                updateActivityArea.setValue(null);
            }
            else {
                insertNotSuccess(cityFeedback);
            }
        }
        else {
            cityFeedback.setVisible(false);
        }
    }

    public void addPeriod(ActionEvent actionEvent) {
        SeasonalWorker worker = getWorker(updateperiodBox);

        if (worker.getPeriod() == null) {
            worker.setPeriod(new ArrayList<Season>());
        }

        if(updateperiodBox.getValue() != null) {
            Season perd = Season.valueOf((String)updateperiodBox.getValue());
            if (perd != null && !worker.getPeriod().contains(perd)) {
                worker.getPeriod().add(perd);
                insertSuccess(periodFeedback);
                updateperiodBox.setValue(null);
            }
            else {
                insertNotSuccess(periodFeedback);
            }
        }
        else {
            periodFeedback.setVisible(false);
        }
    }

    public void addLanguage(ActionEvent actionEvent) {
        SeasonalWorker worker = getWorker(updateLanguagesBox);

        if (worker.getLanguages() == null) {
            worker.setLanguages(new ArrayList<Language>());
        }

        if(updateLanguagesBox.getValue() != null) {
            Language lang = Language.valueOf((String)updateLanguagesBox.getValue());
            if (lang != null && !worker.getLanguages().contains(lang)) {
                worker.getLanguages().add(lang);
                insertSuccess(languageFeedback);
                updateLanguagesBox.setValue(null);
            }
            else {
                insertNotSuccess(languageFeedback);
            }
        }
        else {
            languageFeedback.setVisible(false);
        }
    }

    public void addLicense(ActionEvent actionEvent) {
        SeasonalWorker worker = getWorker(updateLicenseBox);

        if (worker.getLicense() == null) {
            worker.setLicense(new ArrayList<License>());
        }

        if(updateLicenseBox.getValue() != null){
            License lic = License.valueOf((String)updateLicenseBox.getValue());
            if (lic != null && !worker.getLicense().contains(lic)) {
                worker.getLicense().add(lic);
                insertSuccess(licenseFeedback);
                updateLicenseBox.setValue(null);
            }
            else {
                insertNotSuccess(licenseFeedback);
            }
        }
        else {
            licenseFeedback.setVisible(false);
        }
    }

    public void insertLanguage(ActionEvent actionEvent) {
        SeasonalWorker worker = getWorker(insertLanguage);

        if(insertLanguage.getValue() != null){
            Language lang = Language.valueOf((String)insertLanguage.getValue());
            if (!worker.getLanguages().contains(lang)) {
                worker.getLanguages().add(lang);
                insertSuccess(insertLanguageFeedback);
                insertLanguage.setValue(null);
            }
            else {
                insertNotSuccess(insertLanguageFeedback);
            }
        }
        else {
            insertLanguageFeedback.setVisible(false);
        }
    }

    public void insertActivityArea(ActionEvent actionEvent) {
        SeasonalWorker worker = getWorker(insertActivityArea);

        if(insertActivityArea.getValue() != null){
            City city = City.valueOf((String)insertActivityArea.getValue());
            if (!worker.getActivityArea().contains(city)) {
                worker.getActivityArea().add(city);
                insertSuccess(insertCityFeedback);
                insertActivityArea.setValue(null);
            }
            else {
                insertNotSuccess(insertCityFeedback);
            }
        }
        else {
            insertCityFeedback.setVisible(false);
        }
    }

    private SeasonalWorker getWorker(ChoiceBox insertActivityArea) {
        Stage stage = (Stage) insertActivityArea.getScene().getWindow();
        SeasonalWorker worker = (SeasonalWorker) stage.getUserData();
        return worker;
    }

    public void addUpdateExpHandler(ActionEvent actionEvent) {

        String updateNomeAzienda = updateNameAzienda.getText();
        String regex = "[a-zA-z,.]+";
        if (!Pattern.matches(regex, updateNomeAzienda)) {
            System.err.println("nomeAzienda è sbagliato");
            setError(updateNameAzienda, updateExpErrorField);
        } else {
            unSetError(updateNameAzienda, updateExpErrorField);
        }


        String updateRetribution = updateRetribuzione.getText();
        regex = "^[+]?([0-9]*[.])?[0-9]+$";
        if (!Pattern.matches(regex, updateRetribution)) {
            System.err.println("retribuzione è sbagliato");
            setError(updateRetribuzione, updateExpErrorField);
        } else {
            unSetError(updateRetribuzione, updateExpErrorField);
        }


        String updateAnnoAssunzione = updateAnnoassunzione.getText();
        regex = "^[0-9]{4}$";
        if (!Pattern.matches(regex, updateAnnoAssunzione) || Integer.parseInt(updateAnnoAssunzione) < LocalDate.now().getYear() - 5) {
            System.err.println("anno assunzione è sbagliato");
            setError(updateAnnoassunzione, updateExpErrorField);
        } else {
            unSetError(updateAnnoassunzione, updateExpErrorField);
        }

        String city = (String) updateCitta.getValue();
        if(city == null) {
            System.err.println("city è sbagliato");
            setError(updateCitta, updateExpErrorField);
        }
        else {
            unSetError(updateCitta, updateExpErrorField);
        }

        String period = (String) updatePeriodo.getValue();
        if(period == null) {
            System.err.println("period è sbagliato");
            setError(updatePeriodo, updateExpErrorField);
        }
        else {
            unSetError(updatePeriodo, updateExpErrorField);
        }

        String lavoro = (String) updateJob.getValue();
        if(lavoro == null) {
            System.err.println("lavoro è sbagliato");
            setError(updateJob, updateExpErrorField);
        }
        else {
            unSetError(updateJob, updateExpErrorField);
        }

        String mansion = updateMansioni.getText();

        if(updateNameAzienda.getStyleClass().toString().contains("error") || updateRetribuzione.getStyleClass().toString().contains("error") ||
                updateAnnoassunzione.getStyleClass().toString().contains("error") || updateCitta == null || updatePeriodo == null || updateJob == null
                || updateMansioni.getStyleClass().toString().contains("error")) {
            System.out.println("c'è qualche campo sbagliato");
        }
        else {
            Stage stage = (Stage) updateExpErrorField.getScene().getWindow();
            Worker worker = (SeasonalWorker) stage.getUserData();

            Job tmp = Model.getModel().createJob(Season.valueOf(period), updateNomeAzienda, mansion, City.valueOf(city),
                    Double.parseDouble(updateRetribution), Jobs.valueOf(lavoro), Integer.parseInt(updateAnnoAssunzione));

            if (!Utility.checkPastExpDuplicate(worker, tmp)){
                worker.getPastExperience().add(tmp);
                updateNameAzienda.setText("");
                updateRetribuzione.setText("");
                updateAnnoassunzione.clear();
                updateCitta.setValue(null);
                updatePeriodo.setValue(null);
                updateJob.setValue(null);
                updateMansioni.setText("");
                insertSuccess(updateExpErrorField);
                unSetError(updateNameAzienda, updateExpErrorField);
                unSetError(updateRetribuzione, updateExpErrorField);
                unSetError(updateAnnoassunzione, updateExpErrorField);
                unSetError(updateCitta, updateExpErrorField);
                unSetError(updatePeriodo, updateExpErrorField);
                unSetError(updateJob, updateExpErrorField);
                insertSuccess(updateExpErrorField);
                System.out.println(worker);
            }
            else {
                setDupError(updateNameAzienda, updateExpErrorField);
                setDupError(updateRetribuzione, updateExpErrorField);
                setDupError(updateAnnoassunzione, updateExpErrorField);
                setDupError(updateCitta, updateExpErrorField);
                setDupError(updatePeriodo, updateExpErrorField);
                setDupError(updateJob, updateExpErrorField);
            }
        }
    }

    public void removeActivityAreaHandler(ActionEvent actionEvent) {
        System.out.println("sono stato premuto diocane");

        SeasonalWorker worker = getWorker(updateActivityArea);
        if(updateActivityArea.getValue() != null) {
            City city = City.valueOf((String)updateActivityArea.getValue());
            if (worker.getActivityArea().contains(city)) {
                worker.getActivityArea().remove(worker.getActivityArea().indexOf(city));
                removeSuccess(cityFeedback);
                updateCitta.setValue(null);
            }
            else {
                removeNotSuccess(cityFeedback);
            }
        }
        else {
            cityFeedback.setVisible(false);
        }
    }

    public void removeLicenseHandler(ActionEvent actionEvent) {
        System.out.println("mi premono dioboia");

        SeasonalWorker worker = getWorker(updateLicenseBox);
        if(updateLicenseBox.getValue() != null) {
            License license = License.valueOf((String)updateLicenseBox.getValue());
            if (worker.getLicense().contains(license)) {
                worker.getLicense().remove(worker.getLicense().indexOf(license));
                removeSuccess(licenseFeedback);
                updateLicenseBox.setValue(null);
            }
            else {
                removeNotSuccess(licenseFeedback);
            }
        }
        else {
            licenseFeedback.setVisible(false);
        }
    }

    public void removeLangaugesHandler(ActionEvent actionEvent) {
        System.out.println("qualcuno mi premette dioporco");

        SeasonalWorker worker = getWorker(updateLanguagesBox);
        if(updateLanguagesBox.getValue() != null) {
            Language language = Language.valueOf((String) updateLanguagesBox.getValue());
            if (worker.getLanguages().contains(language)) {
                worker.getLanguages().remove(worker.getLanguages().indexOf(language));
                removeSuccess(languageFeedback);
                updateLanguagesBox.setValue(null);
            } else {
                removeNotSuccess(languageFeedback);
            }
        }
        else {
            languageFeedback.setVisible(false);
        }
    }

    public void removePeriodHandler(ActionEvent actionEvent) {
        System.out.println("sono stato premuto diocane");

        SeasonalWorker worker = getWorker(updateperiodBox);
        if(updateperiodBox.getValue() != null) {
            Season season1 = Season.valueOf((String)updateperiodBox.getValue());
            if (worker.getPeriod().contains(season1)) {
                worker.getPeriod().remove(worker.getPeriod().indexOf(season1));
                removeSuccess(periodFeedback);
                updateperiodBox.setValue(null);
            }
            else {
                removeNotSuccess(periodFeedback);
            }
        }
        else {
            periodFeedback.setVisible(false);
        }
    }
}