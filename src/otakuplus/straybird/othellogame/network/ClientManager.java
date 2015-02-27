package otakuplus.straybird.othellogame.network;

import java.util.ArrayList;

public class ClientManager {
	private ArrayList<Class> observerList = null;

	public ClientManager() {
		observerList = new ArrayList<Class>();
	}

	public void attach(Class observer) {
		observerList.add(observer);
	}

	public void detach(Class observer) {
		observerList.remove(observer);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
