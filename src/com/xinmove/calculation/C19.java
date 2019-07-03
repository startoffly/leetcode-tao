package com.xinmove.calculation;

import org.junit.Test;

/**
 * @ClassName C19
 * @Author CWT
 * @Date 2019/7/2 18:52
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C19 {


    @Test
    public void runSomeTest(){
        System.out.println("------普通测试------");
        int[] nodes = {1,2,3,4,5};//数字342
        int n = 1;
        ListNode nodesHead = getNodes(nodes);

        nodesHead = removeNthFromEndOnece(nodesHead,n);

        while (nodesHead!=null&&nodesHead.next!=null){
            System.out.println("node.val:"+nodesHead.val);
            nodesHead = nodesHead.next;
        }
        System.out.println("node.val:"+(nodesHead!=null?nodesHead.val:"null"));

    }
    //解决
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode node = head;
        int size = 1;
        while (node.next!=null){
            size++;
            node = node.next;
        }
        int indexN = 0;
        ListNode lastNode = null;
        node = head;
        while (node.next!=null){
            if(size-n==indexN){
                break;
            }
            indexN++;
            lastNode = node;
            node = node.next;
        }
        if (lastNode==null) return head.next;
        lastNode.next = node.next;
        return head;
    }

    //尝试一遍扫描
    public ListNode removeNthFromEndOnece(ListNode head, int n) {
        if (head.next==null&&n==1) return null;
        int[] sub = {-1};//删除点的计数
        return removeOnce(1,null,head,n,sub);
    }

    private ListNode removeOnce(int index,ListNode listNode,ListNode nowNode,int n,int[] sub){
        index++;
        if (nowNode.next!=null){
            removeOnce(index,nowNode,nowNode.next,n,sub);
        }else {
            sub[0] = index-n;
        }
        if (index-1==sub[0]){//本节点删除
            if (listNode==null) return nowNode.next;
            listNode.next = nowNode.next;
        }
        return nowNode;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * @param a
     * @Description 生成简易链表并返回第一个元素
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
