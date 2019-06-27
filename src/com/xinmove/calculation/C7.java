package com.xinmove.calculation;

import org.junit.Test;

/**
 * @ClassName C7
 * @Descripption TODO
 * @Author CWT
 * @Date 2019/6/27 12:33
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C7 {

    @Test
    public void test() {
        System.out.println(reverse(1534236469));
    }

    public int reverse(int x) {
        long reverse = 0;
        int z = x > 0 ? 1 : -1;//正反曲线
        int num = x * z;
        while (num > 0) {
            reverse = 10 * reverse + num % 10;
            num /= 10;
        }
        reverse = reverse * z;//回复
        return x > 0 == (int)reverse > 0 &&reverse<2147483647&&reverse>-2147483648? (int)reverse : 0;
    }

}
