package it.univr;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.setMinSize(600, 600);
        pane.setPadding(new Insets(60, 60, 60, 60));
        pane.setHgap(10);
        pane.setVgap(10);

        TextField username = new TextField();
        username.setPromptText("Insert username");

        PasswordField password = new PasswordField();
        password.setPromptText("Insert Password");

        Button submit = new Button("Submit");
        submit.setMaxHeight(30);
        submit.setMaxWidth(100);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GridPane p =new GridPane();
                p.getChildren().add(new Label("prova"));
                Scene sc = new Scene(p, 900, 900);
                sc.setRoot(p);
                stage.setScene(sc);
                stage.show();
            }
        });

        Label label = new Label("SING IN");
        label.setFont(new Font("Verdana", 60));

        Label user = new Label("Username");
        Label pass = new Label("Password");


        VBox box = new VBox(label, user, username, pass, password, submit);

        box.setSpacing(10);
        pane.getChildren().add(box);
        pane.setAlignment(Pos.TOP_CENTER);

        //pane.add(label, 0, 0);
        //pane.add(user, 0, 2);
        //pane.add(username, 1, 2);
        //pane.add(pass, 0, 3);
        //pane.add(password, 1, 3);
        //pane.add(submit, 0, 4);

        Scene scene = new Scene(pane, 600, 600);

        stage.setScene(scene);
        stage.setTitle("Exercise_2");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}