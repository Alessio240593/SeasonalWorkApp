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
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import Model.License;
import Model.Language;
import Model.City;
import Model.Season;
import Model.Job;
import Model.PastExpTableModel;
import Model.Jobs;

import static Controller.Utility.*;

public class UpdateExpController {
    @FXML
    private Button updateRecord;
    @FXML
    private Button logOut;
    @FXML
    private Button insertRecord;
    @FXML
    private TextField updateNameAzienda;
    @FXML
    private TextField updateRetribuzione;
    @FXML
    private TextField updateAnnoassunzione;
    @FXML
    private ChoiceBox updateCitta;
    @FXML
    private ChoiceBox updatePeriodo;
    @FXML
    private ChoiceBox updateJob;
    @FXML
    private TextArea updateMansioni;
    @FXML
    private TextField updateExpErrorField;
    @FXML
    private ChoiceBox updateActivityArea;
    @FXML
    private TextField cityFeedback;
    @FXML
    private ChoiceBox updateLicenseBox;
    @FXML
    private TextField licenseFeedback;
    @FXML
    private ChoiceBox updateLanguagesBox;
    @FXML
    private TextField languageFeedback;
    @FXML
    private ChoiceBox updateperiodBox;
    @FXML
    private TextField periodFeedback;

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
                            tmp = Model.getModel().createWithOutIdWorker("SEASONAL", id, w.getAddress(), w.getPastExperience(), w.getBrithInfo(), w.getLanguages(), w.getLicense(),
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

    public void addActivityArea(ActionEvent actionEvent) {
        SeasonalWorker worker = Utility.getWorker(updateActivityArea);

        if (worker.getActivityArea() == null) {
            worker.setActivityArea(new ArrayList<City>());
        }

        if(updateActivityArea.getValue() != null) {
            City city = City.valueOf((String)updateActivityArea.getValue());
            if (city != null && !worker.getActivityArea().contains(city)) {
                worker.getActivityArea().add(city);
                setError(cityFeedback, cityFeedback, "insertSuccess");
                updateActivityArea.setValue(null);
            }
            else {
                setError(cityFeedback, cityFeedback, "insertError");
            }
        }
        else {
            cityFeedback.setVisible(false);
        }
    }

    public void addPeriod(ActionEvent actionEvent) {
        SeasonalWorker worker = Utility.getWorker(updateperiodBox);

        if (worker.getPeriod() == null) {
            worker.setPeriod(new ArrayList<Season>());
        }

        if(updateperiodBox.getValue() != null) {
            Season perd = Season.valueOf((String)updateperiodBox.getValue());
            if (perd != null && !worker.getPeriod().contains(perd)) {
                worker.getPeriod().add(perd);
                setError(periodFeedback, periodFeedback, "insertSuccess");
                updateperiodBox.setValue(null);
            }
            else {
                setError(periodFeedback, periodFeedback, "insertError");
            }
        }
        else {
            periodFeedback.setVisible(false);
        }
    }

    public void addLanguage(ActionEvent actionEvent) {
        SeasonalWorker worker = Utility.getWorker(updateLanguagesBox);

        if (worker.getLanguages() == null) {
            worker.setLanguages(new ArrayList<Language>());
        }

        if(updateLanguagesBox.getValue() != null) {
            Language lang = Language.valueOf((String)updateLanguagesBox.getValue());
            if (lang != null && !worker.getLanguages().contains(lang)) {
                worker.getLanguages().add(lang);
                setError(languageFeedback, languageFeedback, "insertSuccess");
                updateLanguagesBox.setValue(null);
            }
            else {
                setError(languageFeedback, languageFeedback, "insertError");
            }
        }
        else {
            languageFeedback.setVisible(false);
        }
    }

    public void addLicense(ActionEvent actionEvent) {
        SeasonalWorker worker = Utility.getWorker(updateLicenseBox);

        if (worker.getLicense() == null) {
            worker.setLicense(new ArrayList<License>());
        }

        if(updateLicenseBox.getValue() != null){
            License lic = License.valueOf((String)updateLicenseBox.getValue());
            if (lic != null && !worker.getLicense().contains(lic)) {
                worker.getLicense().add(lic);
                setError(licenseFeedback, licenseFeedback, "insertSuccess");
                updateLicenseBox.setValue(null);
            }
            else {
                setError(licenseFeedback, licenseFeedback, "insertError");
            }
        }
        else {
            licenseFeedback.setVisible(false);
        }
    }

    public void removeActivityAreaHandler(ActionEvent actionEvent) {
        SeasonalWorker worker = Utility.getWorker(updateActivityArea);
        if(updateActivityArea.getValue() != null) {
            City city = City.valueOf((String)updateActivityArea.getValue());
            if (worker.getActivityArea().contains(city)) {
                worker.getActivityArea().remove(worker.getActivityArea().indexOf(city));
                setError(cityFeedback, cityFeedback, "removeSuccess");
                updateActivityArea.setValue(null);
            }
            else {
                setError(cityFeedback, cityFeedback, "removeError");
            }
        }
        else {
            cityFeedback.setVisible(false);
        }
    }

    public void removeLicenseHandler(ActionEvent actionEvent) {
        SeasonalWorker worker = Utility.getWorker(updateLicenseBox);
        if(updateLicenseBox.getValue() != null) {
            License license = License.valueOf((String)updateLicenseBox.getValue());
            if (worker.getLicense().contains(license)) {
                worker.getLicense().remove(worker.getLicense().indexOf(license));
                setError(licenseFeedback, licenseFeedback, "removeSuccess");
                updateLicenseBox.setValue(null);
            }
            else {
                setError(licenseFeedback, licenseFeedback, "removeError");
            }
        }
        else {
            licenseFeedback.setVisible(false);
        }
    }

    public void removeLangaugesHandler(ActionEvent actionEvent) {
        SeasonalWorker worker = Utility.getWorker(updateLanguagesBox);
        if(updateLanguagesBox.getValue() != null) {
            Language language = Language.valueOf((String) updateLanguagesBox.getValue());
            if (worker.getLanguages().contains(language)) {
                worker.getLanguages().remove(worker.getLanguages().indexOf(language));
                setError(languageFeedback, languageFeedback, "removeSuccess");
                updateLanguagesBox.setValue(null);
            } else {
                setError(languageFeedback, languageFeedback, "removeError");
            }
        }
        else {
            languageFeedback.setVisible(false);
        }
    }

    public void removePeriodHandler(ActionEvent actionEvent) {
        SeasonalWorker worker = Utility.getWorker(updateperiodBox);
        if(updateperiodBox.getValue() != null) {
            Season season1 = Season.valueOf((String)updateperiodBox.getValue());
            if (worker.getPeriod().contains(season1)) {
                worker.getPeriod().remove(worker.getPeriod().indexOf(season1));
                setError(periodFeedback, periodFeedback, "removeSuccess");
                updateperiodBox.setValue(null);
            }
            else {
                setError(periodFeedback, periodFeedback, "removeError");
            }
        }
        else {
            periodFeedback.setVisible(false);
        }
    }

    public void addUpdateExpHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        SeasonalWorker oldWorker = (SeasonalWorker) stage.getUserData();

        String updateNomeAzienda = updateNameAzienda.getText();
        if (!isAlphanumerical(updateNomeAzienda) || updateNomeAzienda.equals("") || updateNomeAzienda.equals(" ")) {
            System.err.println("nomeAzienda è sbagliato");
            setError(updateNameAzienda, updateExpErrorField, "error");
        } else {
            unSetError(updateNameAzienda, updateExpErrorField);
        }


        String updateRetribution = updateRetribuzione.getText();
        String regex = "^[+]?([0-9]*[.])?[0-9]+$";
        if (!Pattern.matches(regex, updateRetribution)) {
            System.err.println("retribuzione è sbagliato");
            setError(updateRetribuzione, updateExpErrorField, "error");
        } else {
            unSetError(updateRetribuzione, updateExpErrorField);
        }


        String updateAnnoAssunzione = updateAnnoassunzione.getText();
        regex = "^[0-9]{4}$";
        if (!Pattern.matches(regex, updateAnnoAssunzione) || Integer.parseInt(updateAnnoAssunzione) < LocalDate.now().getYear() - 5
            || Integer.parseInt(updateAnnoAssunzione) > LocalDate.now().getYear() ||
                Integer.parseInt(updateAnnoAssunzione) < ((oldWorker.getBrithInfo().getBirthDate().getYear() + 16)) ||
                Integer.parseInt(updateAnnoAssunzione) > LocalDate.now().getYear()) {
            setError(updateAnnoassunzione, updateExpErrorField, "error");
        } else {
            unSetError(updateAnnoassunzione, updateExpErrorField);
        }

        String city = (String) updateCitta.getValue();
        if(city == null) {
            System.err.println("city è sbagliato");
            setError(updateCitta, updateExpErrorField, "error");
        }
        else {
            unSetError(updateCitta, updateExpErrorField);
        }

        String period = (String) updatePeriodo.getValue();
        if(period == null) {
            System.err.println("period è sbagliato");
            setError(updatePeriodo, updateExpErrorField, "error");
        }
        else {
            unSetError(updatePeriodo, updateExpErrorField);
        }

        String lavoro = (String) updateJob.getValue();
        if(lavoro == null) {
            System.err.println("lavoro è sbagliato");
            setError(updateJob, updateExpErrorField, "error");
        }
        else {
            unSetError(updateJob, updateExpErrorField);
        }

        String mansion = updateMansioni.getText();
        if(mansion.length() > 512) {
            setError(updateMansioni, updateExpErrorField, "error");
        }
        else {
            unSetError(updateMansioni, updateExpErrorField);
        }

        if(updateNameAzienda.getStyleClass().toString().contains("error") || updateRetribuzione.getStyleClass().toString().contains("error") ||
                updateAnnoassunzione.getStyleClass().toString().contains("error") || updateCitta == null || updatePeriodo == null || updateJob == null
                || updateMansioni.getStyleClass().toString().contains("error")) {
        }
        else {
            Job tmp = Model.getModel().createJob(Season.valueOf(period), updateNomeAzienda, mansion, City.valueOf(city),
                    Double.parseDouble(updateRetribution), Jobs.valueOf(lavoro), Integer.parseInt(updateAnnoAssunzione));

            if (!Utility.checkPastExpDuplicate(oldWorker, tmp)){
                oldWorker.getPastExperience().add(tmp);
                //clean fields
                updateNameAzienda.setText("");
                updateRetribuzione.setText("");
                updateAnnoassunzione.clear();
                updateCitta.setValue(null);
                updatePeriodo.setValue(null);
                updateJob.setValue(null);
                updateMansioni.setText("");

                setError(updateExpErrorField, updateExpErrorField, "insertSuccess");
                unSetError(updateNameAzienda, updateExpErrorField);
                unSetError(updateRetribuzione, updateExpErrorField);
                unSetError(updateAnnoassunzione, updateExpErrorField);
                unSetError(updateCitta, updateExpErrorField);
                unSetError(updatePeriodo, updateExpErrorField);
                unSetError(updateJob, updateExpErrorField);
            }
            else {
                setError(updateNameAzienda, updateExpErrorField, "duplicate");
                setError(updateRetribuzione, updateExpErrorField, "duplicate");
                setError(updateAnnoassunzione, updateExpErrorField, "duplicate");
                setError(updateCitta, updateExpErrorField, "duplicate");
                setError(updatePeriodo, updateExpErrorField, "duplicate");
                setError(updateJob, updateExpErrorField, "duplicate");
            }
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

}
