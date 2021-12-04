package pl.jerzybalcer.organizer_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main for Java FX application. Load fxml file and shows the view.
 * @author Jerzy Balcer
 */
public class App extends Application {

    /** Shows loaded scene to user
     * @param stage place where to put loaded view
     * @throws IOException thrown when there's problem with input/output
     * */
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML("menu"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    /** Loads view from fxml file
     * @param fxml path to the fxml file
     * @throws IOException thrown when there's problem with input/output
     * */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /** Main function starts execution of the application * */
    public static void main(String[] args) {
        launch();
    }

}