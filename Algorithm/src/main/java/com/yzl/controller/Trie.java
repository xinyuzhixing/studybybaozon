package com.yzl.controller;

/**
 * ClassName: Trie
 * Description: 字典树算法的实现
 * date: 2021/5/30 6:33 下午
 *
 * @author yangzhiliang
 */
public class Trie {

    private int SIZE=26;
    private TrieNode root;//字典树的根

    /**
     * @description:  字典树构造方法
     * @author: yangzhiliang
     * @date: 2021/5/30
     * @param : null
     * @return: null
     */
    Trie()
    {
        root=new TrieNode();
    }

    /**
     * @description:  字典树节点类
     * @author: yangzhiliang
     * @date: 2021/5/30
     */

    private class TrieNode
    {
        //有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
        private int num;
        //所有的子节点
        private TrieNode[]  son;
        //是不是最后一个节点
        private boolean isEnd;
        //节点的值
        private char val;
        //是否有子节点
        private boolean haveSon;

        /**
         * @description:  字典树节点类构造方法
         * @author: yangzhiliang
         * @date: 2021/5/30
         * @param : null
         * @return: null
         */
        TrieNode()
        {
            num=1;
            son=new TrieNode[SIZE];
            isEnd=false;
            haveSon=false;
        }
    }

    /**
     * @description:  字典树节点插入方法
     * @author: yangzhiliang
     * @date: 2021/5/30
     * @param str: 要插入的字符串
     * @return: null
     */
    public void insert(String str)
    {
        //当插入的字符串为空，或字符串的长度为0，直接返回
        if(str==null||str.length()==0)
        {
            return;
        }
        //新建一个节点实例
        TrieNode node=root;
        //将字符串转化为字节数组
        char[] letters = str.toCharArray();
        //循环遍历字符串
        for(int i=0,len=str.length(); i<len; i++)
        {
            //把这个字符变成相应的ASCII码
            int pos=letters[i]-'a';

            //如果子节点为空
            if(node.son[pos]==null)
            {
                //创建子节点，设置节点当前节点有子节点
                node.haveSon = true;
                //创建子节点，实例一个子节点
                node.son[pos]=new TrieNode();
                //创建子节点，设置节点的值
                node.son[pos].val=letters[i];
            }
            //子节点不为空
            else
            {
                //节点数量累计，加1
                node.son[pos].num++;
            }
            //将当前节点改为子节点
            node=node.son[pos];
        }
        //节点循环完成，设置节点为终节点
        node.isEnd=true;
    }

    /**
     * @description: 计算字符串前缀的数量
     * @author: yangzhiliang
     * @date: 2021/5/30
     * @param prefix:前缀字符串
     * @return: 数量值
     */
    public int countPrefix(String prefix)
    {
        //如果前缀为空，或长度为0，返回-1
        if(prefix==null||prefix.length()==0)
        {
            return -1;
        }
        //实例一个字典树节点实例
        TrieNode node=root;
        //将前缀字符串转化为字符数组
        char[] letters =prefix.toCharArray();
        //循环字符串
        for(int i=0,len=prefix.length(); i<len; i++)
        {
            //获得字符对应的ASCII码
            int pos=letters[i]-'a';
            //如果查找前缀字符没有在节点中，返回0
            if(node.son[pos]==null)
            {
                return 0;
            }
            // 否则，当前节点等于子节点
            else
            {
                node=node.son[pos];
            }
        }
        //最后返回节点的数值
        return node.num;
    }


    /**
     * @description: 查找并返回指定前缀
     * @author: yangzhiliang
     * @date: 2021/5/30
     * @param prefix: 要找的前缀
     * @return: java.lang.String
     */
    public String hasPrefix(String prefix)
    {
        //前缀字符串为空，或前缀字符串长度为0，直接返回 null
        if (prefix == null || prefix.length() == 0)
        {
            return null;
        }

        //创建一个字典树节点实例
        TrieNode node = root;
        //将前缀字符串转化为字符数组
        char[] letters = prefix.toCharArray();
        //循环遍历字符串
        for (int i = 0, len = prefix.length(); i < len; i++)
        {
            //获得字符对应的ASCII码
            int pos = letters[i] - 'a';
            //如果子节点中不存在要找前缀字符串的字符，直接返回 null
            if (node.son[pos] == null)
            {
                return null;
            }
            //否则，将当前节点改为子节点
            else
            {
                node = node.son[pos];
            }
        }
        //调用字符变为字符串方法
        preTraverse(node, prefix);
        return null;
    }

    /**
     * @description: 输出字典树节点字符串
     * @author: yangzhiliang
     * @date: 2021/5/30
     * @param node: 字典树节点
     * @return: void
     */
    public void preTraverse(TrieNode node)
    {
        //字典树节点不为空
        if(node!=null)
        {
            //输出节点值
            System.out.print(node.val+"-");
            //循环遍历，子节点
            for(TrieNode child:node.son)
            {
                //迭代调用
                preTraverse(child);
            }
        }
    }

    /**
     * @description: 输出查找前缀字符串的字典树节点字符串
     * @author: yangzhiliang
     * @date: 2021/5/30
     * @param node: 字典树节点
     * @param prefix: 字符串前缀
     * @return: void
     */
    public void preTraverse(TrieNode node, String prefix)
    {
        //判断节点是否有子节点
        if (node.haveSon)
        {
            //遍历 子节点
            for (TrieNode child : node.son)
            {
                //如果子节点不为空
                if (child!=null)
                {
                    //迭代调用
                    preTraverse(child, prefix+child.val);
                }
            }
            return;
        }

        //输出字典树节点字符串
        System.out.println(prefix);
    }

    /**
     * @description:
     * @author: yangzhiliang
     * @date: 2021/5/30
     * @param str: 要查找的字符串
     * @return: boolean
     */
    public boolean has(String str)
    {
        //字符串为空，或字符串的长度为0
        if(str==null||str.length()==0)
        {
            //返回 false
            return false;
        }
        //创建一个字典树节点实例
        TrieNode node=root;
        //将查找字符串转化为字符数组
        char[] letters = str.toCharArray();
        //循环遍历字符串
        for(int i=0,len=str.length(); i<len; i++)
        {
            //获得字符对应的ASCII码
            int pos=letters[i]-'a';
            //如果查找字符串的字符在字典树节点中，将当前节点改为子节点
            if(node.son[pos]!=null)
            {
                node=node.son[pos];
            }
            //否则，直接返回 false
            else
            {
                return false;
            }
        }
        // 返回 节点结束
        return node.isEnd;
    }


    /**
     * @description: 获得字典树根节点
     * @author: yangzhiliang
     * @date: 2021/5/30
     * @param :  null
     * @return: com.yzl.controller.Trie.TrieNode
     */
    public TrieNode getRoot()
    {
        return this.root;
    }



}