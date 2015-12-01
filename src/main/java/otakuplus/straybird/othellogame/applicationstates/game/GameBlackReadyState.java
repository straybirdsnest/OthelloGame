package otakuplus.straybird.othellogame.applicationstates.game;

public class GameBlackReadyState implements GameState {
    public void whiteStandBy() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameWhiteSetStateInstance());
    }

    public void whiteStandByCancel() {

    }

    public void blackStandBy() {

    }

    public void blackStandByCancel() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameNoReadyStateInstance());
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

    }
}
