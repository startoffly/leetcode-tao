package com.xinmove.calculation;

import org.junit.Test;

/**
 * @ClassName C9
 * @Descripption TODO
 * @Author CWT
 * @Date 2019/6/27 18:59
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C9 {

    @Test
    public void test(){

        System.out.println(isPalindrome(127656721));
    }

    public boolean isPalindrome(int x) {

        if (x<0) return false;
        if (x<10) return true;
        int a = x;
        long result = 0;
        while (a>0){
            result = result*10+a%10;
            a/=10;
        }
        return result==x;
    }


}
