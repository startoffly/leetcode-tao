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
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class C18 {

    @Test
    public void test() {
        int[] nums = {-3,-3,-2,-1,0,0,0,1,2,3};
        int target = 0;

        List<List<Integer>> result = fourSum2(nums, target);
        System.out.println("结果：" + result.size());
        for (List<Integer> four : result) {
            System.out.print("[");
            for (Integer num : four) {
                System.out.print(num + " ");
            }
            System.out.println("]");
        }
    }

    //四数之和
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) return result;
        Arrays.sort(nums);//排序

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;//去重进位
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j - i > 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) continue;

                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int tmp = nums[i] + nums[j] + nums[left] + nums[right];
                    if (tmp == target) {
                        List<Integer> tmpList = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        result.add(tmpList);
                        while (left < right && nums[left] == nums[left + 1]) left += 1;
                        while (left < right && nums[right] == nums[right - 1]) right -= 1;
                        left += 1;
                        right -= 1;
                    } else if (tmp > target) right -= 1;
                    else left += 1;
                }
            }

        }

        return result;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);//排序
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+" ");
        }
        System.out.println();
        int[] index = new int[4];
        for (int i = 0; i < index.length - 1; i++) {
            index[i] = i;
        }
        index[index.length - 1] = nums.length - 1;
//        int[] index = {0,1,2,nums.length-1};//指针数组
        arrayAdd(0, index, nums, target, result);

        return result;
    }

    //n数之和
    public List<List<Integer>> nSum(int n, int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);//排序
//        int[] index = new int[n];
//        for (int i = 0; i < index.length - 1; i++) {
//            index[i] = i;
//        }
//        index[index.length - 1] = nums.length - 1;
        int[] index = {0,1,2,nums.length-1};//指针数组
        arrayAdd(0, index, nums, target, result);

        return result;
    }

    //可求n数之和
    private void arrayAdd(int nowIndex, int[] index, int[] nums, int target, List<List<Integer>> result) {

        if (nowIndex == index.length - 1) {//最后一位
            index[nowIndex] = nums.length - 1;//最后一位指针复位
            long sum;
            while (index[nowIndex] < nums.length && index[nowIndex] > index[nowIndex - 1]) {
                printfIndex(index);
                sum = sumIndex(index, nums);
                if (sum < target) break;
                if (sum == target) {
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < index.length; i++) {
                        list.add(nums[index[i]]);
                    }
                    System.out.print("解：");
                    printfIndex(index);
                    result.add(list);
                    break;
                }
                index[nowIndex]--;
            }
        } else {
            while (index[nowIndex] <= nums.length + nowIndex - index.length) {
                index[nowIndex + 1] = index[nowIndex] + 1;//下位复位
                arrayAdd(nowIndex + 1, index, nums, target, result);
            }
        }

        if (nowIndex - 1 >= 0) {//上一位进位

            while (index[nowIndex - 1] < nums.length + nowIndex - 2 - index.length && nums[index[nowIndex - 1]] == nums[index[nowIndex - 1] + 1]) {
                index[nowIndex - 1]++;
            }
            index[nowIndex - 1]++;
            if (nowIndex<index.length-1){
                index[nowIndex] = index[nowIndex - 1] + 1;//本位复位
                while(sumIndex(index,nums)>target&&index[nowIndex - 1]<nums.length + nowIndex - 2 - index.length){//上位去直接到最大值
                    index[nowIndex - 1]++;
                }
            }
        }
    }

    //求和
    private long sumIndex(int[] index, int[] nums) {
        long sum = 0;
        for (int i = 0; i < index.length; i++) {
            sum += nums[index[i]];
        }

        return sum;
    }


    private void printfIndex(int[] index) {
        System.out.print("index[");
        for (int i = 0; i < index.length; i++) {
            System.out.print(index[i] + " ");
        }
        System.out.println("]");
    }
}
