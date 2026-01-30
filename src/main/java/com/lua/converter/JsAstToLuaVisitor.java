package com.lua.converter;

import org.mozilla.javascript.ast.*;

public class JsAstToLuaVisitor implements NodeVisitor {
    private final StringBuilder sb;

    public JsAstToLuaVisitor(StringBuilder sb) {
        this.sb = sb;
    }

    @Override
    public boolean visit(AstNode node) {
        if (node instanceof AstRoot) {
            sb.append("-- Converted from JavaScript\n");
        } else if (node instanceof VariableDeclaration vd) {
            vd.getVariables().forEach(var -> {
                sb.append(var.getTarget().toSource() + " = nil\n");
            });
        } else if (node instanceof FunctionNode fn) {
            sb.append("function " + fn.getName() + "(" + fn.getParams().stream().map(AstNode::toSource).reduce((a,b)->a+", "+b).orElse("") + ")\n");
            // Rhino does not support isArrow(); skip arrow function check
            if (fn.getBody() != null) fn.getBody().visit(this);
            sb.append("end\n");
        } else if (node instanceof IfStatement ifs) {
            sb.append("if " + ifs.getCondition().toSource() + " then\n");
            if (ifs.getThenPart() != null) ifs.getThenPart().visit(this);
            if (ifs.getElsePart() != null) {
                sb.append("else\n");
                ifs.getElsePart().visit(this);
            }
            sb.append("end\n");
        } else if (node instanceof ForLoop forLoop) {
            sb.append("for --[[init/cond/inc]] do\n");
            if (forLoop.getBody() != null) forLoop.getBody().visit(this);
            sb.append("end\n");
        } else if (node instanceof WhileLoop whileLoop) {
            sb.append("while " + whileLoop.getCondition().toSource() + " do\n");
            if (whileLoop.getBody() != null) whileLoop.getBody().visit(this);
            sb.append("end\n");
        } else if (node instanceof DoLoop doLoop) {
            sb.append("repeat\n");
            if (doLoop.getBody() != null) doLoop.getBody().visit(this);
            sb.append("until not (" + doLoop.getCondition().toSource() + ")\n");
        } else if (node instanceof SwitchStatement switchStmt) {
            sb.append("-- switch statement\n");
            for (SwitchCase sc : switchStmt.getCases()) {
                if (sc.isDefault()) {
                    sb.append("else\n");
                } else {
                    sb.append("if " + sc.getExpression().toSource() + " then\n");
                }
                for (AstNode stmt : sc.getStatements()) {
                    stmt.visit(this);
                }
            }
            sb.append("end\n");
        } else if (node instanceof BreakStatement) {
            sb.append("break\n");
        } else if (node instanceof ContinueStatement) {
            sb.append("continue\n");
        } else if (node instanceof ReturnStatement ret) {
            sb.append("return");
            if (ret.getReturnValue() != null) {
                sb.append(" " + ret.getReturnValue().toSource());
            }
            sb.append("\n");
        } else if (node instanceof ArrayLiteral arr) {
            sb.append("{ ");
            for (AstNode el : arr.getElements()) {
                sb.append(el.toSource() + ", ");
            }
            sb.append(" }\n");
        } else if (node instanceof ObjectLiteral objLit) {
            sb.append("{ ");
            for (ObjectProperty prop : objLit.getElements()) {
                sb.append("[" + prop.getLeft().toSource() + "] = " + prop.getRight().toSource() + ", ");
            }
            sb.append(" }\n");
        } else if (node instanceof Assignment assignment) {
            sb.append(assignment.getLeft().toSource() + " = " + assignment.getRight().toSource() + "\n");
        } else if (node instanceof InfixExpression infix) {
            sb.append("-- binary expr: " + infix.getLeft().toSource() + " " + infix.getOperator() + " " + infix.getRight().toSource() + "\n");
        } else if (node instanceof UnaryExpression unary) {
            sb.append("-- unary expr: " + unary.getOperator() + unary.getOperand().toSource() + "\n");
        } else if (node instanceof FunctionCall call) {
            sb.append("-- call: " + call.getTarget().toSource() + "\n");
        } else if (node instanceof PropertyGet propGet) {
            sb.append("-- property get: " + propGet.getProperty().getIdentifier() + "\n");
        } else if (node instanceof Name name) {
            sb.append(name.getIdentifier() + "\n");
        } else if (node instanceof NumberLiteral num) {
            sb.append(num.getValue() + "\n");
        } else if (node instanceof StringLiteral str) {
            sb.append('"' + str.getValue() + '"' + "\n");
        } else if (node instanceof ParenthesizedExpression paren) {
            sb.append("(");
            if (paren.getExpression() != null) paren.getExpression().visit(this);
            sb.append(")\n");
        } else if (node instanceof TryStatement tryStmt) {
            sb.append("local ok, result = pcall(function()\n");
            if (tryStmt.getTryBlock() != null) tryStmt.getTryBlock().visit(this);
            sb.append("end)\n");
            if (tryStmt.getCatchClauses() != null) {
                for (CatchClause cc : tryStmt.getCatchClauses()) {
                    sb.append("if not ok then\n");
                    if (cc.getBody() != null) cc.getBody().visit(this);
                    sb.append("end\n");
                }
            }
            if (tryStmt.getFinallyBlock() != null) {
                sb.append("-- finally\n");
                tryStmt.getFinallyBlock().visit(this);
            }
        } else if (node instanceof Scope scope) {
            // Block/Scope: visit children using iterator
            for (java.util.Iterator<?> it = scope.iterator(); it.hasNext(); ) {
                Object child = it.next();
                if (child instanceof AstNode astNode) {
                    astNode.visit(this);
                }
            }
        }
        return true;
    }
}
