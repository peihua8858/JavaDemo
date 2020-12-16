package com.fz.demo.asm;

public class ExceptionUtils {
    public static void uploadCatchException(Throwable e) {
        if (e != null) {
            System.out.println("执行遇到异常：" + e.getMessage());
            e.printStackTrace();
        }
    }
}