package otakuplus.straybird.othellogame.applicationstates.game;

public interface GameState {
    public void whiteStandBy();

    public void whiteStandByCancel();

    public void blackStandBy();

    public void blackStandByCancel();

    public void beginGame();

    public void whiteSet(Integer x, Integer y);

    public void blackSet(Integer x, Integer y);

    public void endGame();

    public void giveUp();
}
