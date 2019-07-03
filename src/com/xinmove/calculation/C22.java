package com.xinmove.calculation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName C22
 * @Descripption 括号生成
 * @Author CWT
 * @Date 2019/7/2 19:03
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C22 {

    @Test
    public void test(){

        List<String> result =generateParenthesis(3);
        result.forEach(System.out::println);

    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        parenthesis(n, n, "",result);
        return result;
    }
    private void parenthesis(int left, int right, String s, List<String> result) {
        if (left == 0) {//左（耗尽
            while (right-- > 0)
                s += ')';
            result.add(s);
            return;
        }
        //保证左优先减
        if (left<=right){
            parenthesis(left - 1, right, s + '(',result);
        }
        if (left>right) return;
        parenthesis(left, right - 1, s + ')',result);
    }
}
