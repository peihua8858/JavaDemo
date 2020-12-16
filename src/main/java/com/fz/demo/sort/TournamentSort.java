package com.fz.demo.sort;

/**
 * 利用胜利树，每次从m个元素中选出ceiling（m/2）个元素，即所有元素初始化为外部节点，
 * 然后每次把小者上升为父节点，直到在根处找到最小值。
 * 时间复杂度为： O(nlogn)     时间换空间
 * 稳定性：个人认为，比较时如果是相等的左子上升的话是稳定的
 */
public class TournamentSort {
    /**
     * 用node来存储竞赛排序过程中的节点，包括里面的数据和数据在数组中的ID
     */
    private class Node {
        public int data;
        public int id;

        public Node() {
        }

        public Node(int _data, int _id) {
            data = _data;
            id = _id;
        }
    }

    /**
     * 当去除最小元素以后，我们要调整数组
     *
     * @param data
     * @param idx
     */
    public void Adjust(Node[] data, int idx) {
        while (idx != 0) {
            //当前id是奇数，说明并列的是idx + 1, 父节点是 (idx-1)/2
            if (idx % 2 == 1) {
                if (data[idx].data < data[idx + 1].data) {
                    data[(idx - 1) / 2] = data[idx];
                } else {
                    data[(idx - 1) / 2] = data[idx + 1];
                }
                idx = (idx - 1) / 2;
            } else {
                if (data[idx - 1].data < data[idx].data) {
                    data[idx / 2 - 1] = data[idx - 1];
                } else {
                    data[idx / 2 - 1] = data[idx];
                }
                idx = (idx / 2 - 1);
            }
        }
    }


    public void Sort(int[] data) {
        int nNodes = 1;
        int nTreeSize;
        while (nNodes < data.length) {
            nNodes *= 2;
        }
        nTreeSize = 2 * nNodes - 1;//竞赛树节点的个数, nNode算出来是为了做成满二叉树
        Node[] nodes = new Node[nTreeSize];//竞赛树用数组存储
        //initialize the data
        int i, j;
        int idx;
        //初始化竞赛树数据
        for (i = nNodes - 1; i < nTreeSize; i++) {
            idx = i - (nNodes - 1);
            if (idx < data.length) {
                nodes[i] = new Node(data[idx], i);
            } else {
                nodes[i] = new Node(Integer.MAX_VALUE, -1);//对于补充的数据，我们初始化成最大。
            }
        }
        for (i = nNodes - 2; i >= 0; i--) {
            nodes[i] = new Node();
            if (nodes[i * 2 + 1].data < nodes[i * 2 + 2].data) {
                nodes[i] = nodes[i * 2 + 1];
            } else {
                nodes[i] = nodes[i * 2 + 2];
            }
        }
        //the real sorting procedure
        //实际排序的过程
        for (i = 0; i < data.length; i++) {
            data[i] = nodes[0].data;//取出最小的
            nodes[nodes[0].id].data = Integer.MAX_VALUE;
            Adjust(nodes, nodes[0].id);
        }
    }
}
