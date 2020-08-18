package com.sgpublic.xml.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatcher {
    private final Matcher matcher;
    private final String input;

    public StringMatcher(String regex, String input){
        this.input = input;
        matcher = Pattern.compile(regex).matcher(input);
    }

    public boolean find(){
        return matcher.find();
    }

    public boolean find(int start){
        return matcher.find(start);
    }

    public String getFoundString(){
        return input.substring(matcher.start(), matcher.end());
    }

    public int start(){
        return matcher.start();
    }

    public int end(){
        return matcher.end();
    }
}
