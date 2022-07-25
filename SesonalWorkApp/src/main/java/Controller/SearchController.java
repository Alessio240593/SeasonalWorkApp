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
import Model.PastExpTableModel;
import Model.SearchModel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import Model.Job;
import Model.VehicleDisp;

import static Controller.Utility.*;

public class SearchController {
    @FXML
    private ChoiceBox filterField;
    @FXML
    private ChoiceBox filter;
    @FXML
    private ChoiceBox andOrFilter;
    @FXML
    private TextField searchFeedback;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private ChoiceBox andOrFilterText;
    @FXML
    private Button addTextFilter;
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
            andOrFilter.setDisable(false);
            if(andOrFilter.getValue() == null ) {
                setError(andOrFilter, searchFeedback, "error");
            }
            else {
                unSetError(andOrFilter, searchFeedback);
            }
        }

        if(filter.getStyleClass().toString().contains("error") || filterField.getStyleClass().toString().contains("error")||
                (andOrFilter.getStyleClass().toString().contains("error"))) {
        }
        else {
            andOrFilter.setDisable(false);
            andOrFilterText.setDisable(false);
            unSetError(filterField, searchFeedback);
            unSetError(andOrFilter, searchFeedback);
            unSetError(filter, searchFeedback);

            if(filters.size() != 0) {
                newFilter = new SearchModel((String) filterField.getValue().toString().toLowerCase(),
                        (String) andOrFilter.getValue().toString().toLowerCase(), filter.getValue().toString().toLowerCase());
            }
            else {
                newFilter = new SearchModel((String) filterField.getValue().toString().toLowerCase(),
                        null, filter.getValue().toString().toLowerCase());
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
        TableView<SearchModel> resultTable = (TableView<SearchModel>) stage.getScene().lookup("#resultTable");
        ObservableList<SearchModel> list = FXCollections.observableArrayList();

        List<SearchModel> filters = (List<SearchModel>) stage.getUserData();
        filters.clear();
        andOrFilter.setValue(null);
        andOrFilterText.setValue(null);
        filterField.setValue(null);
        filter.setValue(null);
        andOrFilter.setDisable(true);
        andOrFilterText.setDisable(true);
        name.setText("");
        surname.setText("");
        unSetError(filterField, searchFeedback);
        unSetError(filter, searchFeedback);
        unSetError(name, searchFeedback);
        unSetError(surname, searchFeedback);
        unSetError(andOrFilter, searchFeedback);
        unSetError(andOrFilterText, searchFeedback);
        table.setItems(list);
        resultTable.setItems(list);
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
        if (!Pattern.matches(regex, nome) && !nome.equals("")) {
            setError(name, searchFeedback, "error");
        } else {
            unSetError(name, searchFeedback);
        }

        String cognome = surname.getText();
        if (!Pattern.matches(regex, cognome) && !cognome.equals("")) {
            setError(surname, searchFeedback, "error");
        } else {
            unSetError(surname, searchFeedback);
        }

        if(cognome.equals("") && nome.equals("")) {
            setError(name, searchFeedback, "error");
            setError(surname, searchFeedback, "error");
        }

        if (filters.size() != 0) {
            if(andOrFilterText.getValue() == null ) {
                setError(andOrFilterText, searchFeedback, "error");
            }
            else {
                unSetError(andOrFilterText, searchFeedback);
            }
        }

        if(name.getStyleClass().toString().contains("error") && surname.getStyleClass().toString().contains("error") ||
                andOrFilterText.toString().contains("error")) {
        }
        else {
            andOrFilter.setDisable(false);
            andOrFilterText.setDisable(false);
            unSetError(name, searchFeedback);
            unSetError(surname, searchFeedback);

            SearchModel newName = null;
            SearchModel newSurname = null;

            boolean duplicate = false;

            if(filters.size() != 0) {
                newName = new SearchModel(nome,
                        andOrFilterText.getValue().toString().toLowerCase(), "NOME");

                newSurname = new SearchModel(cognome,
                        andOrFilterText.getValue().toString().toLowerCase(), "COGNOME");
            }
            else {
                newName = new SearchModel(nome, null, "NOME");

                if(nome.isEmpty() ) {
                    newSurname = new SearchModel(cognome, null, "COGNOME");
                }
                else {
                    newSurname = new SearchModel(cognome, "and", "COGNOME");
                }
            }

            if(!Utility.containsSameFilter(filters, newName) && !((String) newName.getFilter()).equals("")) {
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
            else if (Utility.containsSameFilter(filters, newName)){
                duplicate = true;
            }

            if (!Utility.containsSameFilter(filters, newSurname) && !((String) newSurname.getFilter()).equals("")) {
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
            else if (Utility.containsSameFilter(filters, newSurname)){
                duplicate = true;
            }

            if(duplicate) {
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
            List<SeasonalWorker> workers = Utility.gsonWorkerReader(path);
            List<SeasonalWorker> res = new ArrayList<>(workers);

            for(SearchModel filter : filters) {
                if(filter.getType() != null && filter.getType().equals("and")) {
                    SearchModel f = filter;
                    res = andSearch(f, res);
                }
                else if (filter.getType() != null && filter.getType().equals("or")) {
                    SearchModel f = filter;
                    res = orSearch(f, workers, res);
                }
                else {
                    SearchModel f = filter;
                    res = andSearch(f, res);
                }
            }

            ObservableList<TableViewModel> tableRes = FXCollections.observableArrayList();
            for(SeasonalWorker w : res) {
                tableRes.add(new TableViewModel(w.getId(), w.getRecord().getName(), w.getRecord().getSurname(),
                        w.getRecord().getEmail(), w.getRecord().getCellnum(), getItalianDate(w),
                        w.getBrithInfo().getBirthplace()));
            }

            table.setItems(tableRes);
        }
    }

    public List<SeasonalWorker> orSearch(SearchModel fil, List<SeasonalWorker> workers, List<SeasonalWorker> res) {
        Object filter = fil.getFilter();

        for(SeasonalWorker w : workers) {
            if(checkType(fil).equals("Language")) {
                for (Language lang : w.getLanguages()) {
                    if(lang.equals(Language.valueOf(filter.toString().toUpperCase())) && !res.contains(w)) {
                        res.add(w);
                    }
                }
            }
            else if(checkType(fil).equals("License")) {
                for (License lic : w.getLicense()) {
                    if (lic.equals(License.valueOf(filter.toString().toUpperCase())) && !res.contains(w)) {
                        res.add(w);
                    }
                }
            }
            else if(checkType(fil).equals("Season")) {
                for (Season season : w.getPeriod()) {
                    if(season.equals(Season.valueOf(filter.toString().toUpperCase())) && !res.contains(w)) {
                        res.add(w);
                    }
                }
            }
            else if(checkType(fil).equals("Jobs")) {
                for (Job j : w.getPastExperience()) {
                    if(j.getJob().equals(Jobs.valueOf(filter.toString().toUpperCase())) && !res.contains(w)) {
                        res.add(w);
                    }
                }
            }
            else if(checkType(fil).equals("City")) {
                for (City city : w.getActivityArea()) {
                    if(city.equals(City.valueOf(filter.toString().toUpperCase())) && !res.contains(w)) {
                        res.add(w);
                    }
                }
            }
            else if(checkType(fil).equals("VehicleDisp") && !res.contains(w)) {
                if(w.isWithVehicle().equals(VehicleDisp.converter(filter.toString().toUpperCase())) && !res.contains(w)) {
                    res.add(w);
                }
            }
            else if(checkType(fil).equals("nome") && !res.contains(w)) {
                if (w.getRecord().getName().equalsIgnoreCase((String) filter)) {
                    res.add(w);
                }
            }
            else if(checkType(fil).equals("cognome") && !res.contains(w)) {
                if(w.getRecord().getSurname().equalsIgnoreCase((String) filter)) {
                    res.add(w);
                }
            }
        }

        return res;
    }

    public List<SeasonalWorker> andSearch(SearchModel fil, List<SeasonalWorker> res) {
        Object filter = fil.getFilter();
        boolean found = false;
        List<SeasonalWorker> copy = new ArrayList<>(res);
        for(SeasonalWorker w : copy) {
            found = false;
            if(checkType(fil).equals("Language")) {
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
            else if(checkType(fil).equals("License")) {
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
            else if(checkType(fil).equals("Season")) {
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
            else if(checkType(fil).equals("Jobs")) {
                for (Job j : w.getPastExperience()) {
                    if(j.getJob().equals(Jobs.valueOf(filter.toString().toUpperCase())) /*&& !res.contains(w)*/) {
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    res.remove(w);
                }
            }
            else if(checkType(fil).equals("City")) {
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
            else if(checkType(fil).equals("VehicleDisp")) {
                if(w.isWithVehicle() == VehicleDisp.converter(filter.toString().toUpperCase())) {
                   found = true;
                   break;
                }
                if(!found) {
                    res.remove(w);
                }
            }
            else if(checkType(fil).equals("nome")) {
                if (!(w.getRecord().getName().equalsIgnoreCase((String) filter))) {
                    res.remove(w);
                }
            }
            else if(checkType(fil).equals("cognome")) {
                if(!w.getRecord().getSurname().equalsIgnoreCase((String) filter)) {
                    res.remove(w);
                }
            }
        }
        return res;
    }
}