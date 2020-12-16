package com.fz.demo.asm;


import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class TryCatchHookAdapter extends AdviceAdapter {
    private Label from = new Label();
    private Label to = new Label();
    private Label target = new Label();
    private String superClassName;
    private String mClassName;
    final Type returnType;

    protected TryCatchHookAdapter(MethodVisitor methodVisitor, String className, int access, String name,
                                  String descriptor) {
        super(Opcodes.ASM7, methodVisitor, access, name, descriptor);
        this.mClassName = className;
        returnType = Type.getReturnType(descriptor);
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        mv.visitTryCatchBlock(from, to, target, "java/lang/Throwable");
        mv.visitLabel(from);
    }

    @Override
    protected void onMethodExit(int opcode) {
//        mv.visitJumpInsn(GOTO, to);
//        mv.visitLabel(target);
//        mv.visitVarInsn(ASTORE, 1);
//        mv.visitVarInsn(ALOAD, 1);
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Throwable", "printStackTrace", "()V", false);
//        Label label6 = new Label();
//        mv.visitLocalVariable("e", "Ljava/lang/Throwable;", null, to, target, 1);
//        mv.visitLocalVariable("this", "L" + mClassName + ";", null, to, label6, 0);
//        mv.visitLabel(to);

        mv.visitLabel(to);
        Label l3 = new Label();
        mv.visitJumpInsn(GOTO, l3);
        mv.visitLabel(target);
        mv.visitVarInsn(ASTORE, 1);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Throwable", "printStackTrace", "()V", false);
        Label label6 = new Label();
        mv.visitLocalVariable("e", "Ljava/lang/Throwable;", null, to, target, 1);
        mv.visitLocalVariable("this", "L" + mClassName + ";", null, l3, label6, 0);
        mv.visitLabel(l3);
        super.onMethodExit(opcode);
    }
}
