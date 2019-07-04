package com.xinmove.calculation;

import com.xinmove.calculation.vo.ListNode;
import org.junit.Test;

/**
 * @ClassName C24
 * @Descripption 两两交换其中相邻的节点
 * @Author CWT
 * @Date 2019/7/4 9:36
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C24 {


    @Test
    public void test(){
        ListNode head = ListNode.getNodes(new int[]{1,2,3,4,5});
        ListNode node = swapPairs(head);

        System.out.print("[");

        while (node.next!=null){
            System.out.print(node.val+" ");
            node = node.next;
        }
        System.out.println(node.val+"]");

    }

    public ListNode swapPairs(ListNode head) {
        int index = 0;
        ListNode result = head;
        ListNode node = head;
        ListNode lastNode = null;
        ListNode lastChangeNode = null;
        while (node!=null){
            index++;
            if (index%2==0){//逢偶交换
                if (index==2){//确定返回头
                    result=node;
                }else {
                    lastChangeNode.next = node;
                }
                lastNode.next = node.next;
                node.next = lastNode;
                node = lastNode;
                lastChangeNode = lastNode;
            }
            lastNode = node;
            node = node.next;
        }
        return result;
    }

}
