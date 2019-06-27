package com.xinmove.calculation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName C10
 * @Author CWT
 * @Date 2019/6/27 19:12
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C10 {

    @Test
    public void test(){
        String s = "aaaa";
        String p = "a*a";
        System.out.println(isMatch(s,p));
    }

    public boolean isMatch(String s, String p) {

        if (s == null || p == null) return false;
        if (s.equals(p)) return true;
        if ("".equals(s) || "".equals(p)) return false;

        int pLength = p.length();
        List<String> pList = new ArrayList<>();
        String pA = "";
        char pC;
        for (int i = 0; i < pLength; i++) {//规则列
            pC = p.charAt(i);
            if ("".equals(pA)) {//空规则列
                if (pC != '*') pA += pC;
            } else {
                if (pC != '*') {
                    pList.add(pA);
                    pA = "" + pC;
                }else {
                    pList.add(pA+'*');
                    pA = "";
                }

            }
        }
        if (!"".equals(pA)){
            pList.add(pA);
        }

        boolean flg = true;
        int index = 0;
        char sC;
        char p0;
        boolean all = false;//全匹配状态 x*
        char cAll = '.';
        for (String pT : pList) {

            p0 = pT.charAt(0);
            if (pT.length() > 1) {
                all=true;
                cAll= p0;//替换
            } else {
                if (all){//处理匹配
                    if ('.'!=p0){
                        index = s.indexOf(p0,index);
                        if (index<0){
                            return false;
                        }
                    }else {
                        for (int i = index; i <s.length() ; i++) {
                            if (cAll==s.charAt(i))  index++;
                        }
                    }
                    index++;
                }else {
                    if(index>=s.length()) return false;
                    sC = s.charAt(index);
                    if (p0 != '.' && sC != p0) {
                        return false;
                    } else {
                        index++;
                    }
                }
                all = false;//去除匹配状态
                cAll = '.';
            }
        }
        if (all){
            if('.' != cAll){
                for (int i = index; i <s.length() ; i++) {
                    if (cAll!=s.charAt(i)) return false;
                    index++;
                }
            }else {
                return true;
            }
        }
        flg = index==s.length();
        return flg;
    }

}
