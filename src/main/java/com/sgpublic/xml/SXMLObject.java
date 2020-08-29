package com.sgpublic.xml;

import com.sgpublic.xml.exception.SXMLException;
import com.sgpublic.xml.helper.StringMatcher;
import com.sgpublic.xml.helper.TagMatcher;
import com.sgpublic.xml.helper.TagParser;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SXMLObject {
    private final String xmlString;
    private final String rootTag;
    private String rootTagName;
    private final Map<String, String> attrs;
    private boolean hasInnerData;
    private String innerString;

    /**
     * 创建一个空 SXMLObject 对象
     */
    public SXMLObject(){
        hasInnerData = false;
        xmlString = null;
        rootTag = null;
        rootTagName = null;
        innerString = "";
        attrs = new HashMap<>();
    }

    /**
     * 创建一个 SXMLObject 对象
     *
     * @param xmlString XML数据文本
     * @throws SXMLException 若该 XML 的根节点不完整或不存在，则抛出 SXMLException。
     */
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
                rootTag = matcher.group();
                TagParser parser = new TagParser(rootTag);
                rootTagName = parser.getTagName();
                attrs = parser.getAttrMap();
                TagMatcher tagMatcher = matchTag(xmlData, rootTagName);
                if (tagMatcher.matches()) {
                    this.xmlString = xmlData;
                    if (tagMatcher.getType() == TagMatcher.TYPE_NORMAL){
                        hasInnerData = true;
                        innerString = xmlData.replace(rootTag, "")
                                .replaceAll("</" + rootTagName + ">", "");
                    } else {
                        hasInnerData = false;
                    }
                    return;
                }
            }
        }
        throw new SXMLException(SXMLException.NOT_A_XML_DATA);
    }

    public void setRootTagName(String name){
        this.rootTagName = name;
    }

    /**
     * 判断当前 XML 节点是否有内容
     *
     * @return 返回当前 XML 节点是否有子节点
     */
    public boolean hasInnerData() {
        return hasInnerData;
    }

    /**
     * 判断当前 XML 节点是否有指定标签名称的子节点。
     *
     * @param tagName 子节点标签名称
     * @return 返回当前 XML 节点是否有该标签名称的子节点。
     */
    public boolean isTagNull(String tagName){
        if (hasInnerData){
            StringMatcher matcher = new StringMatcher("<" + tagName, xmlString);
            return !matcher.find();
        } else {
            return true;
        }
    }

    /**
     * 添加属性值
     *
     * @param key 欲添加的属性名称
     * @param value 欲添加的属性值
     * @return 返回当前对象
     */
    public SXMLObject putAttr(String key, String value){
        attrs.put(key, value);
        return this;
    }

    public SXMLObject putAttr(String key, Integer value){
        attrs.put(key, String.valueOf(value));
        return this;
    }

    public SXMLObject putAttr(String key, Long value){
        attrs.put(key, String.valueOf(value));
        return this;
    }

    public SXMLObject putAttr(String key, Double value){
        attrs.put(key, String.valueOf(value));
        return this;
    }

    public SXMLObject putAttr(String key, Boolean value){
        attrs.put(key, String.valueOf(value));
        return this;
    }

    /**
     * 获取当前节点中指定子节点标签名称的子节点。
     *
     * @param tagName 子节点标签名称
     * @return 获取到的 SXMLObject。
     * @throws SXMLException 若当前 XML 节点中没有该子节点或该子节点标签不完整，则抛出 SXMLException。
     */
    public SXMLObject getXMLObject(String tagName) throws SXMLException {
        if (hasInnerData) {
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

    /**
     * 若当前节点中不包含子节点且有内部数据，则可以直接获取这些数据。
     *
     * @return 节点中非子节点数据
     * @throws SXMLException 若当前节点包含子节点或不包含任何内容，则抛出 SXMLException。
     */
    public String getInnerData() throws SXMLException {
        if (hasInnerData) {
            String innerString = xmlString.replace(rootTag, "")
                    .replaceAll("</" + rootTagName + ">", "");
            if (!new StringMatcher("<", innerString).find()){
                return innerString;
            } else {
                throw new SXMLException(SXMLException.INNER_NOT_STRING_DATA);
            }
        } else {
            throw new SXMLException(SXMLException.INNER_NULL, rootTagName);
        }
    }

    /**
     * 获取节点标签中 String 类型的属性值。
     *
     * @param attrName 属性名称
     * @return 获取到的 String 类型属性值。
     * @throws SXMLException 若该节点中不存在该属性值，则抛出 SXMLException。
     */
    public String getStringAttr(String attrName) throws SXMLException {
        return getAttrValue(attrName);
    }

    /**
     * 获取节点标签中 boolean 类型的属性值。
     *
     * @param attrName 属性名称
     * @return 获取到的 boolean 类型属性值。
     * @throws SXMLException 若该节点中不存在该属性值，则抛出 SXMLException。
     */
    public boolean getBooleanAttr(String attrName) throws SXMLException {
        return Boolean.parseBoolean(getAttrValue(attrName));
    }

    /**
     * 获取节点标签中 int 类型的属性值。
     *
     * @param attrName 属性名称
     * @return 获取到的 int 类型属性值。
     * @throws SXMLException 若该节点中不存在该属性值，则抛出 SXMLException。
     */
    public int getIntAttr(String attrName) throws SXMLException {
        return Integer.parseInt(getAttrValue(attrName));
    }

    /**
     * 获取节点标签中 long 类型的属性值。
     *
     * @param attrName 属性名称
     * @return 获取到的 long 类型属性值。
     * @throws SXMLException 若该节点中不存在该属性值，则抛出 SXMLException。
     */
    public long getLongAttr(String attrName) throws SXMLException {
        return Long.parseLong(getAttrValue(attrName));
    }

    /**
     * 获取节点标签中 double 类型的属性值。
     *
     * @param attrName 属性名称
     * @return 获取到的 double 类型属性值。
     * @throws SXMLException 若该节点中不存在该属性值，则抛出 SXMLException。
     */
    public double getDoubleAttr(String attrName) throws SXMLException {
        return Double.parseDouble(getAttrValue(attrName));
    }

    /**
     * 从类内部获取到节点属性值的 String 类型
     *
     * @param attrName 属性名称
     * @return 返回
     * @throws SXMLException 若该节点中没有属性值或不存在指定的属性值，则抛出 SXMLException。
     */
    private String getAttrValue(String attrName) throws SXMLException {
        if (!attrs.isEmpty()){
            String attr = attrs.get(attrName);
            if (attr != null){
                return attr;
            } else {
                throw new SXMLException(SXMLException.ATTR_KEY_NULL, attrName);
            }
        } else {
            throw new SXMLException(SXMLException.ATTR_NULL, rootTagName);
        }
    }

    /**
     * 若节点中包含多个名称相同的子节点，则可以将这些子节点创建为一个 SXMLArray 对象。
     *
     * @param tagName 子节点标签名称
     * @return 返回获取到的 SXMLArray 对象
     * @throws SXMLException 若当前 XML 节点中没有该子节点或该子节点标签不完整，则抛出 SXMLException。
     */
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

    /**
     * 判断当前 XML 节点的标签中是否有指定名称的属性值。
     *
     * @param attrName 指定名称的属性值
     * @return 返回当前 XML 节点的标签中是否有指定名称的属性值。
     */
    public boolean isAttrNull(String attrName){
        return attrs.get(attrName) == null;
    }

    /**
     * 删除属性值
     *
     * @param key 欲删除的属性名称
     * @return 返回当前对象
     */
    public SXMLObject removeAttr(String key){
        if (attrs.get(key) != null){
            attrs.remove(key);
        }
        return this;
    }

    /**
     * 添加子标签
     *
     * @param object 欲添加的子标签对象
     * @return 返回当前对象
     */
    public SXMLObject putInnerObject(SXMLObject object){
        hasInnerData = true;
        innerString = innerString + object.toString();
        return this;
    }

    /**
     * 删除子标签
     *
     * @param tagName 欲删除的子标签名称
     * @return 返回当前对象那个
     * @throws SXMLException 若该子标签不存在或当前标签没有子标签，则抛出 SXMLException
     */
    public SXMLObject removeInnerObject(String tagName) throws SXMLException {
        if (hasInnerData) {
            TagMatcher tagMatcher = matchTag(innerString, tagName);
            if (tagMatcher.matches()) {
                innerString = innerString.replace(tagMatcher.getXmlData(), "");
            } else {
                throw new SXMLException(SXMLException.TAG_NULL, tagName);
            }
        } else {
            throw new SXMLException(
                    SXMLException.INNER_UNAVAILABLE, rootTagName
            );
        }
        return this;
    }

    /**
     * 设置当前标签内容
     *
     * @param string 标签内容
     * @return 返回当前对象
     */
    public SXMLObject setInnerData(String string){
        hasInnerData = true;
        innerString = string;
        return this;
    }

    /**
     * 返回经过 SXMLObject 格式化后的 XML 数据。
     *
     * @return 格式化后的 XML 数据
     */
    public String toString() {
        if (rootTagName == null) {
            Throwable e = new SXMLException(SXMLException.TAG_NOT_SET);
            e.printStackTrace();
            return null;
        } else {
            StringBuilder data = new StringBuilder();
            data.append("<")
                    .append(rootTagName);
            attrs.forEach((s, s2) -> data.append(" ").append(s).append("=\"").append(s2).append("\""));
            StringBuilder result = new StringBuilder();
            if (hasInnerData){
                data.append(">").append(innerString)
                        .append("</").append(rootTagName)
                        .append(">");

//                String[] split = data.toString().split("<");
//                StringBuilder format = new StringBuilder();
//                for (int index = 0; index < split.length; index++) {
//                    String splitIndex = split[index];
//                    if (!splitIndex.equals("")){
//                        result.append("<").append(splitIndex);
//                        if (splitIndex.startsWith("/")){
//                            format.delete(0, 4);
//                        } else if (!splitIndex.matches("/(^\"*?)>")){
//                            format.append("    ");
//                        }
//                        if (splitIndex.endsWith(">") && index < split.length - 1){
//                            result.append("\n").append(format);
//                        }
//                    }
//                }

                result.append(data);
            } else {
                result.append(data).append("/>");
            }
            return result.toString();
        }
    }

    /**
     * 从指定的 XML 数据中按指定的子节点标签名称查找子标签
     *
     * @param xmlString 指定的 XML 数据
     * @param tagName 指定的子节点标签名称
     * @return 返回获取到的子节点信息
     * @throws SXMLException 若当前 XML 节点中没有该子节点或该子节点标签不完整，则抛出 SXMLException。
     */
    private TagMatcher matchTag(String xmlString, String tagName) throws SXMLException {
        StringMatcher matcher;
        String nowString = xmlString;
        String nowTag = new TagParser(xmlString).getTagName();
        int tagLength = nowTag.length();

        while (!tagName.equals(nowTag)){
            matcher = new StringMatcher("<" + nowTag + "(.*?)/([^\"]*?)>", nowString);
            if (matcher.find()){
                String tagRange = matcher.group();
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
            String tagRange = matcher.group();
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
