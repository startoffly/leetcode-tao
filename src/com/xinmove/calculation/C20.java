package com.xinmove.calculation;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @ClassName C20
 * @Descripption 有效的括号
 * @Author CWT
 * @Date 2019/7/3 12:49
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 空格 ascii码 为 32
 **/
public class C20 {

    @Test
    public void test(){
        String s = "()";
        System.out.println(s+";"+isValid2(s));

        s = "()[]{}";
        System.out.println(s+";"+isValid2(s));

        s = "(]";
        System.out.println(s+";"+isValid2(s));

        s = "([)]";
        System.out.println(s+";"+isValid2(s));

        s = "{[]}";
        System.out.println(s+";"+isValid2(s));

    }

    //栈对象 时间快
    public boolean isValid2(String s) {

        int length = s.length();
        if (length<1) return true;
        if (length%2!=0) return false;

        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<>();
        map.put('{','}');
        map.put('[',']');
        map.put('(',')');

        int index = 0;
        char a;
        char pop;
        while(index<length){
            a = s.charAt(index);
            if (a=='{'||a=='['||a=='('){
                stack.push(a);
            }else {
                if (stack.isEmpty()) return false;
                pop = stack.pop();
                if (a!=map.get(pop)) return false;
            }
            index++;
        }
        return stack.isEmpty();
    }


    //万能通过 且能过滤空格 缺点时间较慢
    public boolean isValid(String s) {
        int length = s.length();
        if (length<1) return true;
        boolean[] flg = new boolean[length];//标识数组

        int lindex;//左标识
        int rindex = 0;//右标识
        char c;
        boolean calid = false;
        while (rindex < length) {
            c = s.charAt(rindex);
            if (c==32){//过滤空格
                flg[rindex]=true;
                continue;
            }
            if (isRight(c)){//进行检索
                calid = false;
                lindex = rindex-1;
                if (lindex<0){
                    break;
                }
                while(lindex>0&&flg[lindex]){//中间型过滤
                    lindex--;
                }
                switch (s.charAt(lindex)){
                    case '{':
                        calid = c=='}';
                        break;
                    case '[':
                        calid = c==']';
                        break;
                    case '(':
                        calid = c==')';
                        break;
                        default:
                            break;
                }
                if (calid){
                    flg[lindex] = true;
                    flg[rindex] = true;
                }else {
                    break;
                }
            }
            rindex++;
        }

        if (!calid) return false;

        for (int i = 0; i < flg.length; i++) {//未全通过则抛
            if (!flg[i]) return false;
        }

        return true;
    }

    private boolean isRight(char c){
        return c==']'||c==')'||c=='}';
    }
}
