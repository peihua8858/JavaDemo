package com.fz.demo.sort;

public class SortMethod {
    /**
     * 冒泡排序改进版，鸡尾酒排序
     *
     * @param a
     * @param n
     */
    public static void cocktailSort(Integer a[], int n) {
        int left = 0;
        int right = n - 1;
        while (left < right) {
            for (int i = left; i < right; i++) {
                if (a[i] > a[i + 1]) {
                    swap(a, i, i + 1);
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (a[i - 1] > a[i]) {
                    swap(a, i - 1, i);
                }
            }
            left++;
        }
    }

    /**
     * 交换数据
     *
     * @param a
     * @param i
     * @param i2
     */
    private static void swap(Integer[] a, int i, int i2) {
        int temp = a[i];
        a[i] = a[i2];
        a[i2] = temp;
    }

    /**
     * 冒泡排序
     * 1、比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 2、对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 3、针对所有的元素重复以上的步骤，除了最后一个。
     * 4、持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     *
     * @param arr
     * @param length
     */
    public static void bubbleSort(Integer arr[], int length) {
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 插入法排序
     * 原理：所谓插入排序法，就是检查第i个数字，如果在它的左边的数字比它大，进行交换，这个动作一直继续下去，直到这个数字的左边数字比它还要小
     *
     * @param arr   排序數組
     * @param start 開始位置
     * @param end   結束位置
     */
    public static <T extends Comparable<T>> void insertSort(T[] arr, int start, int end) {
        if (arr == null || start >= end) {
            return;
        }
        for (int i = 1; i < end; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                } else {
                    // 接下来是无用功
                    break;
                }
            }
        }
    }

    /**
     * 交换数据
     *
     * @param arr
     * @param i
     * @param i2
     */
    private static <T extends Comparable<T>> void swap(T[] arr, int i, int i2) {
        T temp = arr[i];
        arr[i] = arr[i2];
        arr[i2] = temp;
    }

    public static void basket(int data[]) {
        int n = data.length;
        int bask[][] = new int[10][n];
        int index[] = new int[10];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = max > (Integer.toString(data[i]).length()) ? max : (Integer.toString(data[i]).length());
        }
        String str;
        for (int i = max - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                str = "";
                if (Integer.toString(data[j]).length() < max) {
                    for (int k = 0; k < max - Integer.toString(data[j]).length(); k++)
                        str += "0";
                }
                str += Integer.toString(data[j]);
                bask[str.charAt(i) - '0'][index[str.charAt(i) - '0']++] = data[j];
            }
            int pos = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < index[j]; k++) {
                    data[pos++] = bask[j][k];
                }
            }
            for (int x = 0; x < 10; x++)
                index[x] = 0;
        }
    }
}
