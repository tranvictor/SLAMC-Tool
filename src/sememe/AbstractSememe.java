package sememe;

public abstract class AbstractSememe {
	protected String sememe;
	protected int line;
	protected int column;
	
	public AbstractSememe(String sememe){
		this.sememe = sememe;
		this.line = 0;
		this.column = 0;
	}
	
	public AbstractSememe(String sememe, int l, int c){
		this.sememe = sememe;
		this.line = l;
		this.column = c;
	}
	
	public int getLine(){ return line; }
	
	public int getColumn(){ return column; }
	
	public String getSememe() {
		return sememe;
	}
	
	public abstract int hashCode();
	
	public String toString(){
		return String.format("%s(%s,%s)", sememe, line, column);
	}

	
}
