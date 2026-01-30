package com.lua.converter;

import java.io.File;
import java.nio.file.Files;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ParseResult;
import java.io.IOException;
import java.util.Optional;

public class JavaToLuaConverter {
    public static String convert(File javaFile) throws IOException {
        String source = Files.readString(javaFile.toPath());
        JavaParser parser = new JavaParser();
        ParseResult<CompilationUnit> result = parser.parse(source);
        Optional<CompilationUnit> cuOpt = result.getResult();
        if (cuOpt.isEmpty()) {
            throw new IOException("Failed to parse Java source file: " + javaFile.getName());
        }
        CompilationUnit cu = cuOpt.get();
        StringBuilder sb = new StringBuilder();
        JavaAstToLuaVisitor visitor = new JavaAstToLuaVisitor();
        visitor.visit(cu, sb);
        return sb.toString();
    }
}
