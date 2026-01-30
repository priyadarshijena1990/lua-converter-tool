package com.lua.converter;

import java.io.FileWriter;
import java.io.IOException;

public class LuaWriter {
    public static void writeToFile(String luaCode, String outputFile) throws IOException {
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(luaCode);
        }
    }
}
