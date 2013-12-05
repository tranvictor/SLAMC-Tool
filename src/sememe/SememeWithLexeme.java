package sememe;

import java.util.ArrayList;
import java.util.List;

import recoder.java.SourceElement.Position;

public class SememeWithLexeme extends AbstractSememe{
	private List<String> lexemes = new ArrayList<String>();
	
	public SememeWithLexeme(String sememe, int l, int c) {
		super(sememe, l, c);
	}
	
	public SememeWithLexeme(String sememe, Position p){
		super(sememe, p.getLine(), p.getColumn());
	}
	
	public List<String> getAssociatedLexemes(){
		return lexemes;
	}

	@Override
	public int hashCode() {
		int hash = 5381;
		for (int i=0; i<this.sememe.length(); i++)
            hash = ((hash << 5) + hash) + this.sememe.charAt(i); /* hash * 33 + c */
        return hash;
	}
	
}
