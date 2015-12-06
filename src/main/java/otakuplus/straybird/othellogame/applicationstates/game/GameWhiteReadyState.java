package otakuplus.straybird.othellogame.applicationstates.game;

import otakuplus.straybird.othellogame.applicationstates.ApplicationContext;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.models.GameRecord;

import java.time.ZoneId;
import java.time.ZonedDateTime;

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
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        GameRecord gameRecord = applicationContext.getGameRecord();
        ZonedDateTime nowTime = ZonedDateTime.now(ZoneId.of("GMT+8"));
        gameRecord.setGameBeginTime(nowTime.toString());
    }

    public void blackStandByCancel() {

    }

    public void skipSet() {

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
