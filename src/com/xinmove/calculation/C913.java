package com.xinmove.calculation;

import org.junit.Test;

import java.util.*;

/**
 * 两位玩家分别扮演猫和老鼠，在一张 无向 图上进行游戏，两人轮流行动。
 * <p>
 * 图的形式是：graph[a] 是一个列表，由满足 ab 是图中的一条边的所有节点 b 组成。
 * (说人话就是 graph[0] 代表0的相邻点，graph[1]代表1的相邻点)
 * 老鼠从节点 1 开始，第一个出发；猫从节点 2 开始，第二个出发。在节点 0 处有一个洞。
 * <p>
 * 在每个玩家的行动中，他们 必须 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 1 ，那么它必须移动到 graph[1] 中的任一节点。
 * <p>
 * 此外，猫无法移动到洞中（节点 0）。
 * <p>
 * 然后，游戏在出现以下三种情形之一时结束：
 * <p>
 * 如果猫和老鼠出现在同一个节点，猫获胜。
 * 如果老鼠到达洞中，老鼠获胜。
 * 如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。
 * 给你一张图 graph ，并假设两位玩家都都以最佳状态参与游戏：
 * <p>
 * 如果老鼠获胜，则返回 1；
 * 如果猫获胜，则返回 2；
 * 如果平局，则返回 0 。
 *  
 * 示例 1：
 * <p>
 * <p>
 * 输入：graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
 * 输出：0
 * 示例 2：
 * <p>
 * <p>
 * 输入：graph = [[1,3],[0],[3],[0,2]]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= graph.length <= 50
 * 1 <= graph[i].length < graph.length
 * 0 <= graph[i][j] < graph.length
 * graph[i][j] != i
 * graph[i] 互不相同
 * 猫和老鼠在游戏中总是移动
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cat-and-mouse
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/1/4 18:30
 */
public class C913 {

    //[0]，当前；[1]，上一次；[2]，上上次；-1，未开始
    private static int[] cat = {2, -1, -1};

    private static int[] mouse = {1, -1, -1};

    //老鼠路径
    private static int[] mouseWay = {};

    //老鼠尝试路径集合
    public static Map<String, Integer> mouseTryWayMap = null;

    @Test
    public void findWayTest() {
        int[][] graph = {{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}};
        //期待输出 0
//        int[][] graph = {{1, 3}, {0}, {3}, {0, 2}};
        //期待输出 1


        int[] result = null;
        int skip = 5;//不能踩的点
        result = findOneWay(3, 0, graph, 1, result, new HashSet<>(), skip);
        if (result != null) {
            System.out.print("路径解：");
            for (int i : result) {
                System.out.print(i + " ");
            }
        } else {
            System.out.print("路径无解！");
        }
    }

    @Test
    public void catMoveTest() {
        int[][] graph = {{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}};
        boolean isMouseCan = mouseMove(graph);
        System.out.println("老鼠是否可移动：" + isMouseCan + "，移动后：");
        for (int i : mouse) {
            System.out.printf(i + " ");
        }
        //老鼠目标路线
        System.out.println("老鼠目标路线:");
        for (int i : mouseWay) {
            System.out.printf(i + " ");
        }
        System.out.println();
        boolean isCan = catMove(graph);
        System.out.println("猫是否可移动：" + isCan + "，移动后：");
        for (int i : cat) {
            System.out.printf(i + " ");
        }

    }

    @Test
    public void mouseMoveTest() {
        int[][] graph = {{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}};

        //[0]，当前；[1]，上一次；[2]，上上次；-1，未开始

        int[] result = null;
        result = findOneWay(mouse[0], 0, graph, 1, result, new HashSet<>(), cat[0]);

        for (int i : result) {
            System.out.printf(i + " ");
        }
        cat[0] = 2;
        boolean isCan = mouseMove(graph);

        System.out.println("是否可移动：" + isCan + "，移动后：");
        for (int i : mouse) {
            System.out.printf(i + " ");
        }
    }

    @Test
    public void gameTest() {
        int[][] graph8 = {{2,3,4},{2,3},{0,1,3,4},{0,1,2,4},{0,2,3}};
        System.out.println(String.format("预期结果: %s 执行结果：" +
                " %s", 2, catMouseGame(graph8)));

    }

    @Test
    public void someTest() {
        int[][] graph1 = {{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}};
        System.out.println(String.format("预期结果: %s 执行结果： %s", 0, catMouseGame(graph1)));

        int[][] graph2 = {{1, 3}, {0}, {3}, {0, 2}};
        System.out.println(String.format("预期结果: %s 执行结果： %s", 1, catMouseGame(graph2)));

        int[][] graph3 = {{3, 4}, {3, 5}, {3, 6}, {0, 1, 2}, {0, 5, 6}, {1, 4}, {2, 4}};
        System.out.println(String.format("预期结果: %s 执行结果： %s", 0, catMouseGame(graph3)));

        //[[2,3,4],[4],[0,3],[0,2],[0,1]] 预期1
        int[][] graph4 = {{2, 3, 4}, {4}, {0, 3}, {0, 2}, {0, 1}};
        System.out.println(String.format("预期结果: %s 执行结果： %s", 1, catMouseGame(graph4)));

        //[[4],[2,3,5],[1,5,3],[1,2],[0],[1,2]] 预期2
        int[][] graph5 = {{4}, {2, 3, 5}, {1, 5, 3}, {1, 2}, {0}, {1, 2}};
        System.out.println(String.format("预期结果: %s 执行结果： %s", 2, catMouseGame(graph5)));

        //[[5,6],[3,4],[6],[1,4,5],[1,3,5],[0,3,4,6],[0,2,5]]
        int[][] graph6 = {{5, 6}, {3, 4}, {6}, {1, 4, 5}, {1, 3, 5}, {0, 3, 4, 6}, {0, 2, 5}};
        System.out.println(String.format("预期结果: %s 执行结果： %s", 2, catMouseGame(graph6)));

        int[][] graph7 = {{5, 21, 28}, {6, 8, 9, 13, 23, 24, 30}, {9, 10, 22, 24}, {24, 30}, {5, 6, 8, 9, 13, 18, 19, 20, 24}, {0, 4, 9, 10, 11, 12
                , 22, 27}, {1, 4, 9, 11, 16, 19, 25, 30}, {8, 9, 13, 19, 25, 26}, {1, 4, 7, 9, 29}, {1, 2, 4, 5, 6, 7, 8, 13, 18, 19, 24, 26, 28, 29}, {2, 5, 15, 22, 27, 30}, {5, 6, 12, 24}, {5, 11, 20, 22, 23}, {1, 4, 7, 9, 29, 30}, {19, 24, 27}, {10, 16, 19}, {6, 15, 27}, {20, 22, 24, 29}, {4, 9, 21}, {4, 6, 7, 9, 14, 15, 20, 26, 28, 30}, {4, 12, 17, 19, 21}, {0, 18, 20, 27}, {2, 5, 10, 12, 17}, {1, 12, 26, 30}, {1, 2, 3, 4, 9, 11, 14, 17, 27, 29}, {6, 7, 26, 27, 29}, {7, 9, 19, 23, 25}, {5, 10, 14, 16, 21, 24, 25}, {0, 9, 19, 30}, {8, 9, 13, 17, 24, 25}, {1, 3, 6, 10, 13, 19, 23, 28}};
        System.out.println(String.format("预期结果: %s 执行结果： %s", 1, catMouseGame(graph7)));

        //[[2,3,4],[2,3],[0,1,3,4],[0,1,2,4],[0,2,3]] {{2,3,4},{2,3},{0,1,3,4},{0,1,2,4},{0,2,3}}

        int[][] graph8 = {{2,3,4},{2,3},{0,1,3,4},{0,1,2,4},{0,2,3}};
        System.out.println(String.format("预期结果: %s 执行结果：" +
                " %s", 2, catMouseGame(graph8)));

    }

    public int catMouseGame(int[][] graph) {
        init();
        int status = -1;
        System.out.println("目标地图：");
        for (int i = 0; i < graph.length; i++) {

            System.out.println(i + ":" + Arrays.toString(graph[i]));
        }
        while (status == -1) {
            //老鼠移动
            mouseMove(graph);

            System.out.println("老鼠行动,当前位置：" + mouse[0] + "，整体状态：" + Arrays.toString(mouse) + ",老鼠当前路径：" + Arrays.toString(mouseWay));
//            System.out.println("老鼠预期目标：" + Arrays.toString(mouseWay));

            status = checkStatus(cat, mouse);
            if (status != -1) {
                return status;
            }
            //猫移动
            catMove(graph);
            System.out.println("猫行动,当前位置：" + cat[0] + "，整体状态：" + Arrays.toString(cat));
            status = checkStatus(cat, mouse);
        }
        return status;
    }

    //执行前初始化
    public void init() {
        cat = new int[]{2, -1, -1, -1, -1};

        mouse = new int[]{1, -1, -1, -1, -1};

        //老鼠路径
        mouseWay = new int[]{};
        mouseTryWayMap = new HashMap<>();
    }

    //这是老鼠尝试该路径的第几次
    private int theWayTry(int[] mouseWay) {
        //同位置 同相似路径
        String key = cat[0] + mouse[0] + Arrays.toString(mouseWay);
        mouseTryWayMap.merge(key, 1, Integer::sum);

        for (Integer value : mouseTryWayMap.values()) {
        }

        return mouseTryWayMap.get(key);
    }

    /**
     * 猫移动
     *
     * @param graph 地图
     * @return boolean 是否能继续游戏
     * @date 2022/1/5 12:10
     **/
    private boolean catMove(int[][] graph) {

        int move = -1;
        //洞所在位置不能为目标
        int skip = 0;
        //猫的第一目标为 老鼠
        int to = mouse[0];


        int[] way = findOneWay(cat[0], to, graph, 1, null, new HashSet<>(), skip);
        move = way != null && way.length > 1 ? way[1] : cat[0];
        //猫追击路线如果和老鼠路线重合，则选择该路线
        boolean same = sameCatWayAndMouseWay(way);

        //无重合则
        if (!same) {
            //猫各个子节点选择一条通往老鼠的路径
            for (int i : graph[cat[0]]) {
                if (skip == i || move == i) {
                    continue;
                }
                way = findOneWay(i, to, graph, 1, null, new HashSet<>(), skip);
                same = sameCatWayAndMouseWay(way);
                //重合，选择该路
                if (same) {
                    move = way.length > 1 ? way[0] : cat[0];
                    break;
                }
            }

        }

        //所有路线失败，尝试选择去终点附近
        if (!same) {
            to = 0;
            skip = -1;
            way = findOneWay(cat[0], to, graph, 1, null, new HashSet<>(), skip);
            if (way != null && way.length > 2 && way[1] != -1) {
                move = way[1];
            } else {
                //随便选个位
                for (int i : graph[cat[0]]) {
                    if (i != 0) {
                        move = i;
                    }
                }
            }
        }
//        System.out.println("猫路径：" + Arrays.toString(way) + ",当前选择：" + move + ",是否包含老鼠目标:" + same + ",老鼠当前路径：" + Arrays.toString(mouseWay));

        //记忆步数
        for (int i = 4; i > 0; i--) {
            cat[i] = cat[i - 1];
        }
        cat[0] = move;
        return move > 0;
    }

    private boolean sameCatWayAndMouseWay(int[] catWay) {
        boolean same = false;
        if (catWay != null && mouseWay != null ) {
            if ( mouseWay.length > 1){
                for (int i1 = 0; i1 < mouseWay.length; i1++) {
                    if (contains(catWay, mouseWay[i1])) {
                        same = true;
                        break;
                    }
                }
            }else {
                same = contains(catWay,mouse[0]);
            }
        }
        return same;
    }

    /**
     * 老鼠移动
     * <p>
     * //     * @param cat   猫状态
     * //     * @param mouse 老鼠状态
     *
     * @param graph 地图
     * @return boolean 是否能继续游戏
     * @date 2022/1/5 12:09
     **/
//    private boolean mouseMove(int[] cat, int[] mouse, int[][] graph) {
    private boolean mouseMove(int[][] graph) {
        int move = -1;
        //猫所在位置不能为目标
        int skip = cat[0];
        //老鼠目标永远为 0
        int to = 0;
        int[] way = findOneWay(mouse[0], to, graph, 1, null, new HashSet<>(), skip);
        if (way != null && way.length > 1) {
            //目标平移
            move = way[1];
            //如果终点外目标位置附近有猫，下一步会被吃
            if (move != 0 && contains(graph[move], cat[0])) {
                System.out.println("初始目标：" + move + "附近有猫！会输！");
                boolean have = false;
                //遍历其他节点-寻求其他无猫位移
                for (int i : graph[mouse[0]]) {
                    if (i == move || i == cat[0]) {
                        continue;
                    }
                    //System.out.println("遍历可走目标：" + i);
                    if (!contains(graph[i], cat[0]) && i != mouse[1]) {
                        int[] noMove = Arrays.copyOf(mouse, mouse.length + 2);
                        noMove[0] = cat[0];
                        noMove[mouse.length] = move;
                        noMove[mouse.length + 1] = mouseWay.length>1?mouseWay[0]:-1;
                        way = findOneWay(i, 0, graph, 1, null, new HashSet<>(), noMove);
                        if (way == null) {
                            continue;
                        }
                        //重试模块
                        int tryCount = theWayTry(way);
                        if (tryCount>2){
                            continue;
                        }
                        mouseWay = new int[way.length - 1];
                        for (int j = 1; j < way.length; j++) {
                            mouseWay[j - 1] = way[j];
                        }
                        move = i;
                        have = true;
                        break;
                    }

//                    System.out.println("没救，下一个！");
                }




                //无其他路可走,且能回头
                if (!have && mouse[1] != -1) {
                    int[] noMove = Arrays.copyOf(mouse, mouse.length + 1);
                    noMove[0] = cat[0];
                    noMove[mouse.length] = move;
                    way = findOneWay(mouse[1], 0, graph, 1, null, new HashSet<>(), noMove);
                    if (way != null) {
                        mouseWay = new int[way.length - 1];
                        for (int j = 1; j < way.length; j++) {
                            mouseWay[j - 1] = way[j];
                        }
                    }
                    move = mouse[1];

                }
//                System.out.println("附近有猫最终抉择为："+move+",是否有活路："+have+",路径："+ Arrays.toString(mouseWay));
            } else {
                if (way.length > 3) {
                    System.out.println("老鼠当前路径：" + Arrays.toString(way));
                    mouseWay = new int[way.length - 2];
                    for (int i = 2; i < way.length; i++) {
                        mouseWay[i - 2] = way[i];
                    }
                }
            }
        } else {
            for (int i : graph[mouse[0]]) {
                move = i;
                if (i != cat[0]) {
                    break;
                }
            }
            //不动
//            move = mouse[0];
        }

        //执行移动
        //记忆步数
        for (int i = 4; i > 0; i--) {
            mouse[i] = mouse[i - 1];
        }
        mouse[0] = move;
        return move > 0;
    }

    /**
     * 寻找一条最短路径
     *
     * @param from
     * @param to
     * @param graph
     * @param index
     * @param result
     * @param skip   不能走的点
     * @return int[]
     * @date 2022/1/5 12:11
     **/
    private int[] findOneWay(int from, int to, int[][] graph, int index, int[] result,
                             Set<String> fromToSet, int... skip) {

        if (result != null) {
            result[--index] = from;
            return result;
        }
        //相同求解跳过
        String fromTo = from + "" + to;
        if (fromToSet.contains(fromTo)) {
            return null;
        } else {
            fromToSet.add(fromTo);
        }
        index++;
        //当前点有
        if (contains(graph[from], to) && !contains(skip, to)) {
            result = new int[2];
            result[0] = from;
            result[1] = to;
//            System.out.println("子路径：" + from + "->" + to + "节点");
            return result;
        } else {
            index++;
            //当前点没有,目标子集检索
            for (int toi : graph[to]) {
                //不能走跳过点
                if (contains(skip, toi)) {
                    continue;
                }
                if (contains(graph[from], toi)) {
                    result = new int[3];
                    result[0] = from;
                    result[1] = toi;
                    result[2] = to;
//                System.out.println(from + "->" + toi + "->" + to + "，index:" + index);
                    return result;
                }
            }
            //均没有
            for (int fromi : graph[from]) {
                for (int toi : graph[to]) {
                    //不能走跳过点
                    if (contains(skip, toi) || contains(skip, fromi) || fromi == to || toi == from) {
                        continue;
                    }
                    result = findOneWay(fromi, toi, graph, index, result, fromToSet, skip);
                    if (result != null) {
                        int start = 0;
                        int end = result.length;

                        for (int i = 0; i < result.length; i++) {
                            //修剪
                            if (result[i] == from) {
                                //当个不复制
                                start = i + 1;
                            }
                            if (result[i] == to) {
                                end = i;
                            }
                        }
                        //复制目标头尾
                        int[] fatherResult = new int[end - start + 2];
                        fatherResult[0] = from;
                        fatherResult[end - start + 1] = to;
                        for (int i = start, j = 1; i < end; i++, j++) {
                            fatherResult[j] = result[i];
                        }
//                    System.out.println("结果不为空：" + fromi + "->" + toi + "，index:" + index);
                        return fatherResult;
                    }
                }
            }
        }
        return result;
    }

    private boolean contains(int[] arr, int a) {
        if (arr == null) {
            return false;
        }
        for (int i : arr) {
            if (i == a) {
                return true;
            }
        }
        return false;
    }

    //状态判定
    private int checkStatus(int cat[], int mouse[]) {

        if (!contains(cat, -1) && !contains(mouse, -1)) {
            boolean catSame = true;
            for (int i = 0; i < cat.length - 2; i++) {
                catSame = catSame && (cat[i] == cat[i + 2]);
            }
            boolean mouseSame = true;
            for (int i = 0; i < mouse.length - 2; i++) {
                mouseSame = mouseSame && (mouse[i] == mouse[i + 2]);
            }

            //和局
            if (catSame && mouseSame) {
                return 0;
            }
        }
        //鼠胜
        if (mouse[0] == 0) {
            return 1;
        }
        //猫胜
        if (cat[0] == mouse[0]) {
            return 2;
        }
        //继续
        return -1;
    }

}
