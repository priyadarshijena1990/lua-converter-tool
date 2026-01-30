package com.lua.converter;

import java.io.File;

public class ConverterFacade {
    public static String convertToLua(File inputFile) throws Exception {
        String fileName = inputFile.getName().toLowerCase();
        if (fileName.endsWith(".java")) {
            return JavaToLuaConverter.convert(inputFile);
        } else if (fileName.endsWith(".js")) {
            return JsToLuaConverter.convert(inputFile);
        // Python-to-Lua conversion removed
        } else if (fileName.endsWith(".jar")) {
            return JarToLuaConverter.convert(inputFile);
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + fileName);
        }
    }
}
