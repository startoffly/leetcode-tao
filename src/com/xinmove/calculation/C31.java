package com.xinmove.calculation;

import org.junit.Test;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * <p>
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * <p>
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * 通过次数269,840提交次数721,657
 *
 * @author cwt
 */
public class C31 {

    @Test
    public void test(){

        int[] nums;

        nums =new int[]{1,2,3};
        System.out.println("输入："+Arrays.toString(nums));
        System.out.println("期待结果：[1,3,2]");
        nextPermutation(nums);
        System.out.println("实际结果："+Arrays.toString(nums));
        System.out.println("==============================");

        nums =new int[]{1,3,2};
        System.out.println("输入："+Arrays.toString(nums));
        System.out.println("期待结果：[2,1,3]");
        nextPermutation(nums);
        System.out.println("实际结果："+Arrays.toString(nums));
        System.out.println("==============================");

        nums =new int[]{1,1,5};
        System.out.println("输入："+Arrays.toString(nums));
        System.out.println("期待结果：[1,5,1]");
        nextPermutation(nums);
        System.out.println("实际结果："+Arrays.toString(nums));
        System.out.println("==============================");
    }

    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        //倒序冒泡
        int move = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                move = nums[i] + nums[i - 1];
                nums[i] = move - nums[i];
                nums[i - 1] = move - nums[i];
                return;
            }
        }

        //程序到达此处代表没有下一个，启动数据对调
        int midIndex = nums.length / 2;
        for (int i = 0; i < midIndex; i++) {
            move = nums[i] + nums[nums.length - 1 - i];
            nums[i] = move - nums[i];
            nums[nums.length - 1 - i] = move- nums[i];
        }

    }


}
