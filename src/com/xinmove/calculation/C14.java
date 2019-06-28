package com.xinmove.calculation;

import org.junit.Test;

/**
 * @ClassName C14
 * @Author CWT
 * @Date 2019/6/28 19:18
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C14 {


    @Test
    public void test(){
        String[] a = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(a));
    }

    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if (strs==null||strs.length<1) return "";
        String minStr = strs[0];
        int min = minStr.length();//取到一个值
        for (int i = 1; i <strs.length; i++) {//
            if (strs[i].length()<min){
                min = strs[i].length();
                minStr = strs[i];
            }
        }

        char indexChar;
        int count;
        for (int i = 0; i < min; i++) {
            count = 0;
            indexChar = minStr.charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i)!=indexChar){
                    break;
                }else {
                    count++;
                }
            }
            if (count<strs.length){
                break;
            }else {
                result+=indexChar;
            }
        }
        return result;
    }
}
