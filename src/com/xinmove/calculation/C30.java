package com.xinmove.calculation;

import org.junit.Test;

import java.util.*;

/**
 * 30. 串联所有单词的子串
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * <p>
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 */
public class C30 {


    @Test
    public void test() {
        String s;
        String[] words;

        s = "barfoothefoobarman";
        words = new String[]{"foo", "bar"};
        System.out.println("输入 " + s);
        System.out.println("目标输出 [0,9]或[9,0]");
        System.out.println("实际输出 " + findSubstring(s, words).toString());

        s = "wordgoodgoodgoodbestword";
        words = new String[]{"word", "good", "best", "word"};
        System.out.println("输入 " + s);
        System.out.println("目标输出 []");
        System.out.println("实际输出 " + findSubstring(s, words).toString());

        s = "barfoofoobarthefoobarman";
        words = new String[]{"bar", "foo", "the"};
        System.out.println("输入 " + s);
        System.out.println("目标输出 [6,9,12]");
        System.out.println("实际输出 " + findSubstring(s, words).toString());
        s = "wordgoodgoodgoodbestword";
        words = new String[]{"word", "good", "best", "good"};
        System.out.println("输入 " + s);
        System.out.println("目标输出 [8]");
        System.out.println("实际输出 " + findSubstring(s, words).toString());

    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        HashMap<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.merge(word, 1, Integer::sum);
        }
        int sLength = s.length();
        int[] wordIndexs = new int[sLength];

        for (int i = 0; i < words.length; i++) {
            int index = s.indexOf(words[i]);
            while (index > -1) {
                //默认为0，所以+1为下标标记
                if (wordIndexs[index] == 0) {
                    wordIndexs[index] = i + 1;
                }
                index = s.indexOf(words[i], index + 1);
            }
        }

        for (int i = 0; i < wordIndexs.length; i++) {
            if (wordIndexs[i] == 0) {
                continue;
            }
            if (isContinuity(i, wordIndexs, words, wordMap)) {
                result.add(i);
            }
        }


        return result;
    }

    //是否连续
    public boolean isContinuity(int nowIndex, int[] wordIndexs, String[] words, Map<String, Integer> wordMap) {
        //连续性判断
        Map<String, Integer> wordUseMap = new HashMap<>();
        String nowWord;
        int wordIndex;
        int index = nowIndex;
        //连续次数仅到词量上线
        for (int i = 0; i < words.length && index < wordIndexs.length; i++) {
            if (wordIndexs[index] == 0) {
                return false;
            }
            wordIndex = wordIndexs[index] - 1;

            nowWord = words[wordIndex];
            wordUseMap.merge(nowWord,1,Integer::sum);
            index = index + nowWord.length();
        }
        //如果有没用到的
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            if (!entry.getValue().equals(wordUseMap.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }


    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int sLength = s.length();
        int[] wordIndexs = new int[sLength];

        for (int i = 0; i < words.length; i++) {
            int index = s.indexOf(words[i]);
            while (index > -1) {
                //默认为0，所以+1为下标标记
                wordIndexs[index] = i + 1;
                index = s.indexOf(words[i], index + 1);
            }
        }

        for (int i = 0; i < wordIndexs.length; i++) {
            if (wordIndexs[i] == 0) {
                continue;
            }
            if (isContinuity1(i, wordIndexs, words)) {
                result.add(i);
            }
        }

        return result;
    }

    //是否连续
    public boolean isContinuity1(int nowIndex, int[] wordIndexs, String[] words) {
        //连续性判断
        boolean[] wordUse = new boolean[words.length];
        String nowWord;
        int wordIndex;
        int index = nowIndex;
        //连续次数仅到词量上线
        for (int i = 0; i < words.length && index < wordIndexs.length; i++) {
            if (wordIndexs[index] == 0) {
                return false;
            }
            wordIndex = wordIndexs[index] - 1;
            wordUse[wordIndex] = true;
            nowWord = words[wordIndex];
            index = index + nowWord.length();
        }
        //如果有没用到的
        for (int i = 0; i < wordUse.length; i++) {
            if (!wordUse[i]) {
                return false;
            }
        }

        return true;
    }


}
