package otakuplus.straybird.othellogame.applicationstates;

public interface GameState {
    public void whiteStandBy();

    public void whiteStandByCancle();

    public void blackStandBy();

    public void blackStandByCancle();

    public void beginGame();

    public void whiteSet(Long x, Long y);

    public void blackSet(Long x, Long y);

    public void endGame();
}
