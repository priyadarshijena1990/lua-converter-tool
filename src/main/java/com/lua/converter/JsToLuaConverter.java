package com.lua.converter;

import java.io.File;
import java.nio.file.Files;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ast.AstRoot;

public class JsToLuaConverter {
    public static String convert(File jsFile) throws Exception {
        String source = Files.readString(jsFile.toPath());
        Parser parser = new Parser();
        AstRoot ast = parser.parse(source, jsFile.getName(), 1);
        StringBuilder sb = new StringBuilder();
        JsAstToLuaVisitor visitor = new JsAstToLuaVisitor(sb);
        ast.visit(visitor);
        return sb.toString();
    }
}
