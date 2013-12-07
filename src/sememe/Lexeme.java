package sememe;

import structure.Hashable;

public class Lexeme implements Hashable {
	private String lexeme;
	
	public String getLexeme() {
		return lexeme;
	}
	@Override
	public int hashCode() {
		int hash = 5381;
		for (int i=0; i<this.lexeme.length(); i++)
            hash = ((hash << 5) + hash) + this.lexeme.charAt(i); /* hash * 33 + c */
        return hash;
	}
	@Override
	public String getActualData() {
		return lexeme;
	}
	@Override
	public boolean equals(Hashable h) {
		return lexeme.equals(h.getActualData());
	}
	
}
