package Controller;

import Model.Model;
import Model.SeasonalWorker;
import Model.TableViewModel;
import Model.Worker;
import Model.Language;
import Model.License;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Season;
import Model.Jobs;
import Model.SearchModel;
import java.util.ArrayList;
import java.util.List;

import static Controller.Utility.unSetError;
import static Controller.Utility.setError;

public class SearchController {
    @FXML
    public ChoiceBox filterField;
    @FXML
    public ChoiceBox filter;
    @FXML
    public ChoiceBox andOrFilter;
    @FXML
    public TextField searchFeedback;
    @FXML
    private Button updateRecord;
    @FXML
    private Button logOut;
    @FXML
    private Button insertRecord;

    public void homeHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.setUserData(null);
        Utility.changeScene("Home.fxml", stage);
    }

    public void searchHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        stage.setUserData(null);
        Utility.changeScene("Search.fxml", stage);

        ChoiceBox<String> filter = (ChoiceBox<String>) stage.getScene().lookup("#filter");
        ChoiceBox<Object> filterField = (ChoiceBox<Object>) stage.getScene().lookup("#filterField");

        filter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String langFilter = null;
                if (filter.getValue() == null) {
                    filter.getStyleClass().add("error");
                } else {
                    langFilter = (String) filter.getValue().toString().toLowerCase();
                    filter.getStyleClass().removeAll("error");
                    ObservableList<Object> list = FXCollections.observableArrayList();

                    switch (langFilter) {
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
                    }
                    filterField.setItems(list);
                }
            }
        });
    }

    public void insertHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        stage.setUserData(null);
        Utility.changeScene("Insert.fxml", stage);
    }

    public void updateHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) updateRecord.getScene().getWindow();
        stage.setUserData(null);
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
        stage.setUserData(null);
        Utility.changeScene("Login.fxml", stage);
    }

    public void exitHandler(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void addFilter(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();

        if(stage.getUserData() == null) {
            List<SearchModel> tmp = new ArrayList<>();
            stage.setUserData(tmp);
        }

        List<SearchModel> filters = (List<SearchModel>) stage.getUserData();
        String fieldFilter = null;
        String andOrField = null;
        SearchModel newFilter = null;

        if (filter.getValue() == null) {
            setError(filter, searchFeedback, "error");
        }
        else {
            unSetError(filter, searchFeedback);
        }

        if (filterField.getValue() == null) {
            setError(filterField, searchFeedback, "error");
        }
        else {
            unSetError(filterField, searchFeedback);
        }

        if (filters.size() != 0) {
            if(andOrFilter.getValue() == null ) {
                setError(andOrFilter, searchFeedback, "error");
            }
            else {
                unSetError(andOrFilter, searchFeedback);
            }
        }

        if(filter.getValue() == null || filterField.getValue() == null || (andOrFilter.getValue() == null && filters.size() != 0)) {
            System.out.println("dati sbagliati");
        }
        else {
            unSetError(filterField, searchFeedback);
            unSetError(andOrFilter, searchFeedback);
            unSetError(filter, searchFeedback);

            if(filters.size() != 0) {
                newFilter = new SearchModel((String) filterField.getValue().toString().toLowerCase(),
                        (String) andOrFilter.getValue().toString().toLowerCase());
            }
            else {
                newFilter = new SearchModel((String) filterField.getValue().toString().toLowerCase(),
                        null);
            }

            if(!Utility.containsSameFilter(filters, newFilter)) {
                filters.add(newFilter);

                TableView<SearchModel> table = (TableView<SearchModel>) stage.getScene().lookup("#table");

                final ObservableList<SearchModel> data = FXCollections.observableArrayList(
                        newFilter);

                if(table.getItems().size() == 0) {
                    table.setItems(data);
                }
                else {
                    table.getItems().add(newFilter);
                }
                andOrFilter.setValue(null);
                filterField.setValue(null);
            }
            else {
                setError(searchFeedback, searchFeedback, "duplicate");
            }
        }
    }

    public void searchRecord(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        String path = System.getenv("PWD") + "/src/resources/database/workers.json";

        if(stage.getUserData() != null) {
            List<SearchModel> filters = (List<SearchModel>) stage.getUserData();
            List<SeasonalWorker> workers = Utility.gsonWorkerReader(path);

            for(SearchModel filter : filters) {
                if(filter.getType().equals("and")) {
                    Object f = filter.getFilter();
                    andSearch(f, workers);
                }
                else if (filter.getType().equals("or")) {

                }
                else {
                    //qui è null
                }
            }
        }
    }

    public List<SeasonalWorker> andSearch(Object filter, List<SeasonalWorker> workers) {
        List<SeasonalWorker> res = new ArrayList<SeasonalWorker>();

        for(SeasonalWorker w : workers) {
            if(filter.getClass().getName().equals("Language")) {
                if(w.getLanguages().equals(filter)) {
                    res.add(w);
                }
            }
            else if(filter.getClass().getName().equals("License")) {

            }
            //vari use case
        }
        return res;
    }

    public void resetFilter(ActionEvent actionEvent) {

    }
}