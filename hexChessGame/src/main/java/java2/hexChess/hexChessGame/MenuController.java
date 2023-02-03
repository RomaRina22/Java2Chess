package java2.hexChess.hexChessGame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class MenuController {
	public void playGame() {
		App.mainStage.setScene(App.Scenes.get("gamefield"));
	}
	public void openOptions() {
		App.mainStage.setScene(App.Scenes.get("options"));
	}
	public void quitGame() {
		App.mainStage.close();
	}
}
