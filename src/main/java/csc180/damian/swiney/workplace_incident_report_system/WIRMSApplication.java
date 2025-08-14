package csc180.damian.swiney.workplace_incident_report_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WIRMSApplication extends Application {
    @Override
    public void start(Stage mainStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setTitle("Workplace Incident Reporting Management System");
        mainStage.setScene(scene);
        mainStage.show();


    }

    public void stop(){
        // TODO This is where exit logic needs to go, i.e. saving all cached data to the database, closing things that have been left open, etc.
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}