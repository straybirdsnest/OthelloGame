package otakuplus.straybird.othellogame;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.eclipse.swt.widgets.Display;

import otakuplus.straybird.othellogame.model.User;
import otakuplus.straybird.othellogame.model.UserInformation;
import otakuplus.straybird.othellogame.network.Login;
import otakuplus.straybird.othellogame.network.Logout;
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

	protected User currentUser;
	protected UserInformation currentUserInformation;

	protected ApplicationState applicationState;

	protected ArrayList<UserInformation> userInformationList;

	public MainApplication() {
		userInformationList = new ArrayList<UserInformation>();

		applicationState = new ApplicationState();
		// network
		clientEnd = new OthelloGameClientEnd(this);

		// UI
		display = Display.getDefault();

		loginWindow = new LoginWindow(this);
		gameHallWindow = new GameHallWindow(this);
		othelloGameWindow = new OthelloGameWindow(this);
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
		clientEnd.close();
		clientEnd.stop();
	}

	public ApplicationState getApplicationState() {
		return applicationState;
	}

	public void login(String username, String password) {
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(password);
		clientEnd.login(login);
	}

	public void logout() {
		if (currentUser != null) {
			Logout logout = new Logout();
			logout.setUserId(currentUser.getUserId());
			clientEnd.logout(logout);
		}
	}

	public void setCurrentUser(User user) {
		if (user != null) {
			System.out.println("Set current user.");
			currentUser = user;
			System.out.println(currentUser.getUsername() + ","
					+ currentUser.getCreateTime());
			getCurrentUserInformation();
		}
	}

	public void getCurrentUserInformation() {
		if (currentUser != null) {
			clientEnd.getUserInformation(currentUser.getUserId());
		}
	}

	public ArrayList<UserInformation> getUserInformationList() {
		return userInformationList;
	}

	public void receiveUserInformation(UserInformation userInformation) {
		if (currentUser != null) {
			if (currentUser.getUserId() == userInformation.getUserId()) {
				currentUserInformation = userInformation;
				// add current user information
				userInformationList.add(userInformation);
				postLogin();
			} else {
				userInformationList.add(userInformation);
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						gameHallWindow.notifyUserListUpdate();
					}
				});
			}
		}
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
					notifyUserListUpdate();
				}
			}
		});
	}

	public void receiveLogout(Logout logout) {
		if (userInformationList != null) {
			Iterator<UserInformation> userListIterator = userInformationList
					.iterator();
			UserInformation userInformation = null;
			boolean userListUpdate = false;
			while (userListIterator.hasNext()) {
				userInformation = userListIterator.next();
				if (logout.getUserId() == userInformation.getUserId()) {
					// iterator's remove is the only safe way to modify list
					userListIterator.remove();
					userListUpdate = true;
				}
			}
			if (userListUpdate == true) {
				notifyUserListUpdate();
			}
		}
	}

	public void sendMessage(String message) {
		if (currentUser != null && currentUserInformation != null) {
			SendMessage sendMessage = new SendMessage();
			sendMessage.setNickname(currentUserInformation.getNickname());
			sendMessage.setMessage(message);
			sendMessage.setMessageTime(new Date());
			clientEnd.sendMessage(sendMessage);
		}
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
				// should only update after user has logined
				if (currentUser != null) {
					gameHallWindow.receiveMessage(sendMessage);
				}
			}
		});

	}

	public void exitApplication() {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				loginWindow.close();
				gameHallWindow.close();
				othelloGameWindow.close();
				display.dispose();
			}
		});
	}

	public void notifyUserListUpdate() {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				gameHallWindow.notifyUserListUpdate();
			}
		});

	}

	public static void main(String[] args) {
		Log.set(Log.LEVEL_DEBUG);
		MainApplication mainApplication = new MainApplication();
		mainApplication.startUp();
	}

}
