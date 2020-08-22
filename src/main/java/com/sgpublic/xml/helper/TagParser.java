package com.sgpublic.xml.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class TagParser {
    private final String tagName;
    private final Map<String, String> attrMap = new HashMap<>();

    /**
     * 创建一个节点标签解析器
     *
     * @param tagString 节点标签文本
     */
    public TagParser(String tagString) {
        StringMatcher matcher;
        String tagData = tagString.replace("\"/>", "\">")
                .substring(1).split(">")[0];
        matcher = new StringMatcher("\\u0020", tagData);
        if (matcher.find()) {
            String[] list = tagData.split("\\u0020");
            tagName = list[0];
            for (int index = 0; index < list.length; index++) {
                if (index != 0) {
                    String listIndex = list[index];
                    String[] attr = listIndex.split("=");
                    attrMap.put(attr[0], attr[1].substring(1, attr[1].length() - 1));
                }
            }
        } else {
            tagName = tagData;
        }
    }

    /**
     * 获取当前节点中解析到的属性值集合
     *
     * @return 属性值集合
     */
    public Map<String, String> getAttrMap() {
        return attrMap;
    }

    /**
     * 获取当前节点中解析到的标签名称
     *
     * @return 标签名称
     */
    public String getTagName() {
        return tagName;
    }
}
