package com.lua.converter;

import org.objectweb.asm.*;

public class JarClassToLuaVisitor extends ClassVisitor {
    private final StringBuilder sb;

    public JarClassToLuaVisitor(StringBuilder sb) {
        super(Opcodes.ASM9);
        this.sb = sb;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        sb.append("-- class " + name.replace('/', '.') + "\n");
        sb.append(name.replace('/', '.') + " = {}\n");
        sb.append("-- access: " + access + "\n");
        if ((access & Opcodes.ACC_INTERFACE) != 0) sb.append("-- interface\n");
        if ((access & Opcodes.ACC_ENUM) != 0) sb.append("-- enum\n");
        if (superName != null && !superName.equals("java/lang/Object")) {
            sb.append("-- extends " + superName.replace('/', '.') + "\n");
        }
        if (interfaces != null && interfaces.length > 0) {
            for (String iface : interfaces) {
                sb.append("-- implements " + iface.replace('/', '.') + "\n");
            }
        }
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        sb.append("-- field: " + name + " type: " + descriptor + " access: " + access + "\n");
        sb.append(name + " = nil\n");
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        sb.append("-- method: " + name + " desc: " + descriptor + " access: " + access + "\n");
        if ("<clinit>".equals(name)) {
            sb.append("-- static initializer\n");
        } else if ("<init>".equals(name)) {
            sb.append("function new()\n");
            sb.append("    local self = setmetatable({}, {__index = " + "CLASSNAME" + "})\n");
            sb.append("    -- constructor body\n");
            sb.append("    return self\n");
            sb.append("end\n");
        } else {
            sb.append("function " + name + "()\n");
            sb.append("    -- method body\n");
            sb.append("end\n");
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        sb.append("-- annotation: " + descriptor + "\n");
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitEnd() {
        sb.append("-- end class\n");
        super.visitEnd();
    }
}
