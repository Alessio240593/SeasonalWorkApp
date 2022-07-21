package Controller;

import Model.Model;
import Model.SeasonalWorker;
import Model.TableViewModel;
import Model.Worker;
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
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import Model.License;
import Model.Language;
import Model.City;
import Model.Record;
import Model.Jobs;
import Model.BirthData;
import Model.Season;
import Model.Person;
import Model.Job;

import static Controller.Utility.setError;
import static Controller.Utility.unSetError;

public class InsertController {

    @FXML
    public ChoiceBox withVehicleChoicheBox;
    @FXML
    private Button insertRecord;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField cellnum;
    @FXML
    private TextField address;
    @FXML
    private TextField email;
    @FXML
    private TextField nazionalità;
    @FXML
    private TextField cittàNascita;
    @FXML
    private TextField emergency_name;
    @FXML
    private TextField emergency_surname;
    @FXML
    private TextField emergency_email;
    @FXML
    private TextField emergency_cellnum;
    @FXML
    private TextField errorEmergencyField;
    @FXML
    private TextField errorField;
    @FXML
    private Button next;
    @FXML
    private Button logOut;
    @FXML
    private Button updateRecord;
    @FXML
    private DatePicker date;
    @FXML
    private HBox license1;
    @FXML
    private HBox license2;
    @FXML
    private HBox license3;
    @FXML
    private HBox season;

    public void homeHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        Utility.changeScene("Home.fxml", stage);
        stage.setUserData(null);
    }

    public void searchHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        Utility.changeScene("Search.fxml", stage);
        stage.setUserData(null);


        ChoiceBox<Object> filter = (ChoiceBox<Object>) stage.getScene().lookup("#filter");
        ChoiceBox<Object> filterField = (ChoiceBox<Object>) stage.getScene().lookup("#filterField");

        filter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String Filter = null;
                if (filter.getValue() == null) {
                    filter.getStyleClass().add("error");
                } else {
                    Filter = (String) filter.getValue().toString().toLowerCase();
                    filter.getStyleClass().removeAll("error");
                    ObservableList<Object> list = FXCollections.observableArrayList();

                    switch (Filter) {
                        case "language":
                            for (Language lan : Language.values()) {
                                list.add(lan);
                            }
                            break;
                        case "license":
                            for (License lic : License.values()) {
                                list.add(lic);
                            }
                            break;
                        case "period":
                            for (Season season : Season.values()) {
                                list.add(season);
                            }
                            break;
                        case "job":
                            for (Jobs job : Jobs.values()) {
                                list.add(job);
                            }
                            break;
                        case "activity area":
                            for (City city : City.values()) {
                                list.add(city);
                            }
                            break;
                        case "with vehicle":
                            //se vogliamo gestire si and no vehicle nella ricerca
                            list.add("YES VEHICLE");
                            list.add("NO VEHICLE");
                            break;
                    }

                    filterField.setItems(list);
                }
            }
        });
    }

    public void insertHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        Utility.changeScene("Insert.fxml", stage);
        stage.setUserData(null);
    }

    public void updateHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) updateRecord.getScene().getWindow();
        String path = System.getenv("PWD") + "/src/resources/database/workers.json";
        Utility.changeScene("UpdateChoice.fxml", stage);
        stage.setUserData(null);

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
                            System.out.println(w);
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
                            Model.getModel().createRecord(tmp.getRecord().getName(), tmp.getRecord().getSurname(), tmp.getRecord().getEmail(),
                                    tmp.getRecord().getCellnum(), tmpDate , tmp.getBrithInfo().getBirthplace()));

                    table.setItems(data);
                }
            }
        });
    }

    public void logOutHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        Utility.changeScene("Login.fxml", stage);
        stage.setUserData(null);
    }

    public void exitHandler(ActionEvent actionEvent) {
        System.exit(0);
    }

    public static boolean checkName(String name, String regex) {
        return Pattern.matches(regex, name);
    }

    public static boolean checkSurname(String surname, String regex) {
        return Pattern.matches(regex, surname);
    }

    public void nextInsertHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();

        List<SeasonalWorker> workers = (List<SeasonalWorker>) stage.getUserData();

        if(workers == null) {
            String path = System.getenv("PWD") + "/src/resources/database/workers.json";
            workers = Utility.gsonWorkerReader(path);
            stage.setUserData(workers);
        }

        String nome = name.getText();
        String regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nome)) {
            System.err.println("nome è sbagliato");
            setError(name, errorField, "error");
        } else {
            unSetError(name, errorField);
        }
        //worker.stream().forEach(worker1 -> {if(worker1.getRecord().getName().equals(name.getText()))});


        String cognome = surname.getText();
        if (!Pattern.matches(regex, cognome)) {
            System.err.println("cognome è sbagliato");
            setError(surname, errorField, "error");
        } else {
            unSetError(surname, errorField);
        }

        String cellulare = cellnum.getText();
        regex = "^[0-9]{10}$";
        if (!Pattern.matches(regex, cellulare)) {
            System.err.println("cellulare è sbagliato");
            setError(cellnum, errorField, "error");
        } else {
            for (Worker worker : workers)
                if (worker.getRecord().getCellnum().equals(cellulare)) {
                    System.out.println("Numero di cellulare associato ad un altro utente");
                    setError(cellnum, errorField, "duplicate field");
                    break;
                }
                else {
                    unSetError(cellnum, errorField);
                }
        }


        String postaelettronica = email.getText();
        regex = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        if (!Pattern.matches(regex, postaelettronica)) {
            System.err.println("email è sbagliato");
            setError(email, errorField, "error");
        } else {
            for (Worker worker : workers) {
                if (worker.getRecord().getEmail().equals(postaelettronica)) {
                    System.out.println("Email associata ad un altro utente");
                    setError(email, errorField, "duplicate field");
                    break;
                }
                else {
                    unSetError(email, errorField);
                }
            }
        }

        //TODO controllare regex
        String indirizzo = address.getText();
        /*regex = "^[a-zA-z]+ .+,? (?:n.)?[0-9]+.*$";
        if (!Pattern.matches(regex, cellulare)) {
            System.out.println("indirizzo è sbagliato");
            setError(address, errorField, "error");
        }
        else {
            unSetError(address, errorField);
        }*/

        LocalDate data = date.getValue();

        if (data == null) {
            System.out.println("data sbagliata");
            setError(date, errorField, "error");
        } else if ((data.getYear() < 1900 || data.getYear() >= (Year.now().getValue() - 16))) {
            setError(date, errorField, "error");
        } else {
            unSetError(date, errorField);
        }

        String nazionalita = nazionalità.getText();
        regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nazionalita)) {
            System.err.println("nazionalità è sbagliato");
            setError(nazionalità, errorField, "error");
        } else {
            unSetError(nazionalità, errorField);
        }

        String luogoNascita = cittàNascita.getText();
        regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, luogoNascita)) {
            System.err.println("luogoNascita è sbagliato");
            setError(cittàNascita, errorField, "error");
        } else {
            unSetError(cittàNascita, errorField);
        }

        String nome_emergenza = emergency_name.getText();
        regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nome_emergenza)) {
            System.err.println("nome_emergenza è sbagliato");
            setError(emergency_name, errorField, "error");
        }
        else {
            unSetError(emergency_name, errorField);
        }

        String cognome_emergenza = emergency_surname.getText();
        if (!Pattern.matches(regex, cognome_emergenza)) {
            System.err.println("cognome_emergenza è sbagliato");
            setError(emergency_surname, errorField, "error");
        }
        else {
            unSetError(emergency_surname, errorField);
        }

        String cellulare_emergenza = emergency_cellnum.getText();
        regex = "^[0-9]{10}$";
        if (!Pattern.matches(regex, cellulare_emergenza)) {
            System.err.println("cellulare_emergenza è sbagliato");
            setError(emergency_cellnum, errorField, "error");
        }
        else if(cellulare_emergenza.equals(cellulare)){
            setError(emergency_cellnum, errorEmergencyField, "contactError");
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
            setError(emergency_email, errorField, "error");
        }
        else if(postaelettronica_emergenza.equals(postaelettronica)){
            setError(emergency_email, errorEmergencyField, "contactError");
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

        Boolean veicolo = null;
        if(withVehicleChoicheBox.getValue() == null) {
            withVehicleChoicheBox.getStyleClass().add("error");
        }
        else {
            veicolo = withVehicleChoicheBox.getValue().toString().toLowerCase().equals("yes") ? true : false;
            withVehicleChoicheBox.getStyleClass().removeAll("error");
            System.out.println(veicolo);
        }

        if (name.getStyleClass().toString().contains("error") || surname.getStyleClass().toString().contains("error") || cellnum.getStyleClass().toString().contains("error") ||
                address.getStyleClass().toString().contains("error") || email.getStyleClass().toString().contains("error") || emergency_name.getStyleClass().toString().contains("error") ||
                emergency_surname.getStyleClass().toString().contains("error") || emergency_cellnum.getStyleClass().toString().contains("error") ||
                emergency_email.getStyleClass().toString().contains("error") || nazionalità.getStyleClass().toString().contains("error") || cittàNascita.getStyleClass().toString().contains("error") ||
                data == null || date.getStyleClass().toString().contains("error") || withVehicleChoicheBox.getValue() == null) {
            System.out.println("c'è qualche campo sbagliato");
        } else {
            stage = (Stage) next.getScene().getWindow();
            Parent content2;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Utility.class.getResource("/view/InsertExp.fxml"));
                content2 = loader.load();
                Scene scene = new Scene(content2, 850, 900);
                stage.setMinWidth(950);
                stage.setMinHeight(1000);
                stage.getIcons().add(new Image(String.valueOf(Utility.class.getResource("/logo/icon.png"))));

                //occhio a BirthData
                stage.setUserData(Model.getModel().createWorker("SEASONAL", indirizzo, new ArrayList<Job>(),
                        new BirthData(data, nazionalita, luogoNascita), new ArrayList<Language>(), licenselist, veicolo,
                        new ArrayList<City>(), seasonlist, new Person(new Record(nome_emergenza, cognome_emergenza,
                                cellulare_emergenza, postaelettronica_emergenza)),
                        new Record(nome, cognome, postaelettronica, cellulare)));

                //stage.setUserData(new SeasonalWorker(12));
                stage.setTitle("SeasonalWorkApp");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
