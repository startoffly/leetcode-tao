package com.xinmove.calculation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName C1
 * @Descripption TODO
 * @Author CTW
 * @Date 2019/6/25 12:34
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C2 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void runSomeTest(){
        System.out.println("------普通测试------");
        int[] l1 = {2,4,3};//数字342
        int[] l2 = {5,6,4};//数字465
        test(l1,l2);
        System.out.println("------非等------");
        l1 = new int[]{2,9,3,5};//数字5392
        l2 = new int[]{5,6,4};//数字465
        test(l1,l2);
        System.out.println("------单零------");
        l1 = new int[]{2,9,3,5};//数字5392
        l2 = new int[]{0};
        test(l1,l2);
        System.out.println("------双进位------");
        l1 = new int[]{1,9};//数字91
        l2 = new int[]{9};//9
        test(l1,l2);



    }

//    @Test
    public void test(int[] l1,int[] l2) {
//        int[] l1 = {2,4,3};//数字342
//        int[] l2 = {5,6,4};//465

        ListNode l1Node = getNodes(l1);
        ListNode l2Node = getNodes(l2);

        StringBuilder outStr = new StringBuilder();
        outStr.append("代码意义：");
        for (int i = l1.length-1; i >= 0; i--) {
            outStr.append(l1[i]);
        }
        outStr.append(" + ");
        for (int i = l2.length-1; i >= 0; i--) {
            outStr.append(l2[i]);
        }
        outStr.append(" = ");

        ListNode result = addTwoNumbers(l1Node, l2Node);
        System.out.print("输出结果为：");
        List<Integer> ints = new ArrayList<>();
        while (result != null) {
            ints.add(result.val);
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();
        Collections.reverse(ints);//倒转
        ints.forEach(i-> outStr.append(i));
        System.out.println(outStr);
    }

    /**
     * @Description 题解运算实体
     * @param l1
     * @param l2
     * @Return com.xinmove.calculation.C2.ListNode
     * @Author cwt
     * @Date 2019/6/25 12:44
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int add = 0;
        ListNode result = new ListNode((l1.val + l2.val) % 10);
        ListNode nowListNode = result;
        ListNode nextListNode;
        boolean flg = true;
        int num1;
        int num2;
        int sum;
        while (flg) {
            num1 = l1 != null ? l1.val : 0;
            num2 = l2 != null ? l2.val : 0;
            sum = num1 + num2 + add;
            nowListNode.val = sum % 10;
            add = sum / 10;//下次进位
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            flg = add != 0 || l1 != null || l2 != null;
            if (flg) {//若下一位有运算需要

                nextListNode = new ListNode(0);//下一位对象
                nowListNode.next = nextListNode;//下一位关系确立
                nowListNode = nextListNode;//运算位交由下一位
            }
        }
        return result;
    }

    @Test
    public void cTest() {

        ListNode result = getNodes(new int[]{1, 2, 3});
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    /**
     * @param a
     * @Description 生成简易链表并返回第一个元素
     * @Return com.xinmove.calculation.C2.ListNode
     * @Author cwt
     * @Date 2019/6/25 12:30
     */
    public ListNode getNodes(int[] a) {

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
