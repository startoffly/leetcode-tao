package com.xinmove.calculation;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName C6
 * @Descripption TODO
 * @Author CWT
 * @Date 2019/6/27 19:28
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class C6 {

    @Test
    public void test(){
        String s = "LEETCODEISHIRING";
        System.out.println(convert2(s,3));

    }

    //优化
    public String convert2(String s, int numRows) {
        if (s.length()<=1||numRows<=1) return s;
        byte[] bytes = s.getBytes();

        for (int i = 0; i <bytes.length; i++) {
            System.out.println(bytes[i]);

        }

        int col= s.length()/(numRows-1) +1;//列
        int[][] arra = new int[numRows][col];
        System.out.println("maxRow:"+numRows+"，rol:"+col);
        int index = 1;//数值默认值为0，所以从1开始计数
        int maxIndex = s.length();
        System.out.println(maxIndex);
        boolean isCome = false;//是否为回列
        for (int i = 0; i < col ; i++) {
            if (isCome){
                for (int j = numRows-2; j > 0; j--) {//去头尾
                    if (index>maxIndex){
                        break;
                    }
                    arra[j][i] = index++;
                }
            }else {
                for (int j = 0; j < numRows; j++) {
                    if (index>maxIndex){
                        break;
                    }
                    arra[j][i] = index++;
                }
            }
            isCome = !isCome;
            if (index>maxIndex){
                break;
            }
        }

        byte[] re = new byte[bytes.length];
        index = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < col; j++) {
//                System.out.print(arra[i][j]);
                if (arra[i][j]!=0) re[index++]=bytes[arra[i][j]-1];
            }
//            System.out.println();
        }
        return new String(re);
    }

    public String convert(String s, int numRows) {
        if (s.length()<=1||numRows<=1) return s;
        int col= s.length()/(numRows-1) +1;//列
        int[][] arra = new int[numRows][col];
        System.out.println("maxRow:"+numRows+"，rol:"+col);
        int index = 1;//数值默认值为0，所以从1开始计数
        int maxIndex = s.length();
        System.out.println(maxIndex);
        boolean isCome = false;//是否为回列
        for (int i = 0; i < col ; i++) {
            if (isCome){
                for (int j = numRows-2; j > 0; j--) {//去头尾
                    if (index>maxIndex){
                        break;
                    }
                    arra[j][i] = index++;
                }
            }else {
                for (int j = 0; j < numRows; j++) {
                    if (index>maxIndex){
                        break;
                    }
                    arra[j][i] = index++;
                }
            }
            isCome = !isCome;
            if (index>maxIndex){
                break;
            }
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < col; j++) {
//                System.out.print(arra[i][j]);
                if (arra[i][j]!=0) str.append(s.charAt(arra[i][j]-1));
            }
//            System.out.println();
        }
        return str.toString();
    }

}
