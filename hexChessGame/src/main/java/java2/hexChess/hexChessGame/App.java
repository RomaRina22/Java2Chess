package java2.hexChess.hexChessGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GameSettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static HashMap<String,Scene> Scenes = new HashMap<>();
    public static GameSettings GameSettings = new GameSettings();
    
    @Override
    public void start(Stage stage) throws IOException {
    	
        scene = new Scene(loadFXML("menu"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}