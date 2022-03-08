package com.xinmove.calculation;

import org.junit.Test;

import java.util.HashMap;

/**
 *
 * 给你一个字符串 s，它由数字（'0' - '9'）和 '#' 组成。我们希望按下述规则将 s 映射为一些小写英文字符：
 *
 * 字符（'a' - 'i'）分别用（'1' - '9'）表示。
 * 字符（'j' - 'z'）分别用（'10#' - '26#'）表示。 
 * 返回映射之后形成的新字符串。
 *
 * 题目数据保证映射始终唯一。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "10#11#12"
 * 输出："jkab"
 * 解释："j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
 * 示例 2：
 *
 * 输入：s = "1326#"
 * 输出："acz"
 * 示例 3：
 *
 * 输入：s = "25#"
 * 输出："y"
 * 示例 4：
 *
 * 输入：s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
 * 输出："abcdefghijklmnopqrstuvwxyz"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s[i] 只包含数字（'0'-'9'）和 '#' 字符。
 * s 是映射始终存在的有效字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decrypt-string-from-alphabet-to-integer-mapping
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/3/7 16:23
 */
public class C1309 {

    public String freqAlphabets(String s) {
        if (s.length()<2){
            return s;
        }

        //初始化对照字典
        HashMap<String,Character> characterHashMap = new HashMap<>();
        int  k = 1;
        for (char v = 'a'; v <= 'i'; v++,k++) {
            characterHashMap.put(k+"",v);
        }
        k = 10;
        for (char v = 'j'; v <= 'z'; v++,k++) {
            characterHashMap.put(k+"#",v);
        }

        StringBuilder result = new StringBuilder();

        StringBuilder key = new StringBuilder();
        for (int i = s.length()-1; i >= 0; i--) {
            char iChar = s.charAt(i);

            //该字母不是2维标记的时候
            if ('#'!=iChar){
                switch (key.length()){
                    case 0:
                        key.append(iChar);
                        result.append(characterHashMap.get(key.toString()));
                        key.delete(0,key.length());
                        break;
                    case 2:
                        key.append(iChar);
                        String a = key.reverse().toString();
                        result.append(characterHashMap.get(a));
                        key.delete(0,key.length());
                        break;
                    default:
                        key.append(iChar);
                }
            }else {
                key.append(iChar);
            }
        }
        if (key.length()>0){
            result.append(characterHashMap.get(key.reverse().toString()));
        }

        return result.reverse().toString();
    }

    /**
     *
     * 2 ms
     * @param s
     * @return java.lang.String
     **/
    public String freqAlphabets2(String s) {
        //1，起始值
        char start = 'a'- 1;

        //游标
        int index = s.length()-1;

        char a;
        StringBuilder result = new StringBuilder();
        StringBuilder key;
        while (index>=0){
            a = s.charAt(index);
            if ('#' == a){
                key = new StringBuilder();
                key.append(s.charAt(index-2));
                key.append(s.charAt(index-1));
                int add = Integer.parseInt(key.toString());
                result.append((char) (start + add));
                index -= 3;
            }else {
                int add = Integer.parseInt(a+"");
                result.append((char) (start + add));
                index--;
            }

        }
        return result.reverse().toString();
    }


    @Test
    public void test(){
        String s;
        s = "10#11#12";
        System.out.println("输入："+s+"，预期结果：jkab ，实际结果："+freqAlphabets2(s));

        s = "1326#";
        System.out.println("输入："+s+"，预期结果：acz，实际结果："+freqAlphabets2(s));

        s = "25#";
        System.out.println("输入："+s+"，预期结果：y ，实际结果："+freqAlphabets2(s));

        s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#";
        System.out.println("输入："+s+"，预期结果：abcdefghijklmnopqrstuvwxyz，实际结果："+freqAlphabets2(s));

    }

}
