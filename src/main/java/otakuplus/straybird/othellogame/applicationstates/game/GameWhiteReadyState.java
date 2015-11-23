package otakuplus.straybird.othellogame.applicationstates.game;

public class GameWhiteReadyState implements GameState {
    public void whiteStandBy() {

    }

    public void whiteStandByCancel() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameNoReadyStateInstance());
    }

    public void blackStandBy() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameBlackSetStateInstance());
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
}
