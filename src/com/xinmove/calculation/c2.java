package com.xinmove.calculation;

import org.junit.Test;

/**
 * @ClassName c1
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
public class c2 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void test() {
        //342
        ListNode l1_1 = new ListNode(2);
        ListNode l1_2 = new ListNode(4);
        l1_1.next = l1_2;
        ListNode l1_3 = new ListNode(3);
        l1_2.next = l1_3;

        //465
        ListNode l2_1 = new ListNode(5);
        ListNode l2_2 = new ListNode(6);
        l2_1.next = l2_2;
        ListNode l2_3 = new ListNode(4);
        l2_2.next = l2_3;

        ListNode end = addTwoNumbers(l1_1, l2_1);
        while(end != null) {
            System.out.print(end.val + " ");
            end = end.next;
        }


    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int add = 0;
        ListNode result = new ListNode((l1.val + l2.val) % 10);
        ListNode nowListNode = result;
        ListNode nextListNode;
        boolean flg =true;
        int num1;
        int num2;
        int sum;
        while (flg) {
            num1 = l1!=null?l1.val:0;
            num2 = l2!=null?l2.val:0;
            sum = num1 + num2 + add;
            nowListNode.val = sum % 10;
            add = sum/ 10;//下次进位
            l1 = l1!=null?l1.next:null;
            l2 = l2!=null?l2.next:null;
            flg = add !=0||l1 != null || l2 != null;
            if (flg) {//若下一位有运算需要

                nextListNode = new ListNode(0);//下一位对象
                nowListNode.next = nextListNode;//下一位关系确立
                nowListNode = nextListNode;//运算位交由下一位
            }
        }
        return result;
    }

}
