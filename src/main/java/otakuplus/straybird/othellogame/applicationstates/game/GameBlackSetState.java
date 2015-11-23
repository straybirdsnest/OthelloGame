package otakuplus.straybird.othellogame.applicationstates.game;

import otakuplus.straybird.othellogame.models.ChessBoard;

public class GameBlackSetState implements GameState {
    public void whiteStandBy() {

    }

    public void whiteStandByCancel() {

    }

    public void blackStandBy() {

    }

    public void blackStandByCancel() {

    }

    public void beginGame() {

    }

    public void whiteSet(Integer x, Integer y) {

    }

    public void blackSet(Integer x, Integer y) {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        ChessBoard chessBoard = gameContext.getChessBoard();
        if (chessBoard.checkHasNext()) {
            if (chessBoard.setChessman(x, y)) {
                chessBoard.turnEnd();
                chessBoard.searchSuggestedChessmanPosition();
            } else {
                System.out.println("You must set your chessman.");
            }
        } else {
            System.out.println("The current player have to pass.");
            chessBoard.turnEnd();
            chessBoard.searchSuggestedChessmanPosition();
        }
        gameContext.changeState(GameStateSingleton.getGameWhiteSetStateInstance());
    }

    public void endGame() {

    }

    public void giveUp() {

    }
}
