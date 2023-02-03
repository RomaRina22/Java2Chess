package java2.hexChess.hexChessGame;

import java.io.IOException;

import javafx.css.converter.ColorConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Color;
import model.Player;



public class OptionsController {
		private Stage stage;
		private Scene scene;
		private Parent parent;
		public static model.Color temp;
		
		@FXML
		private TextField TextName;
	    @FXML
	    private Button backButton;
	    @FXML
		private ComboBox<model.Color> CboxColor;

	    @FXML
	    private void goBack(ActionEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
	 //   stage = (Stage)((Node)event.getSource()).getScene().getWindow()
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	    	
	    }
	  
			
		
	    
	    @FXML
	    private void saveit(ActionEvent event) {
	    String usernameForPlayer = TextName.getText();	
	    Color selectedcolor = CboxColor.getSelectionModel().getSelectedItem();
	   
	    }
	    @FXML
	    public void initialize() 
		{
			//TODO ielādēt trīs valstis combobox komponentē
	    	CboxColor.getItems().addAll(Color.values());
		}
	    
	    public void returnToMenu() {
	    	App.mainStage.setScene(App.Scenes.get("menu"));
	    }
}




















