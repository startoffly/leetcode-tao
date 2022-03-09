package com.xinmove.calculation;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为 [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。此后，任何值小于或等于其索引的项都可以记作一分。
 * <p>
 * 例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,4,0]
 * 输出：3
 * 解释：
 * 下面列出了每个 k 的得分：
 * k = 0,  nums = [2,3,1,4,0],    score 2
 * k = 1,  nums = [3,1,4,0,2],    score 3
 * k = 2,  nums = [1,4,0,2,3],    score 3
 * k = 3,  nums = [4,0,2,3,1],    score 4
 * k = 4,  nums = [0,2,3,1,4],    score 3
 * 所以我们应当选择 k = 3，得分最高。
 * 示例 2：
 * <p>
 * 输入：nums = [1,3,0,2,4]
 * 输出：0
 * 解释：
 * nums 无论怎么变化总是有 3 分。
 * 所以我们将选择最小的 k，即 0。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] < nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-rotation-with-highest-score
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author cwt
 */
public class C798 {


    @Test
    public void test() {
        int[] nums;
//        nums = new int[]{2, 3, 1, 4, 0};
//        System.out.println("输入" + Arrays.toString(nums));
//        System.out.println("期待结果：3，实际结果：" + bestRotation(nums));
//
//        nums = new int[]{1, 3, 0, 2, 4};
//        System.out.println("输入" + Arrays.toString(nums));
//        System.out.println("期待结果：0，实际结果：" + bestRotation(nums));
        nums = new int[]{6,2,8,3,5,2,4,3,7,6};
        System.out.println("输入" + Arrays.toString(nums));
        System.out.println("期待结果：9，实际结果：" + bestRotation(nums));
        nums = new int[]{0,1,1,6,9,3,8,7,9,8};
        System.out.println("输入" + Arrays.toString(nums));
        System.out.println("期待结果：0，实际结果：" + bestRotation(nums));


    }

    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[] subs = new int[n];
        //转化为区间计数，每个数的得分区间连续
        for (int i = 0; i < n; i++) {
            //得分k的区间大小
            int addLength = n - nums[i];
            int addIndex = i + 1;
            addIndex = addIndex >= n ? addIndex - n : addIndex;
            subs[addIndex]++;
            int subIndex = addIndex + addLength;
            subIndex = subIndex >= n ? subIndex - n : subIndex;
            subs[subIndex]--;
            if (addIndex>=subIndex){
                subs[0]++;
            }
        }
        System.out.println(Arrays.toString(subs));
        int k = 0;
        int kMax = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += subs[i];
            if (sum > kMax) {
                k = i;
                kMax = sum;
            }
        }
        return k;
    }

    //时间复杂度 O(n+n(n+1)/2)，预计超时
    //果然超时
    public int bestRotation1(int[] nums) {

        //每个下标为k，ks[k]为该k得分
        int[] ks = new int[nums.length];

        int sub;
        //计算每个下标的值
        for (int i = 0; i < nums.length; i++) {
            sub = i - nums[i];
            //归类总结，数字 nums[i] ，k值最大为 nums.length-1 对于不同的k，仅有 nums.length-nums[i]个k计分，且其k值区间头尾连续
            //得分区为
            //当前小于0时，说明当前不得分，需位移至得分区
            if (sub < 0) {
                //最大k为使其为0时的值，最小k为使其为差值最小的值
                for (int j = 0, index = i + 1; j < nums.length - nums[i]; j++, index++) {
                    ks[index]++;
                }
            }//当其大于等于0时，当前k为得分k，区间位于首尾即
            else {
                //[0,sub]为得分k
                for (int j = 0; j <= sub; j++) {
                    ks[j]++;
                }
                //nums[i]能拿到的总得分k数-已用k，得末尾段的剩余得分k
                for (int j = nums.length - nums[i] - sub - 1, index = nums.length - 1; j > 0; j--, index--) {
                    ks[index]++;
                }
            }

        }
        //获取结果
        int kMax = 0;
        int k = 0;
        for (int i = 0; i < ks.length; i++) {
            if (kMax < ks[i]) {
                kMax = ks[i];
                k = i;
            }
        }
        return k;
    }


}
