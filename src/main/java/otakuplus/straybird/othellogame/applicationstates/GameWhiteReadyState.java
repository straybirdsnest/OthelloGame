package otakuplus.straybird.othellogame.applicationstates;

public class GameWhiteReadyState implements GameState {
    public void whiteStandBy() {

    }

    public void whiteStandByCancle() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameNoReadyStateInstance());
    }

    public void blackStandBy() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameBlackSetStateInstance());
    }

    public void blackStandByCancle() {

    }

    public void beginGame() {

    }

    public void whiteSet(Long x, Long y) {

    }

    public void blackSet(Long x, Long y) {

    }

    public void endGame() {

    }
}
