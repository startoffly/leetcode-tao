package com.xinmove.calculation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName C17
 * @Author CWT
 * @Date 2019/6/29 12:48
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class C18 {

    @Test
    public void test(){
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        for (List<Integer> four:
             fourSum(nums,target)) {
            System.out.print("[");
            for (Integer num:
                 four) {
                System.out.print(num+" ");
            }
            System.out.println("]");
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length<4) return result;

        Arrays.sort(nums);//排序

        int[] index = {0,1,2,3};//指针数组




        return result;
    }

}
