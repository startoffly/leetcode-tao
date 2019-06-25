package com.xinmove.calculation;

import org.junit.Test;

/**
 * @ClassName c1
 * @Descripption TODO
 * @Author CTW
 * @Date 2019/6/25 12:34
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class c1 {

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
