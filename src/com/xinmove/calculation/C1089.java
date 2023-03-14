package com.xinmove.calculation;

import org.junit.Test;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 *
 * 注意：请不要在超过该数组长度的位置写入元素。
 *
 * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[1,0,2,3,0,4,5,0]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
 * 示例 2：
 *
 * 输入：[1,2,3]
 * 输出：null
 * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/duplicate-zeros
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author cwt
 * @date 2022/6/17 11:06
 */
public class C1089 {

    @Test
    public void test(){
        int[] arr = {1,0,2,3,0,4,5,0};
        int[] result = {1,0,0,2,3,0,0,4};
        System.out.println("期待结果："+ Arrays.toString(result));
        duplicateZeros(arr);
        System.out.println("实际结果："+ Arrays.toString(arr));
    }

    @Test
    public void test2(){
        int[] arr = {1,2,3};
        int[] result = {1,2,3};
        System.out.println("期待结果："+ Arrays.toString(result));
        duplicateZeros(arr);
        System.out.println("实际结果："+ Arrays.toString(arr));
    }


    @Test
    public void test3(){
        int[] arr = {1,0,0,1};
        int[] result = {1,0,0,0};
        System.out.println("期待结果："+ Arrays.toString(result));
        duplicateZeros(arr);
        System.out.println("实际结果："+ Arrays.toString(arr));
    }

    public void duplicateZeros(int[] arr) {
        Queue queue = new ArrayBlockingQueue(arr.length);



    }

}
