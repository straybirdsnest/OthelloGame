package otakuplus.straybird.othellogame.applicationstates.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otakuplus.straybird.othellogame.models.ChessBoard;

public class GameContext {

    private static final Logger logger = LoggerFactory.getLogger(GameContext.class);

    protected GameState gameState;
    protected ChessBoard chessBoard;

    public GameContext() {
        gameState = GameStateSingleton.getGameNoReadyStateInstance();
        chessBoard = new ChessBoard();
    }

    public void blackStandBy() {
        gameState.blackStandBy();
    }

    public void blackStandByCancel() {
        gameState.blackStandByCancel();
    }

    public void whiteStandBy() {
        gameState.whiteStandBy();
    }

    public void whiteStandByCancel() {
        gameState.whiteStandByCancel();
    }

    public void beginGame() {
        gameState.beginGame();
    }

    public void endGame() {
        gameState.endGame();
    }

    public void blackSet(Integer x, Integer y) {
        gameState.blackSet(x, y);
    }

    public void whiteSet(Integer x, Integer y) {
        gameState.whiteSet(x, y);
    }

    public void changeState(GameState gameState) {
        this.gameState = gameState;
        logger.info("change to" + gameState);
    }

    public ChessBoard getChessBoard(){
        return chessBoard;
    }
}