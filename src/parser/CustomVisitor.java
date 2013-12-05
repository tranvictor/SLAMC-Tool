package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import config.GlobalConfig;

import recoder.CrossReferenceServiceConfiguration;
import recoder.abstraction.ClassType;
import recoder.abstraction.Type;
import recoder.java.CompilationUnit;
import recoder.java.Expression;
import recoder.java.LoopInitializer;
import recoder.java.ProgramElement;
import recoder.java.SourceVisitor;
import recoder.java.Statement;
import recoder.java.StatementBlock;
import recoder.java.declaration.AnnotationUseSpecification;
import recoder.java.declaration.ClassDeclaration;
import recoder.java.declaration.FieldDeclaration;
import recoder.java.declaration.FieldSpecification;
import recoder.java.declaration.LocalVariableDeclaration;
import recoder.java.declaration.MemberDeclaration;
import recoder.java.declaration.MethodDeclaration;
import recoder.java.declaration.ParameterDeclaration;
import recoder.java.declaration.VariableSpecification;
import recoder.java.expression.ArrayInitializer;
import recoder.java.expression.ElementValueArrayInitializer;
import recoder.java.expression.ParenthesizedExpression;
import recoder.java.expression.literal.BooleanLiteral;
import recoder.java.expression.literal.CharLiteral;
import recoder.java.expression.literal.DoubleLiteral;
import recoder.java.expression.literal.FloatLiteral;
import recoder.java.expression.literal.IntLiteral;
import recoder.java.expression.literal.LongLiteral;
import recoder.java.expression.literal.NullLiteral;
import recoder.java.expression.literal.StringLiteral;
import recoder.java.expression.operator.BinaryAnd;
import recoder.java.expression.operator.BinaryAndAssignment;
import recoder.java.expression.operator.BinaryNot;
import recoder.java.expression.operator.BinaryOr;
import recoder.java.expression.operator.BinaryOrAssignment;
import recoder.java.expression.operator.BinaryXOr;
import recoder.java.expression.operator.BinaryXOrAssignment;
import recoder.java.expression.operator.Conditional;
import recoder.java.expression.operator.CopyAssignment;
import recoder.java.expression.operator.Divide;
import recoder.java.expression.operator.DivideAssignment;
import recoder.java.expression.operator.Equals;
import recoder.java.expression.operator.GreaterOrEquals;
import recoder.java.expression.operator.GreaterThan;
import recoder.java.expression.operator.Instanceof;
import recoder.java.expression.operator.LessOrEquals;
import recoder.java.expression.operator.LessThan;
import recoder.java.expression.operator.LogicalAnd;
import recoder.java.expression.operator.LogicalNot;
import recoder.java.expression.operator.LogicalOr;
import recoder.java.expression.operator.Minus;
import recoder.java.expression.operator.MinusAssignment;
import recoder.java.expression.operator.Modulo;
import recoder.java.expression.operator.ModuloAssignment;
import recoder.java.expression.operator.Negative;
import recoder.java.expression.operator.New;
import recoder.java.expression.operator.NewArray;
import recoder.java.expression.operator.NotEquals;
import recoder.java.expression.operator.Plus;
import recoder.java.expression.operator.PlusAssignment;
import recoder.java.expression.operator.Positive;
import recoder.java.expression.operator.PostDecrement;
import recoder.java.expression.operator.PostIncrement;
import recoder.java.expression.operator.PreDecrement;
import recoder.java.expression.operator.PreIncrement;
import recoder.java.expression.operator.ShiftLeft;
import recoder.java.expression.operator.ShiftLeftAssignment;
import recoder.java.expression.operator.ShiftRight;
import recoder.java.expression.operator.ShiftRightAssignment;
import recoder.java.expression.operator.Times;
import recoder.java.expression.operator.TimesAssignment;
import recoder.java.expression.operator.TypeCast;
import recoder.java.expression.operator.UnsignedShiftRight;
import recoder.java.expression.operator.UnsignedShiftRightAssignment;
import recoder.java.reference.ArrayReference;
import recoder.java.reference.EnumConstructorReference;
import recoder.java.reference.FieldReference;
import recoder.java.reference.MetaClassReference;
import recoder.java.reference.MethodReference;
import recoder.java.reference.ReferencePrefix;
import recoder.java.reference.SpecialConstructorReference;
import recoder.java.reference.SuperConstructorReference;
import recoder.java.reference.ThisReference;
import recoder.java.reference.TypeReference;
import recoder.java.reference.VariableReference;
import recoder.java.statement.Assert;
import recoder.java.statement.Branch;
import recoder.java.statement.Break;
import recoder.java.statement.Case;
import recoder.java.statement.Catch;
import recoder.java.statement.Continue;
import recoder.java.statement.Default;
import recoder.java.statement.Do;
import recoder.java.statement.Else;
import recoder.java.statement.EnhancedFor;
import recoder.java.statement.Finally;
import recoder.java.statement.For;
import recoder.java.statement.If;
import recoder.java.statement.Return;
import recoder.java.statement.Switch;
import recoder.java.statement.SynchronizedBlock;
import recoder.java.statement.Then;
import recoder.java.statement.Throw;
import recoder.java.statement.Try;
import recoder.java.statement.While;
import recoder.list.generic.ASTList;
import recoder.service.SourceInfo;
import sememe.AbstractSememe;
import sememe.SememeWithLexeme;
import sememe.SememeGeneratorWithLexeme;

public class CustomVisitor extends SourceVisitor {
	private SememeGeneratorWithLexeme generator = new SememeGeneratorWithLexeme();
	//private CrossReferenceServiceConfiguration crsc;
	private SourceInfo si;
	private List<AbstractSememe> sequence = new ArrayList<AbstractSememe>();
	private Stack<ClassDeclaration> classStack = new Stack<ClassDeclaration>();
	
	public CustomVisitor(CrossReferenceServiceConfiguration crsc) {
		//this.crsc = crsc;
		this.si = crsc.getSourceInfo();
	}

	public void visitCompilationUnit(CompilationUnit cu){
		System.out.println("Parsing in File " + cu.getName());
		for (int i=0; i<cu.getChildCount(); i++){
			ProgramElement temp = cu.getChildAt(i);
			if (temp instanceof ClassDeclaration){
				this.visitClassDeclaration((ClassDeclaration) temp);
			}
		}
	}
	
	public void visitClassDeclaration(ClassDeclaration cd){
		System.out.println("Parsing in class " + cd.getFullName());
		classStack.push(cd);
		sequence.add(new SememeWithLexeme(generator.generateClassDeclarationSememe(), cd.getStartPosition()));
		sequence.add(new SememeWithLexeme(generator.generateClassBeginSememe(), cd.getStartPosition()));
		for (MemberDeclaration c : cd.getMembers()){
			if (c instanceof FieldDeclaration){
				this.visitFieldDeclaration((FieldDeclaration) c);
			} else if (c instanceof MethodDeclaration) {
				this.visitMethodDeclaration((MethodDeclaration) c);
			} else {
				GlobalConfig.LOGGER.log("Unimplemented handler for Class's member: " + c.getClass());
			}
		}
		sequence.add(new SememeWithLexeme(generator.generateClassEndSememe(), cd.getEndPosition()));
		classStack.pop();
	}
	
	public void visitFieldDeclaration(FieldDeclaration fd){
		System.out.println(fd.toSource());
		ClassDeclaration owner = classStack.peek();
		TypeReference type = fd.getTypeReference();
		List<FieldSpecification> variables = fd.getVariables();
		sequence.add(new SememeWithLexeme(generator.generateTypeSememe(type.getName()), fd.getStartPosition()));
		for (FieldSpecification v : variables){
			System.out.println(v.toSource());
			System.out.println(v.getExpressionCount());
			sequence.add(new SememeWithLexeme(generator.generateFieldSememe(owner.getName(), v.getName()), fd.getStartPosition()));
			if (v.getExpressionCount() == 1){
				sequence.add(new SememeWithLexeme(generator.generateAssignSememe(), fd.getStartPosition()));
				this.visitExpression(v.getExpressionAt(0));
			} else if (v.getExpressionCount() > 1){
				GlobalConfig.LOGGER.log("Unhandled field declaration initialization: " + v.getClass() + "|" + v.toSource());
			}
		}
	}
	
	public void visitMethodDeclaration(MethodDeclaration md){
		System.out.println("Parsing in method " + md.getFullName());
		System.out.println("Position: " + md.getStartPosition());
		ClassType owner = md.getContainingClassType();
		String name = md.getName();
		ASTList<ParameterDeclaration> params = md.getParameters();
		ArrayList<String> paraNames = new ArrayList<String>();
		for (ParameterDeclaration p : params){
			paraNames.add(p.getTypeReference().getName());
		}
		Type returnType = md.getReturnType();
		String rettype = "void";
		if (returnType != null) rettype = returnType.getName();
		sequence.add(new SememeWithLexeme(generator.generateMethodDeclarationSememe(owner.getName(), 
				name, 
				paraNames, 
				rettype), md.getStartPosition()));
		//sequence.add(new SememeWithLexeme(generator.generateMethodBegin(), md.getStartPosition()));
		StatementBlock body = md.getBody();
		this.visitStatementBlock(body);
		//sequence.add(new SememeWithLexeme(generator.generateMethodEnd(), md.getEndPosition()));
	}
	
	public void visitStatement(Statement s){
		System.out.println("Position: " + s.getStartPosition());
		if (s instanceof Assert){
		    this.visitAssert((Assert) s);
		} else 
		if (s instanceof BinaryAndAssignment){
		    this.visitBinaryAndAssignment((BinaryAndAssignment) s);
		} else 
		if (s instanceof BinaryOrAssignment){
		    this.visitBinaryOrAssignment((BinaryOrAssignment) s);
		} else 
		if (s instanceof BinaryXOrAssignment){
		    this.visitBinaryXOrAssignment((BinaryXOrAssignment) s);
		} else 
		if (s instanceof Break){
		    this.visitBreak((Break) s);
		} else 
		if (s instanceof ClassDeclaration){
		    this.visitClassDeclaration((ClassDeclaration) s);
		} else 
		if (s instanceof Continue){
		    this.visitContinue((Continue) s);
		} else 
		if (s instanceof CopyAssignment){
		    this.visitCopyAssignment((CopyAssignment)s);
		} else 
		if (s instanceof DivideAssignment){
		    this.visitDivideAssignment((DivideAssignment) s);
		} else 
		if (s instanceof Do){
		    this.visitDo((Do) s);
		} else 
		if (s instanceof EnhancedFor){
		    this.visitEnhancedFor((EnhancedFor) s);
		} else 
		if (s instanceof EnumConstructorReference){
		    //TODO: unimplemented
		} else 
		if (s instanceof For){
		    this.visitFor((For) s);
		} else 
		if (s instanceof If){
		    this.visitIf((If) s);
		} else 
//		if (s instanceof LabeledStatement){
//		    //TODO: unimplemented
//		} else 
//		if (s instanceof LabelJumpStatement){
//		    //TODO: unimplemented
//		} else 
		if (s instanceof LocalVariableDeclaration){
		    this.visitLocalVariableDeclaration((LocalVariableDeclaration) s);
		} else 
		if (s instanceof MethodReference){
		    this.visitMethodReference((MethodReference) s);
		} else 
		if (s instanceof MinusAssignment){
		    this.visitMinusAssignment((MinusAssignment) s);
		} else 
		if (s instanceof ModuloAssignment){
		    this.visitModuloAssignment((ModuloAssignment) s);
		} else 
		if (s instanceof New){
		    this.visitNew((New) s);
		} else 
		if (s instanceof ParenthesizedExpression){
		    this.visitParenthesizedExpression((ParenthesizedExpression) s);
		} else 
		if (s instanceof PlusAssignment){
		    this.visitPlusAssignment((PlusAssignment) s);
		} else 
		if (s instanceof PostDecrement){
		    this.visitPostDecrement((PostDecrement) s);
		} else 
		if (s instanceof PostIncrement){
			this.visitPostIncrement((PostIncrement) s);
		} else 
		if (s instanceof PreDecrement){
			this.visitPreDecrement((PreDecrement) s);
		} else 
		if (s instanceof PreIncrement){
			this.visitPreIncrement((PreIncrement) s);
		} else 
		if (s instanceof Return){
		    this.visitReturn((Return) s);
		} else 
		if (s instanceof ShiftLeftAssignment){
		    this.visitShiftLeftAssignment((ShiftLeftAssignment) s);
		} else 
		if (s instanceof ShiftRightAssignment){
		    this.visitShiftRightAssignment((ShiftRightAssignment) s);
		} else 
		if (s instanceof StatementBlock){
		    this.visitStatementBlock((StatementBlock) s);
		} else 
		if (s instanceof SpecialConstructorReference){
		    this.visitSpecialConstructorReference((SpecialConstructorReference) s);
		} else 
		if (s instanceof Switch){
		    this.visitSwitch((Switch) s);
		} else 
		if (s instanceof SynchronizedBlock){
		    //TODO: unimplemented
		} else 
		if (s instanceof Throw){
		    //TODO: unimplemented
		} else 
		if (s instanceof TimesAssignment){
		    this.visitTimesAssignment((TimesAssignment) s);
		} else 
		if (s instanceof Try){
		    this.visitTry((Try) s);
		} else 
		if (s instanceof UnsignedShiftRightAssignment){
		    this.visitUnsignedShiftRightAssignment((UnsignedShiftRightAssignment) s);
		} else 
		if (s instanceof While){
		    this.visitWhile((While) s);
		} else {
			GlobalConfig.LOGGER.log("Unhandled statement " + s.getClass());
		}
	}
	
	public void visitTry(Try t){
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("try"), t.getStartPosition()));
		StatementBlock body = t.getBody();
		//sequence.add(new SememeWithLexeme(generator.generateTryBegin(), t.getStartPosition()));
		this.visitStatementBlock(body);
		//sequence.add(new SememeWithLexeme(generator.generateTryEnd(), t.getEndPosition()));
		ASTList<Branch> branches = t.getBranchList();
		if (branches != null){
			for (Branch b : branches){
				this.visitBranch(b);
			}
		}
	}
	
	public void visitSwitch(Switch s){
		System.out.println(s.toSource());
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("switch"), s.getStartPosition()));
		Expression comparer = s.getExpressionAt(0);
		this.visitExpression(comparer);
		//sequence.add(new SememeWithLexeme(generator.generateSwitchBegin(), s.getStartPosition()));
		ASTList<Branch> branches = s.getBranchList();
		for (Branch b : branches){
			this.visitBranch(b);
		}
		//sequence.add(new SememeWithLexeme(generator.generateSwitchEnd(), s.getEndPosition()));
	}
	
	public void visitBranch(Branch b){
		if (b instanceof Case){
			this.visitCase((Case) b);
		} else
		if (b instanceof Catch){
			this.visitCatch((Catch) b);
		} else
		if (b instanceof Default){
			this.visitDefault((Default) b);
		} else
		if (b instanceof Else){
			this.visitElse((Else) b);
		} else 
		if (b instanceof Finally){
			this.visitFinally((Finally) b);
		} else
		if (b instanceof Then) {
			this.visitThen((Then) b);
		}
	}
	
	public void visitStatementBlock(StatementBlock sb){
		ASTList<Statement> statements = sb.getBody();
		for (Statement s : statements){
			System.out.println(s.getClass());
			this.visitStatement(s);
		}
	}
	
	public void visitCopyAssignment(CopyAssignment ca){
		ASTList<Expression> expressions = ca.getArguments();
		System.out.println(expressions);
		Expression left = expressions.get(0);
		Expression right = expressions.get(1);
		if (expressions.size() > 2)
			GlobalConfig.LOGGER.log("Unexpected case: CopyAssignment has " + expressions.size() + " arguments");
		this.visitExpression(left);
		sequence.add(new SememeWithLexeme(generator.generateAssignSememe(), ca.getStartPosition()));
		this.visitExpression(right);
	}	
	
	private void visitExpression(Expression exp) {
		System.out.println("Line number: " + exp.getStartPosition().getLine());
		if (exp instanceof AnnotationUseSpecification){
		} else
		if (exp instanceof ArrayInitializer){
		} else
		if (exp instanceof ArrayReference){
		    this.visitArrayReference((ArrayReference) exp);
		} else
		if (exp instanceof BinaryAnd){
		    this.visitBinaryAnd((BinaryAnd) exp);
		} else
		if (exp instanceof BinaryAndAssignment){
		    this.visitBinaryAndAssignment((BinaryAndAssignment) exp);
		} else
		if (exp instanceof BinaryNot){
		    this.visitBinaryNot((BinaryNot) exp);
		} else
		if (exp instanceof BinaryOr){
		    this.visitBinaryOr((BinaryOr) exp);
		} else
		if (exp instanceof BinaryOrAssignment){
		    this.visitBinaryOrAssignment((BinaryOrAssignment) exp);
		} else
		if (exp instanceof BinaryXOr){
		    this.visitBinaryXOr((BinaryXOr) exp);
		} else
		if (exp instanceof BinaryXOrAssignment){
		    this.visitBinaryXOrAssignment((BinaryXOrAssignment) exp);
		} else
		if (exp instanceof BooleanLiteral){
		    this.visitBooleanLiteral((BooleanLiteral) exp);
		} else
		if (exp instanceof CharLiteral){
		    this.visitCharLiteral((CharLiteral) exp);
		} else
		if (exp instanceof Conditional){
		    this.visitConditional((Conditional) exp);
		} else
		if (exp instanceof CopyAssignment){
		    this.visitCopyAssignment((CopyAssignment) exp);
		} else
		if (exp instanceof Divide){
		    this.visitDivide((Divide) exp);
		} else
		if (exp instanceof DivideAssignment){
		    this.visitDivideAssignment((DivideAssignment) exp);
		} else
		if (exp instanceof DoubleLiteral){
		    this.visitDoubleLiteral((DoubleLiteral) exp);
		} else
		if (exp instanceof ElementValueArrayInitializer){
		} else
		if (exp instanceof Equals){
		    this.visitEquals((Equals) exp);
		} else
		if (exp instanceof FieldReference){
			this.visitFieldReference((FieldReference) exp);
		} else
		if (exp instanceof FloatLiteral){
		    this.visitFloatLiteral((FloatLiteral) exp);
		} else
		if (exp instanceof GreaterOrEquals){
		    this.visitGreaterOrEquals((GreaterOrEquals) exp);
		} else
		if (exp instanceof GreaterThan){
		    this.visitGreaterThan((GreaterThan) exp);
		} else
		if (exp instanceof Instanceof){
		    this.visitInstanceof((Instanceof) exp);
		} else
		if (exp instanceof IntLiteral){
		    this.visitIntLiteral((IntLiteral) exp);
		} else
		if (exp instanceof LessOrEquals){
		    this.visitLessOrEquals((LessOrEquals) exp);
		} else
		if (exp instanceof LessThan){
		    this.visitLessThan((LessThan) exp);
		} else
		if (exp instanceof LogicalAnd){
		    this.visitLogicalAnd((LogicalAnd) exp);
		} else
		if (exp instanceof LogicalNot){
		    this.visitLogicalNot((LogicalNot) exp);
		} else
		if (exp instanceof LogicalOr){
		    this.visitLogicalOr((LogicalOr) exp);
		} else
		if (exp instanceof LongLiteral){
		    this.visitLongLiteral((LongLiteral) exp);
		} else
		if (exp instanceof MetaClassReference){
		    //TODO: unimplemented
		} else
		if (exp instanceof MethodReference){
		    this.visitMethodReference((MethodReference) exp);
		} else
		if (exp instanceof Minus){
		    this.visitMinus((Minus) exp);
		} else
		if (exp instanceof MinusAssignment){
		    this.visitMinusAssignment((MinusAssignment) exp);
		} else
		if (exp instanceof Modulo){
		    this.visitModulo((Modulo) exp);
		} else
		if (exp instanceof ModuloAssignment){
		    this.visitModuloAssignment((ModuloAssignment) exp);
		} else
		if (exp instanceof Negative){
		    this.visitNegative((Negative) exp);
		} else
		if (exp instanceof New){
		    this.visitNew((New) exp);
		} else
		if (exp instanceof NewArray){
			//TODO:
		} else
		if (exp instanceof NotEquals){
		    this.visitNotEquals((NotEquals) exp);
		} else
		if (exp instanceof NullLiteral){
		    this.visitNullLiteral((NullLiteral) exp);
		} else
		if (exp instanceof ParenthesizedExpression){
		    this.visitParenthesizedExpression((ParenthesizedExpression) exp);
		} else
		if (exp instanceof Plus){
		    this.visitPlus((Plus) exp);
		} else
		if (exp instanceof PlusAssignment){
		    this.visitPlusAssignment((PlusAssignment) exp);
		} else
		if (exp instanceof Positive){
		    this.visitPositive((Positive) exp);
		} else
		if (exp instanceof PostDecrement){
		    this.visitPostDecrement((PostDecrement) exp);
		} else
		if (exp instanceof PostIncrement){
		    this.visitPostIncrement((PostIncrement) exp);
		} else
		if (exp instanceof PreDecrement){
		    this.visitPreDecrement((PreDecrement) exp);
		} else
		if (exp instanceof PreIncrement){
		    this.visitPreIncrement((PreIncrement) exp);
		} else
		if (exp instanceof ShiftLeft){
		    this.visitShiftLeft((ShiftLeft) exp);
		} else
		if (exp instanceof ShiftLeftAssignment){
		    this.visitShiftLeftAssignment((ShiftLeftAssignment) exp);
		} else
		if (exp instanceof ShiftRight){
		    this.visitShiftRight((ShiftRight) exp);
		} else
		if (exp instanceof ShiftRightAssignment){
		    this.visitShiftRightAssignment((ShiftRightAssignment) exp);
		} else
		if (exp instanceof StringLiteral){
		    this.visitStringLiteral((StringLiteral) exp);
		} else
		if (exp instanceof Times){
		    this.visitTimes((Times) exp);
		} else
		if (exp instanceof TimesAssignment){
		    this.visitTimesAssignment((TimesAssignment) exp);
		} else
		if (exp instanceof TypeCast){
		    this.visitTypeCast((TypeCast) exp);
		} else
		if (exp instanceof UnsignedShiftRight){
		    this.visitUnsignedShiftRight((UnsignedShiftRight) exp);
		} else
		if (exp instanceof UnsignedShiftRightAssignment){
		    this.visitUnsignedShiftRightAssignment((UnsignedShiftRightAssignment) exp);
		} else
		if (exp instanceof VariableReference){
		    this.visitVariableReference((VariableReference) exp);
		} else {
		    GlobalConfig.LOGGER.log("Unhandled expression:" + exp.getClass());
		}
	}
	
	public void visitArrayReference(ArrayReference ar){
		System.out.println(ar.toSource());
		if (ar.getExpressionCount() != 2) {
			GlobalConfig.LOGGER.log("ArrayReference doesnt have 2 expressions:");
			GlobalConfig.LOGGER.log(ar.toSource());
		}
		this.visitExpression(ar.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateArrayAccessOperator(), ar.getStartPosition()));
		this.visitExpression(ar.getExpressionAt(1));
	}
	
	
	public void visitVariableReference(VariableReference vr){
	    sequence.add(new SememeWithLexeme(generator.generateVariableSememe(this.si.getType(vr).getName()), vr.getStartPosition()));
	}
	
	public void visitFieldReference(FieldReference fr){
		ClassDeclaration owner = classStack.peek();
	    System.out.println(owner);
	    sequence.add(new SememeWithLexeme(generator.generateFieldSememe(owner.getName(), fr.getName()), fr.getStartPosition()));
	}
	
	public void visitBinaryAnd(BinaryAnd ba){
		System.out.println(ba.toSource());
		System.out.println(ba.getArguments());
		this.visitExpression(ba.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateBitAndOperatorSememe(), ba.getStartPosition()));
		this.visitExpression(ba.getExpressionAt(1));
	}
	
	public void visitBinaryNot(BinaryNot bn){
		System.out.println(bn.toSource());
		System.out.println(bn.getArguments());
		sequence.add(new SememeWithLexeme(generator.generateBitNotOperatorSememe(), bn.getStartPosition()));
		this.visitExpression(bn.getExpressionAt(0));
	}
	
	public void visitBinaryOr(BinaryOr bo){
		System.out.println(bo.toSource());
		System.out.println(bo.getArguments());
		this.visitExpression(bo.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateBitOrOperatorSememe(), bo.getStartPosition()));
		this.visitExpression(bo.getExpressionAt(1));
	}
	
	public void visitBinaryXOr(BinaryXOr bx){
		System.out.println(bx.toSource());
		System.out.println(bx.getArguments());
		this.visitExpression(bx.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateBitXOrOperatorSememe(), bx.getStartPosition()));
		this.visitExpression(bx.getExpressionAt(1));
	}
	
	public void visitBinaryAndAssignment(BinaryAndAssignment baa){
		System.out.println(baa.toSource());
		System.out.println(baa.getArguments());
		this.visitExpression(baa.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateBitAndAssignmentSememe(), baa.getStartPosition()));
		this.visitExpression(baa.getExpressionAt(1));
	}
	
	public void visitBinaryOrAssignment(BinaryOrAssignment boa){
		System.out.println(boa.toSource());
		System.out.println(boa.getArguments());
		this.visitExpression(boa.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateBitOrAssignmentSememe(), boa.getStartPosition()));
		this.visitExpression(boa.getExpressionAt(1));
	}
	
	public void visitBinaryXOrAssignment(BinaryXOrAssignment boa){
		System.out.println(boa.toSource());
		System.out.println(boa.getArguments());
		this.visitExpression(boa.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateBitXOrAssignmentSememe(), boa.getStartPosition()));
		this.visitExpression(boa.getExpressionAt(1));
	}
	
	public void visitDivideAssignment(DivideAssignment da){
		System.out.println(da.toSource());
		System.out.println(da.getArguments());
		this.visitExpression(da.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateDivideAssignmentSememe(), da.getStartPosition()));
		this.visitExpression(da.getExpressionAt(1));
	}
	
	public void visitMinusAssignment(MinusAssignment ma){
		System.out.println(ma.toSource());
		System.out.println(ma.getArguments());
		this.visitExpression(ma.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateMinusAssignmentSememe(), ma.getStartPosition()));
		this.visitExpression(ma.getExpressionAt(1));
	}
	
	public void visitModuloAssignment(ModuloAssignment ma){
		System.out.println(ma.toSource());
		System.out.println(ma.getArguments());
		this.visitExpression(ma.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateModuloAssignmentSememe(), ma.getStartPosition()));
		this.visitExpression(ma.getExpressionAt(1));
	}
	
	public void visitPlusAssignment(PlusAssignment pa){
		System.out.println(pa.toSource());
		System.out.println(pa.getArguments());
		this.visitExpression(pa.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generatePlusAssignmentSememe(), pa.getStartPosition()));
		this.visitExpression(pa.getExpressionAt(1));
	}
	
	public void visitTimesAssignment(TimesAssignment da){
		System.out.println(da.toSource());
		System.out.println(da.getArguments());
		this.visitExpression(da.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateTimesAssignmentSememe(), da.getStartPosition()));
		this.visitExpression(da.getExpressionAt(1));
	}
	
	public void visitDivide(Divide d){
		System.out.println(d.toSource());
		System.out.println(d);
		this.visitExpression(d.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateDivideSememe(), d.getStartPosition()));
		this.visitExpression(d.getExpressionAt(1));
	}
	
	public void visitPlus(Plus p){
		System.out.println(p.toSource());
		System.out.println(p);
		this.visitExpression(p.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generatePlusSememe(), p.getStartPosition()));
		this.visitExpression(p.getExpressionAt(1));
	}
	
	public void visitMinus(Minus m){
		System.out.println(m.toSource());
		System.out.println(m);
		this.visitExpression(m.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateMinusSememe(), m.getStartPosition()));
		this.visitExpression(m.getExpressionAt(1));
	}
	
	public void visitTimes(Times t){
		System.out.println(t.toSource());
		System.out.println(t);
		this.visitExpression(t.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateTimesSememe(), t.getStartPosition()));
		this.visitExpression(t.getExpressionAt(1));
	}
	
	public void visitModulo(Modulo m){
		System.out.println(m.toSource());
		System.out.println(m);
		this.visitExpression(m.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateModuloSememe(), m.getStartPosition()));
		this.visitExpression(m.getExpressionAt(1));
	}
	
	public void visitNegative(Negative n){
		System.out.println(n.toSource());
		System.out.println(n);
		sequence.add(new SememeWithLexeme(generator.generateModuloSememe(), n.getStartPosition()));
		this.visitExpression(n.getExpressionAt(0));
	}
	
	public void visitPositive(Positive p){
		System.out.println(p.toSource());
		System.out.println(p);
		sequence.add(new SememeWithLexeme(generator.generateModuloSememe(), p.getStartPosition()));
		this.visitExpression(p.getExpressionAt(0));
	}
	
	public void visitEquals(Equals e){
		System.out.println(e.toSource());
		System.out.println(e);
		this.visitExpression(e.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateEqualsSememe(), e.getStartPosition()));
		this.visitExpression(e.getExpressionAt(1));
	}
	
	public void visitGreaterThan(GreaterThan gt){
		System.out.println(gt.toSource());
		System.out.println(gt);
		this.visitExpression(gt.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateGreaterThanSememe(), gt.getStartPosition()));
		this.visitExpression(gt.getExpressionAt(1));
	}
	
	public void visitGreaterOrEquals(GreaterOrEquals goet){
		System.out.println(goet.toSource());
		System.out.println(goet);
		this.visitExpression(goet.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateGreaterOrEqualsSememe(), goet.getStartPosition()));
		this.visitExpression(goet.getExpressionAt(1));
	}
	
	public void visitLessThan(LessThan lt){
		System.out.println(lt.toSource());
		System.out.println(lt);
		this.visitExpression(lt.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateLessThanSememe(), lt.getStartPosition()));
		this.visitExpression(lt.getExpressionAt(1));
	}
	
	public void visitLessOrEquals(LessOrEquals loe){
		System.out.println(loe.toSource());
		System.out.println(loe);
		this.visitExpression(loe.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateLessOrEqualsSememe(), loe.getStartPosition()));
		this.visitExpression(loe.getExpressionAt(1));
	}
	
	public void visitNotEquals(NotEquals ne){
		System.out.println(ne.toSource());
		System.out.println(ne);
		this.visitExpression(ne.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateNotEqualsSememe(), ne.getStartPosition()));
		this.visitExpression(ne.getExpressionAt(1));
	}
	
	public void visitLogicalAnd(LogicalAnd la){
		System.out.println(la.toSource());
		System.out.println(la);
		this.visitExpression(la.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateLogicalAndSememe(), la.getStartPosition()));
		this.visitExpression(la.getExpressionAt(1));
	}
	
	public void visitLogicalOr(LogicalOr lo){
		System.out.println(lo.toSource());
		System.out.println(lo);
		this.visitExpression(lo.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateLogicalOrSememe(), lo.getStartPosition()));
		this.visitExpression(lo.getExpressionAt(1));
	}
	
	public void visitLogicalNot(LogicalNot ln){
		System.out.println(ln.toSource());
		System.out.println(ln);
		sequence.add(new SememeWithLexeme(generator.generateLogicalNotSememe(), ln.getStartPosition()));
		this.visitExpression(ln.getExpressionAt(0));
	}
	
	public void visitPostDecrement(PostDecrement pd){
		System.out.println(pd.toSource());
		System.out.println(pd);
		this.visitExpression(pd.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generatePostDecrementSememe(), pd.getStartPosition()));
	}
	
	public void visitPostIncrement(PostIncrement pi){
		System.out.println(pi.toSource());
		System.out.println(pi);
		this.visitExpression(pi.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generatePostIncrementSememe(), pi.getStartPosition()));
	}
	
	public void visitPreDecrement(PreDecrement pd){
		System.out.println(pd.toSource());
		System.out.println(pd);
		sequence.add(new SememeWithLexeme(generator.generatePreDecrementSememe(), pd.getStartPosition()));
		this.visitExpression(pd.getExpressionAt(0));
	}
	
	public void visitPreIncrement(PreIncrement pi){
		System.out.println(pi.toSource());
		System.out.println(pi);
		sequence.add(new SememeWithLexeme(generator.generatePreIncrementSememe(), pi.getStartPosition()));
		this.visitExpression(pi.getExpressionAt(0));
	}
	
	public void visitShiftLeft(ShiftLeft shl){
		System.out.println(shl.toSource());
		System.out.println(shl);
		this.visitExpression(shl.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateShiftLeftSememe(), shl.getStartPosition()));
		this.visitExpression(shl.getExpressionAt(1));
	}
	
	public void visitShiftRight(ShiftRight shr){
		System.out.println(shr.toSource());
		System.out.println(shr);
		this.visitExpression(shr.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateShiftRightSememe(), shr.getStartPosition()));
		this.visitExpression(shr.getExpressionAt(1));
	}
	
	public void visitShiftLeftAssignment(ShiftLeftAssignment shla){
		System.out.println(shla.toSource());
		System.out.println(shla);
		this.visitExpression(shla.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateShiftLeftAssignmentSememe(), shla.getStartPosition()));
		this.visitExpression(shla.getExpressionAt(1));
	}
	
	public void visitShiftRightAssignment(ShiftRightAssignment shra){
		System.out.println(shra.toSource());
		System.out.println(shra);
		this.visitExpression(shra.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateShiftRightAssignmentSememe(), shra.getStartPosition()));
		this.visitExpression(shra.getExpressionAt(1));
	}
	
	public void visitParenthesizedExpression(ParenthesizedExpression pe){
		System.out.println(pe.toSource());
		System.out.println("Number of expression: " + pe.getExpressionCount());
		if (pe.getExpressionCount() > 0){
			System.out.println(pe.getExpressionAt(0));
			this.visitExpression(pe.getExpressionAt(0));
		}
	}
	
	public void visitUnsignedShiftRight(UnsignedShiftRight ushr){
		System.out.println(ushr.toSource());
		System.out.println(ushr);
		this.visitExpression(ushr.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateUnsignedShiftRightSememe(), ushr.getStartPosition()));
		this.visitExpression(ushr.getExpressionAt(1));
	}
	
	public void visitUnsignedShiftRightAssignment(UnsignedShiftRightAssignment ushra){
		System.out.println(ushra.toSource());
		System.out.println(ushra);
		this.visitExpression(ushra.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateUnsignedShiftRightAssignmentSememe(), ushra.getStartPosition()));
		this.visitExpression(ushra.getExpressionAt(1));
	}
	
	public void visitLocalVariableDeclaration(LocalVariableDeclaration lvd){
		System.out.println(lvd.toSource());
		System.out.println(lvd.getTypeReference());
		System.out.println(lvd.getVariables());
		TypeReference type = lvd.getTypeReference();
		List<VariableSpecification> variables = lvd.getVariables();
		sequence.add(new SememeWithLexeme(generator.generateTypeSememe(type.getName()), lvd.getStartPosition()));
		for (VariableSpecification vs : variables){
			sequence.add(new SememeWithLexeme(generator.generateVariableSememe(type.getName()), lvd.getStartPosition()));
			if (vs.getExpressionCount() == 1){
				System.out.println(vs.getExpressionAt(0));
				sequence.add(new SememeWithLexeme(generator.generateAssignSememe(), lvd.getStartPosition()));
				this.visitExpression(vs.getExpressionAt(0));
			} else if (vs.getExpressionCount() > 1){
				GlobalConfig.LOGGER.log("Unhandled local variable declaration expression: " + vs.toSource());
			}
		}
	}
	
	public void visitAssert(Assert a){
		System.out.println(a);
		System.out.println(a.toSource());
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("assert"), a.getStartPosition()));
		for (int i=0; i<a.getExpressionCount(); i++){
			this.visitExpression(a.getExpressionAt(i));
		}
	}
	
	public void visitBreak(Break b){
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("break"), b.getStartPosition()));
	}
	
	public void visitReturn(Return r){
		System.out.println(r.getExpressionCount());
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("return"), r.getStartPosition()));
		for (int i=0; i<r.getExpressionCount(); i++){
			this.visitExpression(r.getExpressionAt(i));
		}
	}
	
	public void visitContinue(Continue c){
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("Continue"), c.getStartPosition()));
	}
	
	public void visitThisReference(ThisReference tr){
		System.out.println(tr);
		System.out.println(tr.toSource());
		//TODO: unimplemented this reference
	}
	
	public void visitMethodReference(MethodReference mr){
		System.out.println(mr);
		System.out.println(mr.toSource());
		String name = mr.getName();
		String owner;
		ASTList<Expression> params = mr.getArguments();
		System.out.println(params);
		ReferencePrefix prefix = mr.getReferencePrefix();
		if (prefix != null) owner = this.si.getType(prefix).getName();
		else owner = classStack.peek().getName();
		ArrayList<String> paramTypes = new ArrayList<String>();
		if (params != null){
			for (Expression param : params){
				paramTypes.add(si.getType(param).getName());
			}
		}
		String rettype = "void";
		Type returnType = si.getType(mr);
		if (returnType != null) rettype = returnType.getName();
		sequence.add(new SememeWithLexeme(generator.generateMethodReference(owner, name, paramTypes, rettype), mr.getStartPosition()));
		if (params != null){
			for (Expression param : params){
				this.visitExpression(param);
			}
		}
	}
	
	public void visitSpecialConstructorReference(SpecialConstructorReference scr){
		System.out.println(scr);
		System.out.println(scr.toSource());
		String owner;
		ASTList<Expression> params = scr.getArguments();
		System.out.println(params);
		ReferencePrefix prefix;
		if (scr instanceof SuperConstructorReference)
			prefix = ((SuperConstructorReference)scr).getReferencePrefix();
		else
			prefix = null;
		
		if (prefix != null) owner = this.si.getType(prefix).getName();
		else owner = classStack.peek().getName();
		ArrayList<String> paramTypes = new ArrayList<String>();
		if (params != null){
			for (Expression param : params){
				paramTypes.add(si.getType(param).getName());
			}
		}
		sequence.add(new SememeWithLexeme(generator.generateConstructorReference(owner, paramTypes), scr.getStartPosition()));
	}
	
	public void visitEnhancedFor(EnhancedFor ef){
		System.out.println(ef.toSource());
		System.out.println(ef.getUpdates());
		System.out.println(ef.getBody());
		System.out.println(ef.getGuard());
		System.out.println(ef.getInitializers());
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("for"), ef.getStartPosition()));
		ASTList<LoopInitializer> inits = ef.getInitializers();
		for (LoopInitializer i : inits){
			this.visitStatement((Statement)i);
		}
		Expression guard = ef.getGuard();
		this.visitExpression(guard);
		Statement body = ef.getBody();
		if (body instanceof StatementBlock){
			//sequence.add(new SememeWithLexeme(generator.generateForBegin(), ef.getStartPosition()));
			this.visitStatement(body);
			//sequence.add(new SememeWithLexeme(generator.generateForEnd(), ef.getEndPosition()));
		} else {
			this.visitStatement(body);
		}
	}
	
	public void visitFor(For f){
		System.out.println(f.toSource());
		System.out.println(f.getUpdates());
		System.out.println(f.getBody());
		System.out.println(f.getGuard());
		System.out.println(f.getInitializers());
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("for"), f.getStartPosition()));
		ASTList<LoopInitializer> inits = f.getInitializers();
		for (LoopInitializer i : inits){
			this.visitStatement((Statement)i);
		}
		Expression guard = f.getGuard();
		if (guard != null) this.visitExpression(guard);
		ASTList<Expression> updates = f.getUpdates();
		if (updates != null){
			for (Expression update : updates){
				this.visitExpression(update);
			}
		}
		Statement body = f.getBody();
		if (body instanceof StatementBlock){
			//sequence.add(new SememeWithLexeme(generator.generateForBegin(), f.getStartPosition()));
			this.visitStatement(body);
			//sequence.add(new SememeWithLexeme(generator.generateForEnd(), f.getEndPosition()));
		} else {
			this.visitStatement(body);
		}
	}
	
	public void visitIf(If i){
		System.out.println(i.toSource());
		System.out.println(i.getBranchCount());
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("if"), i.getStartPosition()));
		Expression condition = i.getExpressionAt(0);
		this.visitExpression(condition);
		this.visitThen(i.getThen());
		Else e = i.getElse();
		if (e != null){
			this.visitElse(e);
		}
	}
	
	public void visitThen(Then t){
		Statement body = t.getBody();
		System.out.println(body);
		if (body instanceof StatementBlock){
			//sequence.add(new SememeWithLexeme(generator.generateIfBegin(), t.getStartPosition()));
			this.visitStatement(body);
			//sequence.add(new SememeWithLexeme(generator.generateIfEnd(), t.getEndPosition()));
		} else {
			this.visitStatement(body);
		}
	}
	
	public void visitElse(Else e){
		Statement body = e.getBody();
		System.out.println(body);
		if (body instanceof StatementBlock){
			//sequence.add(new SememeWithLexeme(generator.generateElseBegin(), e.getStartPosition()));
			this.visitStatement(body);
			//sequence.add(new SememeWithLexeme(generator.generateElseEnd(), e.getEndPosition()));
		} else {
			this.visitStatement(body);
		}
	}
	
	public void visitCase(Case c){
		Expression target = c.getExpressionAt(0);
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("case"), c.getStartPosition()));
		this.visitExpression(target);
		ASTList<Statement> body = c.getBody();
		if (body != null){
			for (Statement b : body){
				this.visitStatement(b);
			}
		}
	}
	
	public void visitDefault(Default d){
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("default"), d.getStartPosition()));
		ASTList<Statement> body = d.getBody();
		if (body != null){
			for (Statement b : body){
				this.visitStatement(b);
			}
		}
	}
	
	public void visitCatch(Catch c){
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("catch"), c.getStartPosition()));
		for (int i=0; i<c.getParameterDeclarationCount(); i++){
			ParameterDeclaration para = c.getParameterDeclarationAt(i);
			this.visitParameterDeclaration(para);
		}
		Statement body = c.getBody();
		if (body != null){
			if (body instanceof StatementBlock){
				//sequence.add(new SememeWithLexeme(generator.generateCatchBegin(), c.getStartPosition()));
				this.visitStatement(body);
				//sequence.add(new SememeWithLexeme(generator.generateCatchEnd(), c.getEndPosition()));
			} else this.visitStatement(body);
		}
	}
	
	public void visitFinally(Finally f){
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("finally"), f.getStartPosition()));
		Statement body = f.getBody();
		if (body != null){
			if (body instanceof StatementBlock){
				//sequence.add(new SememeWithLexeme(generator.generateFinallyBegin(), body.getStartPosition()));
				this.visitStatement(body);
				//sequence.add(new SememeWithLexeme(generator.generateFinallyEnd(), body.getEndPosition()));
			} else this.visitStatement(body);
		}
	}
	
	public void visitParameterDeclaration(ParameterDeclaration pd){
		TypeReference type = pd.getTypeReference();
		sequence.add(new SememeWithLexeme(generator.generateTypeSememe(type.getName()), pd.getStartPosition()));
	}
	
	public void visitDo(Do d){
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("do"), d.getStartPosition()));
		Statement doBody = d.getBody();
		System.out.println(doBody);
		System.out.println(doBody.toSource());
		if (doBody instanceof StatementBlock){
			//sequence.add(new SememeWithLexeme(generator.generateDoBegin(), d.getStartPosition()));
			this.visitStatementBlock((StatementBlock) doBody);
			//sequence.add(new SememeWithLexeme(generator.generateDoEnd(), d.getEndPosition()));
		}
		Expression condition = d.getGuard();
		System.out.println(condition);
		System.out.println(condition.toSource());
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("while"), d.getEndPosition()));
		this.visitExpression(condition);
	}
	
	public void visitWhile(While w){
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("while"), w.getStartPosition()));
		Statement whileBody = w.getBody();
		Expression condition = w.getGuard();
		System.out.println(condition);
		System.out.println(condition.toSource());
		this.visitExpression(condition);
		System.out.println(whileBody);
		System.out.println(whileBody.toSource());
		if (whileBody instanceof StatementBlock){
			//sequence.add(new SememeWithLexeme(generator.generateWhileBegin(), w.getStartPosition()));
			this.visitStatementBlock((StatementBlock) whileBody);
			//sequence.add(new SememeWithLexeme(generator.generateWhileEnd(), w.getEndPosition()));
		}	
	}
	
	public void visitTypeCast(TypeCast tc){
		System.out.println(tc.toSource());
		System.out.println(tc);
		System.out.println(tc.getTypeReference());
		sequence.add(new SememeWithLexeme(generator.generateTypeCastSememe(tc.getTypeReference().getName()), tc.getStartPosition()));
		System.out.println(tc.getExpressionCount());
		this.visitExpression(tc.getExpressionAt(0));
	}
	
	public void visitConditional(Conditional c){
		System.out.println(c);
		System.out.println(c.toSource());
		System.out.println(c.getArguments());
		this.visitExpression(c.getExpressionAt(0));
		sequence.add(new SememeWithLexeme(generator.generateTernarySememe(), c.getStartPosition()));
		this.visitExpression(c.getExpressionAt(1));
		this.visitExpression(c.getExpressionAt(2));
	}
	
	public void visitInstanceof(Instanceof i){
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("instanceof"), i.getStartPosition()));
	}
	
	public void visitNew(New i){
		sequence.add(new SememeWithLexeme(generator.generateKeywordSememe("new"), i.getStartPosition()));
	}
	
	public void visitBooleanLiteral(BooleanLiteral bl){
		boolean value = bl.getValue();
		sequence.add(new SememeWithLexeme(generator.generateBooleanLiteralSememe(value), bl.getStartPosition()));
	}
	
	public void visitIntLiteral(IntLiteral il){
		String value = il.getValue();
		sequence.add(new SememeWithLexeme(generator.generateIntLiteralSememe(value), il.getStartPosition()));
	}
	
	public void visitCharLiteral(CharLiteral cl){
		String value = cl.getValue();
		sequence.add(new SememeWithLexeme(generator.generateCharLiteralSememe(value), cl.getStartPosition()));
	}
	
	public void visitStringLiteral(StringLiteral sl){
		String value = sl.getValue();
		sequence.add(new SememeWithLexeme(generator.generateStringLiteralSememe(value), sl.getStartPosition()));
	}
	
	public void visitDoubleLiteral(DoubleLiteral dl){
		String value = dl.getValue();
		sequence.add(new SememeWithLexeme(generator.generateDoubleLiteralSememe(value), dl.getStartPosition()));
	}
	
	public void visitFloatLiteral(FloatLiteral fl){
		String value = fl.getValue();
		sequence.add(new SememeWithLexeme(generator.generateFloatLiteralSememe(value), fl.getStartPosition()));
	}
	
	public void visitNullLiteral(NullLiteral nl){
		sequence.add(new SememeWithLexeme(generator.generateNullLiteralSememe(), nl.getStartPosition()));
	}
	
	public void visitLongLiteral(LongLiteral ll){
		String value = ll.getValue();
		sequence.add(new SememeWithLexeme(generator.generateLongLiteralSememe(value), ll.getStartPosition()));
	}
	
	public List<AbstractSememe> parsedSememe(){
		return sequence;
	}
	
	public String readableSememe(){
		String result = "";
		int previousLine = 0;
		int currentLine = 0;
		for (int i=0; i<sequence.size(); i++){
			currentLine = sequence.get(i).getLine();
			if (currentLine != previousLine){
				result += "\r\n" + currentLine + ":";
				for (int j=0; j<sequence.get(i).getColumn(); j++) result += ' ';
				result += sequence.get(i).getSememe() + " ";
			} else {
				result += sequence.get(i).getSememe() + " ";
			}
			previousLine = currentLine;
		}
		return result;
	}
}