package com.xinmove.calculation;

import com.xinmove.calculation.vo.ListNode;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName C25
 * @Descripption TODO
 * @Author CWT
 * @Date 2019/7/4 10:25
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例 :
 *
 * 给定这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明 :
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C25 {

    @Test
    public void test(){
        ListNode head = ListNode.getNodes(new int[]{1,2,3,4,5});
        ListNode start  =  reverseOnce(head,3);
//        ListNode start  =  reverseKGroup(head,3);

        System.out.print("[");

        while (start.next!=null){
            System.out.print(start.val+" ");
            start = start.next;
        }
        System.out.println(start.val+"]");

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = null;
        ListNode node = head;
        ListNode reverseHead = null;
        ListNode lastNode = null;
        while(node!=null){
            System.out.println(node.val);
            reverseHead = reverseOnce(node,k);
            if (newHead!=null){
                lastNode.next = reverseHead;
            }else {
                newHead = reverseHead;
            }
            lastNode = node;
            node=node.next;
        }
        return newHead!=null?newHead:head;
    }

    //翻转
    private ListNode reverseOnce(ListNode head,int k){
        ListNode end = head;
        ListNode node = head;
        ListNode lastNode = null;
        ListNode nextNode;
        int index=1;
        boolean canReverse = false;
        while (node!=null){//初始判断
            if (index>=k) {
                canReverse = true;
                break;
            }
            index++;
            node = node.next;
        }
        if (!canReverse) return head;
        node = head;
        while (node!=null&&k-->0){
            nextNode = node.next;
            node.next = lastNode;
            lastNode = node;
            end = node;
            head.next = nextNode;
            node = nextNode;
        }
        return end;
    }

}
