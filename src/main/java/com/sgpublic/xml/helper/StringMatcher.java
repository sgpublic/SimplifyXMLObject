package com.sgpublic.xml.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatcher {
    private final Matcher matcher;
    private final String input;

    /**
     * 一个正则表达式的二次封装
     *
     * @param regex 正则表达式
     * @param input 待匹配文本
     */
    public StringMatcher(String regex, String input){
        this.input = input;
        this.matcher = Pattern.compile(regex).matcher(input);
    }

    /**
     * 开始匹配并返回匹配结果
     *
     * @return 匹配结果
     */
    public boolean find(){
        return matcher.find();
    }

    /**
     * 从指定位置开始匹配并返回匹配结果
     *
     * @param start 开始匹配的位置
     * @return 匹配结果
     */
    public boolean find(int start){
        return matcher.find(start);
    }

    /**
     * 获取匹配结果中匹配到的文本
     *
     * @return 匹配到的文本
     */
    public String getFoundString(){
        return input.substring(matcher.start(), matcher.end());
    }

    /**
     * 获取匹配到的文本在源文本中起始的位置
     *
     * @return 起始位置
     */
    public int start(){
        return matcher.start();
    }

    /**
     * 获取匹配到的文本在源文本中结束的位置
     *
     * @return 结束位置
     */
    public int end(){
        return matcher.end();
    }
}
