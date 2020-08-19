package com.sgpublic.xml.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class TagParser {
    private final String tagName;
    private final Map<String, String> attrMap = new HashMap<>();

    public TagParser(String tagString) {
        StringMatcher matcher;
        String tagData = tagString.substring(1).split(">")[0];
        matcher = new StringMatcher("\\u0020", tagData);
        if (matcher.find()) {
            String[] list = tagData.split("\\u0020");
            tagName = list[0];
            for (int index = 0; index < list.length; index++) {
                if (index != 0) {
                    String listIndex = list[index];
                    String[] attr = listIndex.split("=\"");
                    attrMap.put(attr[0], attr[1].substring(0, attr[1].length() - 1));
                }
            }
        } else {
            tagName = tagData;
        }
    }

    public Map<String, String> getAttrMap() {
        return attrMap;
    }

    public String getTagName() {
        return tagName;
    }
}
