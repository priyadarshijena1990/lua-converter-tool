package com.lua.converter;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class JavaToLuaConverterTest {
    @Test
    public void testSimpleClassConversion() throws Exception {
        File javaFile = new File("src/test/resources/SimpleClass.java");
        String lua = JavaToLuaConverter.convert(javaFile);
        assertTrue(lua.contains("SimpleClass = {}"));
        assertTrue(lua.contains("function"));
    }
}
