package com.fz.demo.forTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 验证for循环数组元素的三种写法的性能问题， 结果第一种方法耗时最长；
 * 
 * @author dingpeihua
 *
 */
public class ArrayFor {

	public static void main(String[] args) {
		List<Integer> sets = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 50000; i++) {
			sets.add(random.nextInt(8000));
		}
		Integer[] array = sets.toArray(new Integer[sets.size()]);
		long startTime = System.nanoTime();
		for (int i = 0; i < array.length; ) {
			final int item = array[i];
			i++;
//			System.out.println("item1：" + item);
		}
		long method1Time = System.nanoTime() - startTime;
		System.out.println("方法1：" + method1Time);
		startTime = System.nanoTime();
		for (int i = 0, len = array.length; i < len; ) {
			final int item = array[i];
			i++;
//			System.out.println("item2：" + item);
		}
		long method2Time = System.nanoTime() - startTime;
		System.out.println("方法2：" + method2Time);
		startTime = System.nanoTime();
		for (Integer item : array) {
			final int temp = item + 1;
//			System.out.println("item3：" + item);
		}
		long method3Time = System.nanoTime() - startTime;
		System.out.println("方法3：" + method3Time);
//		startTime = System.nanoTime();
//		Integer item;
//		for (int i;;item = array[++i]) {
//			final int temp = item + 1;
////			System.out.println("item3：" + item);
//		}
//		long method4Time = System.nanoTime() - startTime;
//		System.out.println("方法4：" + method3Time);
		long result = Math.min(Math.min(method1Time, method2Time), method3Time);
		System.out.println("最佳时间：" + result);
	}

}
