package otakuplus.straybird.othellogame.applicationstates;

public class GameContext {
    protected GameState gameState;

    public GameContext() {
        gameState = GameStateSingleton.getGameNoReadyStateInstance();
    }

    public void blackStandBy() {
        gameState.blackStandBy();
    }

    public void blackStandByCancle() {
        gameState.blackStandByCancle();
    }

    public void whiteStandBy() {
        gameState.whiteStandBy();
    }

    public void WhiteStandByCanecle() {
        gameState.whiteStandByCancle();
    }

    public void beginGame() {
        gameState.beginGame();
    }

    public void endGame() {
        gameState.endGame();
    }

    public void blackSet(Long x, Long y) {
        gameState.blackSet(x, y);
    }

    public void whiteSet(Long x, Long y) {
        gameState.whiteSet(x, y);
    }

    public void changeState(GameState gameState) {
        this.gameState = gameState;
    }
}
