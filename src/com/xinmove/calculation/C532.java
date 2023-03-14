package com.xinmove.calculation;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 * <p>
 * k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
 * <p>
 * 0 <= i, j < nums.length
 * i != j
 * nums[i] - nums[j] == k
 * 注意，|val| 表示 val 的绝对值。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3, 1, 4, 1, 5], k = 2
 * 输出：2
 * 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个 1 ，但我们只应返回不同的数对的数量。
 * 示例 2：
 * <p>
 * 输入：nums = [1, 2, 3, 4, 5], k = 1
 * 输出：4
 * 解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5) 。
 * 示例 3：
 * <p>
 * 输入：nums = [1, 3, 1, 5, 4], k = 0
 * 输出：1
 * 解释：数组中只有一个 0-diff 数对，(1, 1) 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * -107 <= nums[i] <= 107
 * 0 <= k <= 107
 * 通过次数54,843提交次数122,255
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-diff-pairs-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author cwt
 * @date 2022/6/17 8:28
 */
public class C532 {


    @Test
    public void test() {
        int[] nums = {1, 3, 1, 5, 4};
        int k = 0;
        System.out.println(Arrays.toString(nums));
        System.out.println(findPairs(nums, k));

        nums = new int[]{1, 2, 3, 4, 5};
        k = 1;
        System.out.println(Arrays.toString(nums));
        System.out.println(findPairs(nums, k));


        nums = new int[]{3, 1, 4, 1, 5};
        k = 2;
        System.out.println(Arrays.toString(nums));
        System.out.println(findPairs(nums, k));

    }

    @Test
    public void test2() {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 1;
        System.out.println(Arrays.toString(nums));
        System.out.println(findPairs(nums, k));


    }

    @Test
    public void test3() {
        int[] nums = {3, 1, 4, 1, 5};
        int k = 2;
        System.out.println(Arrays.toString(nums));
        System.out.println(findPairs(nums, k));

    }

    @Test
    public void test4() {
        int[] nums = {1, 3, 1, 5, 4};
        int k = 0;
        int trueResult = 1;
        System.out.println(Arrays.toString(nums));
        System.out.println("预期：" + trueResult + "，结果：" + findPairs(nums, k));

    }

    public int findPairs(int[] nums, int k) {
        int result = 0;


        return result;
    }

    public int findPairs2(int[] nums, int k) {
        int result = 0;
        if (nums.length < 2) return result;
        nums = Arrays.stream(nums).sorted().toArray();
        //上轮指标
        int sub;
        int maxIndex = nums.length - 1;
        for (int i = maxIndex; i >= 0; i--) {
            //与上一位相同则跳过
            if (i < maxIndex && nums[i + 1] == nums[i]) {
                continue;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (j < i - 1 && nums[j + 1] == nums[j]) {
                    continue;
                }
                sub = nums[i] - nums[j];
                //期望结果
                if (sub == k) {
                    result++;
                }
                if (sub > k) {
                    break;
                }
            }
        }

        return result;
    }


}
