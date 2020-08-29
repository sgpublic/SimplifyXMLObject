package com.sgpublic.xml.helper;

import com.sgpublic.xml.exception.SXMLException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatcher {
    private final Matcher matcher;

    /**
     * 一个正则表达式的二次封装
     *
     * @param regex 正则表达式
     * @param input 待匹配文本
     */
    public StringMatcher(String regex, String input){
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
     * 遍历匹配结果
     *
     * @param event 遍历事件
     */
    public void matches(EachEvent event){
        int count = 0;
        while (find()){
            event.onEach(matcher.start(), matcher.end(), matcher.group(), count);
            count++;
        }
    }

    /**
     * 获取匹配结果中匹配到的文本
     *
     * @return 匹配到的文本
     */
    public String group(){
        return matcher.group();
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

    public interface EachEvent {
        void onEach(int start, int end, String group, int index);
    }
}
