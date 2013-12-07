package structure;

import java.util.ArrayList;
import java.util.List;

import sememe.Lexeme;
import sememe.Sememe;
import cern.colt.map.OpenIntObjectHashMap;

public class SememeContainer {
	private OpenIntObjectHashMap container = new OpenIntObjectHashMap();
	
	@SuppressWarnings("unchecked")
	public boolean add(Sememe s, Lexeme l){
		int hash = s.hashCode();
		if (container.containsKey(hash)){
			List<SememeLexemesPair> pairs = (List<SememeLexemesPair>) container.get(hash);
			for (SememeLexemesPair p : pairs){
				if (p.getSememe().equals(s)){
					return p.getLexemes().add(l);
				}
			}
			SememeLexemesPair p = new SememeLexemesPair(s);
			p.addLexeme(l);
			pairs.add(p);
		} else {
			SememeLexemesPair pair = new SememeLexemesPair(s);
			ArrayList<SememeLexemesPair> newElement = new ArrayList<SememeLexemesPair>();
			newElement.add(pair);
			container.put(hash, newElement);
		}
		return true;
	}
}
