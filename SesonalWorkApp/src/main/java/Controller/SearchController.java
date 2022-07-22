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
import javafx.scene.control.*;
import javafx.stage.Stage;
import Model.Season;
import Model.Jobs;
import Model.City;
import Model.SearchModel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import Model.Job;
import Model.VehicleDisp;

import static Controller.Utility.*;

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
    public TextField name;
    @FXML
    public TextField surname;
    @FXML
    public ChoiceBox andOrFilterText;
    @FXML
    Button addTextFilter;
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
                            list.add("YES_VEHICLE");
                            list.add("NO_VEHICLE");
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

        if (filter.getValue() == null || filterField.getValue() == null) {
            if(filter.getValue() == null ) {
                setError(filter, searchFeedback, "error");
            }
            else {
                setError(filterField, searchFeedback, "error");
            }
        }
        else {
            if (filterField.getValue() != null) {
                unSetError(filterField, searchFeedback);
            }
            if (filter.getValue() != null) {
                unSetError(filter, searchFeedback);
            }
        }

        if (filterField.getValue() == null && filter.getValue() == null) {
            setError(filterField, searchFeedback, "error");
            setError(filter, searchFeedback, "error");
        }
        else {
            if (filterField.getValue() != null) {
                unSetError(filterField, searchFeedback);
            }
            if (filter.getValue() != null) {
                unSetError(filter, searchFeedback);
            }
        }

        if (filters.size() != 0) {
            if(andOrFilter.getValue() == null ) {
                setError(andOrFilter, searchFeedback, "error");
            }
            else {
                unSetError(andOrFilter, searchFeedback);
            }
        }

        if(filter.getStyleClass().toString().contains("error") || filterField.getStyleClass().toString().contains("error")||
                (andOrFilter.getStyleClass().toString().contains("error"))) {
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

    public void resetFilter(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        TableView<SearchModel> table = (TableView<SearchModel>) stage.getScene().lookup("#table");
        ObservableList<SearchModel> list = FXCollections.observableArrayList();

        stage.setUserData(null);
        andOrFilter.setValue(null);
        andOrFilterText.setValue(null);
        filterField.setValue(null);
        filter.setValue(null);
        name.setText("");
        surname.setText("");
        table.setItems(list);
    }

    public void addTextFilter(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();

        if(stage.getUserData() == null) {
            List<SearchModel> tmp = new ArrayList<>();
            stage.setUserData(tmp);
        }

        List<SearchModel> filters = (List<SearchModel>) stage.getUserData();

        String nome = name.getText();
        String regex = "^[a-zA-Z]+";
        if (!Pattern.matches(regex, nome)) {
            System.err.println("nome è sbagliato");
            setError(name, searchFeedback, "error");
        } else {
            unSetError(name, searchFeedback);
        }

        String cognome = surname.getText();
        if (!Pattern.matches(regex, cognome)) {
            System.err.println("cognome è sbagliato");
            setError(surname, searchFeedback, "error");
        } else {
            unSetError(surname, searchFeedback);
        }

        if (filters.size() != 0) {
            if(andOrFilterText.getValue() == null ) {
                setError(andOrFilterText, searchFeedback, "error");
            }
            else {
                unSetError(andOrFilterText, searchFeedback);
            }
        }

        if(name.getStyleClass().toString().contains("error") || surname.getStyleClass().toString().contains("error") ||
                andOrFilterText.toString().contains("error")) {
            System.out.println("campo nome e/o cognome non corretti");
        }
        else {
            unSetError(name, searchFeedback);
            unSetError(surname, searchFeedback);

            SearchModel newName = null;
            SearchModel newSurname = null;
            if(filters.size() != 0) {
                newName = new SearchModel(name,
                        andOrFilterText.getValue().toString().toLowerCase());

                newSurname = new SearchModel(surname,
                        andOrFilterText.getValue().toString().toLowerCase());
            }
            else {
                //newFilter = new SearchModel( name.getText() + " " + surname.getText(), null);
            }

            if(!Utility.containsSameFilter(filters, newName)) {
                filters.add(newName);

                TableView<SearchModel> table = (TableView<SearchModel>) stage.getScene().lookup("#table");

                final ObservableList<SearchModel> data = FXCollections.observableArrayList(
                        newName);

                if(table.getItems().size() == 0) {
                    table.setItems(data);
                }
                else {
                    table.getItems().add(newName);
                }
                name.setText("");
                surname.setText("");
                andOrFilter.setValue(null);
            }
            else if (!Utility.containsSameFilter(filters, newSurname)) {
                    filters.add(newSurname);

                    TableView<SearchModel> table = (TableView<SearchModel>) stage.getScene().lookup("#table");

                    final ObservableList<SearchModel> data = FXCollections.observableArrayList(
                            newSurname);

                    if(table.getItems().size() == 0) {
                        table.setItems(data);
                    }
                    else {
                        table.getItems().add(newSurname);
                    }
                    name.setText("");
                    surname.setText("");
                    andOrFilter.setValue(null);
            }
            else {
                setError(searchFeedback, searchFeedback, "duplicate");
            }
        }
    }

    public void searchRecord(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        String path = System.getenv("PWD") + "/src/resources/database/workers.json";
        TableView<TableViewModel> table = (TableView<TableViewModel>) stage.getScene().lookup("#resultTable");
        table.setItems(null);

        if(stage.getUserData() != null && ((List<SearchModel>)stage.getUserData()).size() != 0) {
            List<SearchModel> filters = (List<SearchModel>) stage.getUserData();
            System.out.println(filters);
            List<SeasonalWorker> workers = Utility.gsonWorkerReader(path);
            List<SeasonalWorker> res = new ArrayList<>(workers);

            for(SearchModel filter : filters) {
                if(filter.getType() != null && filter.getType().equals("and")) {
                    Object f = filter.getFilter();
                    res = andSearch(f, res);
                }
                else if (filter.getType() != null && filter.getType().equals("or")) {
                    Object f = filter.getFilter();
                    res = orSearch(f, workers, res);
                }
                else {
                    Object f = filter.getFilter();
                    res = andSearch(f, res);
                }
            }

            ObservableList<TableViewModel> tableRes = FXCollections.observableArrayList();
            for(SeasonalWorker w : res) {
                tableRes.add(new TableViewModel(w.getRecord().getName(), w.getRecord().getSurname(),
                        w.getRecord().getEmail(), w.getRecord().getCellnum(), getItalianDate(w),
                        w.getBrithInfo().getBirthplace()));
            }

            table.setItems(tableRes);
        }
    }

    public List<SeasonalWorker> orSearch(Object filter, List<SeasonalWorker> workers, List<SeasonalWorker> res) {
        for(SeasonalWorker w : workers) {
            if(checkType(filter).equals("Language")) {
                for (Language lang : w.getLanguages()) {
                    if(lang.equals(Language.valueOf(filter.toString().toUpperCase())) && !res.contains(w)) {
                        res.add(w);
                        break;
                    }
                }
            }
            else if(checkType(filter).equals("License")) {
                for (License lic : w.getLicense()) {
                    if (lic.equals(License.valueOf(filter.toString().toUpperCase())) && !res.contains(w)) {
                        res.add(w);
                        break;
                    }
                }
            }
            else if(checkType(filter).equals("Season")) {
                for (Season season : w.getPeriod()) {
                    if(season.equals(Season.valueOf(filter.toString().toUpperCase())) && !res.contains(w)) {
                        res.add(w);
                        break;
                    }
                }
            }
            else if(checkType(filter).equals("Jobs")) {
                for (Job j : w.getPastExperience()) {
                    if(j.getJob().equals(Jobs.valueOf(filter.toString().toUpperCase())) && !res.contains(w)) {
                        res.add(w);
                        break;
                    }
                }
            }
            else if(checkType(filter).equals("City")) {
                for (City city : w.getActivityArea()) {
                    if(city.equals(City.valueOf(filter.toString().toUpperCase())) && !res.contains(w)) {
                        res.add(w);
                        break;
                    }
                }
            }
            else if(checkType(filter).equals("VehicleDisp")) {
                if(w.isWithVehicle().equals(VehicleDisp.converter(filter.toString().toUpperCase())) && !res.contains(w)) {
                    res.add(w);
                    break;
                }
            }
        }

        return res;
    }

    public List<SeasonalWorker> andSearch(Object filter, List<SeasonalWorker> res) {
        boolean found = false;
        List<SeasonalWorker> copy = new ArrayList<>(res);
        for(SeasonalWorker w : copy) {
            found = false;
            if(checkType(filter).equals("Language")) {
                for (Language lang : w.getLanguages()) {
                    if(lang.equals(Language.valueOf(filter.toString().toUpperCase())) /*&& !res.contains(w)*/) {
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    res.remove(w);
                }
            }
            else if(checkType(filter).equals("License")) {
                for (License lic : w.getLicense()) {
                    if(lic.equals(License.valueOf(filter.toString().toUpperCase())) /*&& !res.contains(w)*/) {
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    res.remove(w);
                }
            }
            else if(checkType(filter).equals("Season")) {
                for (Season season : w.getPeriod()) {
                    if(season.equals(Season.valueOf(filter.toString().toUpperCase())) /*&& !res.contains(w)*/) {
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    res.remove(w);
                }
            }
            else if(checkType(filter).equals("Jobs")) {
                for (Job j : w.getPastExperience()) {
                    if(j.equals(Jobs.valueOf(filter.toString().toUpperCase())) /*&& !res.contains(w)*/) {
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    res.remove(w);
                }
            }
            else if(checkType(filter).equals("City")) {
                for (City city : w.getActivityArea()) {
                    if(city.equals(City.valueOf(filter.toString().toUpperCase())) /*&& !res.contains(w)*/) {
                        res.add(w);
                        break;
                    }
                }
                if(!found) {
                    res.remove(w);
                }
            }
            else if(checkType(filter).equals("VehicleDisp")) {
                if(w.isWithVehicle() == VehicleDisp.converter(filter.toString().toUpperCase())) {
                   found = true;
                   break;
                }
                if(!found) {
                    res.remove(w);
                }
            }
        }
        return res;
    }
}