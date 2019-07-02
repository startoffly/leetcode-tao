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
        int[] nodes = {1,2,3,4};//数字342
        int n = 4;
        ListNode nodesHead = getNodes(nodes);
        nodesHead = removeNthFromEnd(nodesHead,n);
        ListNode node = nodesHead;

        while (node!=null&&node.next!=null){
            System.out.println("node.val:"+node.val);
            node = node.next;
        }
        System.out.println("node.val:"+(node!=null?node.val:"null"));

    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n<1) return head;
        if (head.next==null) return n==1?null:head;//唯一元素删除

//        int size = remove(head,head.next,0,n);//去头元素
        if (head.next.next!=null){
            int size = remove(head,head.next,-1,n);//最低三个元素算法
            System.out.println("结果："+size);
        }else {
            if (n==1){
                head.next=null;
                return head;
            }else {
                return head.next;
            }
        }

        return head;
    }
    private int remove(ListNode lastNode,ListNode nowNode,int index,int n){
        if (nowNode!=null){//子元素不为空
            index =  remove(nowNode,nowNode.next,index,n)+1;
        }else {
            index++;
        }
        if (index==n){
            lastNode.next =nowNode == null?null:nowNode.next;
            System.out.println(n+",删除:"+nowNode.val);
        }
        return index;
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
