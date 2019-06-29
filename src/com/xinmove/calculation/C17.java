package com.xinmove.calculation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName C17
 * @Descripption TODO
 * @Author CWT
 * @Date 2019/6/29 10:48
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 个人题解思路：每个数字按键代表3-4个字母位数
 * a~z  码表 97~122
 * 0~9       48~57
 **/
public class C17 {

    @Test
    public void test(){
        String a = "23";
        letterCombinations(a).forEach(System.out::println);


    }
    String[] mapKey ={"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};//对应表
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits==null||digits.isEmpty()) return result;
        int index = 0;
        deal(result,digits,"",index);
        return result;
    }

    private List<String> deal(List<String> result,String digits,String lastStr,int index){
//        List<String> result = new ArrayList<>();
        if (index>=digits.length()) return result;
        String key = mapKey[digits.charAt(index)-'2'];//'0','1' 无按键直接跳过
        index++;
        String nStr;
        for (int i = 0; i < key.length(); i++) {
            nStr = lastStr+key.charAt(i);
            if (index>=digits.length()){//递归最后一层
                result.add(nStr);//递归底层结束
            }else {
                deal(result,digits,nStr,index);//非最后一层，递归处理
            }
        }

        return result;
    }


}
