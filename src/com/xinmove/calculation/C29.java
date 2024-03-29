package com.xinmove.calculation;

import org.junit.Test;

/**
 * @ClassName C29
 * @Descripption 两数相除
 * @Author CWT
 * @Date 2019/7/9 18:58
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C29 {

    @Test
    public void test(){

        System.out.println(divide(10,3));
        System.out.println(divide(7,-3));
        System.out.println(divide(10,8));
        System.out.println(divide(1234124,4));
    }

    public int divide(int dividend, int divisor) {
        if (dividend==Integer.MIN_VALUE&&divisor==-1) return Integer.MAX_VALUE;
        if (divisor==1) return dividend;
        if (divisor==-1) return -dividend;
        if (dividend==divisor) return 1;
        int sum = 2;
        if (dividend > 0) sum--;
        if (divisor > 0) sum--;
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;
        int ans = 0;
        while (dividend <= divisor) {
            for (int i = 0; i < 32; i++) {
                dividend -= divisor << i;
                ans += 1 << i;
                if (divisor << i + 1 > 0 || (divisor << i + 1) < dividend) break;
            }
        }
        return sum == 1 ? -ans : ans;
    }




}
