// Generated from Python3.g4 by ANTLR 4.13.1
package com.lua.converter;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Python3Parser}.
 */
public interface Python3Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Python3Parser#file_input}.
	 * @param ctx the parse tree
	 */
	void enterFile_input(Python3Parser.File_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#file_input}.
	 * @param ctx the parse tree
	 */
	void exitFile_input(Python3Parser.File_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(Python3Parser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(Python3Parser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSimple_stmt(Python3Parser.Simple_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#simple_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSimple_stmt(Python3Parser.Simple_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void enterSmall_stmt(Python3Parser.Small_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#small_stmt}.
	 * @param ctx the parse tree
	 */
	void exitSmall_stmt(Python3Parser.Small_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExpr_stmt(Python3Parser.Expr_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExpr_stmt(Python3Parser.Expr_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#test}.
	 * @param ctx the parse tree
	 */
	void enterTest(Python3Parser.TestContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#test}.
	 * @param ctx the parse tree
	 */
	void exitTest(Python3Parser.TestContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCompound_stmt(Python3Parser.Compound_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCompound_stmt(Python3Parser.Compound_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#funcdef}.
	 * @param ctx the parse tree
	 */
	void enterFuncdef(Python3Parser.FuncdefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#funcdef}.
	 * @param ctx the parse tree
	 */
	void exitFuncdef(Python3Parser.FuncdefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(Python3Parser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(Python3Parser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link Python3Parser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(Python3Parser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link Python3Parser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(Python3Parser.SuiteContext ctx);
}