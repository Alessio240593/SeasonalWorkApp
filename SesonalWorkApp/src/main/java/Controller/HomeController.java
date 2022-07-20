package Controller;

import Model.Model;
import Model.SeasonalWorker;
import Model.TableViewModel;
import Model.Worker;
import Model.Season;
import Model.Language;
import Model.Jobs;
import Model.License;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class HomeController {
    @FXML
    private Button updateRecord;
    @FXML
    private Button logOut;
    @FXML
    private Button insertRecord;

    /*@FXML
    public void initialze() {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        BarChart<Jobs, Integer> chart = (BarChart<Jobs, Integer>) stage.getScene().lookup("#chart");

        //Defining the x axis
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "Speed", "User rating", "Milage", "Safety")));
        xAxis.setLabel("category");

        //Defining the y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("score");


        chart.getData().add(new XYChart.Data<>(Jobs.CHEF, 3));
    }*/

    public void homeHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) logOut.getScene().getWindow();
        Utility.changeScene("Home.fxml", stage);
    }

    public void searchHandler(ActionEvent actionEvent) {
        Stage stage = (Stage) insertRecord.getScene().getWindow();
        Utility.changeScene("Search.fxml", stage);


        ChoiceBox<Object> filter = (ChoiceBox<Object>) stage.getScene().lookup("#filter");
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
}
