package com.xinmove.calculation;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName C4
 * @Descripption TODO
 * @Author CWT
 * @Date 2019/6/25 18:56
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C4 {

    @Test
    public void test() {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
//        System.out.println(findMedianSortedArrays(nums1,nums2));


        System.out.println(findMedianSortedArrays2(nums1, nums2));
    }

    //偷懒
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < nums1.length; i++) {
            list.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            list.add(nums2[i]);
        }

        Collections.sort(list);
        int size = list.size();
        double r = 0.0;
        if (list.size() % 2 == 1) {//单数
            r = list.get(size / 2);
        } else {//偶数
            r = (list.get(size / 2) + list.get(size / 2 - 1)) / 2.0;
        }
        return r;
    }


    //超时间
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        LinkedList<Integer> list = new LinkedList<>();
        int lastIndex = 0;
        for (int i = 0; i < nums1.length; i++) {
            list.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            lastIndex = listIndex(list, nums2[i], lastIndex);
            list.add(lastIndex, nums2[i]);//插入适宜的位置
        }
        int size = list.size();
        double r = 0.0;
        if (size % 2 == 1) {//单数
            r = list.get(size / 2);
        } else {//偶数
            r = (list.get(size / 2) + list.get(size / 2 - 1)) / 2.0;
        }
        return r;
    }

    /**
     * 获取数字插入的位置
     *
     * @param list
     * @param a
     * @param lastIndex
     * @return
     */
    private int listIndex(List<Integer> list, int a, int lastIndex) {
        int index = 0;
        int size = list.size();
        for (int i = lastIndex; i < size; i++) {
            if (a > list.get(i)) {
                index = i + 1;
            }
        }

        return index;
    }

    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {

        int index = 0;
        int min;
        int sumlength = nums1.length+nums1.length;
        if (nums1.length <= 2 & nums2.length <= 2) {
            int[] copy = new int[sumlength];

            for (int i = 0; i < nums1.length; i++) {
                copy[index++] = nums1[i];
            }
            for (int i = 0; i < nums2.length; i++) {
                min = nums1[i];
                for (int j = 0; j < sumlength; j++) {
//                    if (min==null)

                    if (min < copy[j]) {
                        min += copy[j];
                        copy[j] = min - copy[j];
                        min -= copy[j];
                    }
                }
            }
        }
        return index;
    }
}
