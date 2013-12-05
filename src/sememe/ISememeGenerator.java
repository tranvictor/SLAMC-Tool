package sememe;

import java.util.ArrayList;

public interface ISememeGenerator {
	public String generateArrayAccessOperator();

	public String generateAssignSememe();

	public String generateBitAndAssignmentSememe();

	public String generateBitAndOperatorSememe();

	public String generateBitNotOperatorSememe();

	public String generateBitOrAssignmentSememe();

	public String generateBitOrOperatorSememe();

	public String generateBitXOrAssignmentSememe();

	public String generateBitXOrOperatorSememe();

	public String generateBooleanLiteralSememe(boolean value);

	public String generateCatchBegin();

	public String generateCatchEnd();

	public String generateCharLiteralSememe(String value);

	public String generateClassBeginSememe();

	public String generateClassDeclarationSememe();

	public String generateClassEndSememe();

	public String generateConstructorReference(String owner,
			ArrayList<String> paramTypes);

	public String generateDivideAssignmentSememe();

	public String generateDivideSememe();

	public String generateDoBegin();

	public String generateDoEnd();

	public String generateDoubleLiteralSememe(String value);

	public String generateElseBegin();

	public String generateElseEnd();

	public String generateEqualsSememe();

	public String generateFieldSememe(String owner, String name);

	public String generateFinallyBegin();

	public String generateFinallyEnd();

	public String generateFloatLiteralSememe(String value);

	public String generateForBegin();

	public String generateForEnd();

	public String generateGreaterOrEqualsSememe();

	public String generateGreaterThanSememe();

	public String generateIfBegin();

	public String generateIfEnd();

	public String generateIntLiteralSememe(String value);

	public String generateKeywordSememe(String keyword);

	public String generateLessOrEqualsSememe();

	public String generateLessThanSememe();

	public String generateLogicalAndSememe();

	public String generateLogicalNotSememe();

	public String generateLogicalOrSememe();

	public String generateLongLiteralSememe(String value);

	public String generateMethodDeclarationSememe(String owner, String name,
			ArrayList<String> paraNames, String rettype);

	public String generateMethodReference(String owner, String name,
			ArrayList<String> paramTypes, String rettype);

	public String generateMinusAssignmentSememe();

	public String generateMinusSememe();

	public String generateModuloAssignmentSememe();

	public String generateModuloSememe();

	public String generateNotEqualsSememe();

	public String generateNullLiteralSememe();

	public String generatePlusAssignmentSememe();

	public String generatePlusSememe();

	public String generatePostDecrementSememe();

	public String generatePostIncrementSememe();

	public String generatePreDecrementSememe();

	public String generatePreIncrementSememe();

	public String generateShiftLeftAssignmentSememe();

	public String generateShiftLeftSememe();

	public String generateShiftRightAssignmentSememe();

	public String generateShiftRightSememe();

	public String generateStringLiteralSememe(String value);

	public String generateSwitchBegin();

	public String generateSwitchEnd();

	public String generateTernarySememe();

	public String generateTimesAssignmentSememe();

	public String generateTimesSememe();

	public String generateTryBegin();

	public String generateTryEnd();

	public String generateTypeCastSememe(String name);

	public String generateTypeSememe(String type);

	public String generateUnsignedShiftRightAssignmentSememe();

	public String generateUnsignedShiftRightSememe();

	public String generateVariableSememe(String type);

	public String generateWhileBegin();

	public String generateWhileEnd();
}
