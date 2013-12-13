package structure;

import java.util.ArrayList;

import config.GlobalConfig;

import recoder.java.Expression;
import recoder.java.JavaProgramElement;
import recoder.java.declaration.ClassDeclaration;
import recoder.java.declaration.FieldSpecification;
import recoder.java.declaration.VariableSpecification;
import recoder.java.expression.literal.BooleanLiteral;
import recoder.java.expression.literal.CharLiteral;
import recoder.java.expression.literal.DoubleLiteral;
import recoder.java.expression.literal.FloatLiteral;
import recoder.java.expression.literal.IntLiteral;
import recoder.java.expression.literal.LongLiteral;
import recoder.java.expression.literal.NullLiteral;
import recoder.java.expression.literal.StringLiteral;
import recoder.java.expression.operator.Conditional;
import recoder.java.expression.operator.New;
import recoder.java.expression.operator.TypeCast;
import recoder.java.reference.FieldReference;
import recoder.java.reference.MethodReference;
import recoder.java.reference.TypeReference;
import recoder.java.reference.VariableReference;
import recoder.list.generic.ASTList;
import sememe.AbstractSememe;
import sememe.Lexeme;
import sememe.Sememe;

public class SememeSequence extends ArrayList<AbstractSememe> {
	private static final long serialVersionUID = -1797759331325759875L;
	private SememeContainer sememeContainer;

	public SememeSequence(){
		super();
		sememeContainer = new SememeContainer();
	}

	public boolean add(Sememe s, JavaProgramElement e){
		Lexeme lexeme = null;
		switch (s.getType()){
		case AbstractSememe.CLASS_DECLARATION_SEMEME:
			lexeme = associatedLexeme((ClassDeclaration) e);
			break;
		case AbstractSememe.VARIABLE_SEMEME:
			if (e instanceof VariableSpecification){
				lexeme = associatedLexeme((VariableSpecification) e);
			} else if (e instanceof VariableReference){
				lexeme = associatedLexeme((VariableReference) e);
			}
			break;
		case AbstractSememe.FIELD_SEMEME:
			if (e instanceof FieldSpecification) {
				lexeme = associatedLexeme((FieldSpecification) e);
			} else if (e instanceof FieldReference) {
				lexeme = associatedLexeme((FieldReference) e);
			}
			break;
		case AbstractSememe.TYPE_SEMEME:
			lexeme = associatedLexeme((TypeReference) e);
			break;
		case AbstractSememe.METHOD_DECLARATION_SEMEME:
			break;
		case AbstractSememe.BOOLEAN_LITERAL_SEMEME:
			lexeme = associatedLexeme((BooleanLiteral) e);
			break;
		case AbstractSememe.INT_LITERAL_SEMEME:
			lexeme = associatedLexeme((IntLiteral) e);
			break;
		case AbstractSememe.CHAR_LITERAL_SEMEME:
			lexeme = associatedLexeme((CharLiteral) e);
			break;
		case AbstractSememe.STRING_LITERAL_SEMEME:
			lexeme = associatedLexeme((StringLiteral) e);
			break;
		case AbstractSememe.DOUBLE_LITERAL_SEMEME:
			lexeme = associatedLexeme((DoubleLiteral) e);
			break;
		case AbstractSememe.FLOAT_LITERAL_SEMEME:
			lexeme = associatedLexeme((FloatLiteral) e);
			break;
		case AbstractSememe.NULL_LITERAL_SEMEME:
			lexeme = associatedLexeme((NullLiteral) e);
			break;
		case AbstractSememe.LONG_LITERAL_SEMEME:
			lexeme = associatedLexeme((LongLiteral) e);
			break;
		case AbstractSememe.TYPE_CAST_SEMEME:
			lexeme = associatedLememe((TypeCast) e);
			break;
		case AbstractSememe.TERNARY_SEMEME:
			lexeme = assoicatedLexeme((Conditional) e);
			break;
		case AbstractSememe.METHOD_REFERENCE:
			lexeme = associatedLexeme((MethodReference) e);
			break;
		case AbstractSememe.CONSTRUCTOR_REFERENCE:
			lexeme = associatedLexeme((New) e);
			break;
		case AbstractSememe.SPECIAL_CONSTRUCTOR_REFERENCE:
			GlobalConfig.LEXEME_LOGGER.log("Unimplemented for special constructor reference: " + e.getStartPosition());
			break;
		case AbstractSememe.PACKAGE_SEMEME:
			break;
		case AbstractSememe.KEYWORD_SEMEME:
			lexeme = associatedLexeme(s);
			break;
//		case AbstractSememe.DO_BEGIN:
//			break;
//		case AbstractSememe.DO_END:
//			break;
//		case AbstractSememe.WHILE_BEGIN:
//			break;
//		case AbstractSememe.WHILE_END:
//			break;
//		case AbstractSememe.FOR_BEGIN:
//			break;
//		case AbstractSememe.FOR_END:
//			break;
//		case AbstractSememe.IF_BEGIN:
//			break;
//		case AbstractSememe.IF_END:
//			break;
//		case AbstractSememe.ELSE_BEGIN:
//			break;
//		case AbstractSememe.ELSE_END:
//			break;
//			case AbstractSememe.CLASS_END_SEMEME:
//			//TODO
//			break;
//		case AbstractSememe.CLASS_BEGIN_SEMEME:
//			//TODO:
//			break;
//		case AbstractSememe.SWITCH_BEGIN:
//			break;
//		case AbstractSememe.SWITCH_END:
//			break;
//		case AbstractSememe.CATCH_BEGIN:
//			break;
//		case AbstractSememe.CATCH_END:
//			break;
//		case AbstractSememe.FINALLY_BEGIN:
//			break;
//		case AbstractSememe.FINALLY_END:
//			break;
//		case AbstractSememe.TRY_END:
//			break;
//		case AbstractSememe.TRY_BEGIN:
//			break;
//		case AbstractSememe.METHOD_BEGIN:
//			break;
//		case AbstractSememe.METHOD_END:
//			break;
		case AbstractSememe.ASSIGN_SEMEME:
		case AbstractSememe.NEGATIVE_SEMEME:
		case AbstractSememe.POSITIVE_SEMEME:
		case AbstractSememe.ACCESS_OPERATOR:
		case AbstractSememe.ARRAY_ACCESS_OPERATOR:
		case AbstractSememe.BIT_AND_OPERATOR_SEMEME:
		case AbstractSememe.BIT_NOT_OPERATOR_SEMEME:
		case AbstractSememe.BIT_OR_OPERATOR_SEMEME:
		case AbstractSememe.BIT_X_OR_OPERATOR_SEMEME:
		case AbstractSememe.BIT_AND_ASSIGNMENT_SEMEME:
		case AbstractSememe.BIT_OR_ASSIGNMENT_SEMEME:
		case AbstractSememe.BIT_X_OR_ASSIGNMENT_SEMEME:
		case AbstractSememe.DIVIDE_ASSIGNMENT_SEMEME:
		case AbstractSememe.MINUS_ASSIGNMENT_SEMEME:
		case AbstractSememe.MODULO_ASSIGNMENT_SEMEME:
		case AbstractSememe.PLUS_ASSIGNMENT_SEMEME:
		case AbstractSememe.TIMES_ASSIGNMENT_SEMEME:
		case AbstractSememe.DIVIDE_SEMEME:
		case AbstractSememe.PLUS_SEMEME:
		case AbstractSememe.MINUS_SEMEME:
		case AbstractSememe.TIMES_SEMEME:
		case AbstractSememe.MODULO_SEMEME:
		case AbstractSememe.EQUALS_SEMEME:
		case AbstractSememe.GREATER_THAN_SEMEME:
		case AbstractSememe.GREATER_OR_EQUALS_SEMEME:
		case AbstractSememe.LESS_THAN_SEMEME:
		case AbstractSememe.LESS_OR_EQUALS_SEMEME:
		case AbstractSememe.NOT_EQUALS_SEMEME:
		case AbstractSememe.LOGICAL_AND_SEMEME:
		case AbstractSememe.LOGICAL_OR_SEMEME:
		case AbstractSememe.LOGICAL_NOT_SEMEME:
		case AbstractSememe.POST_DECREMENT_SEMEME:
		case AbstractSememe.POST_INCREMENT_SEMEME:
		case AbstractSememe.PRE_DECREMENT_SEMEME:
		case AbstractSememe.PRE_INCREMENT_SEMEME:
		case AbstractSememe.SHIFT_LEFT_SEMEME:
		case AbstractSememe.SHIFT_RIGHT_SEMEME:
		case AbstractSememe.SHIFT_LEFT_ASSIGNMENT_SEMEME:
		case AbstractSememe.SHIFT_RIGHT_ASSIGNMENT_SEMEME:
		case AbstractSememe.UNSIGNED_SHIFT_RIGHT_SEMEME:
		case AbstractSememe.UNSIGNED_SHIFT_RIGHT_ASSIGNMENT_SEMEME:
			lexeme = associatedLexeme(s);
			break;
		}
		sememeContainer.add(s, lexeme);
		return super.add(s);
	}
	
	private Lexeme associatedLexeme(FieldSpecification e) {
		Lexeme result = new Lexeme(e.getName());
		GlobalConfig.LEXEME_LOGGER.log("Field: " + e.getName());
		return result;
	}
	
	private Lexeme associatedLexeme(FieldReference e) {
		Lexeme result = new Lexeme(e.getName());
		GlobalConfig.LEXEME_LOGGER.log("Field: " + e.getName());
		return result;
	}
	
	private String paramsToSource(ASTList<Expression> params){
		String lex = "(";
		if (params != null && params.size() >= 1){
        	for (int i=0; i < params.size() - 1; i++) {
        		lex += params.get(i).toSource() + ", ";
        	}
        	lex += params.get(params.size() - 1).toSource();
        } 
        lex += ")";
        return lex;
	}

	private Lexeme associatedLexeme(New e) {
		String className = e.getTypeReference().getName();
		ASTList<Expression> params = e.getArguments();
		String lex = className + paramsToSource(params);
		Lexeme result = new Lexeme(lex);
		GlobalConfig.LEXEME_LOGGER.log("Constructor call: " + result.getLexeme());
		return result;
	}

	private Lexeme associatedLexeme(MethodReference e) {
		String name = e.getName();
		ASTList<Expression> params = e.getArguments();
        String lex = name;
        lex += paramsToSource(params);
        Lexeme result = new Lexeme(lex);
        GlobalConfig.LEXEME_LOGGER.log("Method call: " + result.getLexeme());
        return result;
	}

	private Lexeme assoicatedLexeme(Conditional e) {
		Lexeme result = new Lexeme("(<e>) ? (<e>) : (<e>)");;
		GlobalConfig.LEXEME_LOGGER.log("Ternary: " + result.getLexeme());
		return result;
	}

	private Lexeme associatedLememe(TypeCast e) {
		Lexeme result = new Lexeme(String.format("(%s)", e.getTypeReference().getName()));;
		GlobalConfig.LEXEME_LOGGER.log("TypeCast: " + result.getLexeme());
		return result;
	}

	private Lexeme associatedLexeme(LongLiteral e) {
		Lexeme result = new Lexeme(e.getValue());
		GlobalConfig.LEXEME_LOGGER.log("Long literal: " + e.getValue());
		return result;
	}

	private Lexeme associatedLexeme(NullLiteral e) {
		Lexeme result = new Lexeme("null");
		GlobalConfig.LEXEME_LOGGER.log("Null: null");
		return result;
	}

	private Lexeme associatedLexeme(FloatLiteral e) {
		Lexeme result = new Lexeme(e.getValue());
		GlobalConfig.LEXEME_LOGGER.log("Float literal: " + e.getValue());
		return result;
	}

	private Lexeme associatedLexeme(DoubleLiteral e) {
		Lexeme result = new Lexeme(e.getValue());
		GlobalConfig.LEXEME_LOGGER.log("Double literal: " + e.getValue());
		return result;
	}

	private Lexeme associatedLexeme(StringLiteral e) {
		Lexeme result = new Lexeme(e.getValue());
		GlobalConfig.LEXEME_LOGGER.log("String literal: " + e.getValue());
		return result;
	}

	private Lexeme associatedLexeme(CharLiteral e) {
		Lexeme result = new Lexeme(e.getValue());
		GlobalConfig.LEXEME_LOGGER.log("Char literal: " + e.getValue());
		return result;
	}

	private Lexeme associatedLexeme(IntLiteral e) {
		Lexeme result = new Lexeme(e.getValue());
		GlobalConfig.LEXEME_LOGGER.log("Int literal: " + e.getValue());
		return result;
	}

	private Lexeme associatedLexeme(VariableReference e) {
		Lexeme result = new Lexeme(e.getName());
		GlobalConfig.LEXEME_LOGGER.log("Variable: " + e.getName());
		return result;
	}
	
	private Lexeme associatedLexeme(VariableSpecification e) {
		Lexeme result = new Lexeme(e.getName());
		GlobalConfig.LEXEME_LOGGER.log("Variable: " + e.getName());
		return result;
	}

	private Lexeme associatedLexeme(BooleanLiteral e) {
		Lexeme result = new Lexeme(e.getValue() + "");
		GlobalConfig.LEXEME_LOGGER.log("Boolean literal: " + e.getValue());
		return result;
	}

	private Lexeme associatedLexeme(Sememe s) {
		String lex = "";
		switch (s.getType()){
		case AbstractSememe.ACCESS_OPERATOR:
			lex = "."; break;
		case AbstractSememe.ASSIGN_SEMEME:
			lex = "="; break;
		case AbstractSememe.NEGATIVE_SEMEME:
			lex = "-"; break;
		case AbstractSememe.POSITIVE_SEMEME:
			lex = "+"; break;
		case AbstractSememe.ARRAY_ACCESS_OPERATOR:
			lex = "[<i>]"; break;
		case AbstractSememe.BIT_AND_OPERATOR_SEMEME:
			lex = "&"; break;
		case AbstractSememe.BIT_NOT_OPERATOR_SEMEME:
			lex = "!"; break;
		case AbstractSememe.BIT_OR_OPERATOR_SEMEME:
			lex = "|"; break;
		case AbstractSememe.BIT_X_OR_OPERATOR_SEMEME:
			lex = "^"; break;
		case AbstractSememe.BIT_AND_ASSIGNMENT_SEMEME:
			lex = "&="; break;
		case AbstractSememe.BIT_OR_ASSIGNMENT_SEMEME:
			lex = "|="; break;
		case AbstractSememe.BIT_X_OR_ASSIGNMENT_SEMEME:
			lex = "^="; break;
		case AbstractSememe.MINUS_ASSIGNMENT_SEMEME:
			lex = "-="; break;
		case AbstractSememe.MODULO_ASSIGNMENT_SEMEME:
			lex = "%="; break;
		case AbstractSememe.PLUS_ASSIGNMENT_SEMEME:
			lex = "+="; break;
		case AbstractSememe.TIMES_ASSIGNMENT_SEMEME:
			lex = "*="; break;
		case AbstractSememe.DIVIDE_ASSIGNMENT_SEMEME:
			lex = "/="; break;
		case AbstractSememe.DIVIDE_SEMEME:
			lex = "/"; break;
		case AbstractSememe.PLUS_SEMEME:
			lex = "+"; break;
		case AbstractSememe.MINUS_SEMEME:
			lex = "-"; break;
		case AbstractSememe.TIMES_SEMEME:
			lex = "*"; break;
		case AbstractSememe.MODULO_SEMEME:
			lex = "%"; break;
		case AbstractSememe.EQUALS_SEMEME:
			lex = "=="; break;
		case AbstractSememe.GREATER_THAN_SEMEME:
			lex = ">"; break;
		case AbstractSememe.GREATER_OR_EQUALS_SEMEME:
			lex = ">="; break;
		case AbstractSememe.LESS_THAN_SEMEME:
			lex = "<"; break;
		case AbstractSememe.LESS_OR_EQUALS_SEMEME:
			lex = "<="; break;
		case AbstractSememe.NOT_EQUALS_SEMEME:
			lex = "!="; break;
		case AbstractSememe.LOGICAL_AND_SEMEME:
			lex = "&&"; break;
		case AbstractSememe.LOGICAL_OR_SEMEME:
			lex = "||"; break;
		case AbstractSememe.LOGICAL_NOT_SEMEME:
			lex = "!"; break;
		case AbstractSememe.POST_DECREMENT_SEMEME:
			lex = "<e>--"; break;
		case AbstractSememe.POST_INCREMENT_SEMEME:
			lex = "<e>++"; break;
		case AbstractSememe.PRE_DECREMENT_SEMEME:
			lex = "--<e>"; break;
		case AbstractSememe.PRE_INCREMENT_SEMEME:
			lex = "++<e>"; break;
		case AbstractSememe.SHIFT_LEFT_SEMEME:
			lex = "<<"; break;
		case AbstractSememe.SHIFT_RIGHT_SEMEME:
			lex = ">>"; break;
		case AbstractSememe.SHIFT_LEFT_ASSIGNMENT_SEMEME:
			lex = "<<="; break;
		case AbstractSememe.SHIFT_RIGHT_ASSIGNMENT_SEMEME:
			lex = ">>="; break;
		case AbstractSememe.UNSIGNED_SHIFT_RIGHT_SEMEME:
			lex = ">>>"; break;
		case AbstractSememe.UNSIGNED_SHIFT_RIGHT_ASSIGNMENT_SEMEME:
			lex = ">>>="; break;
		case AbstractSememe.KEYWORD_SEMEME:
			switch (s.getSememe()){
			case "TRY": lex = "try { }"; break;
			case "SWITCH": lex = "switch (<e>){ }"; break;
			case "FOR": lex = "for (<e>; <e>; <e>) { }"; break;
			case "IF": lex = "if (<e>) { }"; break;
			case "ELSE": lex = "else { }"; break;
			case "CASE": lex = "case <e>:"; break;
			case "DEFAULT": lex = "default:"; break;
			case "CATCH": lex = "catch (<e>) { }"; break;
			case "FINALLY": lex = "finally { }"; break;
			case "DO": lex = "do { }"; break;
			case "WHILE": lex = "while (<e>) { }"; break;
			default: lex = s.getSememe().toLowerCase();
			}
		}
		GlobalConfig.LEXEME_LOGGER.log(s.getSememe() + ": " + lex);
		return new Lexeme(lex);
	}

	private Lexeme associatedLexeme(TypeReference e) {
		String lex = e.getName();
		Lexeme result = new Lexeme(lex);
		GlobalConfig.LEXEME_LOGGER.log("Type reference: " + lex);
		return result;
	}

	private Lexeme associatedLexeme(ClassDeclaration e) {
		String lex = "class " + e.getName() + " {}";
		Lexeme result = new Lexeme(lex);
		GlobalConfig.LEXEME_LOGGER.log("Class declaration: " + lex);
		return result;
	}
	
	public String associatedLexemes(){
		return sememeContainer.associatedLexemes();
	}
}
