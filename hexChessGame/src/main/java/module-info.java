module java2.hexChess.hexChessGame {
    requires javafx.controls;
    requires javafx.fxml;

    opens java2.hexChess.hexChessGame to javafx.fxml;
    exports java2.hexChess.hexChessGame;
}
