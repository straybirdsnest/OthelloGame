package otakuplus.straybird.othellogame;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.eclipse.swt.widgets.Display;

import otakuplus.straybird.othellogame.model.GameTable;
import otakuplus.straybird.othellogame.model.User;
import otakuplus.straybird.othellogame.model.UserInformation;
import otakuplus.straybird.othellogame.network.Login;
import otakuplus.straybird.othellogame.network.Logout;
import otakuplus.straybird.othellogame.network.OthelloGameClientEnd;
import otakuplus.straybird.othellogame.network.ProcessResponse;
import otakuplus.straybird.othellogame.network.SendMessage;
import otakuplus.straybird.othellogame.network.UpdateGameTable;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

import com.esotericsoftware.minlog.Log;

public class MainApplication {

	public static final int AUTO_UPDATE_TIME = 30000;
	public static final int STOP_TIME = -1;

	protected Display display;

	protected LoginWindow loginWindow;
	protected GameHallWindow gameHallWindow;
	protected OthelloGameWindow othelloGameWindow;

	protected OthelloGameClientEnd clientEnd;

	protected User currentUser;
	protected UserInformation currentUserInformation;

	protected ApplicationState applicationState;

	protected ArrayList<UserInformation> userInformationList;
	protected GameTable currentGameTable;
	protected ArrayList<GameTable> gameTableList;

	protected Runnable autoUserListTimer;
	protected Runnable autoGameTableListTimer;

	public MainApplication() {
		applicationState = new ApplicationState();
		applicationState.turnLogin();

		userInformationList = new ArrayList<UserInformation>();
		gameTableList = new ArrayList<GameTable>();
		// there should be 100 empty tables in the game
		for (int i = 1; i < 101; i++) {
			GameTable tempGameTable = new GameTable();
			tempGameTable.setGameTableId(i);
			gameTableList.add(tempGameTable);
		}

		autoUserListTimer = new Runnable() {

			@Override
			public void run() {
				if (clientEnd != null && currentUser != null) {
					clientEnd.getUserOnlineList(0, 50);
				}
			}
		};

		autoGameTableListTimer = new Runnable() {

			@Override
			public void run() {
				if (clientEnd != null && currentUser != null) {
					clientEnd.getGameTableList(0, 50);
				}
			}
		};

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

	public void receiveUser(User user) {
		if (currentUser == null && user != null) {
			currentUser = user;
			getCurrentUserInformation();
		}
	}

	public void getCurrentUserInformation() {
		if (currentUser != null) {
			clientEnd.getUserInformation(currentUser.getUserId());
		}
	}

	public GameTable getCurrentGameTable() {
		return currentGameTable;
	}

	public ArrayList<GameTable> getGameTableList() {
		return gameTableList;
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
				if (userInformationList.size() > 0) {
					Iterator<UserInformation> userInformationIterator = userInformationList
							.iterator();
					UserInformation tempUserInformation = null;
					while (userInformationIterator.hasNext()) {
						tempUserInformation = userInformationIterator.next();
						if (tempUserInformation.getUserId() == userInformation
								.getUserId()) {
							// remove the old one to update
							userInformationIterator.remove();
							break;
						}
					}
				}
				userInformationList.add(userInformation);
				notifyUserListUpdate();
			}
		}
	}

	public void receiveUserInformationList(
			ArrayList<UserInformation> userOnlineList) {
		if (userOnlineList.size() > 0) {
			Iterator<UserInformation> userInformationIterator = userOnlineList
					.iterator();
			userInformationList.clear();
			UserInformation tempUserInfo = null;
			boolean includeSlef = false;
			while (userInformationIterator.hasNext()) {
				tempUserInfo = userInformationIterator.next();
				if (tempUserInfo.getUserId() == currentUser.getUserId()) {
					includeSlef = true;
				}
				userInformationList.add(tempUserInfo);
			}
			if (includeSlef == false) {
				userInformationList.add(currentUserInformation);
			}
			notifyUserListUpdate();
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
					applicationState.turnGameHall();
					loginWindow.hide();
					gameHallWindow.show();
					notifyUserListUpdate();
					startTimerTask();
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
					if (applicationState.getState() == ApplicationState.GAME_HALL
							|| applicationState.getState() == ApplicationState.GAME_TABLE) {
						gameHallWindow.receiveMessage(sendMessage);
					}

					if (applicationState.getState() == ApplicationState.GAME_TABLE) {
						othelloGameWindow.receiveMessage(sendMessage);
					}
				}
			}
		});

	}

	public void receiveGameTable(GameTable gameTable) {
		if (currentGameTable == null && gameTable != null) {
			currentGameTable = gameTable;
			postTakeGameTable();
		}
	}

	public void postTakeGameTable() {
		if (othelloGameWindow != null) {
			Display.getDefault().asyncExec(new Runnable() {

				@Override
				public void run() {
					applicationState.turnGameTable();
					othelloGameWindow.show();
				}
			});
		}
	}

	public void receiveGameTableList(ArrayList<GameTable> newGameTableList) {
		if (gameTableList.size() > 0) {
			Iterator<GameTable> newGameTableIterator = gameTableList.iterator();
			Iterator<GameTable> oldGameTableIterator = this.gameTableList
					.iterator();
			GameTable oldGameTable = null;
			GameTable newGameTable = null;
			while (oldGameTableIterator.hasNext()) {
				oldGameTable = oldGameTableIterator.next();
				while (newGameTableIterator.hasNext()) {
					newGameTable = newGameTableIterator.next();
					if (oldGameTable.getGameTableId() == newGameTable
							.getGameTableId()) {
						// write with new data
						oldGameTable.setPlayerAId(newGameTable.getPlayerAId());
						oldGameTable.setPlayerBId(newGameTable.getPlayerBId());
						if (currentGameTable != null
								&& currentGameTable.getGameTableId() == newGameTable
										.getGameTableId()) {
							currentGameTable.setPlayerAId(newGameTable
									.getPlayerAId());
							currentGameTable.setPlayerBId(newGameTable
									.getPlayerBId());
						}
					}
				}
			}
			notifyUserListUpdate();
		}
	}

	public void takeGameTable(int tableNumber, int tablePosition) {
		if (currentUser != null && currentGameTable == null) {
			UpdateGameTable updateGameTable = new UpdateGameTable();
			updateGameTable.setGameTableId(tableNumber);
			updateGameTable.setUserId(currentUser.getUserId());
			updateGameTable.setTablePosition(tablePosition);
			updateGameTable.setAction(UpdateGameTable.ACTION_TAKE);
			clientEnd.updateGameTable(updateGameTable);
		}
	}

	public void leaveGameTable() {
		if (currentUser != null && currentGameTable != null) {
			UpdateGameTable updateGameTable = new UpdateGameTable();
			updateGameTable.setGameTableId(currentGameTable.getGameTableId());
			updateGameTable.setUserId(currentUser.getUserId());
			if (currentGameTable.getPlayerAId() != null
					&& currentGameTable.getPlayerAId() == currentUser
							.getUserId()) {
				updateGameTable.setTablePosition(1);
			}
			if (currentGameTable.getPlayerBId() != null
					&& currentGameTable.getPlayerBId() == currentUser
							.getUserId()) {
				updateGameTable.setTablePosition(2);
			}
			updateGameTable.setAction(UpdateGameTable.ACTION_LEFT);
			clientEnd.updateGameTable(updateGameTable);
		}
	}

	public void postLeaveGameTable() {
		currentGameTable = null;
		applicationState.turnGameHall();
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				othelloGameWindow.hide();
			}
		});
	}

	public void updateGameTable(int tableNumber, int tablePosition) {
		if (clientEnd != null && currentUser != null) {
			if (currentGameTable != null) {
				UpdateGameTable updateGameTable = new UpdateGameTable();
				updateGameTable.setGameTableId(currentGameTable
						.getGameTableId());
				updateGameTable.setUserId(currentUser.getUserId());
				if (currentGameTable.getPlayerAId() == currentUser.getUserId()) {
					updateGameTable.setTablePosition(1);
				}
				if (currentGameTable.getPlayerBId() == currentUser.getUserId()) {
					updateGameTable.setTablePosition(2);
				}
				updateGameTable.setAction(UpdateGameTable.ACTION_LEFT);
				clientEnd.updateGameTable(updateGameTable);
			} else {
				UpdateGameTable updateGameTable = new UpdateGameTable();
				updateGameTable.setGameTableId(tableNumber);
				updateGameTable.setUserId(currentUser.getUserId());
				updateGameTable.setTablePosition(tablePosition);
				updateGameTable.setAction(UpdateGameTable.ACTION_TAKE);
				clientEnd.updateGameTable(updateGameTable);
			}
		}
	}

	public void startTimerTask() {
		if (currentUser != null) {
			Display.getDefault().timerExec(AUTO_UPDATE_TIME, autoUserListTimer);
			Display.getDefault().timerExec(AUTO_UPDATE_TIME,
					autoGameTableListTimer);
		}
	}

	public void stopTimerTask() {
		if (currentUser != null) {
			Display.getDefault().timerExec(STOP_TIME, autoUserListTimer);
			Display.getDefault().timerExec(STOP_TIME, autoGameTableListTimer);
		}
	}

	public void exitApplication() {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				stopTimerTask();
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

	public void notifyGameTableListUpdate() {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				gameHallWindow.notifyGameTableListUpdate();
			}
		});
	}

	public static void main(String[] args) {
		Log.set(Log.LEVEL_DEBUG);
		MainApplication mainApplication = new MainApplication();
		mainApplication.startUp();
	}

}
