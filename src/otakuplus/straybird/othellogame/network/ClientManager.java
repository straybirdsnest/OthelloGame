package otakuplus.straybird.othellogame.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClientManager {
	private HashSet<Object> observerSet = null;
	private HashMap<Class, ArrayList<Object>> observerConcrete = null;

	public ClientManager() {
		observerSet = new HashSet<Object>();
		observerConcrete = new HashMap<Class, ArrayList<Object>>();
	}

	public void attach(Object observer) {
		if (observer != null) {
			observerSet.add(observer);
		}
	}

	public void detach(Object observer) {
		if (observer != null && observerConcrete.isEmpty() != true) {
			Set<Class> keySet = observerConcrete.keySet();
			Iterator<Class> keySetIterator = keySet.iterator();
			Iterator<Object> itemIterator;
			Class key;
			Object object;
			ArrayList<Object> itemList;
			while (keySetIterator.hasNext()) {
				key = keySetIterator.next();
				itemList = observerConcrete.get(key);
				if (itemList != null && itemList.isEmpty() != true) {
					itemIterator = itemList.iterator();
					while (itemIterator.hasNext()) {
						object = itemIterator.next();
						if (observer == object) {
							System.out.println("Observer remove!");
							itemList.remove(observer);
						}
					}
				}
			}
			observerSet.remove(observer);
		}
	}

	public void registerConcrete(Class concrete, Object observer) {
		ArrayList<Object> itemList;
		if (observer != null && concrete != null) {
			if ((itemList = observerConcrete.get(concrete)) != null) {
				itemList.add(observer);
			} else {
				itemList = new ArrayList<Object>();
				itemList.add(observer);
				observerConcrete.put(concrete, itemList);
			}
		}
	}

	public ArrayList<Object> getObserver(Class concrete) {
		ArrayList<Object> itemList = observerConcrete.get(concrete);
		return itemList;
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
