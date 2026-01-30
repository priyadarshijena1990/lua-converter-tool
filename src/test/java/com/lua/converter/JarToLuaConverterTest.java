package com.lua.converter;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class JarToLuaConverterTest {
    @Test
    public void testJarConversion() throws Exception {
        File jarFile = new File("src/test/resources/simple.jar");
        String lua = JarToLuaConverter.convert(jarFile);
        assertTrue(lua.contains("-- class"));
    }
}
