package com.fz.demo.asm;

import java.util.ArrayList;
import java.util.List;

public class AsmDemo333 {

    public double change2(String text) {
        try {
            double number = Double.parseDouble(text);
            return number * 20f / 1.5f;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public String change3(String text) {
        try {
            double number = Double.parseDouble(text);
            return "$" + (number * 20 / 1.5f);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Boolean change4(String text) {
        try {
            double number = Double.parseDouble(text);
            return (number * 20 / 1.5f) > 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void change(String text) {
        try {
            double number = Double.parseDouble(text);
            double result = number * 20.0D / 1.5D;
            System.out.println("change>>" + result);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    public List<Double> change5(String text) {
        List<Double> result = new ArrayList<>();
        try {
            double number = Double.parseDouble(text);
            result.add(number * 20.0D / 1.5D);
            return result;
        } catch (Throwable var5) {
            var5.printStackTrace();
        }
        return result;
    }
    public short change6(String text) {
        double number = Double.parseDouble(text);
        return (short) (number * 20f / 1.5f);
    }
    public Double[] change7(String text) {
        Double[] result = new Double[1];
        try {
            double number = Double.parseDouble(text);
            result[0]=(number * 20.0D / 1.5D);
            return result;
        } catch (Throwable var5) {
            var5.printStackTrace();
        }
        return result;
    }
    public Double[] change8(String text) {
        return new Double[0];
    }
    public Double[][] change9(String text) {
        try {
            return new Double[0][];
        } catch (Exception e) {
            e.printStackTrace();
            return  new Double[0][0];
        }
    }

//
//    public static void change(String text) {
//        double number = Double.parseDouble(text);
//        double result = number * 20 / 1.5f;
//        System.out.println("change>>" + result);
//    }
}
