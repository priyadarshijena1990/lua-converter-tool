package com.lua.converter;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar lua-converter-tool.jar <input-file> [output-lua-file]");
            System.exit(1);
        }
        String inputFile = args[0];
        String outputFile;
        if (args.length >= 2) {
            outputFile = args[1];
        } else {
            outputFile = getDefaultOutputFileName(inputFile);
        }
        try {
            String luaCode = ConverterFacade.convertToLua(new File(inputFile));
            LuaWriter.writeToFile(luaCode, outputFile);
            System.out.println("Conversion successful! Output: " + outputFile);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
    }

    private static String getDefaultOutputFileName(String inputFile) {
        int lastDot = inputFile.lastIndexOf('.');
        if (lastDot > 0) {
            return inputFile.substring(0, lastDot) + ".lua";
        } else {
            return inputFile + ".lua";
        }
    }
}
