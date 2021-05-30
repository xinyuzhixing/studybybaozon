package com.yzl.controller;

import org.junit.Test;

/**
 * ClassName: TireTest
 * Description:  字典树测试方法
 * date: 2021/5/30 6:36 下午
 *
 * @author yangzhiliang
 */
public class TireTest {

    @Test
    public void test01(){

        //创建一个字典树
        Trie tree=new Trie();
        //字典树中存放的字符串
        String[] strs= {"aacc","accc","abbb","aabb","aabc"};

        //要查找的字符串前缀
        String[] prefix= {"aa","ac","aabc","ab"};

        //循环插入字符串
        for(String str:strs)
        {
            tree.insert(str);
        }

        //输出是否有"abc"字符串
        System.out.println(tree.has("abc"));

        //打印字典树节点字符串
        tree.preTraverse(tree.getRoot());

        //换行
        System.out.println();

        //循环遍历，搜索的前缀字符串
        for(String pre:prefix)
        {
            //获得前缀字符串出现的次数
            int num=tree.countPrefix(pre);
            //打印查找的前缀字符串及对应的次数
            System.out.println(pre+" "+num);
        }
    }

}