package com.fz.demo.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * 添加TryCatchMethod
 *
 * @author dingpeihua
 * @version 1.0
 * @date 2020/1/11 11:57
 */
public class HandleTryCatchMethodVisitor extends AdviceAdapter {
    private MethodVisitor mv;
    private String methodName;
    private String className;
    private Label from = new Label();
    private Label to = new Label();
    private Label target = new Label();
    private String exceptionHandleClass;
    private String exceptionHandleMethod;
    final Type returnType;

    public HandleTryCatchMethodVisitor(MethodVisitor mv, String className, final int access,
                                       final String methodName,
                                       final String descriptor, String exceptionHandleClass,
                                       String exceptionHandleMethod) {
        super(Opcodes.ASM7, mv, access, methodName, descriptor);
        this.mv = mv;
        this.methodName = methodName;
        this.className = className;
        this.exceptionHandleClass = exceptionHandleClass;
        this.exceptionHandleMethod = exceptionHandleMethod;
        returnType = Type.getReturnType(descriptor);
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        if (returnType == Type.VOID_TYPE) {
            mv.visitTryCatchBlock(from, to, target, "java/lang/Throwable");
            mv.visitLabel(from);
        }
    }

    @Override
    protected void onMethodExit(int opcode) {
        if (returnType == Type.VOID_TYPE) {
//            mv.visitJumpInsn(GOTO, to);
//            mv.visitLabel(target);
//            mv.visitVarInsn(ASTORE, 1);
//            if (exceptionHandleClass != null && exceptionHandleMethod != null) {
//                mv.visitVarInsn(ALOAD, 1);
//                mv.visitMethodInsn(INVOKESTATIC, exceptionHandleClass,
//                        exceptionHandleMethod, "(Ljava/lang/Throwable;)V", false);
//            }
//            mv.visitLabel(to);
//
            mv.visitLabel(to);
            Label l3 = new Label();
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(target);
            mv.visitVarInsn(ASTORE, 1);
            if (exceptionHandleClass != null && exceptionHandleMethod != null) {
                mv.visitVarInsn(ALOAD, 1);
                mv.visitMethodInsn(INVOKESTATIC, exceptionHandleClass,
                        exceptionHandleMethod, "(Ljava/lang/Throwable;)V", false);
            }
            mv.visitLabel(l3);
        }
        super.onMethodExit(opcode);
    }
}
