package java2.hexChess.hexChessGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Board;
import model.Spot;
import model.SpotStates;

public class GamefieldController {
	private static class SpotButton {
		public Button button;
		public Spot spot;
	}
	//static AnchorPane anchor;
	@FXML AnchorPane anchor;
	@FXML Button BttBack;
	static SpotButton[][] ButtonsArray;
	static Background selected = new Background(new BackgroundFill(Color.GREENYELLOW, null, null));
	static Background targetted = new Background(new BackgroundFill(Color.ORANGERED, null, null));
	static Background available = new Background(new BackgroundFill(Color.LIGHTGRAY, null, null));
	static Background calm = new Background(new BackgroundFill(Color.DARKGRAY, null, null));
	static SVGPath hexagon;
	static Label[] playerLabels;
	
	public void initialize() {
		//anchor = anchorage;
		VBox labelholder = new VBox();
		playerLabels = new Label[6];
		for (int i = 0; i < 6; i++) {
			playerLabels[i] = new Label();
		}
		
		labelholder.getChildren().addAll(playerLabels);
		
		anchor.getChildren().add(labelholder);
		hexagon = new SVGPath();
		hexagon.setContent("M86.60254037844386 0L173.20508075688772 50L173.20508075688772 150L86.60254037844386 200L0 150L0 50Z");
		
		ButtonsArray = loadField(App.GameSettings.getActiveBoard());
		System.out.println(ButtonsArray+ " " +ButtonsArray.length);
		System.out.println("preview "+ButtonsArray[0][0]+"["+ 0 + " , " + 0 +"], ("+ ButtonsArray[0][0].spot+","+ButtonsArray[0][0].button+") ");
		BttBack.setAlignment(Pos.BOTTOM_LEFT);
		/*Button otherbutt = new Button();
		
		otherbutt.setShape(hexagon);
		otherbutt.setMinSize(70.0,70.0);
		otherbutt.setLayoutX(60.0);
		otherbutt.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				otherbutt.setBackground(selected);
			}
		});
		anchor.getChildren().add(otherbutt);*/
		renderField(App.GameSettings.getActiveBoard());
		
	}
	
	public static void reloadField() {
		//ButtonsArray = loadField(App.GameSettings.getActiveBoard());
	}
	
	public SpotButton[][] loadField(Board currentB) {
		int boardsize = currentB.getSize();
		SpotButton[][] ButtonsArrayLoc = new SpotButton[boardsize][boardsize];
		for (int i = 0; i < boardsize; i++ ) {
			for (int j = 0; j < boardsize;j++) {
				ButtonsArrayLoc[j][i] = new SpotButton();
				SpotButton thisButt = ButtonsArrayLoc[j][i];
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
		return ButtonsArrayLoc;
		
	}
	
	public static void renderField(Board renderBoard) {
		
		int boardsize = renderBoard.getSize();
		
		int height = (int)App.mainStage.getHeight();
		int width = (int) App.mainStage.getWidth();
		int xorigin = (int) (width*0.1);
		int yorigin = (int) (height*0.2);
		int allowedspace = Math.min(height-100,width);
		int spotSize = allowedspace/boardsize;
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
					Blend blender = new Blend();
					ColorInput cInput = new ColorInput(0,0,spotSize/2,spotSize/2,playerColor);
					blender.setOpacity(0.5);
					blender.setTopInput(cInput);
					blender.setMode(BlendMode.MULTIPLY);
					ImageView imView = new ImageView(pieceImage);
					imView.setEffect(blender);
					imView.setFitWidth(spotSize-20);
					imView.setFitHeight(spotSize-20);
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
		int noOfPlayer = 0;
		for (int i: renderBoard.getPlayerDirections()) {
			playerLabels[noOfPlayer].setVisible(false);
			if (i != 0) {
				playerLabels[noOfPlayer].setText(App.GameSettings.getPlayers().get(noOfPlayer).getName());
				playerLabels[noOfPlayer].setTextFill(App.GameSettings.getPlayers().get(noOfPlayer).getColor());
			}
			if (renderBoard.getCurrentPlayerTurn() == noOfPlayer) {
				playerLabels[noOfPlayer].setFont(Font.font("System", FontWeight.BOLD, 12.0));
			}
			else {
				playerLabels[noOfPlayer].setFont(Font.font("System", FontWeight.NORMAL, 12.0));
			}
			noOfPlayer++;
		}
		
	} 
	
	public void goBack() {
		App.mainStage.setScene(App.Scenes.get("menu"));
	}
}
