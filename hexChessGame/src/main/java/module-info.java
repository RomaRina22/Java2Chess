module java2.hexChess.hexChessGame {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;

    opens java2.hexChess.hexChessGame to javafx.fxml;
    exports java2.hexChess.hexChessGame;
}
