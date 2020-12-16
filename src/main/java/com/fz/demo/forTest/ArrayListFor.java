package com.fz.demo.forTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayListFor {

	public static void main(String[] args) {
		List<Integer> array = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 80000; i++) {
			array.add(random.nextInt(8000));
		}
		long startTime = System.nanoTime();
		for (int i = 0; i < array.size(); i++) {
//			final int item = array.get(i);
//			System.out.println("item1：" + item);
		}
		long method1Time = System.nanoTime() - startTime;
		System.out.println("方法1：" + method1Time);
		startTime = System.nanoTime();
		for (int i = 0, len = array.size(); i < len; i++) {
//			final int item = array.get(i);
//			System.out.println("item2：" + item);
		}
		long method2Time = System.nanoTime() - startTime;
		System.out.println("方法2：" + method2Time);
		startTime = System.nanoTime();
		for (Integer item : array) {
//			final int temp = item + 1;
//			System.out.println("item3：" + item);
		}
		long method3Time = System.nanoTime() - startTime;
		System.out.println("方法3：" + method3Time);
		long result=Math.min(Math.min(method1Time, method2Time), method3Time);
		System.out.println("最佳时间：" + result);

	}

}
