package otakuplus.straybird.othellogame.network;

import java.io.IOException;
import java.util.ArrayList;

import otakuplus.straybird.othellogame.MainApplication;
import otakuplus.straybird.othellogame.model.User;
import otakuplus.straybird.othellogame.model.UserInformation;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class OthelloGameClientEnd {

	protected MainApplication mainApplication;

	private Client kryonetClient = null;
	private String serverAddress = null;
	private ClientManager clientManager = null;
	private boolean isConnected = false;

	public OthelloGameClientEnd(MainApplication mainApplication) {
		this.mainApplication = mainApplication;
		serverAddress = "localhost";

		kryonetClient = new Client();
		kryonetClient.start();

		KryonetUtil.register(kryonetClient);

		kryonetClient.addListener(new Listener() {
			public void connected(Connection connection) {
			}

			public void received(Connection connection, Object object) {
				if (object instanceof User) {
					User user = (User) object;
					receiveUser(user);
				} else if (object instanceof UserInformation) {
					UserInformation userInformation = (UserInformation) object;
					receiveUserInformation(userInformation);
				} else if (object instanceof ProcessResponse) {
					ProcessResponse processResponse = (ProcessResponse) object;
					receiveProcessResponse(processResponse);
				} else if (object instanceof SendMessage) {
					SendMessage sendMessage = (SendMessage) object;
					receiveSendMessage(sendMessage);
				}
			}
		});

		clientManager = new ClientManager();
	}

	public void setServerAddress(String serverAddress) {
		if (serverAddress != null) {
			this.serverAddress = serverAddress;
		}
	}

	public void attach(Object observer) {
		clientManager.attach(observer);
	}

	public void detach(Object observer) {
		clientManager.detach(observer);
	}

	public void register(Class concrete, Object observer) {
		clientManager.registerConcrete(concrete, observer);
	}

	public void connect() {
		try {
			if (serverAddress != null) {
				kryonetClient.connect(5000, serverAddress,
						KryonetUtil.SERVER_PORT);
				isConnected = true;
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void close() {
		if (kryonetClient != null) {
			kryonetClient.close();
		}
	}

	public void stop(){
		if(kryonetClient != null){
			kryonetClient.stop();
		}
	}
	public void login(Login login) {
		kryonetClient.sendTCP(login);
	}

	public void logout(Logout logout) {
		kryonetClient.sendTCP(logout);
	}

	public void receiveUser(User user) {
		mainApplication.setCurrentUser(user);
	}

	public void getUserInformation(int userId) {
		GetUserInformation getUserInformation = new GetUserInformation();
		getUserInformation.setUserId(userId);
		kryonetClient.sendTCP(getUserInformation);
	}

	public void receiveUserInformation(UserInformation userInformation) {
		mainApplication.receiveUserInformation(userInformation);
	}

	public boolean getIsConnected() {
		return isConnected;
	}

	public void receiveProcessResponse(ProcessResponse processResponse) {
		ArrayList<Object> itemList;
		if (processResponse.getRequestType() == ProcessResponse.LOGIN) {
			itemList = clientManager.getObserver(ProcessResponse.class);
			if (itemList != null && itemList.isEmpty() != true) {
				// OthelloGameClientEnd.this.mainApplication.postLogin();
			}
		} else if (processResponse.getRequestType() == ProcessResponse.LOGOUT) {
			if (processResponse.getResponseState() == true) {
				mainApplication.getApplicationState().turnDestory();
				mainApplication.exitApplication();
			}
		}
	}

	public void sendMessage(SendMessage sendMessage) {
		kryonetClient.sendTCP(sendMessage);
	}

	public void receiveSendMessage(SendMessage sendMessage) {
		mainApplication.receiveMessage(sendMessage);
	}

	public static void main(String[] args) {

	}

}
