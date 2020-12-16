package com.fz.demo.sort;

import java.util.Arrays;

public class RewritedHeapSort {

    public static void main(String[] args) {
        int[] arr = {142, 543, 123, 65, 453, 879, 572, 434, 111, 242, 811, 102};
        RewritedHeapSort m = new RewritedHeapSort();
        m.heapSortNew(arr, 2, 8); // [142, 543, || 65, 111, 123, 434, 453, 572, 879,|| 242, 811, 102]
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 重写堆排序方法，在low,high局部排序
     * @param arr
     * @param low
     * @param high
     */
    private void heapSortNew(int[] arr, int low, int high) {
        //1.构建大顶堆
        //以当前叶子结点为顶点, i表示当前非叶子结点, i>=low即停止
        for (int i = low + (high - low + 1) / 2; i >= low; i--)
            adjustHeap(arr, i, low, high);

        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int i = high; i > low; i--) {
            swap(arr, low, i); //将堆顶元素与末尾元素进行交换
            // 以根结点（第一个low) 为顶点，重新对堆进行调整
            // (第二个low, high:i-1)控制调整范围，由于已将最大元素选出，即不对 low ~ i-1 以外的元素调整
            adjustHeap(arr, low, low, i - 1);
        }
    }

    /**
     * 调整大顶堆
     * @param arr
     * @param i 顶点
     * @param low 和high控制调整范围
     * @param high
     */
    private void adjustHeap(int[] arr, int i, int low, int high) {
        int temp = arr[i];
        for (int child = leftChild(i, low); child < high + 1; child = leftChild(i, low)) {

            // 两个孩子比较
            if (child < high && arr[child] < arr[child + 1])
                child++;

            // 较大孩子和父亲比较
            if (arr[child] > temp){
                arr[i] = arr[child];
                i = child;
            }

            else break;
        }
        arr[i] = temp;
    }

    /**
     * 找到顶点为i的左孩子
     * @param i
     * @param low
     * @return
     */
    private int leftChild(int i, int low) {
        return 2 * i + 1 - low; // 要减掉low
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



//    private void adjustHeap(int[] arr, int i, int low, int high) {
//        int child = leftChild(i, low);
//        int temp = arr[i];
//        for (; leftChild(i, low) < high + 1; i = child) {
//            child = leftChild(i, low);
//            // 两个孩子比较
//            if (child < high && arr[child] < arr[child + 1])
//                child++;
//
//            // 较大孩子和父亲比较
//            if (arr[child] > temp) arr[i] = arr[child];
//            else break;
//        }
//        arr[i] = temp;
//    }