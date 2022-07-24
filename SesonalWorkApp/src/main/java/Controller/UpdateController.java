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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.List;
import java.util.regex.Pattern;
import Model.License;
import Model.Language;
import Model.Jobs;
import Model.Season;
import Model.City;
import Model.Job;
import Model.PastExpTableModel;

import static Controller.Utility.setError;
import static Controller.Utility.unSetError;

public class UpdateController {
    @FXML
    private TextField updateAddress;
    @FXML
    private TextField updateEmail;
    @FXML
    private TextField updateCellnum;
    @FXML
    private TextField updateEmergencyName;
    @FXML
    private TextField updateEmergencySurname;
    @FXML
    private TextField updateEmergencyEmail;
    @FXML
    private TextField updateEmergencyCellnum;
    @FXML
    private TextField updateErrorField;
    @FXML
    private ChoiceBox updateWithVehicle;
    @FXML
    private Button updateRecord;
    @FXML
    private Button logOut;
    @FXML
    private Button insertRecord;

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

    public void updateFieldHandler(ActionEvent actionEvent) {
        String regex;
        Stage stage = (Stage) updateAddress.getScene().getWindow();
        SeasonalWorker oldWorker = (SeasonalWorker) stage.getUserData();

        String indirizzoAggiornato = updateAddress.getText();
        if (!indirizzoAggiornato.isEmpty() || oldWorker.getAddress().equals(indirizzoAggiornato)) {
            regex = "^[a-zA-z]+ .+,? (?:n.)?[0-9]+.*$";
            if (!Pattern.matches(regex, indirizzoAggiornato)) {
                setError(updateAddress, updateErrorField, "error");
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
                setError(updateEmail, updateErrorField, "error");
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
                setError(updateCellnum, updateErrorField, "error");
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
                setError(updateEmergencyName, updateErrorField, "error");
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
                setError(updateEmergencySurname, updateErrorField, "error");
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
                setError(updateEmergencyEmail, updateErrorField, "error");
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
                setError(updateEmergencyCellnum, updateErrorField, "error");
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
                veicoloAggiornato != null || (indirizzoAggiornato == null && emailAggiornata == null && cellulareAggiornato == null &&
                nomeEmergenzaAggiornato == null && cognomeEmergenzaAggiornato == null && emailEmergenzaAggiornato == null && cellulareAggiornato == null
                && veicoloAggiornato == null))
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
    }
}
