package structure;

import java.util.ArrayList;
import java.util.List;

import cern.colt.list.ObjectArrayList;
import cern.colt.map.OpenIntObjectHashMap;

public class Container {
	private OpenIntObjectHashMap container = new OpenIntObjectHashMap();
	
	@SuppressWarnings("unchecked")
	public boolean add(Hashable h){
		int hash = h.hashCode();
		if (container.containsKey(hash)){
			List<Hashable> temp = (List<Hashable>) container.get(hash);
			for (Hashable l : temp){
				if (l.equals(h)) return false;
			}
			temp.add(h);
		} else {
			ArrayList<Hashable> newElement = new ArrayList<Hashable>();
			newElement.add(h);
			container.put(hash, newElement);
		}
		return true;
	}
	
	public ObjectArrayList values(){
		return container.values();
	}
}
