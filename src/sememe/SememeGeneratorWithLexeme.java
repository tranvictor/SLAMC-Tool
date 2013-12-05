package sememe;

import java.util.ArrayList;


public class SememeGeneratorWithLexeme implements ISememeGenerator{
	
	public String generateClassDeclarationSememe() {
		return "CLASS";
	}

	public String generateClassEndSememe() {
		return "CLASSEND";
	}

	public String generateClassBeginSememe() {
		return "CLASSBEGIN";
	}

	public String generateAssignSememe() {
		return "OP[Assign]";
	}

	public String generateVariableSememe(String type) {
		return String.format("VAR[%s]", type);
	}

	public String generateFieldSememe(String owner, String name) {
		return String.format("FIELD[%s,%s]", owner, name);
	}

	public String generateTypeSememe(String type) {
		return String.format("TYPE[%s]", type);
	}

	public String generateMethodDeclarationSememe(String owner, String name,
			ArrayList<String> paraNames, String rettype) {
		String params = "PARA[";
		for (String p: paraNames){
			params += p + ",";
		}
		params += "]";
		String result = String.format("FUNC[%s,%s,%s,%s]", owner, name, params, rettype);
		System.out.println("Generate: MethodDeclaration:" + result);
		return result;
	}

	public String generateArrayAccessOperator() {
		return "OP[ArrayAccess]";
	}

	public String generateBitAndOperatorSememe() {
		return "OP[BitAnd]";
	}

	public String generateBitNotOperatorSememe() {
		return "OP[BitNot]";
	}

	public String generateBitOrOperatorSememe() {
		return "OP[BitOr]";
	}

	public String generateBitXOrOperatorSememe() {
		return "OP[BitXOr]";
	}

	public String generateBitAndAssignmentSememe() {
		return "OP[AndAssign]";
	}

	public String generateBitOrAssignmentSememe() {
		return "OP[OrAssign]";
	}

	public String generateBitXOrAssignmentSememe() {
		return "OP[XOrAssign]";
	}

	public String generateBooleanLiteralSememe(boolean value) {
		if (value) return "TRUE"; else return "FALSE";
	}

	public String generateIntLiteralSememe(String value) {
		if (Integer.parseInt(value) == 0) return "ZERO";
		return "LIT[Int]";
	}

	public String generateCharLiteralSememe(String value) {
		if (value.equals("")) return "EMPTY";
		return "LIT[Char]";
	}

	public String generateStringLiteralSememe(String value) {
		if (value.equals("")) return "EMPTY";
		return "LIT[String]";
	}

	public String generateDoubleLiteralSememe(String value) {
		if (Double.parseDouble(value) == 0) return "ZERO";
		return "LIT[Double]";
	}

	public String generateFloatLiteralSememe(String value) {
		if (Float.parseFloat(value) == 0) return "ZERO";
		return "LIT[Float]";
	}

	public String generateNullLiteralSememe() {
		return "NULL";
	}

	public String generateLongLiteralSememe(String value) {
		if (Long.parseLong(value) == 0) return "ZERO";
		return "LIT[Long]";
	}

	public String generateDivideAssignmentSememe() {
		return "OP[DivAssign]";
	}

	public String generateMinusAssignmentSememe() {
		return "OP[MinusAssign]";
	}

	public String generateModuloAssignmentSememe() {
		return "OP[ModAssign]";
	}

	public String generatePlusAssignmentSememe() {
		return "OP[PlusAssign]";
	}

	public String generateTimesAssignmentSememe() {
		return "OP[TimesAssign]";
	}

	public String generateKeywordSememe(String keyword) {
		return keyword.toUpperCase();
	}

	public String generateDoBegin() {
		return "DoBegin";
	}
	
	public String generateDoEnd() {
		return "DoEnd";
	}

	public String generateDivideSememe() {
		return "OP[Div]";
	}

	public String generatePlusSememe() {
		return "OP[Plus]";
	}

	public String generateMinusSememe() {
		return "OP[Minus]";
	}

	public String generateTimesSememe() {
		return "OP[Times]";
	}

	public String generateModuloSememe() {
		return "OP[Mod]";
	}

	public String generateEqualsSememe() {
		return "OP[Eq]";
	}

	public String generateGreaterThanSememe() {
		return "OP[Gt]";
	}

	public String generateGreaterOrEqualsSememe() {
		return "OP[Ge]";
	}

	public String generateLessThanSememe() {
		return "OP[Lt]";
	}

	public String generateLessOrEqualsSememe() {
		return "OP[Le]";
	}

	public String generateNotEqualsSememe() {
		return "OP[Ne]";
	}

	public String generateLogicalAndSememe() {
		return "OP[LogicAnd]";
	}

	public String generateLogicalOrSememe() {
		return "OP[LogicOr]";
	}
	
	public String generateLogicalNotSememe() {
		return "OP[LogicNot]";
	}

	public String generatePostDecrementSememe() {
		return "OP[PostDec]";
	}

	public String generatePostIncrementSememe() {
		return "OP[PostInc]";
	}

	public String generatePreDecrementSememe() {
		return "OP[PreDec]";
	}
	
	public String generatePreIncrementSememe() {
		return "OP[PreInc]";
	}

	public String generateShiftLeftSememe() {
		return "OP[Shl]";
	}
	
	public String generateShiftRightSememe() {
		return "OP[Shr]";
	}
	
	public String generateShiftLeftAssignmentSememe() {
		return "OP[Shla]";
	}
	
	public String generateShiftRightAssignmentSememe() {
		return "OP[Shra]";
	}

	public String generateUnsignedShiftRightSememe() {
		return "OP[Ushr]";
	}
	
	public String generateUnsignedShiftRightAssignmentSememe() {
		return "OP[Ushra]";
	}

	public String generateTypeCastSememe(String name) {
		return String.format("CAST[%s]", name);
	}

	public String generateTernarySememe() {
		return "OP[Ternay]";
	}

	public String generateWhileBegin() {
		return "WhileBegin";
	}

	public String generateWhileEnd() {
		return "WhileEnd";
	}

	public String generateMethodReference(String owner, String name,
			ArrayList<String> paramTypes, String rettype) {
		return String.format("CALL[%s,%s,%s,%s]", owner, name, paramTypes.size(), rettype);
	}

	public String generateForBegin() {
		return "ForBegin";
	}
	
	public String generateForEnd() {
		return "ForEnd";
	}

	public String generateIfBegin() {
		return "IfBegin";
	}
	
	public String generateIfEnd() {
		return "IfEnd";
	}
	
	public String generateElseBegin() {
		return "ElseBegin";
	}
	
	public String generateElseEnd() {
		return "ElseEnd";
	}

	public String generateConstructorReference(String owner,
			ArrayList<String> paramTypes) {
		String params = "PARA[";
		for (String p : paramTypes){
			params += p + ",";
		}
		params += "]";
		return String.format("CONSTRUCT[%s,%s]", owner, params);
	}

	public String generateSwitchBegin() {
		return "SwitchBegin";
	}
	
	public String generateSwitchEnd() {
		return "SwitchEnd";
	}

	public String generateCatchBegin() {
		return "CatchBegin";
	}
	
	public String generateCatchEnd() {
		return "CatchEnd";
	}

	public String generateFinallyBegin() {
		return "FinallyBegin";
	}
	
	public String generateFinallyEnd() {
		return "FinallyEnd";
	}

	public String generateTryEnd() {
		return "TryEnd";
	}
	
	public String generateTryBegin() {
		return "TryBegin";
	}

	public String generateMethodBegin() {
		return "MethodBegin";
	}
	
	public String generateMethodEnd() {
		return "MethodEnd";
	}
}
