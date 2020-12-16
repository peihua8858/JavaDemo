package com.fz.demo.sort;

/**
 * 适用情况：已知所有待排序的元素都在某个整数区间【m,n】，然后初始化一个长（n-m+1）的数组Temp，其中所有位都是0，
 * 然后遍历带排序数组A，如果A[i]=a,那么Temp[a]++;
 * 可以知道，Temp存放的是每一个元素出现的次数（索引即元素，值为次数），然后按次序读取就行了。
 *
 * 时间复杂度： O(n+k)
 * 其中：n:待排序元素数量
 *       k:元素所在的区间大小
 */
public class CountSort {
    private static int[] countSort(int[] array, int k) {
        int[] C = new int[k + 1];//构造C数组
        int length = array.length, sum = 0;//获取A数组大小用于构造B数组
        int[] B = new int[length];//构造B数组
        for (int i = 0; i < length; i++) {
            C[array[i]] += 1;// 统计A中各元素个数，存入C数组
        }
        //修改C数组
        for (int i = 0; i < k + 1; i++) {
            sum += C[i];
            C[i] = sum;
        }
        //遍历A数组，构造B数组
        for (int i = length - 1; i >= 0; i--) {
            B[C[array[i]] - 1] = array[i];//将A中该元素放到排序后数组B中指定的位置
            C[array[i]]--;//将C中该元素-1，方便存放下一个同样大小的元素

        }
        return B;//将排序好的数组返回，完成排序
    }

    public static void main(String[] args) {
        int[] A = new int[]{2, 5, 3, 0, 2, 3, 0, 3};
        int[] B = countSort(A, 5);
        for (int i = 0; i < A.length; i++) {
            System.out.println((i + 1) + "th:" + B[i]);
        }
    }
}
