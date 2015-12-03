package otakuplus.straybird.othellogame.applicationstates.game;

import otakuplus.straybird.othellogame.models.ChessBoard;

public class GameNoReadyState implements GameState {
    public void whiteStandBy() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameWhiteReadyStateInstance());
    }

    public void whiteStandByCancel() {

    }

    public void blackStandBy() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameBlackReadyStateInstance());
    }

    public void blackStandByCancel() {

    }

    public void beginGame() {

    }

    public void whiteSet(Integer x, Integer y) {

    }

    public void blackSet(Integer x, Integer y) {

    }


    public void endGame() {

    }

    public void reboot() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        ChessBoard chessBoard = gameContext.getChessBoard();
        chessBoard.initChessboard();
        gameContext.changeState(GameStateSingleton.getGameNoReadyStateInstance());
    }
}
