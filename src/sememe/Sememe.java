package sememe;

import recoder.java.SourceElement.Position;
//import cern.colt.list.ObjectArrayList;
import structure.Hashable;

public class Sememe extends AbstractSememe{
	public Sememe(String sememe, int l, int c, int t) {
		super(sememe, l, c, t);
	}
	
	public Sememe(String sememe, Position p, int t){
		super(sememe, p.getLine(), p.getColumn(), t);
	}

	@Override
	public int hashCode() {
		int hash = 5381;
		for (int i=0; i<this.sememe.length(); i++)
            hash = ((hash << 5) + hash) + this.sememe.charAt(i); /* hash * 33 + c */
        return hash;
	}
	
	@Override
	public String getActualData() {
		return sememe;
	}

	@Override
	public boolean equals(Hashable h) {
		return this.sememe.equals(h.getActualData());
	}
}
