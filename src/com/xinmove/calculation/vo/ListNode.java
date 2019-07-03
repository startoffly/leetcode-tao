package com.xinmove.calculation.vo;

/**
 * @ClassName ListNode
 * @Author CWT
 * @Date 2019/7/3 15:21
 **/
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * @param a
     * @Description 生成简易链表并返回第一个元素
     * @Return com.xinmove.calculation.C2.ListNode
     * @Author cwt
     * @Date 2019/6/25 12:30
     */
    public static ListNode getNodes(int[] a) {
        ListNode result = a.length > 0 ? new ListNode(a[0]) : null;
        ListNode next = null;
        ListNode nowNode = result;
        for (int i = 0; i < a.length; i++) {
            next = i + 1 < a.length ? new ListNode(0) : null;
            nowNode.next = next;
            nowNode.val = a[i];
            nowNode = next;
        }
        return result;
    }
}
