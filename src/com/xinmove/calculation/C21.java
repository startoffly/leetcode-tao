package com.xinmove.calculation;

import com.xinmove.calculation.vo.ListNode;
import org.junit.Test;

/**
 * @ClassName C21
 * @Descripption TODO
 * @Author CWT
 * @Date 2019/7/3 19:17
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C21 {

    @Test
    public void test(){
        ListNode l1 = ListNode.getNodes(new int[]{1});
        ListNode l2 = ListNode.getNodes(new int[]{1});


        ListNode result = mergeTwoLists(l1,l2);
        while (result.next!=null){
            System.out.print(result.val+"->");
            result = result.next;
        }
        System.out.println(result.val);

    }

    //执行用时 :1 ms, 在所有 Java 提交中击败了99.74%的用户
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null) return l2;
        if (l2==null) return l1;

        if (l2.val<l1.val){//以首元素较小者为开始主链
            ListNode r = l2;
            l2 = l1;
            l1 = r;
        }
        ListNode lastNode = l1;//l1上游标
        ListNode l1Node = l1.next;//l1游标
        ListNode l2Node = l2;//l2游标

        while(l1Node!=null){//l2插入l1
            if (l2Node.val<l1Node.val){//链后续交换
                lastNode.next = l2Node;
                l2Node = l1Node;
                l1Node = lastNode.next;
            }else {//主链移动
                lastNode = l1Node;
                l1Node = l1Node.next;
            }
        }

        lastNode.next = l2Node;//插入列未耗尽
        return l1;
    }
}
