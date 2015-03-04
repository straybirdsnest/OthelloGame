package otakuplus.straybird.othellogame;

import org.eclipse.swt.widgets.Display;

import otakuplus.straybird.othellogame.network.Login;
import otakuplus.straybird.othellogame.network.OthelloGameClientEnd;
import otakuplus.straybird.othellogame.network.ProcessResponse;
import otakuplus.straybird.othellogame.network.SendMessage;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

import com.esotericsoftware.minlog.Log;

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

		clientEnd = new OthelloGameClientEnd(this);
	}

	public void startUp() {
		loginWindow.open();
		gameHallWindow.open();
		gameHallWindow.hide();
		othelloGameWindow.open();
		othelloGameWindow.hide();

		clientEnd.attach(loginWindow);
		clientEnd.attach(gameHallWindow);
		clientEnd.attach(othelloGameWindow);
		clientEnd.register(ProcessResponse.class, loginWindow);

		clientEnd.connect();

		// This is the main UI thread
		while (!display.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

		clientEnd.close();
	}

	public void login(Login login) {
		clientEnd.login(login);
	}

	public void postLogin() {
		/*
		 * Use asyncExec to call UI thread to update when the caller is in
		 * another thread
		 */
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				if (loginWindow != null && gameHallWindow != null) {
					loginWindow.hide();
					gameHallWindow.show();
				}
			}
		});

	}

	public void receiveMessage(final SendMessage sendMessage) {
		/*
		 * Java doesn't have pass-by-reference at all, it has pass reference by
		 * value, so use final keyword to indicates that the parameter will not
		 * change.
		 */
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				System.out.println("Main Receive");
				gameHallWindow.receiveMessage(sendMessage);
			}
		});

	}

	public void exitApplication() {
		loginWindow.close();
		gameHallWindow.close();
		othelloGameWindow.close();
	}

	public static void main(String[] args) {
		Log.set(Log.LEVEL_DEBUG);
		MainApplication mainApplication = new MainApplication();
		mainApplication.startUp();
	}

}
