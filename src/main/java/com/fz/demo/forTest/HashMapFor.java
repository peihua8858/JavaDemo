package com.fz.demo.forTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class HashMapFor {

	public static void main(String[] args) {
		Map<Integer, Integer> array = new HashMap<>();
		Random random = new Random();
		for (int i = 0; i < 80000; i++) {
			int value = random.nextInt(8000);
			array.put(value, value);
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
		Set<Integer> keys = array.keySet();
		startTime = System.nanoTime();
		for (Integer item : keys) {
//			final int temp = item + 1;
//			System.out.println("item3：" + item);
		}
		long method3Time = System.nanoTime() - startTime;
		System.out.println("方法3：" + method3Time);
		long result = Math.min(Math.min(method1Time, method2Time), method3Time);
		System.out.println("最佳时间：" + result);

	}

}
