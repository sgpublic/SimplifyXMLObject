package com.sgpublic.xml;

import com.sgpublic.xml.helper.StringMatcher;
import com.sgpublic.xml.helper.TagMatcher;
import com.sgpublic.xml.helper.TagParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SXMLObject {
    private final String xmlString;
    private final String rootTag;
    private final String rootTagName;
    private final Map<String, String> attrs;
    private final boolean hasInnerData;

    public SXMLObject(String xmlString) throws SXMLException {
        if (!"".equals(xmlString)) {
            String xmlData = xmlString.replaceAll("\r|\n", "")
                    .replaceAll("<\\?(.*?)\\?>", "")
                    .replaceAll("\\u0020+", " ")
                    .replaceAll(">\\u0020*<", "><")
                    .replaceAll("\\u0020*/\\u0020*>", "/>");
            StringMatcher matcher;

            matcher = new StringMatcher("<(.*?)>", xmlData);
            if (matcher.find()) {
                rootTag = matcher.getFoundString();
                TagParser parser = new TagParser(rootTag);
                rootTagName = parser.getTagName();
                attrs = parser.getAttrMap();
                TagMatcher tagMatcher = matchTag(xmlData, rootTagName);
                if (tagMatcher.matches()) {
                    this.xmlString = xmlData;
                    hasInnerData = tagMatcher.getType() == TagMatcher.TYPE_NORMAL;
                    return;
                }
            }
        }
        throw new SXMLException(SXMLException.NOT_A_XML_DATA);
    }

    public boolean hasInnerData() {
        return hasInnerData;
    }

    public SXMLObject getXMLObject(String tagName) throws SXMLException {
        if (hasInnerData) {
            String innerString = xmlString.replace(rootTag, "")
                    .replaceAll("</" + rootTagName + ">", "");
            TagMatcher tagMatcher = matchTag(innerString, tagName);
            if (tagMatcher.matches()) {
                return new SXMLObject(tagMatcher.getXmlData());
            } else {
                throw new SXMLException(SXMLException.TAG_NULL, tagName);
            }
        } else {
            throw new SXMLException(
                    SXMLException.INNER_UNAVAILABLE, rootTagName
            );
        }
    }

    public String getStringAttr(String attrName) throws SXMLException {
        return getAttrValue(attrName);
    }

    public boolean getBooleanAttr(String attrName) throws SXMLException {
        return Boolean.parseBoolean(getAttrValue(attrName));
    }

    public int getIntAttr(String attrName) throws SXMLException {
        return Integer.parseInt(getAttrValue(attrName));
    }

    public long getLongAttr(String attrName) throws SXMLException {
        return Long.parseLong(getAttrValue(attrName));
    }

    public double getDoubleAttr(String attrName) throws SXMLException {
        return Double.parseDouble(getAttrValue(attrName));
    }

    public SXMLArray getXMLArray(String tagName) throws SXMLException {
        if (hasInnerData) {
            String innerString = xmlString.replace(rootTag, "")
                    .replaceAll("</" + rootTagName + ">", "");
            TagMatcher tagMatcher = matchTag(innerString, tagName);
            ArrayList<String> list = new ArrayList<>();
            if (tagMatcher.matches()) {
                while (tagMatcher.matches()){
                    list.add(tagMatcher.getXmlData());
                    innerString = innerString.replace(tagMatcher.getXmlData(), "");
                    if (innerString.length() > tagName.length() + 1){
                        tagMatcher = matchTag(innerString, tagName);
                    } else {
                        return new SXMLArray(list);
                    }
                }
                return new SXMLArray(list);
            } else {
                throw new SXMLException(SXMLException.TAG_NULL, tagName);
            }
        } else {
            throw new SXMLException(
                    SXMLException.INNER_UNAVAILABLE, rootTagName
            );
        }
    }

    public String toString() {
        return xmlString;
    }

    private String getAttrValue(String attrName) throws SXMLException {
        String attrValue = attrs.get(attrName);
        if (attrValue == null) {
            throw new SXMLException(
                    String.format(SXMLException.ATTR_KEY_NULL, attrName)
            );
        } else {
            return attrValue;
        }
    }

    private TagMatcher matchTag(String xmlString, String tagName) throws SXMLException {
        StringMatcher matcher;
        String nowString = xmlString;
        String nowTag = new TagParser(xmlString).getTagName();
        int tagLength = nowTag.length();

        while (!tagName.equals(nowTag)){
            matcher = new StringMatcher("<" + nowTag + "(.*?)/([^\"]*?)>", nowString);
            if (matcher.find()){
                String tagRange = matcher.getFoundString();
                if (new StringMatcher("<", tagRange).find(1)){
                    matcher = new StringMatcher("</" + nowTag + ">", nowString);
                    if (matcher.find()){
                        nowString = nowString.substring(matcher.end());
                    } else {
                        throw new SXMLException(SXMLException.NOT_A_XML_DATA);
                    }
                } else {
                    nowString = nowString.substring(matcher.end());
                }
            } else {
                return new TagMatcher(false);
            }
            if (nowString.length() > tagLength + 1){
                nowTag = new TagParser(nowString).getTagName();
                tagLength = nowTag.length();
            } else {
                return new TagMatcher(false);
            }
        }
        matcher = new StringMatcher("<" + nowTag + "(.*?)/([^\"]*?)>", nowString);
        if (matcher.find()){
            String tagRange = matcher.getFoundString();
            if (new StringMatcher("<", tagRange).find(1)){
                matcher = new StringMatcher("</" + nowTag + ">", nowString);
                if (matcher.find()){
                    return new TagMatcher(
                            true,
                            TagMatcher.TYPE_NORMAL,
                            nowString.substring(0, matcher.end())
                    );
                } else {
                    throw new SXMLException(SXMLException.NOT_A_XML_DATA);
                }
            } else {
                return new TagMatcher(
                        true,
                        TagMatcher.TYPE_SIMPLIFY,
                        tagRange
                );
            }
        } else {
            return new TagMatcher(false);
        }
    }
}
