package com.demo1;

import jdk.internal.util.xml.impl.Input;

import java.util.*;

public class Demo1 {
    private static HashMap<String, List> distMap = new HashMap<>();
    private static Input input;

    public static void main(String[] args) {
        System.out.println("hello world");
        initMap();
        digitsToLetters();

    }

    // 初始化数据
    private static void initMap() {
        distMap = new HashMap<>();
        distMap.put("0", Arrays.asList(""));
        distMap.put("1", Arrays.asList(""));
        distMap.put("2", Arrays.asList("A", "B", "C"));
        distMap.put("3", Arrays.asList("D", "E", "F"));
        distMap.put("4", Arrays.asList("G", "H", "I"));
        distMap.put("5", Arrays.asList("J", "K", "L"));
        distMap.put("6", Arrays.asList("M", "N", "O"));
        distMap.put("7", Arrays.asList("P", "Q", "R", "S"));
        distMap.put("8", Arrays.asList("T", "U", "V"));
        distMap.put("9", Arrays.asList("W", "X", "Y", "Z"));
    }

    public static void digitsToLetters() {
        System.out.println("Please input digits from 0 to 9:");
        //输入按键数字0-9
        String digits = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int str1 = scanner.nextInt();
                digits = str1 + "";
                if (!digits.matches("[0-9]{1,2}")) {
                    System.out.println("输入不合法！只能输入0-99的数字,请重新输入：");
                } else {
                    break;
                }
            } else {
                System.out.println("输入不合法！只能输入0-99的数字,请重新输入：");
            }
        }
        StringBuilder arrInput = new StringBuilder("Input:arr[] ={");
        String[] arrStr = digits.split("");
        List<String[]> dataList = new ArrayList<String[]>();

        for (int i = 0; i < arrStr.length; i++) {
            arrInput.append(arrStr[i]);
            if (i < arrStr.length - 1) {
                arrInput.append(",");
            }
            //先将多个list中的数据都添加到同一个集合中作为数据源
            List<String> lettersList = distMap.get(arrStr[i]);
            if (lettersList.size() > 0) {//没有数据的集合不能强行转换为数组
                String[] letterArr = (String[]) lettersList.toArray();
                dataList.add(letterArr);
            }
        }
        arrInput.append("}");

        //递归实现多数组排列组合，并返回最终的排列集合
        List<String[]> resultList = makeupLetters(dataList, 0, null);
        //打印输入内容
        System.out.println(arrInput.toString());
        System.out.print("Output:");
        //打印输出排列组合结果
        for (int i = 0; i < resultList.size(); i++) {
            String[] letterArr = resultList.get(i);
            System.out.print(" ");
            for (String s : letterArr) {
                System.out.print(s);
            }
        }
    }

    private static List<String[]> makeupLetters(List<String[]> dataList, int index, List<String[]> resultList) {
        if (index == dataList.size()) {
            return resultList;
        }

        List<String[]> resultList0 = new ArrayList<String[]>();
        if (index == 0) {//第一列数组默认有多少个字母就添加多少个排列数据
            String[] dataArr = dataList.get(0);
            for (String s : dataArr) {
                resultList0.add(new String[]{s});
            }
        } else {
            String[] dataArr = dataList.get(index);
            for (String[] dataArr0 : resultList) {
                for (String s : dataArr) {
                    //复制数组并扩充新元素
                    String[] dataArrCopy = new String[dataArr0.length + 1];
                    System.arraycopy(dataArr0, 0, dataArrCopy, 0, dataArr0.length);
                    dataArrCopy[dataArrCopy.length - 1] = s;
                    //追加到结果集
                    resultList0.add(dataArrCopy);
                }
            }
        }
        return makeupLetters(dataList, ++index, resultList0);
    }
}
