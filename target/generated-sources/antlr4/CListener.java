// Generated from C.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CParser}.
 */
public interface CListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(CParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(CParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(CParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(CParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(CParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(CParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(CParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(CParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(CParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(CParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(CParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(CParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(CParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(CParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#forStat}.
	 * @param ctx the parse tree
	 */
	void enterForStat(CParser.ForStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#forStat}.
	 * @param ctx the parse tree
	 */
	void exitForStat(CParser.ForStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(CParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(CParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(CParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(CParser.FunctionCallContext ctx);
}