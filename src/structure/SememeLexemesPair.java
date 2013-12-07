package structure;

import sememe.Lexeme;
import sememe.Sememe;

public class SememeLexemesPair {
	private Sememe sememe;
	private Container lexemes;
	
	public SememeLexemesPair(Sememe s){
		sememe = s;
		lexemes = new Container();
	}
	
	public Sememe getSememe(){
		return sememe;
	}
	
	public boolean addLexeme(Lexeme l){
		return lexemes.add(l);
	}
	
	public Container getLexemes(){
		return lexemes;
	}
}
