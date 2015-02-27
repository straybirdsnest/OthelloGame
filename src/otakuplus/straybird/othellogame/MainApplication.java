package otakuplus.straybird.othellogame;

import otakuplus.straybird.othellogame.network.OthelloGameClientEnd;
import otakuplus.straybird.othellogame.ui.LoginWindow;

public class MainApplication {

	protected LoginWindow loginWindow;
	protected OthelloGameClientEnd clientEnd;

	public void startup() {
		Thread networkThread = new Thread(new Runnable() {

			@Override
			public void run() {
				clientEnd = new OthelloGameClientEnd();
			}
		});
		networkThread.start();
		Thread uiThread = new Thread(new Runnable() {

			@Override
			public void run() {
				loginWindow = new LoginWindow();
				loginWindow.open();
			}

		});
		uiThread.start();
	}

	public static void main(String[] args) {
		MainApplication mainApplication = new MainApplication();
		mainApplication.startup();
		;
	}

}
