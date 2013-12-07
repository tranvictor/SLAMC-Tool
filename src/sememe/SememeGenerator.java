package sememe;

import java.util.ArrayList;
import java.util.List;


public class SememeGenerator implements ISememeGenerator{
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateClassDeclarationSememe()
	 */
	@Override
	public String generateClassDeclarationSememe() {
		return "CLASS";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateClassEndSememe()
	 */
	@Override
	public String generateClassEndSememe() {
		return "CLASSEND";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateClassBeginSememe()
	 */
	@Override
	public String generateClassBeginSememe() {
		return "CLASSBEGIN";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateAssignSememe()
	 */
	@Override
	public String generateAssignSememe() {
		return "OP[Assign]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateVariableSememe(java.lang.String)
	 */
	@Override
	public String generateVariableSememe(String type) {
		return String.format("VAR[%s]", type);
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateFieldSememe(java.lang.String, java.lang.String)
	 */
	@Override
	public String generateFieldSememe(String owner, String name) {
		return String.format("FIELD[%s,%s]", owner, name);
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateTypeSememe(java.lang.String)
	 */
	@Override
	public String generateTypeSememe(String type) {
		return String.format("TYPE[%s]", type);
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateMethodDeclarationSememe(java.lang.String, java.lang.String, java.util.List, java.lang.String)
	 */
	@Override
	public String generateMethodDeclarationSememe(String owner, String name,
			List<String> paraNames, String rettype) {
		String params = "PARA[";
		for (String p: paraNames){
			params += p + ",";
		}
		params += "]";
		String result = String.format("FUNC[%s,%s,%s,%s]", owner, name, params, rettype);
		System.out.println("Generate: MethodDeclaration:" + result);
		return result;
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateArrayAccessOperator()
	 */
	@Override
	public String generateArrayAccessOperator() {
		return "OP[ArrayAccess]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateBitAndOperatorSememe()
	 */
	@Override
	public String generateBitAndOperatorSememe() {
		return "OP[BitAnd]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateBitNotOperatorSememe()
	 */
	@Override
	public String generateBitNotOperatorSememe() {
		return "OP[BitNot]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateBitOrOperatorSememe()
	 */
	@Override
	public String generateBitOrOperatorSememe() {
		return "OP[BitOr]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateBitXOrOperatorSememe()
	 */
	@Override
	public String generateBitXOrOperatorSememe() {
		return "OP[BitXOr]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateBitAndAssignmentSememe()
	 */
	@Override
	public String generateBitAndAssignmentSememe() {
		return "OP[AndAssign]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateBitOrAssignmentSememe()
	 */
	@Override
	public String generateBitOrAssignmentSememe() {
		return "OP[OrAssign]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateBitXOrAssignmentSememe()
	 */
	@Override
	public String generateBitXOrAssignmentSememe() {
		return "OP[XOrAssign]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateBooleanLiteralSememe(boolean)
	 */
	@Override
	public String generateBooleanLiteralSememe(boolean value) {
		if (value) return "TRUE"; else return "FALSE";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateIntLiteralSememe(java.lang.String)
	 */
	@Override
	public String generateIntLiteralSememe(String value) {
		if (Integer.parseInt(value) == 0) return "ZERO";
		return "LIT[Int]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateCharLiteralSememe(java.lang.String)
	 */
	@Override
	public String generateCharLiteralSememe(String value) {
		if (value.equals("")) return "EMPTY";
		return "LIT[Char]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateStringLiteralSememe(java.lang.String)
	 */
	@Override
	public String generateStringLiteralSememe(String value) {
		if (value.equals("")) return "EMPTY";
		return "LIT[String]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateDoubleLiteralSememe(java.lang.String)
	 */
	@Override
	public String generateDoubleLiteralSememe(String value) {
		if (Double.parseDouble(value) == 0) return "ZERO";
		return "LIT[Double]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateFloatLiteralSememe(java.lang.String)
	 */
	@Override
	public String generateFloatLiteralSememe(String value) {
		if (Float.parseFloat(value) == 0) return "ZERO";
		return "LIT[Float]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateNullLiteralSememe()
	 */
	@Override
	public String generateNullLiteralSememe() {
		return "NULL";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateLongLiteralSememe(java.lang.String)
	 */
	@Override
	public String generateLongLiteralSememe(String value) {
		if (Long.parseLong(value) == 0) return "ZERO";
		return "LIT[Long]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateDivideAssignmentSememe()
	 */
	@Override
	public String generateDivideAssignmentSememe() {
		return "OP[DivAssign]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateMinusAssignmentSememe()
	 */
	@Override
	public String generateMinusAssignmentSememe() {
		return "OP[MinusAssign]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateModuloAssignmentSememe()
	 */
	@Override
	public String generateModuloAssignmentSememe() {
		return "OP[ModAssign]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generatePlusAssignmentSememe()
	 */
	@Override
	public String generatePlusAssignmentSememe() {
		return "OP[PlusAssign]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateTimesAssignmentSememe()
	 */
	@Override
	public String generateTimesAssignmentSememe() {
		return "OP[TimesAssign]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateKeywordSememe(java.lang.String)
	 */
	@Override
	public String generateKeywordSememe(String keyword) {
		return keyword.toUpperCase();
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateDoBegin()
	 */
	@Override
	public String generateDoBegin() {
		return "DoBegin";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateDoEnd()
	 */
	@Override
	public String generateDoEnd() {
		return "DoEnd";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateDivideSememe()
	 */
	@Override
	public String generateDivideSememe() {
		return "OP[Div]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generatePlusSememe()
	 */
	@Override
	public String generatePlusSememe() {
		return "OP[Plus]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateMinusSememe()
	 */
	@Override
	public String generateMinusSememe() {
		return "OP[Minus]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateTimesSememe()
	 */
	@Override
	public String generateTimesSememe() {
		return "OP[Times]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateModuloSememe()
	 */
	@Override
	public String generateModuloSememe() {
		return "OP[Mod]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateEqualsSememe()
	 */
	@Override
	public String generateEqualsSememe() {
		return "OP[Eq]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateGreaterThanSememe()
	 */
	@Override
	public String generateGreaterThanSememe() {
		return "OP[Gt]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateGreaterOrEqualsSememe()
	 */
	@Override
	public String generateGreaterOrEqualsSememe() {
		return "OP[Ge]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateLessThanSememe()
	 */
	@Override
	public String generateLessThanSememe() {
		return "OP[Lt]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateLessOrEqualsSememe()
	 */
	@Override
	public String generateLessOrEqualsSememe() {
		return "OP[Le]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateNotEqualsSememe()
	 */
	@Override
	public String generateNotEqualsSememe() {
		return "OP[Ne]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateLogicalAndSememe()
	 */
	@Override
	public String generateLogicalAndSememe() {
		return "OP[LogicAnd]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateLogicalOrSememe()
	 */
	@Override
	public String generateLogicalOrSememe() {
		return "OP[LogicOr]";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateLogicalNotSememe()
	 */
	@Override
	public String generateLogicalNotSememe() {
		return "OP[LogicNot]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generatePostDecrementSememe()
	 */
	@Override
	public String generatePostDecrementSememe() {
		return "OP[PostDec]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generatePostIncrementSememe()
	 */
	@Override
	public String generatePostIncrementSememe() {
		return "OP[PostInc]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generatePreDecrementSememe()
	 */
	@Override
	public String generatePreDecrementSememe() {
		return "OP[PreDec]";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generatePreIncrementSememe()
	 */
	@Override
	public String generatePreIncrementSememe() {
		return "OP[PreInc]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateShiftLeftSememe()
	 */
	@Override
	public String generateShiftLeftSememe() {
		return "OP[Shl]";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateShiftRightSememe()
	 */
	@Override
	public String generateShiftRightSememe() {
		return "OP[Shr]";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateShiftLeftAssignmentSememe()
	 */
	@Override
	public String generateShiftLeftAssignmentSememe() {
		return "OP[Shla]";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateShiftRightAssignmentSememe()
	 */
	@Override
	public String generateShiftRightAssignmentSememe() {
		return "OP[Shra]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateUnsignedShiftRightSememe()
	 */
	@Override
	public String generateUnsignedShiftRightSememe() {
		return "OP[Ushr]";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateUnsignedShiftRightAssignmentSememe()
	 */
	@Override
	public String generateUnsignedShiftRightAssignmentSememe() {
		return "OP[Ushra]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateTypeCastSememe(java.lang.String)
	 */
	@Override
	public String generateTypeCastSememe(String name) {
		return String.format("CAST[%s]", name);
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateTernarySememe()
	 */
	@Override
	public String generateTernarySememe() {
		return "OP[Ternay]";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateWhileBegin()
	 */
	@Override
	public String generateWhileBegin() {
		return "WhileBegin";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateWhileEnd()
	 */
	@Override
	public String generateWhileEnd() {
		return "WhileEnd";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateMethodReference(java.lang.String, java.lang.String, java.util.List, java.lang.String)
	 */
	@Override
	public String generateMethodReference(String owner, String name,
			List<Object> paramTypes, String rettype) {
		return String.format("CALL[%s,%s,%s,%s]", owner, name, paramTypes.size(), rettype);
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateForBegin()
	 */
	@Override
	public String generateForBegin() {
		return "ForBegin";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateForEnd()
	 */
	@Override
	public String generateForEnd() {
		return "ForEnd";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateIfBegin()
	 */
	@Override
	public String generateIfBegin() {
		return "IfBegin";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateIfEnd()
	 */
	@Override
	public String generateIfEnd() {
		return "IfEnd";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateElseBegin()
	 */
	@Override
	public String generateElseBegin() {
		return "ElseBegin";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateElseEnd()
	 */
	@Override
	public String generateElseEnd() {
		return "ElseEnd";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateConstructorReference(java.lang.String, java.util.ArrayList)
	 */
	@Override
	public String generateConstructorReference(String owner,
			ArrayList<String> paramTypes) {
		String params = "PARA[";
		for (String p : paramTypes){
			params += p + ",";
		}
		params += "]";
		return String.format("CONSTRUCT[%s,%s]", owner, params);
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateSwitchBegin()
	 */
	@Override
	public String generateSwitchBegin() {
		return "SwitchBegin";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateSwitchEnd()
	 */
	@Override
	public String generateSwitchEnd() {
		return "SwitchEnd";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateCatchBegin()
	 */
	@Override
	public String generateCatchBegin() {
		return "CatchBegin";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateCatchEnd()
	 */
	@Override
	public String generateCatchEnd() {
		return "CatchEnd";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateFinallyBegin()
	 */
	@Override
	public String generateFinallyBegin() {
		return "FinallyBegin";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateFinallyEnd()
	 */
	@Override
	public String generateFinallyEnd() {
		return "FinallyEnd";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateTryEnd()
	 */
	@Override
	public String generateTryEnd() {
		return "TryEnd";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateTryBegin()
	 */
	@Override
	public String generateTryBegin() {
		return "TryBegin";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateMethodBegin()
	 */
	@Override
	public String generateMethodBegin() {
		return "MethodBegin";
	}
	
	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generateMethodEnd()
	 */
	@Override
	public String generateMethodEnd() {
		return "MethodEnd";
	}

	/* (non-Javadoc)
	 * @see sememe.ISememeGenerator#generatePackageSememe(java.lang.String)
	 */
	@Override
	public String generatePackageSememe(String name) {
		return String.format("PACKAGE[%s]", name);
	}

	public String generatePositiveSememe() {
		return "OP[Pos]";
	}
	
	public String generateNegativeSememe() {
		return "OP[Neg]";
	}
}
