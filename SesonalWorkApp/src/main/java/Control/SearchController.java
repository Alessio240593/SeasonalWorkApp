package Control;

import Model.Model;
import Model.SeasonalWorker;
import Model.TableViewModel;
import Model.Worker;
import Model.Language;
import Model.License;
import Model.SearchModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SearchController {

    @FXML
    public ChoiceBox languageFilter;
    @FXML
    public ChoiceBox languageOrAndFilter;
    @FXML
    public ChoiceBox licenseFilter;
    @FXML
    public ChoiceBox licenseOrAndFilter;
    @FXML
    private Button updateRecord;
    @FXML
    private Button logOut;
    @FXML
    private Button insertRecord;

    public void homeHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        Utility.changeScene("Home.fxml", stage);
    }

    public void insertHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        Utility.changeScene("Insert.fxml", stage);
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
    }

    public void exitHandler(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void addLanguageFilter(ActionEvent actionEvent) {
        Stage stage = (Stage) languageFilter.getScene().getWindow();
        List<SearchModel> model = (List<SearchModel>) stage.getUserData();
        if (model == null) {
            model = new ArrayList<SearchModel>();
            stage.setUserData(model);
        }


        if (languageFilter.getValue() != null) {
            Language lang = Language.valueOf((String) languageFilter.getValue());

            if(!model.contains(lang)) {
                if (model.size() == 0) {
                    model.add(new SearchModel(lang, null));
                }
                else {
                    if (languageOrAndFilter.getValue() != null) {
                        model.add(new SearchModel(lang, (String) languageOrAndFilter.getValue()));
                    }
                    else {
                        //super errore
                    }
                }
            }
        }
        System.out.println(model);
    }

    public void addLicenseFilter(ActionEvent actionEvent) {
        Stage stage = (Stage) licenseFilter.getScene().getWindow();
        List<SearchModel> model = (List<SearchModel>) stage.getUserData();
        if (model == null) {
            model = new ArrayList<SearchModel>();
            stage.setUserData(model);
        }


        if (licenseFilter.getValue() != null) {
            License lic = License.valueOf((String) licenseFilter.getValue());

            if(!model.contains(lic)) {
                if (model.size() == 0) {
                    model.add(new SearchModel(lic, null));
                }
                else {
                    if (licenseOrAndFilter.getValue() != null) {
                        model.add(new SearchModel(lic, (String) licenseOrAndFilter.getValue()));
                    }
                    else {
                        //super errore
                    }
                }
            }
        }
        System.out.println(model);
    }
}
