package Controller;

import Model.Model;
import Model.SeasonalWorker;
import Model.TableViewModel;
import Model.Worker;
import Model.Season;
import Model.Language;
import Model.Jobs;
import Model.City;
import Model.License;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.Observable;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import  Model.Job;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController {
    @FXML
    public AnchorPane pane;
    @FXML
    private Button updateRecord;
    @FXML
    private Button logOut;
    @FXML
    private Button insertRecord;

    @FXML
    private void initialize() {
        String path = System.getenv("PWD") + "/src/resources/database/workers.json";
        List<SeasonalWorker> workers = Utility.gsonWorkerReader(path);
        Map<Jobs, Integer> map =  new HashMap<>();

        for (Worker w : workers) {
            List<Job> past = w.getPastExperience();
            for (Job job : past) {
                if (!map.containsKey(job.getJob())) {
                    map.put(job.getJob(), 1);
                }
                else {
                    map.merge(job.getJob(), 1, Integer::sum);
                }
            }
        }

        //Defining the x axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>
                observableArrayList(Arrays.asList("chef", "farmer", "waiter", "busdriver",
                "maintainer", "lifeguard", "sailinginstructor")));
        xAxis.setLabel("category");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number");

        //Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Workers type");
        barChart.setTranslateX(250);
        barChart.setTranslateY(650);

        //Prepare XYChart.Series objects by setting data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>(String.valueOf(Jobs.CHEF).toLowerCase(), map.containsKey(Jobs.CHEF) ? map.get(Jobs.CHEF) : 0));
        series1.getData().add(new XYChart.Data<>(String.valueOf(Jobs.FARMER).toLowerCase(), map.containsKey(Jobs.FARMER ) ? map.get(Jobs.FARMER) : 0));
        series1.getData().add(new XYChart.Data<>(String.valueOf(Jobs.WAITER).toLowerCase(), map.containsKey(Jobs.WAITER) ? map.get(Jobs.WAITER): 0));
        series1.getData().add(new XYChart.Data<>(String.valueOf(Jobs.BUSDRIVER).toLowerCase(), map.containsKey(Jobs.BUSDRIVER) ? map.get(Jobs.BUSDRIVER): 0));
        series1.getData().add(new XYChart.Data<>(String.valueOf(Jobs.MAINTAINER).toLowerCase(), map.containsKey(Jobs.MAINTAINER) ? map.get(Jobs.MAINTAINER): 0));
        series1.getData().add(new XYChart.Data<>(String.valueOf(Jobs.LIFEGUARD).toLowerCase(), map.containsKey(Jobs.LIFEGUARD) ? map.get(Jobs.LIFEGUARD): 0));
        series1.getData().add(new XYChart.Data<>(String.valueOf(Jobs.SAILINGINSTRUCTOR).toLowerCase(),  map.containsKey(Jobs.SAILINGINSTRUCTOR) ? map.get(Jobs.SAILINGINSTRUCTOR): 0));


        //Setting the data to bar chart
        barChart.getData().addAll(series1);

        pane.getChildren().add(barChart);
    }

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
}
