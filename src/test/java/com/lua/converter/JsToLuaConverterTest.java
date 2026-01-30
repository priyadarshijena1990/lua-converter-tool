package com.lua.converter;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class JsToLuaConverterTest {
    @Test
    public void testSimpleFunctionConversion() throws Exception {
        File jsFile = new File("src/test/resources/simple.js");
        String lua = JsToLuaConverter.convert(jsFile);
        assertTrue(lua.contains("function"));
        assertTrue(lua.contains("-- Converted from JavaScript"));
    }
}
