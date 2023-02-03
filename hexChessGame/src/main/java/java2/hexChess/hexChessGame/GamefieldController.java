package java2.hexChess.hexChessGame;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class GamefieldController {
	@FXML AnchorPane anchor;
	static Background selected = new Background(new BackgroundFill(Color.GREENYELLOW, null, null));
	static Background targetted = new Background(new BackgroundFill(Color.ORANGERED, null, null));
	static Background available = new Background(new BackgroundFill(Color.LIGHTGRAY, null, null));
	static Background calm = new Background(new BackgroundFill(Color.DARKGRAY, null, null));
	
	public void initialize() {
		Button newbutt = new Button();
		SVGPath hexagon = new SVGPath();
		hexagon.setContent("M86.60254037844386 0L173.20508075688772 50L173.20508075688772 150L86.60254037844386 200L0 150L0 50Z");
		hexagon.setFill(Color.AZURE);
		newbutt.setShape(hexagon);
		newbutt.setMinSize(50.0,50.0);
		Button otherbutt = new Button();
		SVGPath Hexatwo = new SVGPath();
		Hexatwo.setContent("M86.60254037844386 0L173.20508075688772 50L173.20508075688772 150L86.60254037844386 200L0 150L0 50Z");
		Hexatwo.setFill(Color.ORANGE);
		otherbutt.setShape(hexagon);
		otherbutt.setMinSize(50.0,50.0);
		otherbutt.setLayoutX(60.0);
		otherbutt.setBackground(new Background(new BackgroundFill(Color.AZURE,null,null)));
		
		anchor.getChildren().add(newbutt);
		anchor.getChildren().add(otherbutt);
	}
	
	private static void renderField() {
		
	} 
}
