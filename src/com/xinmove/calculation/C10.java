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
 *
 * "aab"
 * "c*a*b"
 **/
public class C10 {

    @Test
    public void test(){
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatch(s,p));
    }

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        if (s.equals(p)) return true;
        if ("".equals(s) || "".equals(p)) return false;

        return macth(0,s.length()-1,s,p.length()-1,p);
    }

    private boolean macth(int begin,int end,String s,int indexP,String p){
        if (indexP<0||end<begin||end<0) return false;
        boolean flg = true;
        char lastCP = '@';
        char lastP = '@';
        char pI;
        int index = end;
        boolean isAll = false;
        char sI;
        for (int i = indexP; i > -1; i--) {//
            pI = p.charAt(i);
            if (pI=='*'){
                isAll = true;
            }else {
                if (index<0) return false;
                sI = s.charAt(index);
                if (isAll){
                    if (lastP!='@'&&lastP!=lastCP){
                        for (int j = end; j > begin; j--) {
                            if (s.charAt(j)==lastP){
                                return macth(begin,end-1,s,i,p);
                            }
                        }
                        lastP = '@';
                        isAll = false;
                    }else {
                        lastP = pI;
                    }

                }else {
                    if (equ(sI,pI)){
                        index--;
                    }else {
                        return false;
                    }
                }
            }
            lastCP = pI;

        }
        if (indexP==0){
            flg = end==0||isAll;
        }
        return flg;
    }

    /**
     * @Description 区域内匹配*
     * @Return
     * @Author cwt
     * @Date 2019/6/28 9:39
     */
    private boolean equAll(int begin,int end,String s,char p){
        if (p=='.'){
            return true;
        }else {
            int length = s.length();
            for (int i = begin; i < length&&i<=end; i++) {
                if(p != s.charAt(i)) return false;
            }
        }
        return true;
    }

    private boolean equ(char a, char p){
        return p=='.'||a==p;
    }

    /**
     * @Description 参考
     * @Return
     * @Author cwt
     * @Date 2019/6/28 11:34
     */
    public boolean isMatch2(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] memory = new boolean[2][pLen+1];
        memory[0][0] = true;
        int cur = 0, pre = 0;
        for(int i = 0; i <= sLen; i++) {
            cur = i % 2;
            pre = (i + 1) % 2;
            if(i > 1) {
                for(int j = 0; j <= pLen; j++) {
                    memory[cur][j] = false;
                }
            }
            for(int j = 1; j <= pLen; j++) {
                if(p.charAt(j-1) == '*') {

                    memory[cur][j] = memory[cur][j-2] || (i > 0 && (s.charAt(i-1) == p.charAt(j-2) ||
                            p.charAt(j-2) == '.') && memory[pre][j]);
                }else {
                    memory[cur][j] = i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
                            && memory[pre][j-1];
                }
            }
        }
        return memory[cur][pLen];
    }
}
