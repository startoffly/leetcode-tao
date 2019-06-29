package com.xinmove.calculation;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName C16
 * @Author CWT
 * @Date 2019/6/29 10:05
 *
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C16 {

    @Test
    public void test(){

        int[] nums = {1,1,1,0};
        int target = -100;
        System.out.println(threeSumClosest(nums,target));

        System.out.println(threeSumClosest2(nums,target));
    }

    //时间复杂度 O(n^3)
    public int threeSumClosest(int[] nums, int target) {

        int result = 0;
        if (nums.length<=3){
            for (int i = 0; i < nums.length; i++) {
                result+=nums[i];
            }
            return result;
        }
        int minSub = Integer.MAX_VALUE;
        int sub;//距离
        int sum;

        int index = 0;//主指针
        int lIndex;//左指针
        int rIndex= nums.length-1;//右指针
        while (index<nums.length-2){
            lIndex = index+1;
            while (rIndex>lIndex){//截止指针相撞
                while (rIndex>lIndex){
                    sum =  nums[index]+ nums[lIndex]+ nums[rIndex];
                    sub = target>sum?target-sum:sum-target;
                    if (sub<minSub){
                        minSub = sub;
                        result = sum;
                    }
                    rIndex--;
                }
                rIndex = nums.length-1;//恢复右指针
                lIndex++;//左位移
            }
            index++;
        }
        return result;
    }

    //O(nlogn) + O(n^2) = O(n^2)
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++) {
            int start = i+1, end = nums.length - 1;
            while(start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if(Math.abs(target - sum) < Math.abs(target - result))
                    result = sum;
                if(sum > target)
                    end--;
                else if(sum < target)
                    start++;
                else
                    return result;
            }
        }
        return result;
    }

}
