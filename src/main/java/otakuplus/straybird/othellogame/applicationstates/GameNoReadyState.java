package otakuplus.straybird.othellogame.applicationstates;

public class GameNoReadyState implements GameState{
    public void whiteStandBy() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameWhiteReadyStateInstance());
    }

    public void whiteStandByCancle() {

    }

    public void blackStandBy() {
        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        gameContext.changeState(GameStateSingleton.getGameBlackReadyStateInstance());
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
