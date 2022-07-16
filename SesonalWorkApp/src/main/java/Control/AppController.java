package Control;

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
    public HBox topBox;
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
    public TableView table;
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
    public ChoiceBox updateLicense;
    @FXML
    public ChoiceBox updateLanguages;
    @FXML
    public TextField updateEmergencyName;
    @FXML
    public TextField updateEmergencySurname;
    @FXML
    public TextField updateEmergencyEmail;
    @FXML
    public TextField updateEmergencyCellnum;
    @FXML
    public Button updateField;
    @FXML
    public ToggleButton updateVehicle;


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

    public void setError(Node field, TextField error)  {
        field.getStyleClass().removeAll("field");
        field.getStyleClass().add("error");
        error.setVisible(true);
        error.setText("Dati inseriti nei campi rossi non validi");
        error.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder;");
    }

    public void setEmegengyError(TextField field)  {
        field.getStyleClass().removeAll("field");
        field.getStyleClass().add("error");
        errorEmergencyField.setVisible(true);
        errorEmergencyField.setText( "Contatto emergenza non valido");
        errorEmergencyField.setStyle("-fx-text-fill: red; -fx-font-size: 14px;-fx-font-weight: bolder;");
    }

    public void unSetError(Node field) {
        field.getStyleClass().removeAll("error");
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
        Utility.changeScene("UpdateChoiche.fxml", stage);

        List<SeasonalWorker> workers = Utility.gsonWorkerReader(path);
        ChoiceBox<String> check = (ChoiceBox<String>) stage.getScene().lookup("#workerId");

        check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("ciao");
            }
        });

        ObservableList<String> list = FXCollections.observableArrayList();

        for (SeasonalWorker w : workers) {
            list.add(String.valueOf(w.getId()));
        }

        check.setItems(list);

        //int id = Integer.parseInt(check.getValue());

        final ObservableList<String> data = FXCollections.observableArrayList(
                new String("ciao"), new String("come")
        );

        TableView<String> table2 = (TableView<String>) stage.getScene().lookup("#table");
        table2.setItems(data);
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
            unSetError(name);
        }

        String cognome = surname.getText();
        if (!Pattern.matches(regex, cognome)) {
            System.err.println("cognome è sbagliato");
            setError(surname, errorField);
        } else {
            unSetError(surname);
        }

        String cellulare = cellnum.getText();
        regex = "^[0-9]{10}$";
        if (!Pattern.matches(regex, cellulare)) {
            System.err.println("cellulare è sbagliato");
            setError(cellnum, errorField);
        } else {
            unSetError(cellnum);
        }

        String postaelettronica = email.getText();
        regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        if (!Pattern.matches(regex, postaelettronica)) {
            System.err.println("email è sbagliato");
            setError(email, errorField);
        } else {
            unSetError(email);
        }

        String indirizzo = address.getText();
        regex = "^[a-zA-Z]+ .+,? (?:n.)?[0-9]+.*$";
        if (!Pattern.matches(regex, cellulare)) {
            System.out.println("indirizzo è sbagliato");
            setError(address, errorField);
        }
        else {
            unSetError(address);
        }

        LocalDate data = date.getValue();

        if (data == null) {
            System.out.println("data sbagliata");
            setError(date, errorField);
        } else if ((data.getYear() < 1900 || data.getYear() >= (Year.now().getValue() - 16))) {
            setError(date, errorField);
        } else {
            unSetError(date);
        }

        String nazionalita = nazionalità.getText();
        regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nazionalita)) {
            System.err.println("nazionalità è sbagliato");
            setError(nazionalità, errorField);
        } else {
            unSetError(nazionalità);
        }

        String luogoNascita = cittàNascita.getText();
        regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, luogoNascita)) {
            System.err.println("luogoNascita è sbagliato");
            setError(cittàNascita, errorField);
        } else {
            unSetError(cittàNascita);
        }

        String nome_emergenza = emergency_name.getText();
        regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nome_emergenza)) {
            System.err.println("nome_emergenza è sbagliato");
            setError(emergency_name, errorField);
        }
        else if(nome_emergenza.equals(name.getText())){
             setError(emergency_name, errorEmergencyField);
        }
        else {
            unSetError(emergency_name);
        }

        String cognome_emergenza = emergency_surname.getText();
        if (!Pattern.matches(regex, cognome_emergenza)) {
            System.err.println("cognome_emergenza è sbagliato");
            setError(emergency_surname, errorField);
        }
        else if(cognome_emergenza.equals(surname.getText())){
            setError(emergency_surname, errorEmergencyField);
        }
        else {
            unSetError(emergency_surname);
        }

        String cellulare_emergenza = emergency_cellnum.getText();
        regex = "^[0-9]{10}$";
        if (!Pattern.matches(regex, cellulare_emergenza)) {
            System.err.println("cellulare_emergenza è sbagliato");
            setError(emergency_cellnum, errorField);
        }
        else if(cellulare_emergenza.equals(cellulare)){
            setError(emergency_cellnum, errorEmergencyField);
        }
        else {
            unSetError(emergency_cellnum);
        }

        String postaelettronica_emergenza = emergency_email.getText();
        regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        if (!Pattern.matches(regex, postaelettronica_emergenza)) {
            System.err.println("email_emergenza è sbagliato");
            setError(emergency_email, errorField);
        }
        else if(postaelettronica_emergenza.equals(email.getText())){
            setError(emergency_email, errorEmergencyField);
        }
        else {
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
                stage.setMinWidth(900);
                stage.setMinHeight(900);
                stage.getIcons().add(new Image(String.valueOf(Utility.class.getResource("/icon/icon.png"))));

                //occhio a BirthData
                stage.setUserData(Model.getModel().createWorker("SEASONAL", indirizzo, new ArrayList<Job>(),
                        new BirthData(data, nazionalita, luogoNascita), langlist, licenselist, veicolo,
                            citieslist, seasonlist, new Person(new Record(nome_emergenza, cognome_emergenza, cellulare_emergenza, postaelettronica_emergenza)),
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
    }

    public void addHandler(ActionEvent actionEvent) {

        //da sistemare tutte le regex che hanno scassato il cazzo
        String nomeAzienda = nameAzienda.getText();
        String regex = "[a-zA-z,.]+";
        if (!Pattern.matches(regex, nomeAzienda)) {
            System.err.println("nomeAzienda è sbagliato");
            setError(nameAzienda, expErrorField);
        } else {
            unSetError(nameAzienda);
        }

        String retribution = retribuzione.getText();
        regex = "^[+]?([0-9]*[.])?[0-9]+$";
        if (!Pattern.matches(regex, retribution) || Double.parseDouble(retribution) > 10000) {
            System.err.println("retribuzione è sbagliato");
            setError(retribuzione, expErrorField);
        } else {
            unSetError(retribuzione);
        }

        Stage stage = (Stage) add.getScene().getWindow();
        String annoAssunzione = annoassunzione.getText();
        regex = "^[0-9]{4}$";
        if (!Pattern.matches(regex, annoAssunzione) || Integer.parseInt(annoAssunzione) < 1900) //||
                //Integer.parseInt(annoAssunzione) < (((SeasonalWorker) stage.getUserData()).getBrithInfo().getBirthDate().getYear() + 16))
            {
            System.err.println("annoAssunzione è sbagliato");
            setError(annoassunzione, expErrorField);
        } else {
            unSetError(annoassunzione);
        }

        String city = (String) citta.getValue();
        if(city == null) {
            System.err.println("city è sbagliato");
            setError(citta, expErrorField);
        }
        else {
            unSetError(citta);
        }

        String period = (String) periodo.getValue();
        if(period == null) {
            System.err.println("period è sbagliato");
            setError(periodo, expErrorField);
        }
        else {
            unSetError(periodo);
        }

        String lavoro = (String) job.getValue();
        if(lavoro == null) {
            System.err.println("lavoro è sbagliato");
            setError(job, expErrorField);
        }
        else {
            unSetError(job);
        }

        String mansion = mansioni.getText();
        regex="[a-zA-z,.0-9]+";
        if(!Pattern.matches(regex, mansion)) {
            System.err.println("mansion è sbagliato");
            setError(mansioni, expErrorField);
        }
        else {
            unSetError(mansioni);
        }

        if(nameAzienda.getStyleClass().toString().contains("error") || retribuzione.getStyleClass().toString().contains("error") ||
            annoassunzione.getStyleClass().toString().contains("error") || citta == null || periodo == null || job == null
                || mansioni.getStyleClass().toString().contains("error")) {
            System.out.println("c'è qualche campo sbagliato");
        }
        else {
            Worker worker = (SeasonalWorker) stage.getUserData();

            Job tmp = Model.getModel().createJob(Season.valueOf(period), nomeAzienda, mansion, City.valueOf(city),
                    Double.parseDouble(retribution), Jobs.valueOf(lavoro), Integer.parseInt(annoAssunzione));

            if (!Utility.checkPastExpDuplicate(worker, tmp))
                worker.getPastExperience().add(tmp);

            nameAzienda.setText("");
            retribuzione.setText("");
            annoassunzione.clear();
            citta.setValue(null);
            periodo.setValue(null);
            job.setValue(null);
            mansioni.setText("");
            System.out.println(worker);
        }
    }

    public void nextUpdateHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        Utility.changeScene("Update.fxml", stage);
    }

    public void updateFieldHandler(ActionEvent actionEvent) {
        String regex;

        String indirizzoAggiornato = updateAddress.getText();
        if (!indirizzoAggiornato.isEmpty()) {
            regex = "^[a-z]+ .+,? (?:n.)?[0-9]+.*$";
            if (!Pattern.matches(regex, indirizzoAggiornato)) {
                System.err.println("indirizzo aggiornato è sbagliato");
                setError(updateAddress, errorField);
            } else {
                unSetError(updateAddress);
            }
        } else {
            indirizzoAggiornato = null;
        }

        String emailAggiornata = updateEmail.getText();
        if (!emailAggiornata.isEmpty()) {
            regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
            if (!Pattern.matches(regex, emailAggiornata)) {
                System.err.println("email aggiornata è sbagliata");
                setError(updateEmail, errorField);
            } else {
                unSetError(updateEmail);
            }
        } else {
            emailAggiornata = null;
        }

        String cellulareAggiornato = updateCellnum.getText();
        if (!cellulareAggiornato.isEmpty()) {
            regex = "^[0-9]{10}$";
            if (!Pattern.matches(regex, cellulareAggiornato)) {
                System.err.println("cellulare aggiornato è sbagliato");
                setError(updateCellnum, errorField);
            } else {
                unSetError(updateCellnum);
            }
        } else {
            cellulareAggiornato = null;
        }

        // Ale per te <3
        // TODO
        // with vehicle

        // TODO
        // i campi nei menu a tendina

        String nomeEmergenzaAggiornato = updateEmergencyName.getText();
        if (!nomeEmergenzaAggiornato.isEmpty()) {
            regex = "^[a-zA-Z]+";
            if (!Pattern.matches(regex, nomeEmergenzaAggiornato)) {
                System.err.println("nome emergenza aggiornato è sbagliato");
                setError(updateEmergencyName, errorField);
            } else {
                unSetError(updateEmergencyName);
            }
        } else {
            nomeEmergenzaAggiornato = null;
        }

        String cognomeEmergenzaAggiornato = updateEmergencySurname.getText();
        if (!cognomeEmergenzaAggiornato.isEmpty()) {
            regex = "^[a-zA-Z]+";
            if (!Pattern.matches(regex, cognomeEmergenzaAggiornato)) {
                System.err.println("cognome emergenza aggiornato è sbagliato");
                setError(updateEmergencySurname, errorField);
            } else {
                unSetError(updateEmergencySurname);
            }
        } else {
            cognomeEmergenzaAggiornato = null;
        }

        String emailEmergenzaAggiornato = updateEmergencyEmail.getText();
        if (!emailEmergenzaAggiornato.isEmpty()) {
            regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
            if (!Pattern.matches(regex, emailEmergenzaAggiornato)) {
                System.err.println("email emergenza aggiornata è sbagliata");
                setError(updateEmergencyEmail, errorField);
            } else {
                unSetError(updateEmergencyEmail);
            }
        } else {
            emailEmergenzaAggiornato = null;
        }

        String cellulareEmergenzaAggiornato = updateEmergencyCellnum.getText();
        if (!cellulareEmergenzaAggiornato.isEmpty()) {
            regex = "^[0-9]{10}$";
            if (!Pattern.matches(regex, cellulareEmergenzaAggiornato)) {
                System.err.println("cellulare emergenza aggiornato è sbagliato");
                setError(updateEmergencyCellnum, errorField);
            } else {
                unSetError(updateEmergencyCellnum);
            }
        } else {
            cellulareEmergenzaAggiornato = null;
        }

        // TODO
        // aggiungere i controlli sui campi mancanti
        if (indirizzoAggiornato != null || emailAggiornata != null || cellulareAggiornato != null ||
            nomeEmergenzaAggiornato != null || cognomeEmergenzaAggiornato != null ||
                emailEmergenzaAggiornato != null || cellulareEmergenzaAggiornato != null)
        {

            Stage stage = (Stage) next.getScene().getWindow();
            Parent content3;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Utility.class.getResource("/view/UpdateExp.fxml"));
                content3 = loader.load();
                Scene scene = new Scene(content3, 850, 900);
                stage.setMinWidth(900);
                stage.setMinHeight(900);
                stage.getIcons().add(new Image(String.valueOf(Utility.class.getResource("/icon/icon.png"))));

                // occhio a lingue, patentie, città e veicoli
                stage.setUserData(Model.getModel().createWorker("SEASONAL", indirizzoAggiornato, new ArrayList<Job>(),
                        null,
                        langlist, licenselist, veicolo,  citieslist, seasonlist,
                        new Person(new Record(nomeEmergenzaAggiornato, cognomeEmergenzaAggiornato, cellulareEmergenzaAggiornato, emailEmergenzaAggiornato)),
                        new Record(null, null, cellulareAggiornato, emailAggiornata)));
                //stage.setUserData(new SeasonalWorker(12));
                stage.setTitle("SeasonalWorkApp");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) insertRecord.getScene().getWindow();
        Utility.changeScene("UpdateExp.fxml", stage);
    }

    public void updateExpHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        Utility.changeScene("Home.fxml", stage);
    }

    public void addActivityArea(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();

        if (((SeasonalWorker) (stage.getUserData())) == null) {
            stage.setUserData(new SeasonalWorker(12));
        }

        List<City> listaCitta = ((SeasonalWorker) (stage.getUserData())).getActivityArea();

        listaCitta.add((City) updateActivityArea.getValue());


    }
}
