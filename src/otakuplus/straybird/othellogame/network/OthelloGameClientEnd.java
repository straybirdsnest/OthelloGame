package otakuplus.straybird.othellogame.network;

import java.io.IOException;
import java.util.ArrayList;

import otakuplus.straybird.othellogame.MainApplication;
import otakuplus.straybird.othellogame.model.User;
import otakuplus.straybird.othellogame.model.UserInformation;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

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

				} else if (object instanceof UserInformation) {
					UserInformation userInformation = (UserInformation) object;

				} else if (object instanceof ProcessResponse) {
					ProcessResponse processResponse = (ProcessResponse) object;
					ArrayList<Object> itemList;
					if (processResponse.getRequestType() == ProcessResponse.LOGIN) {
						itemList = clientManager
								.getObserver(ProcessResponse.class);
						if (itemList != null && itemList.isEmpty() != true) {
							System.out.println("Get message.");
							OthelloGameClientEnd.this.mainApplication.postLogin();
						}
					}
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
	
	public void close(){
		if(kryonetClient != null){
			kryonetClient.close();
		}
	}

	public void login(Login login) {
		kryonetClient.sendTCP(login);
	}

	public void logout(int userId) {
		Logout logout = new Logout();
		logout.setUserId(userId);
		kryonetClient.sendTCP(logout);
	}

	public void getUserInformation(int userId) {
		GetUserInformation getUserInformation = new GetUserInformation();
		getUserInformation.setUserId(userId);
		kryonetClient.sendTCP(getUserInformation);
	}

	public boolean getIsConnected() {
		return isConnected;
	}

	public static void main(String[] args) {
		
	}

}
