package com.xinmove.calculation;

import org.junit.Test;

/**
 * @ClassName c1
 * @Descripption TODO
 * @Author CTW
 * @Date 2019/6/25 12:34
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class c2 {

    @Test
    public void test(){
        int[] nums = {2,3,8,9};
        int target  = 17;

        for (int i : twoSum(nums, target)) {
            System.out.print(i+" ");
        }
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j <nums.length; j++) {
                if (target==nums[i]+nums[j]) return new int[]{i,j};
            }
        }
        return null;
    }

}
