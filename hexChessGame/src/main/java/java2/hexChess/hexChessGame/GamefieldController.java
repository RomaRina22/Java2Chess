package java2.hexChess.hexChessGame;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import model.Board;
import model.Spot;
import model.SpotStates;

public class GamefieldController {
	private class SpotButton {
		public Button button;
		public Spot spot;
	}
	@FXML AnchorPane anchor;
	@FXML Button BttBack;
	static SpotButton[][] ButtonsArray;
	static Background selected = new Background(new BackgroundFill(Color.GREENYELLOW, null, null));
	static Background targetted = new Background(new BackgroundFill(Color.ORANGERED, null, null));
	static Background available = new Background(new BackgroundFill(Color.LIGHTGRAY, null, null));
	static Background calm = new Background(new BackgroundFill(Color.DARKGRAY, null, null));
	static SVGPath hexagon;
	
	public void initialize() {
		
		
		hexagon = new SVGPath();
		hexagon.setContent("M86.60254037844386 0L173.20508075688772 50L173.20508075688772 150L86.60254037844386 200L0 150L0 50Z");
		
		ButtonsArray = loadField(App.GameSettings.getActiveBoard());
		System.out.println(ButtonsArray+ " " +ButtonsArray.length);
		System.out.println("preview "+ButtonsArray[0][0]+"["+ 0 + " , " + 0 +"], ("+ ButtonsArray[0][0].spot+","+ButtonsArray[0][0].button+") ");
		Button otherbutt = new Button();
		
		otherbutt.setShape(hexagon);
		otherbutt.setMinSize(70.0,70.0);
		otherbutt.setLayoutX(60.0);
		otherbutt.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				otherbutt.setBackground(selected);
			}
		});
		anchor.getChildren().add(otherbutt);
		renderField(App.GameSettings.getActiveBoard());
		
	}
	
	public SpotButton[][] loadField(Board currentB) {
		int boardsize = currentB.getSize();
		SpotButton[][] ButtonsArray = new SpotButton[boardsize][boardsize];
		for (int i = 0; i < boardsize; i++ ) {
			for (int j = 0; j < boardsize;j++) {
				ButtonsArray[j][i] = new SpotButton();
				SpotButton thisButt = ButtonsArray[j][i];
				thisButt.spot = currentB.getSpotByCoords(new int[] {j,i});
				thisButt.button = new Button();
				thisButt.button.setShape(hexagon);
				Spot spot = thisButt.spot;
				Button butt = thisButt.button;
				anchor.getChildren().add(butt);
				System.out.print("did ["+ j + " , " + i +"], ("+ spot+","+butt+") ");
				butt.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						currentB.selectPiece(spot);
						renderField(currentB);
					}
				});
			}
		}
		System.out.println();
		//System.out.println("preview "+ButtonsArray[0][0]+"["+ 0 + " , " + 0 +"], ("+ ButtonsArray[0][0].spot+","+ButtonsArray[0][0].button+") ");
		return ButtonsArray;
		
	}
	
	public void renderField(Board renderBoard) {
		
		int boardsize = renderBoard.getSize();
		
		int height = (int)anchor.getHeight();
		int width = (int) anchor.getWidth();
		int xorigin = 100;
		int yorigin = 100;
		int allowedspace = Math.min(height-100,width);
		int spotSize = 80;//allowedspace/boardsize;
		System.out.println("spot size is "+spotSize);
		int hoffset = (int)(0.5*spotSize);
		int voffset = (int)(0.0*spotSize);
		System.out.println("offsets are (h,v)"+hoffset+" "+voffset);
		System.out.println(ButtonsArray+ " " +ButtonsArray.length);
		//System.out.println("preview "+ButtonsArray[0][0]+"["+ 0 + " , " + 0 +"], ("+ ButtonsArray[0][0].spot+","+ButtonsArray[0][0].button+") ");
		for (int i = 0; i < boardsize; i++) {
			for (int j = 0; j<boardsize;j++) {
				SpotButton spotButt = (ButtonsArray[j][i]);
				Button butt = spotButt.button;
				Spot spot = spotButt.spot;
				
				//System.out.print("rendering "+spotButt+"["+ j + " , " + i +"], ("+ spot+","+butt+") ");
				butt.setLayoutX(xorigin + j*(spotSize+1) - (i%2)*hoffset);
				butt.setLayoutY(yorigin + i*(spotSize*0.75+1) + (i%2)*voffset);
				butt.setMinSize(spotSize, spotSize);
				butt.setGraphic(null);
				if (spot.getPiece() != null) {
					Image pieceImage = spot.getPiece().getType().getImage();
					Color playerColor = spot.getPiece().getOwner().getColor();
					ColorAdjust recolor = new ColorAdjust();
					recolor.setBrightness(playerColor.getBrightness());
					recolor.setHue(playerColor.getHue());
					recolor.setSaturation(playerColor.getSaturation());
					ImageView imView = new ImageView(pieceImage);
					imView.setEffect(recolor);
					butt.setGraphic(imView);
				}
				if (spot.getState().equals(SpotStates.LOCKED)) {
					butt.setVisible(false);
				}
				switch(spot.getState()) {
				case AVAILABLE:
					butt.setBackground(available);
					break;
				case TARGETTED:
					butt.setBackground(targetted);
					break;
				case CALM:
					butt.setBackground(calm);
					break;
				case SELECTED:
					butt.setBackground(selected);
					break;
				case LOCKED:
					butt.setVisible(false);
					break;
					
				}
				
			}
		}
		
		
	} 
	
	public void goBack() {
		App.mainStage.setScene(App.Scenes.get("menu"));
	}
}
