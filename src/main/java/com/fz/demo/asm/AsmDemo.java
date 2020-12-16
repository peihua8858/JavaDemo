package com.fz.demo.asm;

import com.fz.demo.sort.QuickSort;

import java.util.*;

public class AsmDemo {
    public long change0(String text) {
        double number = Double.parseDouble(text);
        return (long) (number * 20f / 1.5f);
    }

    public int change1(String text) {
        double number = Double.parseDouble(text);
        return (int) (number * 20f / 1.5f);
    }

    public double change2(String text) {
        double number = Double.parseDouble(text);
        return number * 20f / 1.5f;
    }

    public String change3(String text) {
        double number = Double.parseDouble(text);
        return "$" + (number * 20 / 1.5f);
    }

    public Boolean change4(String text) {
        double number = Double.parseDouble(text);
        return (number * 20 / 1.5f) > 0;
    }

    public List<Double> change5(String text) {
        List<Double> result = new ArrayList<>();
        double number = Double.parseDouble(text);
        result.add((number * 20 / 1.5f));
        return result;
    }

    public short change6(String text) {
        double number = Double.parseDouble(text);
        return (short) (number * 20f / 1.5f);
    }

    public static void change7(String text) {
        double number = Double.parseDouble(text);
        double result = number * 20 / 1.5f;
        System.out.println("change>>" + result);
    }

    public char change8(String text) {
        double number = Double.parseDouble(text);
        return (char) (number * 20f / 1.5f);
    }

    public byte change9(String text) {
        double number = Double.parseDouble(text);
        return (byte) (number * 20f / 1.5f);
    }

    public ArrayList<Double> change10(String text) {
        ArrayList<Double> result = new ArrayList<>();
        double number = Double.parseDouble(text);
        result.add((number * 20 / 1.5f));
        return result;
    }

    public LinkedList<Double> change11(String text) {
        LinkedList<Double> result = new LinkedList<>();
        double number = Double.parseDouble(text);
        result.add((number * 20 / 1.5f));
        return result;
    }

    public Map<String, Double> change12(String text) {
        Map<String, Double> result = new HashMap<>();
        double number = Double.parseDouble(text);
        result.put("abc", (number * 20 / 1.5f));
        return result;
    }

    public LinkedHashMap<String, Double> change13(String text) {
        LinkedHashMap<String, Double> result = new LinkedHashMap<>();
        double number = Double.parseDouble(text);
        result.put("abc", (number * 20 / 1.5f));
        return result;
    }

    public Set<Double> change14(String text) {
        Set<Double> result = new HashSet<>();
        double number = Double.parseDouble(text);
        result.add((number * 20 / 1.5f));
        return result;
    }

    public LinkedHashSet<Double> change15(String text) {
        LinkedHashSet<Double> result = new LinkedHashSet<>();
        double number = Double.parseDouble(text);
        result.add((number * 20 / 1.5f));
        return result;
    }

    public Queue<Double> change16(String text) {
        Queue<Double> result = new ArrayDeque<>();
        double number = Double.parseDouble(text);
        result.add((number * 20 / 1.5f));
        return result;
    }

    public PriorityQueue<Double> change17(String text) {
        PriorityQueue<Double> result = new PriorityQueue<>();
        double number = Double.parseDouble(text);
        result.add((number * 20 / 1.5f));
        return result;
    }

    public Double[] change18(String text) {
        Double[] result = new Double[1];
        double number = Double.parseDouble(text);
        result[0] = (number * 20.0D / 1.5D);
        return result;
    }
    public Object[] change19(String text) {
        Object[] result = new Object[1];
        double number = Double.parseDouble(text);
        result[0] = (number * 20.0D / 1.5D);
        return result;
    }
    public AsmModel[] change20(String text) {
        AsmModel[] result = new AsmModel[1];
        double number = Double.parseDouble(text);
        result[0] = new AsmModel(number * 20.0D / 1.5D);
        return result;
    }
    public AsmModel[][] change21(String text) {
        AsmModel[][] result = new AsmModel[1][1];
        double number = Double.parseDouble(text);
        result[0][0] = new AsmModel(number * 20.0D / 1.5D);
        return result;
    }
    public Double[][] change22(String text) {
        Double[][] result = new Double[1][1];
        double number = Double.parseDouble(text);
        result[0][0] = (number * 20.0D / 1.5D);
        return result;
    }
    public Double[][][] change23(String text) {
        Double[][][] result = new Double[1][1][1];
        double number = Double.parseDouble(text);
        result[0][0][0] = (number * 20.0D / 1.5D);
        return result;
    }
    public int[][][] change24(String text) {
        int[][][] result = new int[1][1][1];
        double number = Double.parseDouble(text);
        result[0][0][0] = (int) (number * 20.0D / 1.5D);
        return result;
    }
}
