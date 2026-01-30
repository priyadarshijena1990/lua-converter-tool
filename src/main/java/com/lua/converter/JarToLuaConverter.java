package com.lua.converter;

import java.io.File;
import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import org.objectweb.asm.ClassReader;

public class JarToLuaConverter {
    public static String convert(File jarFile) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (JarInputStream jarStream = new JarInputStream(new FileInputStream(jarFile))) {
            JarEntry entry;
            while ((entry = jarStream.getNextJarEntry()) != null) {
                if (entry.getName().endsWith(".class")) {
                    ClassReader reader = new ClassReader(jarStream);
                    JarClassToLuaVisitor visitor = new JarClassToLuaVisitor(sb);
                    reader.accept(visitor, 0);
                }
            }
        }
        return sb.toString();
    }
}
