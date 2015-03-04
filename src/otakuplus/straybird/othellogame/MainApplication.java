package otakuplus.straybird.othellogame;

import org.eclipse.swt.widgets.Display;

import otakuplus.straybird.othellogame.network.OthelloGameClientEnd;
import otakuplus.straybird.othellogame.network.ProcessResponse;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

public class MainApplication {

	protected Display display;

	protected LoginWindow loginWindow;
	protected GameHallWindow gameHallWindow;
	protected OthelloGameWindow othelloGameWindow;
	
	protected OthelloGameClientEnd clientEnd;

	public MainApplication() {
		display = Display.getDefault();

		loginWindow = new LoginWindow(this);
		gameHallWindow = new GameHallWindow(this);
		othelloGameWindow = new OthelloGameWindow();
		
		clientEnd = new OthelloGameClientEnd();
	}

	public void startUp() {
		loginWindow.open();
		gameHallWindow.open();
		gameHallWindow.hide();
		othelloGameWindow.open();
		othelloGameWindow.hide();

		clientEnd.attach(loginWindow);
		clientEnd.attach(gameHallWindow);
		clientEnd.register(ProcessResponse.class, loginWindow);

		// This is the main UI thread
		while (!display.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public void entryGameHall() {
		loginWindow.hide();
		gameHallWindow.show();
	}

	public void exitApplication() {
		loginWindow.close();
		gameHallWindow.close();
		othelloGameWindow.close();
	}

	public static void main(String[] args) {
		MainApplication mainApplication = new MainApplication();
		mainApplication.startUp();
	}

}
