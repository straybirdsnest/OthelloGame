package otakuplus.straybird.othellogame.applicationstates;

import otakuplus.straybird.othellogame.ui.GameHallWindow;

public class ApplicationDestoryState implements ApplicationState {

	public void initialize() {
	}

	public void connect() {
	}

	public void login() {
	}

	public void enterGameHall() {
	}

	public void leaveGameHall(){

	}

	public void enterGameTable(Long gameTableId,Long seatId) {
	}

	public void leaveGameTable(Long gameTableId,Long seatId){

    }

    public void logout(){

    }

	public void disconnect() {
	}

	public void destory() {
        GameHallWindow gameHallWindow = ApplicationContextSingleton.getInstance().getGameHallWindow();
        if(gameHallWindow!= null){
            gameHallWindow.destoryResources();
            System.out.println("destory is called.");
        }
	}

}
