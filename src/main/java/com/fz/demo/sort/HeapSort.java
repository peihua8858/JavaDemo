package com.fz.demo.sort;

import java.util.Arrays;

/**
 * 标准堆排序
 * 不稳定的。
 * 我们知道堆的结构是节点i的孩子为2 * i和2 * i + 1节点，
 * 大顶堆要求父节点大于等于其2个子节点，小顶堆要求父节点小于等于其2个子节点。
 * 在一个长为n 的序列，堆排序的过程是从第n / 2开始和其子节点共3个值选择最大（大顶堆）或者最小（小顶堆），
 * 这3个元素之间的选择当然不会破坏稳定性。但当为n / 2 - 1， n / 2 - 2， ... 1这些个父节点选择元素时，
 * 就会破坏稳定性。有可能第n / 2个父节点交换把后面一个元素交换过去了，而第n / 2 - 1个父节点把后面一个相同的元素没 有交换，
 * 那么这2个相同的元素之间的稳定性就被破坏了。所以，堆排序不是稳定的排序算法。
 *
 * @author dingpeihua
 * @version 1.0
 * @date 2019/8/14 09:15
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {142, 543, 123, 65, 453, 879, 572, 434, 111, 242, 811, 102};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 标准堆排序
     *
     * @param arr
     */
    private static void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) { //i表示非叶子结点
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length); //以当前叶子结点为顶点
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//以根结点为顶点，重新对堆进行调整，j控制调整区域，由于已将最大元素选出，即不对0~j以外的元素调整
        }
    }

    /**
     * 调整大顶堆
     * 下沉过程
     *
     * @param arr
     * @param i
     * @param length
     */
    private static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//k为i的左子结点，从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点；最终k指向最大子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}