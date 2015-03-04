package otakuplus.straybird.othellogame.network;

import java.util.HashMap;
import java.util.HashSet;

public class ClientManager {
	private HashSet<Object> observerSet = null;
	private HashMap<Class, Object> observerConcrete = null;

	public ClientManager() {
		observerSet = new HashSet<Object>();
		observerConcrete = new HashMap<Class, Object>();
	}

	public void attach(Object observer) {
		if (observer != null) {
			observerSet.add(observer);
		}
	}

	public void detach(Object observer) {
		if (observer != null) {
			while (observerConcrete.isEmpty() != true) {
				// TODO delete all observer for hashmap
			}
			observerSet.remove(observer);
		}
	}

	public void registerConcrete(Class concrete, Object observer) {
		if (observer != null && concrete != null) {
			observerConcrete.put(concrete, observer);
		}
	}

	public void destory() {
		if (observerSet.isEmpty() != true) {
			observerSet.clear();
		}
		if (observerConcrete.isEmpty() != true) {
			observerConcrete.clear();
		}
	}

	public static void main(String[] args) {
	}

}
