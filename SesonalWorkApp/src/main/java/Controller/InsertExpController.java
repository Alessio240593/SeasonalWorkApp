package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import Model.PastExpTableModel;

import static Controller.Utility.isAlphanumerical;
import static Controller.Utility.unSetError;
import static Controller.Utility.setError;

public class InsertExpController {
    @FXML
    private TextField nameAzienda;
    @FXML
    private TextField retribuzione;
    @FXML
    private TextField annoassunzione;
    @FXML
    private TextArea mansioni;
    @FXML
    private ChoiceBox job;
    @FXML
    private ChoiceBox periodo;
    @FXML
    private ChoiceBox citta;
    @FXML
    private Button submit;
    @FXML
    private Button add;
    @FXML
    private TextField expErrorField;
    @FXML
    private Button logOut;
    @FXML
    private Button insertRecord;
    @FXML
    private ChoiceBox insertLanguage;
    @FXML
    private TextField insertLanguageFeedback;
    @FXML
    private ChoiceBox insertActivityArea;
    @FXML
    private TextField insertCityFeedback;
    @FXML
    private Button updateRecord;

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
                            list.add("YES_VEHICLE");
                            list.add("NO_VEHICLE");
                            break;
                    }

                    filterField.setItems(list);
                }
            }
        });

        TableView<TableViewModel> table = (TableView<TableViewModel>) stage.getScene().lookup("#resultTable");
        table.setRowFactory( tv -> {
            TableRow<TableViewModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {

                    ObservableList<TableViewModel> selected = table.getSelectionModel().getSelectedItems();

                    if (!selected.isEmpty()) {
                        String path = System.getenv("PWD") + "/src/resources/database/workers.json";
                        List<SeasonalWorker> workers = Utility.gsonWorkerReader(path);
                        SeasonalWorker w = null;

                        for (SeasonalWorker worker : workers) {
                            if (worker.getId() == selected.get(0).getId()) {
                                w = worker;
                                break;
                            }
                        }
                        stage.setUserData(w);
                        Utility.changeScene("SearchResult.fxml", stage);

                        TextField resultName = (TextField) stage.getScene().lookup("#resultName");
                        resultName.setText(w.getRecord().getName());
                        TextField resultSurname = (TextField) stage.getScene().lookup("#resultSurname");
                        resultSurname.setText(w.getRecord().getSurname());
                        TextField resultEmail = (TextField) stage.getScene().lookup("#resultEmail");
                        resultEmail.setText(w.getRecord().getEmail());
                        TextField resultCellnum = (TextField) stage.getScene().lookup("#resultCellnum");
                        resultCellnum.setText(w.getRecord().getCellnum());
                        TextField resultAddress = (TextField) stage.getScene().lookup("#resultAddress");
                        resultAddress.setText(w.getAddress());
                        TextField resultBirthData = (TextField) stage.getScene().lookup("#resultBirthData");

                        String tmpDate = (w.getBrithInfo().getBirthDate().getDayOfMonth() < 10 ? "0" + w.getBrithInfo().getBirthDate().getDayOfMonth() :
                                w.getBrithInfo().getBirthDate().getDayOfMonth()) + "/" + (w.getBrithInfo().getBirthDate().getMonth().getValue() < 10 ? "0" +
                                w.getBrithInfo().getBirthDate().getMonth().getValue() : w.getBrithInfo().getBirthDate().getMonth().getValue()) + "/" +
                                w.getBrithInfo().getBirthDate().getYear();

                        resultBirthData.setText(tmpDate);
                        TextField resultCittàNascita = (TextField) stage.getScene().lookup("#resultCittàNascita");
                        resultCittàNascita.setText(w.getBrithInfo().getBirthplace());
                        TextField resultEmergency_name = (TextField) stage.getScene().lookup("#resultEmergency_name");
                        resultEmergency_name.setText(w.getEmergencyContact().getRecord().getName());
                        TextField resultEmergency_surname = (TextField) stage.getScene().lookup("#resultEmergency_surname");
                        resultEmergency_surname.setText(w.getEmergencyContact().getRecord().getSurname());
                        TextField resultEmergency_cellnum = (TextField) stage.getScene().lookup("#resultEmergency_cellnum");
                        resultEmergency_cellnum.setText(w.getEmergencyContact().getRecord().getCellnum());
                        TextField resultEmergency_email = (TextField) stage.getScene().lookup("#resultEmergency_email");
                        resultEmergency_email.setText(w.getEmergencyContact().getRecord().getEmail());
                        TextField resultNazionalità = (TextField) stage.getScene().lookup("#resultNazionalità");
                        resultNazionalità.setText(w.getBrithInfo().getNationality());

                        ObservableList<Object> list = FXCollections.observableArrayList();
                        ChoiceBox resultLanguage = (ChoiceBox) stage.getScene().lookup("#resultLanguage");
                        for (Language lan : w.getLanguages()) {
                            list.add(lan);
                        }
                        resultLanguage.setItems(list);

                        ObservableList<Object> list2 = FXCollections.observableArrayList();
                        ChoiceBox resultLicense = (ChoiceBox) stage.getScene().lookup("#resultLicense");
                        for (License lic : w.getLicense()) {
                            list2.add(lic);
                        }
                        resultLicense.setItems(list2);

                        ObservableList<Object> list3 = FXCollections.observableArrayList();
                        ChoiceBox resultCity = (ChoiceBox) stage.getScene().lookup("#resultCity");
                        for (City city : w.getActivityArea()) {
                            list3.add(city);
                        }
                        resultCity.setItems(list3);

                        ObservableList<Object> list4 = FXCollections.observableArrayList();
                        ChoiceBox resultPeriod = (ChoiceBox) stage.getScene().lookup("#resultPeriod");
                        for (Season season : w.getPeriod()) {
                            list4.add(season);
                        }
                        resultPeriod.setItems(list4);

                        ObservableList<Object> list5 = FXCollections.observableArrayList();
                        ChoiceBox resultWithVehicle = (ChoiceBox) stage.getScene().lookup("#resultWithVehicle");
                        if (w.isWithVehicle() == true) {
                            list5.add("YES");
                        } else {
                            list5.add("NO");
                        }
                        resultWithVehicle.setItems(list5);

                        TableView<PastExpTableModel> pastExpTable = (TableView<PastExpTableModel>) stage.getScene().lookup("#pastExpTable");
                        ObservableList<PastExpTableModel> pastExp = FXCollections.observableArrayList();

                        for (Job job : w.getPastExperience()) {
                            pastExp.add(new PastExpTableModel(job.getPeriod().toString(), String.valueOf(job.getYear()), job.getCompanyName(),
                                    job.getTask(), job.getArea().toString(), String.valueOf(job.getGrossDailySalary()) + "€", job.getJob().toString()));
                        }

                        pastExpTable.setItems(pastExp);
                    }
                }
            });
            return row ;
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

    public void insertLanguage(ActionEvent actionEvent) {
        SeasonalWorker worker = Utility.getWorker(insertLanguage);

        if(insertLanguage.getValue() != null){
            Language lang = Language.valueOf((String)insertLanguage.getValue());
            if (!worker.getLanguages().contains(lang)) {
                worker.getLanguages().add(lang);
                setError(insertLanguageFeedback, insertLanguageFeedback, "insertSuccess");
                insertLanguage.setValue(null);
            }
            else {
                setError(insertLanguageFeedback, insertLanguageFeedback, "insertError");
            }
        }
        else {
            insertLanguageFeedback.setVisible(false);
        }
    }

    public void insertActivityArea(ActionEvent actionEvent) {
        SeasonalWorker worker = Utility.getWorker(insertActivityArea);

        if(insertActivityArea.getValue() != null){
            City city = City.valueOf((String)insertActivityArea.getValue());
            if (!worker.getActivityArea().contains(city)) {
                worker.getActivityArea().add(city);
                setError(insertCityFeedback, insertCityFeedback, "insertSuccess");
                insertActivityArea.setValue(null);
            }
            else {
                setError(insertCityFeedback, insertCityFeedback, "insertError");
            }
        }
        else {
            insertCityFeedback.setVisible(false);
        }
    }

    public void addExpHandler(ActionEvent actionEvent) {

        //TODO regex azienda da sistemare
        String nomeAzienda = nameAzienda.getText();
        if (!isAlphanumerical(nomeAzienda) || nomeAzienda.equals("") || nomeAzienda.equals(" ")) {
            System.err.println("nomeAzienda è sbagliato");
            setError(nameAzienda, expErrorField, "error");
        } else {
            unSetError(nameAzienda, expErrorField);
        }

        String retribution = retribuzione.getText();
        String regex = "^[+]?([0-9]*[.])?[0-9]+$";
        if (!Pattern.matches(regex, retribution) || Double.parseDouble(retribution) > 10000) {
            System.err.println("retribuzione è sbagliato");
            setError(retribuzione, expErrorField, "error");
        } else {
            unSetError(retribuzione, expErrorField);
        }

        Stage stage = (Stage) add.getScene().getWindow();
        String annoAssunzione = annoassunzione.getText();
        regex = "^[0-9]{4}$";
        if ((!Pattern.matches(regex, annoAssunzione) || Integer.parseInt(annoAssunzione) < 1950) ||
                Integer.parseInt(annoAssunzione) < LocalDate.now().getYear() - 5 ||
                Integer.parseInt(annoAssunzione) < (((SeasonalWorker) stage.getUserData()).getBrithInfo().getBirthDate().getYear() + 16) ||
                Integer.parseInt(annoAssunzione) > LocalDate.now().getYear()) {
            System.err.println("annoAssunzione è sbagliato");
            setError(annoassunzione, expErrorField, "error");
        } else {
            unSetError(annoassunzione, expErrorField);
        }

        String city = (String) citta.getValue();
        if (city == null) {
            System.err.println("city è sbagliato");
            setError(citta, expErrorField, "error");
        } else {
            unSetError(citta, expErrorField);
        }

        String period = (String) periodo.getValue();
        if (period == null) {
            System.err.println("period è sbagliato");
            setError(periodo, expErrorField, "error");
        } else {
            unSetError(periodo, expErrorField);
        }

        String lavoro = (String) job.getValue();
        if (lavoro == null) {
            System.err.println("lavoro è sbagliato");
            setError(job, expErrorField, "error");
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
                //clean fields
                nameAzienda.setText("");
                retribuzione.setText("");
                annoassunzione.clear();
                citta.setValue(null);
                periodo.setValue(null);
                job.setValue(null);
                mansioni.setText("");

                System.out.println(worker);
                setError(expErrorField, expErrorField, "insertSuccess");
                unSetError(nameAzienda, expErrorField);
                unSetError(retribuzione, expErrorField);
                unSetError(annoassunzione, expErrorField);
                unSetError(citta, expErrorField);
                unSetError(periodo, expErrorField);
                unSetError(job, expErrorField);
            } else {
                setError(nameAzienda, expErrorField, "duplicate");
                setError(retribuzione, expErrorField, "duplicate");
                setError(annoassunzione, expErrorField, "duplicate");
                setError(citta, expErrorField, "duplicate");
                setError(periodo, expErrorField, "duplicate");
                setError(job, expErrorField, "duplicate");
            }
        }
    }

    public void submitHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) add.getScene().getWindow();
        SeasonalWorker worker = (SeasonalWorker) stage.getUserData();
        DaoEmployerImplement tmp = DaoEmployerImplement.getDao();
        boolean success = tmp.addRecord(worker);

        if(success) {
            Utility.changeScene("Home.fxml", (Stage) submit.getScene().getWindow());
            TextField homeFeedback = (TextField) stage.getScene().lookup("#homeFeedback");
            homeFeedback.setVisible(true);
            homeFeedback.setStyle("-fx-text-fill: green; -fx-font-size: 20px;-fx-font-weight: bolder;-fx-text-alignment: center;");
            homeFeedback.setText("Record insert successfully");
        }
        else {
            setError(expErrorField, expErrorField, "duplicate workers");
        }
    }

}
