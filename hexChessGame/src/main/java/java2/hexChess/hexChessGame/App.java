package java2.hexChess.hexChessGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.GameSettings;
import model.Player;

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
    	for (int i = 0; i<6; i++) {
        	GameSettings.allPlayers.add(new Player("Player_" + (i+1), new Color(0.75, 0.75, 0.75, 1.0)));
        }
        System.out.println(GameSettings.getPlayers());
    	
        Scenes.put("menu", new Scene(loadFXML("Menu"), 720, 720));
        Scenes.put("options" , new Scene(loadFXML("Options"), 720, 720));
        Scenes.put("gamefield", new Scene(loadFXML("Gamefield"),720,720));
        
        stage.setScene(Scenes.get("gamefield"));
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