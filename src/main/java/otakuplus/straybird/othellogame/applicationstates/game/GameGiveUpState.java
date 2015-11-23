package otakuplus.straybird.othellogame.applicationstates.game;

public class GameGiveUpState implements GameState {
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

    }

    public void endGame() {

    }

    public void giveUp() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameNoReadyStateInstance());
    }
}
