package com.sgpublic.xml.helper;

public class EntityReferrer {
    public static String encode(String string){
        return string.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("&", "&amp;")
                .replaceAll("'", "&apos;")
                .replaceAll("\"", "&quot;");
    }

    public static String decode(String string){
        return string.replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&")
                .replaceAll("&apos;", "'")
                .replaceAll("&quot;", "\"");
    }
}
