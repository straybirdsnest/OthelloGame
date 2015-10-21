package otakuplus.straybird.othellogame.applicationstates;

public class GameBlackReadyState implements GameState{
    public void whiteStandBy() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameWhiteSetStateInstance());
    }

    public void whiteStandByCancle() {

    }

    public void blackStandBy() {

    }

    public void blackStandByCancle() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameNoReadyStateInstance());
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
