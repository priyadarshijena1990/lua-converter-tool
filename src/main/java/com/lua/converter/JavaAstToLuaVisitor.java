package com.lua.converter;

import com.github.javaparser.ast.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class JavaAstToLuaVisitor extends VoidVisitorAdapter<StringBuilder> {
    @Override
    public void visit(CompilationUnit cu, StringBuilder sb) {
        sb.append("-- Converted from Java\n");
        super.visit(cu, sb);
    }

    @Override
    public void visit(com.github.javaparser.ast.body.ClassOrInterfaceDeclaration n, StringBuilder sb) {
        sb.append("-- class " + n.getNameAsString() + "\n");
        sb.append(n.getNameAsString() + " = {}\n");
        if (n.isInterface()) {
            sb.append("-- interface\n");
        }
        super.visit(n, sb);
    }

    @Override
    public void visit(com.github.javaparser.ast.body.FieldDeclaration n, StringBuilder sb) {
        n.getVariables().forEach(var -> {
            sb.append("-- field: " + var.getNameAsString() + "\n");
            sb.append(var.getNameAsString() + " = nil\n");
        });
        super.visit(n, sb);
    }

    @Override
    public void visit(com.github.javaparser.ast.body.ConstructorDeclaration n, StringBuilder sb) {
        sb.append("function " + n.getNameAsString() + ":new()\n");
        sb.append("    local self = setmetatable({}, {__index = " + n.getNameAsString() + "})\n");
        sb.append("    -- constructor body\n");
        sb.append("    return self\n");
        sb.append("end\n");
        super.visit(n, sb);
    }

    @Override
    public void visit(com.github.javaparser.ast.body.MethodDeclaration n, StringBuilder sb) {
        sb.append("function " + n.getNameAsString() + "()\n");
        sb.append("    -- method body\n");
        sb.append("end\n");
        super.visit(n, sb);
    }

    @Override
    public void visit(com.github.javaparser.ast.stmt.IfStmt n, StringBuilder sb) {
        sb.append("if ");
        sb.append(n.getCondition().toString());
        sb.append(" then\n");
        n.getThenStmt().accept(this, sb);
        n.getElseStmt().ifPresent(elseStmt -> {
            sb.append("else\n");
            elseStmt.accept(this, sb);
        });
        sb.append("end\n");
    }

    @Override
    public void visit(com.github.javaparser.ast.stmt.ForStmt n, StringBuilder sb) {
        sb.append("for --[[init/cond/update]] do\n");
        n.getBody().accept(this, sb);
        sb.append("end\n");
    }

    @Override
    public void visit(com.github.javaparser.ast.stmt.WhileStmt n, StringBuilder sb) {
        sb.append("while " + n.getCondition().toString() + " do\n");
        n.getBody().accept(this, sb);
        sb.append("end\n");
    }

    @Override
    public void visit(com.github.javaparser.ast.expr.VariableDeclarationExpr n, StringBuilder sb) {
        n.getVariables().forEach(var -> {
            sb.append(var.getNameAsString() + " = nil\n");
        });
        super.visit(n, sb);
    }

    @Override
    public void visit(com.github.javaparser.ast.expr.MethodCallExpr n, StringBuilder sb) {
        sb.append("-- call: " + n.getNameAsString() + "\n");
        super.visit(n, sb);
    }

    @Override
    public void visit(com.github.javaparser.ast.stmt.TryStmt n, StringBuilder sb) {
        sb.append("local ok, result = pcall(function()\n");
        n.getTryBlock().accept(this, sb);
        sb.append("end)\n");
        n.getCatchClauses().forEach(catchClause -> {
            sb.append("if not ok then\n");
            sb.append("    -- catch: " + catchClause.getParameter().getNameAsString() + "\n");
            catchClause.getBody().accept(this, sb);
            sb.append("end\n");
        });
        n.getFinallyBlock().ifPresent(finallyBlock -> {
            sb.append("-- finally\n");
            finallyBlock.accept(this, sb);
        });
    }
}
