package otakuplus.straybird.othellogame.applicationstates;

public class GameBlackSetState implements GameState{
    public void whiteStandBy() {

    }

    public void whiteStandByCancle() {

    }

    public void blackStandBy() {

    }

    public void blackStandByCancle() {

    }

    public void beginGame() {

    }

    public void whiteSet(Long x, Long y) {

    }

    public void blackSet(Long x, Long y) {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameWhiteSetStateInstance());
    }

    public void endGame() {

    }
}
