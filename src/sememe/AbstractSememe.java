package sememe;

import structure.Hashable;

public abstract class AbstractSememe implements Hashable{
	public static final int CLASS_DECLARATION_SEMEME = 1;
	public static final int CLASS_END_SEMEME = 2;
	public static final int CLASS_BEGIN_SEMEME = 3;
	public static final int ASSIGN_SEMEME = 4;
	public static final int VARIABLE_SEMEME = 5;
	public static final int FIELD_SEMEME = 6;
	public static final int TYPE_SEMEME = 7;
	public static final int METHOD_DECLARATION_SEMEME = 8;
	public static final int ARRAY_ACCESS_OPERATOR = 9;
	public static final int BIT_AND_OPERATOR_SEMEME = 10;
	public static final int BIT_NOT_OPERATOR_SEMEME = 11;
	public static final int BIT_OR_OPERATOR_SEMEME = 12;
	public static final int BIT_X_OR_OPERATOR_SEMEME = 13;
	public static final int BIT_AND_ASSIGNMENT_SEMEME = 14;
	public static final int BIT_OR_ASSIGNMENT_SEMEME = 15;
	public static final int BIT_X_OR_ASSIGNMENT_SEMEME = 16;
	public static final int BOOLEAN_LITERAL_SEMEME = 17;
	public static final int INT_LITERAL_SEMEME = 18;
	public static final int CHAR_LITERAL_SEMEME = 19;
	public static final int STRING_LITERAL_SEMEME = 20;
	public static final int DOUBLE_LITERAL_SEMEME = 21;
	public static final int FLOAT_LITERAL_SEMEME = 22;
	public static final int NULL_LITERAL_SEMEME = 23;
	public static final int LONG_LITERAL_SEMEME = 24;
	public static final int DIVIDE_ASSIGNMENT_SEMEME = 25;
	public static final int MINUS_ASSIGNMENT_SEMEME = 26;
	public static final int MODULO_ASSIGNMENT_SEMEME = 27;
	public static final int PLUS_ASSIGNMENT_SEMEME = 28;
	public static final int TIMES_ASSIGNMENT_SEMEME = 29;
	public static final int KEYWORD_SEMEME = 30;
	public static final int DO_BEGIN = 31;
	public static final int DO_END = 32;
	public static final int DIVIDE_SEMEME = 33;
	public static final int PLUS_SEMEME = 34;
	public static final int MINUS_SEMEME = 35;
	public static final int TIMES_SEMEME = 36;
	public static final int MODULO_SEMEME = 37;
	public static final int EQUALS_SEMEME = 38;
	public static final int GREATER_THAN_SEMEME = 39;
	public static final int GREATER_OR_EQUALS_SEMEME = 40;
	public static final int LESS_THAN_SEMEME = 41;
	public static final int LESS_OR_EQUALS_SEMEME = 42;
	public static final int NOT_EQUALS_SEMEME = 43;
	public static final int LOGICAL_AND_SEMEME = 44;
	public static final int LOGICAL_OR_SEMEME = 45;
	public static final int LOGICAL_NOT_SEMEME = 46;
	public static final int POST_DECREMENT_SEMEME = 47;
	public static final int POST_INCREMENT_SEMEME = 48;
	public static final int PRE_DECREMENT_SEMEME = 49;
	public static final int PRE_INCREMENT_SEMEME = 50;
	public static final int SHIFT_LEFT_SEMEME = 51;
	public static final int SHIFT_RIGHT_SEMEME = 52;
	public static final int SHIFT_LEFT_ASSIGNMENT_SEMEME = 53;
	public static final int SHIFT_RIGHT_ASSIGNMENT_SEMEME = 54;
	public static final int UNSIGNED_SHIFT_RIGHT_SEMEME = 55;
	public static final int UNSIGNED_SHIFT_RIGHT_ASSIGNMENT_SEMEME = 56;
	public static final int TYPE_CAST_SEMEME = 57;
	public static final int TERNARY_SEMEME = 58;
	public static final int WHILE_BEGIN = 59;
	public static final int WHILE_END = 60;
	public static final int METHOD_REFERENCE = 61;
	public static final int FOR_BEGIN = 62;
	public static final int FOR_END = 63;
	public static final int IF_BEGIN = 64;
	public static final int IF_END = 65;
	public static final int ELSE_BEGIN = 66;
	public static final int ELSE_END = 67;
	public static final int CONSTRUCTOR_REFERENCE = 68;
	public static final int SWITCH_BEGIN = 69;
	public static final int SWITCH_END = 70;
	public static final int CATCH_BEGIN = 71;
	public static final int CATCH_END = 72;
	public static final int FINALLY_BEGIN = 73;
	public static final int FINALLY_END = 74;
	public static final int TRY_END = 75;
	public static final int TRY_BEGIN = 76;
	public static final int METHOD_BEGIN = 77;
	public static final int METHOD_END = 78;
	public static final int PACKAGE_SEMEME = 79;
	public static final int NEGATIVE_SEMEME = 80;
	public static final int POSITIVE_SEMEME = 81;
	
	
	protected String sememe;
	protected int line;
	protected int column;
	protected int type;
	
	public AbstractSememe(String sememe){
		this.sememe = sememe;
		this.line = 0;
		this.column = 0;
		this.type = 0;
	}
	
	public AbstractSememe(String sememe, int l, int c, int t){
		this.sememe = sememe;
		this.line = l;
		this.column = c;
		this.type = t;
	}
	
	public int getLine(){ return line; }
	
	public int getColumn(){ return column; }
	
	public int getType(){ return type; }
	
	public String getSememe() {
		return sememe;
	}
	
	public String toString(){
		return String.format("%s(%s,%s)", sememe, line, column);
	}	
}
