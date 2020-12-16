package com.fz.demo.asm;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class OverrideClassWriteVisitor extends ClassVisitor {
    private String superClassName;
    private String mClassName;
    private boolean isInterface;
    private String exceptionHandleClass;
    private String exceptionHandleMethod;
    private String changeClassName = "";

    public void setChangeClassName(@NotNull String changeClassName) {
        this.changeClassName = changeClassName.replaceAll("\\.", "/");
    }

    public OverrideClassWriteVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM7, classVisitor);
    }

    public void setExceptionHandleClass(@NotNull String exceptionHandleClass) {
        this.exceptionHandleClass = exceptionHandleClass.replaceAll("\\.", "/");
    }

    public void setExceptionHandleMethod(String exceptionHandleMethod) {
        this.exceptionHandleMethod = exceptionHandleMethod;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println("className：" + name);
        this.mClassName = StringUtils.isEmpty(changeClassName) || name.equals("changeClassName") ? name :
                changeClassName;
        super.visit(version, access, mClassName, signature, superName, interfaces);
        superClassName = superName;
        this.isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                                     String[] exceptions) {
        if ("<init>".equals(name)) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
        // 不对接口和私有方法注入
        if (isInterface || (access & Opcodes.ACC_PRIVATE) != 0) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
        //不对抽象方法、native方法、桥接方法、合成方法进行注入
        if ((access & Opcodes.ACC_ABSTRACT) != 0 || (access & Opcodes.ACC_NATIVE) != 0 || (access & Opcodes.ACC_BRIDGE) != 0 || (access & Opcodes.ACC_SYNTHETIC) != 0) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
//        if (exceptionHandleClass != null && exceptionHandleMethod != null) {
//            return new HandleTryCatchMethodVisitor(methodVisitor, mClassName, access, name, descriptor,
//                    exceptionHandleClass, exceptionHandleMethod);
//        }
        return new TryCatchHookAdapter2(methodVisitor, mClassName, access, name, descriptor, exceptionHandleClass,
                exceptionHandleMethod);
    }
}
