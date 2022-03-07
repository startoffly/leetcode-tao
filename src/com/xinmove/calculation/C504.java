package com.xinmove.calculation;

import org.junit.Test;

/**
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 * <p>
 * 输入: num = -7
 * 输出: "-10"
 * <p>
 * 提示：
 * <p>
 * -107 <= num <= 107
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/base-7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author cwt
 * @date 2022/3/7 13:41
 */
public class C504 {


    public String convertToBase7(int num) {

        StringBuilder stringBuilder = new StringBuilder();
        boolean isNegative = false;
        if (num<0){
            num = -num;
            isNegative = true;

        }
        int a;
        //7进制
        int base = 7;
        while (num >= base) {
            //上位结果
            a = num % base;
            //余下值
            num = num/base;
            stringBuilder.append(a);
        }
        stringBuilder.append(num);
        if (isNegative){
            stringBuilder.append('-');
        }
        return stringBuilder.reverse().toString();
    }

    @Test
    public void test(){
//        System.out.println("预期结果：14，计算结果："+convertToBase7(11));
        System.out.println("预期结果：202，计算结果："+convertToBase7(100));
        System.out.println("预期结果：-10，计算结果："+convertToBase7(-7));
        System.out.println("预期结果：-202，计算结果："+convertToBase7(-100));
        System.out.println("预期结果：2630，计算结果："+convertToBase7(1001));



    }

}
