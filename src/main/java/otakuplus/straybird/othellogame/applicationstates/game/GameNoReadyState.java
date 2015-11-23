package otakuplus.straybird.othellogame.applicationstates.game;

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

    public void giveUp() {

    }
}
