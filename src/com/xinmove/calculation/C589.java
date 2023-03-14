package com.xinmove.calculation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 589. N 叉树的前序遍历
 * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
 * <p>
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 节点总数在范围 [0, 104]内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 * <p>
 * <p>
 * 进阶：递归法很简单
 *
 * @author cwt
 */
public class C589 {


    @Test
    public void test() {


        Node node5 = new Node(5);
        Node node6 = new Node(6);

        List<Node> node3s = new ArrayList<>();
        node3s.add(node5);
        node3s.add(node6);
        Node node3 = new Node(3, node3s);
        Node node2 = new Node(2);
        Node node4 = new Node(4);

        List<Node> node1s = new ArrayList<>();

        node1s.add(node3);
        node1s.add(node2);
        node1s.add(node4);
        Node root = new Node(1, node1s);

        List<Integer> p = preorder(root);
        System.out.println(p);
    }

    public List<Integer> preorder(Node root) {
        List<Integer> points = new ArrayList<>();
        if (null == root) {
            return points;
        }
        points.add(root.val);
        getPreamble(root, points);
        return points;
    }

    private void getPreamble(Node node, List<Integer> points) {
        if (node.children != null) {
            for (Node child : node.children) {
                System.out.println("当前：" + node.val + "child:" + child.val);
                if (child != null) {
                    points.add(child.val);
                    getPreamble(child, points);
                }
            }
        }
    }


    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}
