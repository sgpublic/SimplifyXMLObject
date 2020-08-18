package com.sgpublic.xml.helper;

public class TagMatcher {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_SIMPLIFY = 1;
    public static final int TYPE_NULL = -1;

    private final boolean matches;
    private final int type;
    private final String xmlData;

    public TagMatcher(boolean matches) {
        this.matches = matches;
        this.type = -1;
        this.xmlData = null;
    }

    public TagMatcher(boolean matches, int type, String xmlData) {
        this.matches = matches;
        this.type = type;
        this.xmlData = xmlData;
    }

    public int getType() {
        return type;
    }

    public String getXmlData() {
        return xmlData;
    }

    public boolean matches() {
        return matches;
    }
}
