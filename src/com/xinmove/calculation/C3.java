package com.xinmove.calculation;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author cwt
 * @Date 2019/6/25 13:25
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class C3 {


    @Test
    public void test() {
        String str = "abcabcbb";
        System.out.println(str + "其长度为\t" + lengthOfLongestSubstring3(str));
        str = "bbbbb";
        System.out.println(str + "其长度为\t" + lengthOfLongestSubstring3(str));
        str = "pwwkew";
        System.out.println(str + "其长度为\t" + lengthOfLongestSubstring3(str));
        str = " ";
        System.out.println(str + "其长度为\t" + lengthOfLongestSubstring3(str));
    }

    /**
     * @param s
     * @Description 无重复字符的最长子串长度
     * @Return
     * @Author cwt
     * @Date 2019/6/25 16:24
     */
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        Queue<Byte> q = new LinkedBlockingDeque<>();
        byte[] sBytes = s.getBytes();
        boolean flg;

        for (int i = 0; i < sBytes.length; i++) {
            flg = q.contains(sBytes[i]);
            while (flg) {//循环移除
                flg = !q.remove().equals(sBytes[i]);
            }
            q.add(sBytes[i]);//填入新增元素
            max = max > q.size() ? max : q.size();
        }
        return max;
    }

    //尝试优化
    public int lengthOfLongestSubstring2(String s) {
        int max = 0;
        String str = "";
        char index;
        int indexNum = 0;
        for (int i = 0; i < s.length(); i++) {
            index = s.charAt(i);
            indexNum = str.indexOf(index);
            if (indexNum > -1) {
                str = str.substring(indexNum + 1);//截取掉重复
            }
            str += index;
            max = max > str.length() ? max : str.length();
        }
        return max;
    }

    //尝试再优化
    public int lengthOfLongestSubstring3(String s) {
        int length = 0;
        int start = 0;//目标段起始索引
        int result = 0;
        int indexNum;
        for (int i = 0; i < s.length(); i++) {
            indexNum = s.indexOf(s.charAt(i), start);
            if (indexNum < i) {//在游标之前找到
                if (length > result) {
                    result = length;
                }
                if (result >= s.length() - indexNum - 1) {
                    return result;
                }
                length = i - indexNum - 1;//范围长度
                start = indexNum + 1;
            }
            length++;
        }
        return length;
    }
}
