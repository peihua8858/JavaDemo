package com.fz.demo.asm;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.objectweb.asm.*;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Random;

public class AsmTest {
    public static void main(String[] args) {
        try {
            String outputPath = AsmTest.class.getResource("").getPath();
            InputStream inputStream = AsmTest.class.getResourceAsStream("AsmDemo.class");
            ClassReader classReader = new ClassReader(IOUtils.toByteArray(inputStream));
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES);
            OverrideClassWriteVisitor classWriteVisitor = new OverrideClassWriteVisitor(classWriter);
            classWriteVisitor.setExceptionHandleClass(ExceptionUtils.class.getName());
            classWriteVisitor.setExceptionHandleMethod("uploadCatchException");
            classWriteVisitor.setChangeClassName("com.fz.demo.asm.AsmDemoTest");
            classReader.accept(classWriteVisitor, ClassReader.EXPAND_FRAMES);
            //方法字节码增强    
            byte[] bytes = classWriter.toByteArray();
            // 输出方法新字节码
            File outputFile = new File(outputPath, "AsmDemoTest.class");
            if (outputFile.exists()) {
                outputFile.delete();
            }
            FileUtils.writeByteArrayToFile(outputFile, bytes);
            // 测试方法
            Class<?> clazz = Class.forName("com.fz.demo.asm.AsmDemoTest");
            String[] data = {"123", "abc"};
            Random random = new Random();
            Object object = clazz.newInstance();
            for (int i = 0; i < 24; i++) {
                final String method = "change" + i;
                final Method queryUserInfo = clazz.getDeclaredMethod(method, String.class);
                // 正确入参；测试验证结果输出
                final int index = random.nextInt(2);
                final Object obj01 = queryUserInfo.invoke(object, data[index]);
                System.out.println("方法：" + method + " 测试结果：" + obj01);
            }
//            Method queryUserInfo = clazz.getDeclaredMethod("change2", String.class);
//            // 正确入参；测试验证结果输出
//            Object obj01 = queryUserInfo.invoke(clazz.newInstance(), "123");
////            AsmDemo.change("123");
//            System.out.println("01 测试结果：" + obj01);
//            // 异常入参；测试验证打印异常信息
////            AsmDemo.change("abc");
//            Object obj02 = queryUserInfo.invoke(clazz.newInstance(), "abc");
////            System.out.println("02 测试结果：" + obj02);
////            System.out.println("执行完成。");
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("e>>>>>执行异常：" + e.getMessage());
        }
    }

    private static class TryCatchCountingVisitor extends ClassVisitor {
        public int counter;

        public TryCatchCountingVisitor(ClassVisitor classVisitor) {
            super(Opcodes.ASM6, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                                         String[] exceptions) {
            MethodVisitor visitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            return new MethodVisitor(Opcodes.ASM6, visitor) {
                @Override
                public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
                    TryCatchCountingVisitor.this.counter += 1;
                    super.visitTryCatchBlock(start, end, handler, type);
                }
            };
        }
    }
}
