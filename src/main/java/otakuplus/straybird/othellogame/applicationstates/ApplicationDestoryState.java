package otakuplus.straybird.othellogame.applicationstates;

import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

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
		OthelloGameWindow othelloGameWindow = ApplicationContextSingleton.getInstance().getOthelloGameWindow();
        LoginWindow loginWindow = ApplicationContextSingleton.getInstance().getLoginWinodow();
        if(gameHallWindow!= null){
            gameHallWindow.close();
        }
		if(othelloGameWindow != null){
			othelloGameWindow.close();
		}
        if(loginWindow != null){
            loginWindow.close();
        }
        System.out.println("Destory is called.");
	}

}
