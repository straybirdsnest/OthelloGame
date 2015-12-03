package otakuplus.straybird.othellogame.applicationstates.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otakuplus.straybird.othellogame.models.ChessBoard;

public class GameWhiteSetState implements GameState {

    private static final Logger logger = LoggerFactory.getLogger(GameWhiteSetState.class);

    public void whiteStandBy() {

    }

    public void whiteStandByCancel() {

    }

    public void blackStandBy() {

    }

    public void blackStandByCancel() {

    }

    public void skipSet() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        ChessBoard chessBoard = gameContext.getChessBoard();
        if (!chessBoard.checkHasNext()) {
            chessBoard.turnEnd();
            chessBoard.searchSuggestedChessmanPosition();
            logger.info("The current player have to pass.");
        }
        gameContext.changeState(GameStateSingleton.getGameBlackSetStateInstance());
    }

    public void whiteSet(Integer x, Integer y) {
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
        gameContext.changeState(GameStateSingleton.getGameBlackSetStateInstance());
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
