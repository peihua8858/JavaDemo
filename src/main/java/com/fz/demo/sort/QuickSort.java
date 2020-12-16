package com.fz.demo.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author dingpeihua
 */
public class QuickSort {
    public int[] quickSort(int a[], int left, int right) {
        /**关键词：基准
         * 快速排序  十分重要  分治法
         * 快速排序是冒泡排序的改进版，也是最好的一种内排序：任取一个基准，将比其小的移到左边，比其大的移动到右边
         * 当分区选取的基准元素为待排序元素中的"中值"，为最好的情况，时间复杂度为O(nlog2n)。
         */
        int L = left;
        int R = right;
        if (L < R) {   //待排序的元素至少有两个
            int temp = a[L];  //待排序的第一个元素作为基准元素
            while (L != R) {   //从左右两边交替扫描，直到L= R
                while (R > L && a[R] >= temp) R--;        //从右往左扫描，找到第一个比基准元素小的元素  （注意：包含等于）
                a[L] = a[R];                            //找到这种元素a[R]后与a[L]交换
                while (L < R && a[L] <= temp) L++;        //从左往右扫描，找到第一个比基准元素大的元素 （注意：包含等于）
                a[R] = a[L];                            //找到这种元素a[L]后，与a[R]交换
            }
            a[R] = temp;    //基准元素归位
            quickSort(a, left, L - 1);  //对基准元素左边的元素进行递归排序
            quickSort(a, R + 1, right);  //对基准元素右边的进行递归排序
        }
        return a;
    }

    /**
     * 方式三：减少交换次数，提高效率
     *
     * @param targetArr
     * @param start
     * @param end
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> void quickSort(T[] targetArr, int start, int end) {
        if (targetArr == null || targetArr.length == 0) {
            return;
        }
        if (start >= end) {
            //如果左边索引大于或者等于右边的索引就代表已经整理完成一个组了
            return;
        }

        int i = start, j = end;
        T key = targetArr[start];
        while (i < j) {
            //按j--方向遍历目标数组，直到比key小的值为止
            while (j > i && targetArr[j].compareTo(key) >= 0) {
                j--;
            }
            System.out.println(Arrays.toString(targetArr));
            if (i < j) {
                //targetArr[i]已经保存在key中，可将后面的数填入
                targetArr[i] = targetArr[j];
                i++;
            }
            System.out.println(Arrays.toString(targetArr));
            //按i++方向遍历目标数组，直到比key大的值为止
            while (i < j && targetArr[i].compareTo(key) <= 0) {
                //此处一定要小于等于零，假设数组之内有一亿个1，0交替出现的话，
                // 而key的值又恰巧是1的话，那么这个小于等于的作用就会使下面的if语句少执行一亿次。
                i++;
            }
            System.out.println(Arrays.toString(targetArr));
            if (i < j) {
                //  targetArr[j]已保存在targetArr[i]中，可将前面的值填入
                targetArr[j] = targetArr[i];
                j--;
            }
            System.out.println(Arrays.toString(targetArr));
        }
        //此时i==j
        targetArr[i] = key;
        //递归调用，把key前面的完成排序
        quickSort(targetArr, start, i - 1);
        //递归调用，把key后面的完成排序
        quickSort(targetArr, j + 1, end);
    }

    public static void main(String[] args) {
        Integer[] a = {4, 2, 88, 12, 66, 78, 32, 55, 64, 1, 99, 75, 45, 57, 34, 22, 21, 39, 44, 18, 15, 67, 63, 6};
        quickSort(a, 0, a.length - 1);
    }
}
