package structure;

import java.util.ArrayList;
import java.util.List;

import sememe.Lexeme;
import sememe.Sememe;
import cern.colt.list.ObjectArrayList;
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
			pair.addLexeme(l);
			ArrayList<SememeLexemesPair> newElement = new ArrayList<SememeLexemesPair>();
			newElement.add(pair);
			container.put(hash, newElement);
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public String associatedLexemes(){
		String result = "";
		ObjectArrayList pairs = container.values();
		List<SememeLexemesPair> ps;
		Container temp;
		for (int i=0; i<pairs.size(); i++){
			ps = (List<SememeLexemesPair>) pairs.get(i);
			for (SememeLexemesPair p : ps){
				result += p.getSememe().getSememe() + ": ";
				temp = p.getLexemes();
				ObjectArrayList values = temp.values();
				for (int j=0; j<values.size(); j++){
					List<Lexeme> lexemes = (List<Lexeme>) values.get(j);
					for (Lexeme l : lexemes){
						result += l.getLexeme() + ";";	
					}
					
				}
				result += "\n";
			}
		}
		return result;
	}
}
