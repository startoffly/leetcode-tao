package com.xinmove.calculation;

import org.junit.Test;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class C5 {


    @Test
    public void test(){
//        String str = "asdqwertyytrew";
//        System.out.println(longestPalindrome(str));
//        str = "asgfdyfwqasdfghjhgfds";
//        System.out.println(longestPalindrome(str));
        String str = "asssdssaasdassss";
        System.out.println(longestPalindrome(str));
    }

    public String longestPalindrome(String s) {
        String result = "";
        int length = s.length();
        String r;
        String r1;
        for (int i = 0; i < length; i++) {//i为假定中心点
            //奇数回文
            r=getBestCome(i,i,s);

            //偶数回文
            if (i<length-1){
                r1 = getBestCome(i,i+1,s);
                r = r1.length()>r.length()?r1:r;
            }
            result = r.length()>result.length()?r:result;
        }

        return result;
    }


    /**
     * 外扩至最大回文
     * @param begin
     * @param end
     * @param s
     * @return
     */
    private String getBestCome(int begin,int end,String s){

        char beginStr;
        char endStr;
        while (begin <= end&&begin>=0&&end<s.length()){
            beginStr = s.charAt(begin);
            endStr = s.charAt(end);
            if (beginStr != endStr) break;
            begin--;
            end++;
        }
        begin++;//回滚至无错下标
//        end--;//回滚 与 截取时结束坐标+1 对冲公约
//        return s.substring(begin,end+1);//截取时结束坐标+1
        return s.substring(begin,end);
    }

}
