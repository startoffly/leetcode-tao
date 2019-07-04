package com.xinmove.calculation;

import com.xinmove.calculation.vo.ListNode;

/**
 * @ClassName C23
 * @Descripption 合并 k 个排序链表
 * @Author CWT
 * @Date 2019/7/2 18:59
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C23 {


    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode result = null;
        for (ListNode listNode: lists) {
            result = mergeTwoLists(result,listNode);
        }
        return result;
    }


    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = null;
        for (ListNode listNode: lists) {
            result = mergeTwoLists(result,listNode);
        }
        return result;
    }

    //双合并算法
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
