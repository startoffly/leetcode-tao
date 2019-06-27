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
        String str = "asdqwertyytrew";
        System.out.println(longestPalindrome2(str));
        str = "asgfdyfwqasdfghjhgfds";
        System.out.println(longestPalindrome2(str));
        str = "asssdssaasdassss";
        System.out.println(longestPalindrome2(str));
    }

    /**
     * @Description 优化版
     * @param String
     * @Author cwt
     * @Date 2019/6/26 19:59
     */
    public String longestPalindrome2(String s) {
        int begin = 0;//切割起始坐标
        int end = 0;//切割end坐标
        int length = s.length();
        int[] r;
        int[] r1;
        for (int i = 0; i < length; i++) {//i为假定中心点
            //奇数回文
            r=getBestCome2(i,i,s);
            //偶数回文
            if (i<length-1){
                r1 = getBestCome2(i,i+1,s);
                r = r1[1]-r1[0]>r[1]-r[0]?r1:r;
            }
            if (r[1]-r[0]>end-begin){
                begin = r[0];
                end = r[1];
            }
        }
        return s.substring(begin,end);
    }


    /**
     * @Description 初次提交
     * @param String
     * @Author cwt
     * @Date 2019/6/26 19:59
     */
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
     * @param beginT
     * @param endT
     * @param s
     * @return
     */
    private String getBestCome(int beginT,int endT,String s){

        char beginStr;
        char endStr;
        while (beginT <= endT&&beginT>=0&&endT<s.length()){
            beginStr = s.charAt(beginT);
            endStr = s.charAt(endT);
            if (beginStr != endStr) break;
            beginT--;
            endT++;
        }
        beginT++;//回滚至无错下标
//        endT--;//回滚 与 截取时结束坐标+1 对冲公约
//        return s.substring(beginT,endT+1);//截取时结束坐标+1
        return s.substring(beginT,endT);
    }

    /**
     * 外扩至最大回文
     * @param beginT
     * @param endT
     * @param s
     * @return
     */
    private int[] getBestCome2(int beginT,int endT,String s){
        int[] r = new int[2];
        char beginStr;
        char endStr;
        while (beginT <= endT&&beginT>=0&&endT<s.length()){
            beginStr = s.charAt(beginT);
            endStr = s.charAt(endT);
            if (beginStr != endStr) break;
            beginT--;
            endT++;
        }
        beginT++;//回滚至无错下标
//        endT--;//回滚 与 截取时结束坐标+1 对冲公约
//        return s.substring(beginT,endT+1);//截取时结束坐标+1
        r[0] = beginT;
        r[1] = endT;

        return r;
    }
}
