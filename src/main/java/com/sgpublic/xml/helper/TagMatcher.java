package com.sgpublic.xml.helper;

public class TagMatcher {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_SIMPLIFY = 1;
    public static final int TYPE_NULL = -1;

    private final boolean matches;
    private final int type;
    private final String xmlData;

    /**
     * 创建一个节点信息
     *
     * @param matches 是否获取成功
     */
    public TagMatcher(boolean matches) {
        this.matches = matches;
        this.type = -1;
        this.xmlData = null;
    }

    /**
     * 创建一个节点信息
     *
     * @param matches 是否获取成功
     * @param type 获取到的节点类型
     * @param xmlData 获取到的节点文本
     */
    public TagMatcher(boolean matches, int type, String xmlData) {
        this.matches = matches;
        this.type = type;
        this.xmlData = xmlData;
    }

    /**
     * 返回获取到的节点类型
     * @return 获取到的节点类型
     */
    public int getType() {
        return type;
    }

    /**
     * 返回获取到的节点文本
     *
     * @return 获取到的节点文本
     */
    public String getXmlData() {
        return xmlData;
    }

    /**
     * 返回获取结果
     *
     * @return 获取结果
     */
    public boolean matches() {
        return matches;
    }
}
